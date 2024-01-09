package ScriptEngine;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;


/*
 * 测试脚本引擎执行javascript 代码
 */
public class ScriptEngineDemo01 {
	public static void main(String[] args) throws ScriptException, NoSuchMethodException {

		//获得脚本引擎对象
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine engine = sem.getEngineByName("javascript");

		//定义变量,存储到引擎上下文中
		engine.put("msg", "script is very good");

		String str = "var user = {name:'ganquanzhong',age:18};";
		str += "print(user.name);";

		//执行变量
		engine.eval(str);
		System.out.println(engine.get("msg"));

		//定义函数
		engine.eval("function add(a,b){var sum =a+b; return sum;}");
		//取得调用接口
		Invocable jsInvoke = (Invocable) engine;
		//执行脚本中定义的方法
		Object result2 = jsInvoke.invokeFunction("add", new Object[]{45, 89});
		System.out.println(result2);

		//导入其他Java包，使用其他包中的java类
//		String jsCode = "import java.util;var list= Arrays.asList([\"Java\",\"Spring\",\"SpringBoot\"]);";
//		engine.eval(jsCode);
//		List<String> list2=(List<String>)engine.get("list");
//		for(String temp : list2) {
//			System.out.println(temp);
//		}

		//执行一个js文件
		URL url = ScriptEngineDemo01.class.getClassLoader().getResource("test.js");
		FileReader fr;
		try {
			fr = new FileReader(url.getPath());
			engine.eval(fr);
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
