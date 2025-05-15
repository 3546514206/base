package com.lyr_ssh.dao;

import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

/**
 * BaseDao
 * <p>
 * 将dao层数据库的增删改查基本操作，封装到BaseDao中
 * <p>
 * 为了实现通用性，BaseDao使用泛型
 *
 * @author Administrator
 */
public interface BaseDao<T> {

    // 增
    void save(T t);

    // 删
    void delete(T t);

    // 删
    void delete(Serializable id);

    // 改
    void update(T t);

    // 查 根据id查询
    T getById(Serializable id);

    // 查  符合条件的总记录数
    Integer getTotalCount(DetachedCriteria dc);

    // 查  查询分页列表数据
    List<T> getPageList(DetachedCriteria dc, Integer start, Integer pageSize);
}
