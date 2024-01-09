package com.gqz.facade;

public interface 质检局 {
    void orgCodeCertificate();  //办理组织机构代码证
}


class 海淀质检局 implements 质检局 {

    @Override
    public void orgCodeCertificate() {
        System.out.println("在海淀区质检局办理组织机构代码证！");
    }

}
