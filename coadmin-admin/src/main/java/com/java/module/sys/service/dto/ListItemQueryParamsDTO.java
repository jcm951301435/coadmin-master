package com.java.module.sys.service.dto;

/**
 * @author: jcm
 * @date: 2020/09/14
 */
public class ListItemQueryParamsDTO {

    private Long listId;

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    @Override
    public String toString() {
        return "ListItemQueryParamsDTO{" +
                "listId=" + listId +
                '}';
    }
}
