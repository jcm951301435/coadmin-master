package com.java.module.sys.dto.query;

import com.java.module.sys.model.SysListItem;
import com.java.dto.query.BaseQuery;
import com.java.dto.query.FilterType;
import com.java.dto.query.QueryCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author: jcm
 * @date: 2020/09/14
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ListItemQueryDTO extends BaseQuery<SysListItem> {

    @QueryCondition(filterType = FilterType.EQUAL, filterNames = "id", join = "list")
    private Long listId;

}
