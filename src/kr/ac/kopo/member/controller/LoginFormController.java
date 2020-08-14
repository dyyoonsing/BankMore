package kr.ac.kopo.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.Controller;

public class LoginFormController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		String status = req.getParameter("status");
		if(status != null) {
			req.setAttribute("url", req.getContextPath() + "/login.do");
			if(status.equals("MNF")) {
				//존재하지 않는 유저
				req.setAttribute("msg", "아이디/비밀번호가 맞지 않습니다. \\n다시 입력하세요");
			}
			return "/member/loginAlert.jsp";
		}
		
		
		return "/member/loginForm.jsp";
	}

		
}
