package com.lyr_ssh.test;

import com.lyr_ssh.dao.UserDao;
import com.lyr_ssh.entity.User;
import com.lyr_ssh.service.UserService;
import com.lyr_ssh.web.action.UserAction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author Administrator
 */
// Hibernata ��Spring���ϲ�����Ҫ����Spring�����ļ�
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestHibernate { // hibernate ��������
    // ע����Spring�����sessionFactory
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    // ע��userDao
    @Resource(name = "userDao")
    private UserDao userdao;

    // ע��UserService
    @Resource(name = "userService")
    private UserService userService;
    @Resource(name = "userAction")
    private UserAction action;

    @Test
    public void testHibernate() {// hibernate ��������
        //���������ļ�
        Configuration cg = new Configuration().configure();
        //��ȡsessionFactory
        SessionFactory sessionFactory = cg.buildSessionFactory();
        //��ȡsession
        Session session = sessionFactory.openSession();
        //������
        Transaction beginTransaction = session.beginTransaction();
        //��ʼ���Ӳ���
        User u = new User();
        u.setUser_code("����");
        u.setUser_password("1234");
        session.save(u);
        //�ύ����
        beginTransaction.commit();
        //�ر�session
        session.close();
        //�ر�sessionFactory
        sessionFactory.close();
    }

    @Test // hibernate��Spring����
    public void testHibernateAndSpring() {
        // ʹ��Spring�����SessionFactory ��ȡsession
        Session session = sessionFactory.openSession();
        //������
        Transaction beginTransaction = session.beginTransaction();
        //��ʼ���Ӳ���
        User u = new User();
        u.setUser_code("��������");
        u.setUser_password("1234");
        session.save(u);
        //�ύ����
        beginTransaction.commit();
        //�ر�session
        session.close();
        //�ر�sessionFactory
        sessionFactory.close();
    }

    @Test // Spring��c3p0���ӳ����ϲ���
    public void testHibernateAndSpring_C3P0() {
        // ʹ��Spring�����SessionFactory ��ȡsession
        Session session = sessionFactory.openSession();
        //������
        Transaction beginTransaction = session.beginTransaction();
        //��ʼ���Ӳ���
        User u = new User();
        u.setUser_code("��С��");
        u.setUser_password("123456");
        u.setUser_name("С����");
        session.save(u);
        //�ύ����
        beginTransaction.commit();
        //�ر�session
        session.close();
        //�ر�sessionFactory
        // sessionFactory.close(); //sessionFactory��Spring��������Ҫ�ֶ��ر�
    }

    @Test //����HibernateTemplateģ��������ݿ�--->ok
    public void testHibernateTemplate() {
        User u = userdao.getByUserCode("��С��");
        System.out.println(u);
    }

    @Test //����Aop����--->ok
    public void testAOPTransaction() {
        User u = userService.getByUserCode("��������");
        System.out.println(u);
    }

    @Test //����Aop����--->ok
    public void testAOPTransaction2() {
        User user = new User();
        user.setUser_code("��С��");
        user.setUser_password("123456");
        user.setUser_name("С����");
        User u2 = new User();
        u2.setUser_code("·С·");
        u2.setUser_password("12121");
        u2.setUser_name("·С·");
        try {
//			userdao.saveUser(user, u2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test //����Aop����--->ok
    public void testAction() {
        User user = new User();
        user.setUser_code("��С��");
        user.setUser_password("123456");
        user.setUser_name("С����");

        try {
            User uu = userService.getByUserByCodePassword(user);
            System.out.println(uu);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test //����Aop����--->ok
    public void testAction2() {
        action.test();
    }
}
