/**
	 * @author 丁赵雷
	 * @date 2017/5/30 21:17
	 * @param str 需要判断的字符串
	 * @param charset 字符集
	 * @return
	 * 判断该字符串是哪种字符集
	 */
package com.Unionfinance2.util;

import java.io.UnsupportedEncodingException;

public class Garbled {

	/**
	 * 
	 * @param str 将要解码的字符串
	 * @param decode 解码字符集
	 * @param encode 编码字符集
	 * @return 重新编码后的字符集
	 */
	public static String garbled(String str,String decode , String encode){
		boolean flag = false;
		
		try {
			if(str.equals(new String(str.getBytes(decode) , decode))){
				return new String(str.getBytes(decode) , encode);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "未解码成功";
	}
	//在你的dao层用的例子
	/*public List<MenuType> ByName(String typeName) {
		System.out.println(typeName);
		if(garbled(typeName,"iso8859-1")){
			try {
				typeName = new String(typeName.getBytes("iso8859-1"),"utf8");
				System.out.println("eee"+typeName);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String hql="from MenuType m where m.typeName='"+typeName+"'";		
		return this.getEntityList(hql);
	}*/
	
}
