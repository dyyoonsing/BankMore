package kr.ac.kopo.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.Controller;
import kr.ac.kopo.member.dao.MemberDAO;
import kr.ac.kopo.member.vo.MemberVO;

public class AddKakaoController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		
		MemberVO userVO = (MemberVO)session.getAttribute("userVO");
		String id = userVO.getId();
		
		String kakaoId = req.getParameter("kakaoId");
		
		MemberDAO dao = new MemberDAO();
		dao.addKakao(id, kakaoId);
		
		return "redirect:" + req.getContextPath() + "/myPage.do";
	}

}
