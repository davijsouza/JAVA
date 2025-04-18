package com.base.inic.model.dto.request;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.validator.constraints.Range;

import com.base.inic.model.dto.AbstractDTO;


/**
 * Solicitação de paginação dto
 * @date 2017/2/20
 * @author netyjq@gmail.com
 */
@Data
@ToString 
@EqualsAndHashCode(callSuper = false)
public class PageRequestDTO extends AbstractDTO {

    private long pageNum = 1;

    @Range(min = 0, max = 100)
    private long pageSize = 5;

    public PageRequestDTO() {
    }

    public PageRequestDTO setPageSize(Integer pageSize) {
        if (pageSize < 1) {
            pageSize = 1;
        }
        this.pageSize = pageSize;
        return this;
    }

    public PageRequestDTO setPageNum(Integer pageNum) {
        if (pageNum < 0) {
            pageNum = 1;
        }
        this.pageNum = pageNum;
        return this;
    }

    public PageRequestDTO(Integer pageNum, Integer pageSize) {
        setPageNum(pageNum);
        setPageSize(pageSize);
    }

    @Override
    public boolean validate() {
        return false;
    }
}
