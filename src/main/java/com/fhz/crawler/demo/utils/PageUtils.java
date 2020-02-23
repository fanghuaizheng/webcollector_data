package com.fhz.crawler.demo.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @program: data-centre
 * @description: 分页工具类
 * @author: fanghuaizheng
 * @create: 2018-10-15 10:10
 * 组装返回前台的page数据
 *  *  * @param start 开始页面
 *  * 	 * @param length 每页条数
 *  * 	 * @param draw 无用的，框架带的，不知道干嘛
 *  * 	 * @param t 时间参数，防止缓存
 */
public class PageUtils<T> {

    // layui 分页接收参数 start
    private Integer page;
    private Integer limit;
    // layui 分页接收参数 end

    private Integer rows;

    private Integer start;

    private Integer length;

    private Integer draw;

    private String t;

    private Integer pageSize;

    private Integer pageNum;

    private Long total;

    private Integer pages;//共多少页

    private Integer endRow;

    private Integer startRow;

    private Long recordsTotal;

    private List<T> data;


    public PageUtils(){}

    public PageUtils(Page<T> page){
        this.total = page.getTotal();
        this.data = page.getRecords();

    }


    public Page<T> easyUiToPage(){
        Page<T> pageHelp = new Page<>();
        if (this.rows==null){
            rows=10;
        }
//        pageHelp.setPageSize(rows);
        pageHelp.setSize(rows);
        if (this.page==null){
            this.page = 1;
        }
//        pageHelp.setPageNum(page);
        pageHelp.setCurrent(page);
        return pageHelp;
    }


    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }




    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getEndRow() {
        return endRow;
    }

    public void setEndRow(Integer endRow) {
        this.endRow = endRow;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
