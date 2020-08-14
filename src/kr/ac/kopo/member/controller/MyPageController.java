package kr.ac.kopo.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.Controller;
import kr.ac.kopo.member.dao.MemberDAO;
import kr.ac.kopo.member.vo.MemberVO;

public class MyPageController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		HttpSession session = req.getSession();
		MemberVO userVO = (MemberVO)session.getAttribute("userVO");
		
		MemberDAO dao = new MemberDAO();
		MemberVO pageUser = dao.myPageInfo(userVO.getId());
		
		req.setAttribute("pageUser", pageUser);
		
		return "/member/myPage.jsp";
	}

}
