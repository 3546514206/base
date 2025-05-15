package com.lyr_ssh.service.impl;

import com.lyr_ssh.dao.CustomerDao;
import com.lyr_ssh.entity.Customer;
import com.lyr_ssh.service.CustomerService;
import com.lyr_ssh.util.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    // Customer依赖注入 CustomerDao (set)
    private CustomerDao customerDao;

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /**
     * 查询分页数据
     * <p>
     * 分页数据包含：总记录数，
     * 分页信息
     */
    public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
        // 调用dao查询总记录数
        Integer totalCount = customerDao.getTotalCount(dc);
        // 创建pageBean对象
        PageBean pb = new PageBean(currentPage, totalCount, pageSize);
        // 调用dao查询分页列表数据(参数：离线查询对象，起始索引，页面显示条数)
        List<Customer> pageList = customerDao.getPageList(dc, pb.getStart(), pb.getPageSize());
        // 将返回的分页数据放入PageBean对象中
        pb.setList(pageList);
        // 返回PageBean对象
        return pb;
    }


}
