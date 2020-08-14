package kr.ac.kopo.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.Controller;
import kr.ac.kopo.member.dao.MemberDAO;
import kr.ac.kopo.member.vo.MemberVO;

public class LoginProcessController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		
		MemberVO userVO = null;
		MemberDAO dao = new MemberDAO();
		
		String url = null;
		
		if(req.getParameter("kakaoId") != null) {
			//카카오로 로그인시
			MemberVO kakaoVO = dao.loginKakao(req.getParameter("kakaoId"));
			
			if(kakaoVO == null) {
				//회원가입으로 넘어감 - session에 등록후 signin.do로 넘어감
				kakaoVO = new MemberVO();
				kakaoVO.setKakaoId(req.getParameter("kakaoId"));
				kakaoVO.setEmail(req.getParameter("email"));
				kakaoVO.setName(req.getParameter("name"));
				
				session.setAttribute("kakaoVO", kakaoVO);
				System.out.println("kakaoVO 객체가 있나요????" + kakaoVO.getKakaoId());
				//KaKao Login Signin
				url = req.getContextPath() + "/signin.do";
				
			}else {
				//카카오 로그인 성공
				session.setAttribute("userVO", kakaoVO);
				url = req.getContextPath();
			}
			
		}else {
			//일반 유저로 로그인시  
			MemberVO memberVO = new MemberVO();
			memberVO.setId(req.getParameter("id"));
			memberVO.setPassword(req.getParameter("password"));
			
			userVO = dao.login(memberVO);
			
			if(userVO == null) {
				//Member Not Found
				url = req.getContextPath() + "/login.do?status=MNF";
			}else {
				url = req.getContextPath();
				
				session.setAttribute("userVO", userVO);
			}
			
		}
		
		return "redirect:" + url;
	}

		
}
