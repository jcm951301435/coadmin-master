package com.java.dto.query;

import com.java.util.CollectionUtils;
import com.java.util.ReflectUtils;
import com.java.util.StringUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @author: jcm
 * @date: 2020/10/11
 */
@Slf4j
public abstract class BaseQuery<T> {

    /**
     * 构造查询条件
     *
     * @return .
     */
    public Specification<T> toSpecification() {
        return toSpecificationDefault();
    }

    /**
     * 默认构造查询条件
     *
     * @return .
     */
    private Specification<T> toSpecificationDefault() {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Set<Field> fieldSet = ReflectUtils.getAllFieldsWithSupper(this.getClass());
            for (Field field : fieldSet) {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                Object value = ReflectUtils.getFiledValue(field, this);
                if (StringUtils.isEmpty(value)) {
                    continue;
                }
                QueryCondition annotation = field.getAnnotation(QueryCondition.class);
                if (annotation == null) {
                    continue;
                }
                String[] filterNames = annotation.filterNames();
                FilterType filterType = annotation.filterType();
                CompareClassEnum compareClass = annotation.compareClass();
                String join = annotation.join();
                String filterNameSingle = null;
                boolean filterSingle = false;
                if (filterNames.length <= 0) {
                    filterNameSingle = field.getName();
                    filterSingle = true;
                } else if (filterNames.length == 1) {
                    filterNameSingle = filterNames[0];
                    filterSingle = true;
                }
                if (filterSingle) {
                    Predicate tempPredicate = generatePredicate(filterNameSingle, filterType, field, compareClass,
                            join, root, criteriaBuilder);
                    if (tempPredicate != null) {
                        predicates.add(tempPredicate);
                    }
                } else {
                    List<Predicate> tempPredicates = new ArrayList<>();
                    for (String filterNameTemp : filterNames) {
                        Predicate tempPredicate = generatePredicate(filterNameTemp,
                                filterType, field, compareClass, join, root, criteriaBuilder);
                        if (tempPredicate != null) {
                            tempPredicates.add(tempPredicate);
                        }
                    }
                    if (CollectionUtils.isNotEmpty(tempPredicates)) {
                        predicates.add(criteriaBuilder.or(tempPredicates.toArray(new Predicate[0])));
                    }
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    /**
     * 构造查询条件
     *
     * @param filterName      .
     * @param filterType      .
     * @param field           .
     * @param compareClass    .
     * @param join            .
     * @param root            .
     * @param criteriaBuilder .
     * @return .
     */
    private Predicate generatePredicate(String filterName, FilterType filterType, Field field,
                                        CompareClassEnum compareClass, String join,
                                        Root<T> root, CriteriaBuilder criteriaBuilder) {

        Predicate predicate = null;
        Object value = ReflectUtils.getFiledValue(field, this);
        Class<?> fieldType = field.getType();
        Path<T> path;
        if (StringUtils.isNotEmpty(join)) {
            path = root.join(join).get(filterName);
        } else {
            path = root.get(filterName);
        }
        switch (filterType) {
            case EQUAL:
                predicate = criteriaBuilder.equal(path, value);
                break;
            case NOT_EQUAL:
                predicate = criteriaBuilder.notEqual(path, value);
                break;
            case LIKE:
                String valueStr = "%" + value + "%";
                predicate = criteriaBuilder.like(path.as(String.class), valueStr);
                break;
            case GREATER_THAN:
            case GREATER_THAN_OR_EQUALS:
            case LESS_THAN:
            case LESS_THAN_OR_EQUALS:
                predicate = generatePredicateComparable(fieldType, value, path, compareClass, join, criteriaBuilder,
                        filterType);
                break;
            case IN:
                if (value instanceof Collection) {
                    if (CollectionUtils.isNotEmpty((Collection<?>) value)) {
                        predicate = path.in((Collection<?>) value);
                    }
                } else if (value.getClass().isArray()) {
                    Object[] objects = (Object[]) value;
                    if (objects.length > 0) {
                        List<Object> list = Arrays.asList(objects);
                        if (CollectionUtils.isNotEmpty(list)) {
                            predicate = path.in(list);
                        }
                    }
                } else {
                    log.error("参数类型{}有误, 只能为数组或集合", field.getName());
                }
                break;
            case BETWEEN:
                predicate = generatePredicateBetween(fieldType, value, path, compareClass, join, criteriaBuilder);
                break;
            default:
                break;
        }
        return predicate;
    }

    /**
     * 构造 比较类 条件
     *
     * @param fieldType       .
     * @param value           .
     * @param path            .
     * @param compareClass    .
     * @param join    .
     * @param criteriaBuilder .
     * @param filterType      .
     * @return
     */
    @SuppressWarnings("all")
    private Predicate generatePredicateComparable(Class<?> fieldType, Object value, Path<T> path,
                                                  CompareClassEnum compareClass,
                                                  String join,
                                                  CriteriaBuilder criteriaBuilder,
                                                  FilterType filterType) {
        Predicate predicate = null;
        Class<?> clz = compareClass.getClz();
        log.info(Comparable.class.isAssignableFrom(Date.class) ? "true" : "false");
        if (Comparable.class.isAssignableFrom(clz)) {
            Expression expression = path.as(compareClass.getClz());
            Comparable comparable = compareClass.getClz().cast(value);
            switch (filterType) {
                case GREATER_THAN:
                    predicate = criteriaBuilder.greaterThan(expression, comparable);
                    break;
                case GREATER_THAN_OR_EQUALS:
                    predicate = criteriaBuilder.greaterThanOrEqualTo(expression, comparable);
                    break;
                case LESS_THAN:
                    predicate = criteriaBuilder.lessThan(expression, comparable);
                    break;
                case LESS_THAN_OR_EQUALS:
                    predicate = criteriaBuilder.lessThanOrEqualTo(expression, comparable);
                    break;
                default:
                    log.error("过滤类型有误{}", filterType.name());
                    break;
            }
        } else {
            log.error("参数类型{}不能使用此比较符{}", clz, filterType.name());
        }
        return predicate;
    }

    /**
     * 构造 between 条件
     *
     * @param fieldType       .
     * @param value           .
     * @param path            .
     * @param compareClass    .
     * @param join    .
     * @param criteriaBuilder .
     * @return .
     */
    @SuppressWarnings("all")
    private Predicate generatePredicateBetween(Class<?> fieldType, Object value, Path<T> path,
                                               CompareClassEnum compareClass,
                                               String join,
                                               CriteriaBuilder criteriaBuilder) {
        if (value == null) {
            return null;
        }
        Predicate predicate = null;
        if (value instanceof List) {
            List<Predicate> betweenPredicate = new ArrayList<>();
            List<Object> between = (List) value;
            if (between.size() > 0) {
                Object before = between.get(0);
                if (before != null) {
                    Predicate beforePredicate = generatePredicateComparable(fieldType, before, path, compareClass,
                            join, criteriaBuilder, FilterType.GREATER_THAN_OR_EQUALS);
                    if (beforePredicate != null) {
                        betweenPredicate.add(beforePredicate);
                    }
                }
                if (between.size() > 1) {
                    Object end = between.get(1);
                    if (end != null) {
                        Predicate endPredicate = generatePredicateComparable(fieldType, end, path, compareClass,
                                join, criteriaBuilder, FilterType.GREATER_THAN_OR_EQUALS);
                        if (endPredicate != null) {
                            betweenPredicate.add(endPredicate);
                        }
                    }
                }
            }
            if (betweenPredicate.size() > 0) {
                predicate = criteriaBuilder.and(betweenPredicate.toArray(new Predicate[0]));
            }
        } else {
            log.error("between List");
        }
        return predicate;
    }

    /**
     * between 参数类型
     *
     * @param <T>
     */
    @Data
    @NoArgsConstructor
    public static class BetweenParams<T> {

        private T before;

        private T end;

    }

}
