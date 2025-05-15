package com.lyr_ssh.web.action;


import com.lyr_ssh.entity.User;
import com.lyr_ssh.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User> {
    private static final long serialVersionUID = 1L;

    // 模型驱动
    private User user = new User();
    // bean set�ŷ�ע��
    // ע��UserService
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    public void test() {
        System.out.println(userService);
    }

    public String login() throws Exception {
        System.out.println(user.getUser_code());
        // 1.����Serviceִ�е�¼�߼�
        User u = userService.getByUserByCodePassword(user);
        // 2.�����ص�User�������session��
        ActionContext.getContext().getSession().put("user", u);
        // 3.�ض�����Ŀ��ҳ
        return "toHome";
    }


    public User getModel() {
        return user;
    }


}
