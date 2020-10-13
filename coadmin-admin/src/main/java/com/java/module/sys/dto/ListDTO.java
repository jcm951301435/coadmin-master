package com.java.module.sys.dto;

import com.java.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: jcm
 * @date: 2020/10/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ListDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    private String type;

    /**
     * 值
     */
    private String value;

}
