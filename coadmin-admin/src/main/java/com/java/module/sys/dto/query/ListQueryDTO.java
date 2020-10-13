package com.java.module.sys.dto.query;

import com.java.dto.query.BaseQuery;
import com.java.dto.query.CompareClassEnum;
import com.java.dto.query.FilterType;
import com.java.dto.query.QueryCondition;
import com.java.module.sys.model.SysList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author: jcm
 * @date: 2020/09/14
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ListQueryDTO extends BaseQuery<SysList> {

    /**
     * 关键字
     */
    @QueryCondition(filterType = FilterType.LIKE, filterNames = "type")
    private String blurry;

    /**
     * 创建时间区间
     */
    @QueryCondition(filterType = FilterType.BETWEEN, filterNames = "createTime", compareClass = CompareClassEnum.DATE)
    private List<Date> createTime;

}
