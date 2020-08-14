package kr.ac.kopo.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.Controller;
import kr.ac.kopo.member.dao.MemberDAO;

public class CheckIdController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String id = req.getParameter("id");
		MemberDAO dao = new MemberDAO();
		if(dao.myPageInfo(id) != null) {
			req.setAttribute("checkId", "CanUse");
		}
		
		return "/member/checkId.jsp";
	}

}
