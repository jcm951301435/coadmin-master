package com.java.model;

import com.java.util.StringUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: jcm
 * @date: 2020/10/09
 */
@Data
@NoArgsConstructor
@Slf4j
public class CommonQueryPageSort {

    private Integer pageNum;

    private Integer pageSize;

    private String[] sorts;

    /**
     * 排序 正序字符前缀
     */
    private final static String SORT_ASC_PREFIX = "+";

    /**
     * 排序 倒序字符前缀
     */
    private final static String SORT_DESC_PREFIX = "-";

    /**
     * 构造 jpa 排序对象
     * @return .
     */
    public Sort generateSort() {
        List<Sort.Order> orders = new ArrayList<>();
        if (sorts == null || sorts.length <= 0) {
            return Sort.by(orders);
        }
        for (String sort : sorts) {
            if (StringUtils.isEmpty(sort)) {
                continue;
            }
            if (sort.length() <= 1) {
                log.warn("排序字段长度有误, 字段值:{}", sort);
                continue;
            }
            String orderFiled = sort.substring(1);
            if (sort.startsWith(SORT_ASC_PREFIX)) {
                orders.add(new Sort.Order(Sort.Direction.ASC, orderFiled));
            } else if (sort.startsWith(SORT_DESC_PREFIX)) {
                orders.add(new Sort.Order(Sort.Direction.DESC, orderFiled));
            }
        }
        return Sort.by(orders);
    }

}
