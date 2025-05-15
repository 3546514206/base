package com.lyr_ssh.dao.impl;

import com.lyr_ssh.dao.BaseDao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * BaseDaoImpl
 * <p>
 * 实现了BaseDao<T>接口
 * 把接口的方法全部实现
 *
 * @author Administrator
 */

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {


    @SuppressWarnings("rawtypes")
    private Class clazz; // 用于接受运行期的泛型类型

    // 创建无参构造函数
    @SuppressWarnings("rawtypes")
    public BaseDaoImpl() {
        // 获得当前类型的带有泛型类型的父类--->this.getClass().getGenericSuperclass()
        ParameterizedType ptClass = (ParameterizedType) this.getClass().getGenericSuperclass();
        // 获取运行期的泛型类型-->getActualTypeArguments()
        clazz = (Class) ptClass.getActualTypeArguments()[0];
    }

    /**
     * 保存
     */
    public void save(T t) {
        getHibernateTemplate().save(t);
    }

    /**
     * 删除
     */
    public void delete(T t) {
        getHibernateTemplate().delete(t);
    }

    /***
     * 根据id删除
     */
    public void delete(Serializable id) {
        T t = this.getById(id); // 先取，再删
        getHibernateTemplate().delete(t);
    }

    /**
     * 更新
     */
    public void update(T t) {
        getHibernateTemplate().update(t);
    }

    /**
     * 根据id查询
     * <p>
     * 需要动态获取运行时的实体泛型的类型，
     */
    @SuppressWarnings("unchecked")
    public T getById(Serializable id) {
        return (T) getHibernateTemplate().get(clazz, id);
    }

    /**
     * 获取符合条件的总记录数
     */
    public Integer getTotalCount(DetachedCriteria dc) {
        // 设置查询的聚合函数，总记录数
        dc.setProjection(Projections.rowCount());
        // 使用离线查询
        @SuppressWarnings("unchecked")
        List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(dc);
        // 清空离线查询对象上面设置的聚合函数
        dc.setProjection(null);

        if (list != null && list.size() > 0) {
            Long count = list.get(0);
            return count.intValue();
        }
        return null;
    }

    /**
     * 查询页面列表数据
     */
    @SuppressWarnings("unchecked")
    public List<T> getPageList(DetachedCriteria dc, Integer start, Integer pageSize) {

        return (List<T>) getHibernateTemplate().findByCriteria(dc, start, pageSize);
    }


}
