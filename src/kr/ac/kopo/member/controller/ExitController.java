package kr.ac.kopo.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.Controller;
import kr.ac.kopo.member.dao.MemberDAO;
import kr.ac.kopo.member.vo.MemberVO;

public class ExitController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		HttpSession session = req.getSession();
		String id = ((MemberVO)session.getAttribute("userVO")).getId();
		
		MemberDAO dao = new MemberDAO();
		dao.exitMember(id);
		
		req.setAttribute("msg", "회원탈퇴되었습니다");
		req.setAttribute("url", req.getContextPath());
		session.invalidate();
		
		return "/member/loginAlert.jsp";
	}

}
