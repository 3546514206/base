package edu.zjnu.base.base.ref;

/**
 * @description: TODO
 * @author: 杨海波
 * @date: 2022-09-20 16:44
 **/
public class ChangeStringV5 {
    public static String  changeStr(String str){
        str= "welcome" ;
        return str;
    }

    public static void  main(String[] args) {
        String str= "1234" ;
        str = changeStr(str);
        System.out.println(str);
    }
}
