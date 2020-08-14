package kr.ac.kopo.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.Controller;
import kr.ac.kopo.member.dao.MemberDAO;
import kr.ac.kopo.member.vo.MemberVO;

public class SigninProcessController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("utf-8");
		MemberVO member = new MemberVO();
		
		member.setId(req.getParameter("id"));
		member.setPassword(req.getParameter("password"));
		member.setName(req.getParameter("name"));
		member.setEmail(req.getParameter("email"));
		member.setTel(req.getParameter("tel"));
		member.setPost(req.getParameter("post"));
		member.setBasicAddr(req.getParameter("basicAddr"));
		member.setDetailAddr(req.getParameter("detailAddr"));
		member.setKakaoId(req.getParameter("kakaoId"));
		
		
		MemberDAO dao = new MemberDAO();
		dao.signin(member);
		
		//자동로그인
		MemberVO userVO = dao.login(member);
		
		HttpSession session = req.getSession();
		session.setAttribute("userVO", userVO);
		
		req.setAttribute("url", req.getContextPath());
		req.setAttribute("msg", "회원가입이 완료되었습니다. 축하합니다!");
		
		return "member/loginAlert.jsp";
	}

}
