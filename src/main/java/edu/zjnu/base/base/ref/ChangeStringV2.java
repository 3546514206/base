package edu.zjnu.base.base.ref;

/**
 * @description: TODO
 * @author: 杨海波
 * @date: 2022-09-20 16:29
 **/
public class ChangeStringV2 {

    public void  changeStr(String str){
        str= "welcome" ;
    }

    public static void  main(String[] args) {

        String str= "1234" ;
        new ChangeStringV2().changeStr(str);
        System.out.println(str);
    }
}
