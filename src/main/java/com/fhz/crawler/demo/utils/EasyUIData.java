package com.fhz.crawler.demo.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @program: data-centre
 * @description:
 * @author: fanghuaizheng
 * @create: 2018-10-30 17:22
 */
public class EasyUIData {
    private long total = 0;
    private Integer pageSize = 10;
    private List<? extends Object> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<? extends Object> getRows() {
        return rows;
    }

    public void setRows(List<? extends Object> rows) {
        this.rows = rows;
    }
    public EasyUIData(IPage pageResult) {
        rows = pageResult.getRecords();
        total = pageResult.getTotal();
        if (total==0){
            total=rows.size();
        }
    }

    public EasyUIData(List pageResult) {
        rows = pageResult;
        total = pageResult.size();
    }
}
