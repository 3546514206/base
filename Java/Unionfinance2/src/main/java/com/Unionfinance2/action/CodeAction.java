package com.Unionfinance2.action;
/*验证码产生类
 * 4位随机数字组成
 * 通用
 * */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.Unionfinance2.base.BaseAction;

public class CodeAction extends BaseAction implements RequestAware, ServletResponseAware, SessionAware {
	
	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;

	@Override
	public String execute() throws Exception {
		// 设置响应头不要缓存页面
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", -1);

		// 在内存中创建图像
		int width = 60, height = 20;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 获取画笔
		Graphics g = image.getGraphics();
		// 设定背景色
		g.setColor(new Color(255, 255, 255));
		// 填充区域
		g.fillRect(0, 0, width, height);
		// 取随机产生的验证码4位数字
		Random rnd = new Random();
		int randNum = rnd.nextInt(8999) + 1000;

		String randStr = String.valueOf(randNum);
		// 将验证码存到session中
		session.put("randStr", randStr);
		// 将验证码显示到图像中
		g.setColor(Color.black);
		g.setFont(new Font("", Font.PLAIN, 20));
		g.drawString(randStr, 10, 17);
		// 随机产生350个干扰点

		for (int i = 0; i < 350; i++) {
			int y = rnd.nextInt(height);
			int x = rnd.nextInt(width);
			g.drawOval(x, y, 1, 1);
		}

		// 输出到页面
		ImageIO.write(image, "JPEG", response.getOutputStream());
		// 返回值必须为空
		return null;
	}

	public HttpServletResponse getServletResponse() {
		return response;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

}