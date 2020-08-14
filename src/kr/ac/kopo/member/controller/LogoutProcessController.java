package kr.ac.kopo.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.Controller;

public class LogoutProcessController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		HttpSession session = req.getSession();
		session.invalidate();
		
		String msg = "로그아웃되었습니다";
		String url = req.getContextPath();
		
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		
		return "/member/logoutProcess.jsp";
	}

}
