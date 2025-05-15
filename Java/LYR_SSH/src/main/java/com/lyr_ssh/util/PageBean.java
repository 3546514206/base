package com.lyr_ssh.util;

import java.util.List;

/**
 * 分页显示工具类
 *
 * @author Administrator
 */
public class PageBean {

    // 当前页数
    private Integer currentPage;
    // 总记录数
    private Integer totalCount;
    // 每页显示条数
    private Integer pageSize;
    // 总页数
    private Integer totalPage;
    // 分页列表数据
    private List list;

    // 创建对象时，初始化当前页数，总记录数，每页显示条数
    public PageBean(Integer currentPage, Integer totalCount, Integer pageSize) {
        this.currentPage = currentPage;
        this.totalCount = totalCount;
        this.pageSize = pageSize;

        // 如果页面没有指定显示哪一页，默认显示第一页
        if (this.currentPage == null) {
            this.currentPage = 1;
        }
        // 如果每页显示的条数没有指定，默认每页显示3条
        if (this.pageSize == null) {
            this.pageSize = 3;
        }
        // 计算总页数  (总记录数/每页显示条数)---》【每页显示条数-1 --》临界值】 -------》(总记录数+每页显示条数-1)/每页显示条数
        this.totalPage = (this.totalCount + this.pageSize - 1) / this.pageSize;

        // 判断当前页数是否超出范围
        // 不能小于1
        if (this.currentPage < 1) {
            this.currentPage = 1;
        }
        // 不能大于总页数
        if (this.currentPage > this.totalPage) {
            this.currentPage = this.totalPage;
        }
    }

    /**
     * 提供计算起始索引的方法
     * <p>
     * 起始索引：(当前页数-1)*每页显示条数
     *
     * @return
     */
    public Integer getStart() {
        return (this.currentPage - 1) * this.pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }


}
