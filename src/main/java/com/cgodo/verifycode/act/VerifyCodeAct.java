package com.cgodo.verifycode.act;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cgodo.verifycode.VerifyCodeGenerator;
import com.cgodo.verifycode.model.VerifyCodeModel;

/**
 * 验证码相关服务
 * 
 * @author liyixing-pc
 *
 */
@Controller
public class VerifyCodeAct {
	/**
	 * 获取验证码
	 * 
	 * @param code
	 * @param state
	 * @param key
	 *            唯一key，每次请求都需要传入该key，防止不同页面的验证码冲突<br>
	 *            可以调用com.cgodo.verifycode.VerifyCodeValidator.generateKey(
	 *            HttpSession session)生成
	 * @return
	 * @throws IOException 
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/verify_code.jhtml")
	public String makeVerifyCode(HttpSession session, HttpServletRequest request,
			OutputStream outputStream, HttpServletResponse response, String key) throws IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		VerifyCodeModel verifyCodeModel = new VerifyCodeModel();
		session.setAttribute(key, verifyCodeModel);
		VerifyCodeGenerator.outputImage(320, 80, response.getOutputStream(), verifyCodeModel.getCode());
		
		return null;
	}
}
