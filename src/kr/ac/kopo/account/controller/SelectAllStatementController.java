package kr.ac.kopo.account.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.Controller;
import kr.ac.kopo.account.dao.AccountDAO;
import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.member.vo.MemberVO;

public class SelectAllStatementController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO)session.getAttribute("userVO");
		
		AccountDAO dao = new AccountDAO();
		if(member != null) {
			String id = member.getId();
			//bankmore platform에만 추가한 계좌 리스트
			List<AccountVO> accountList = dao.selectAllAccount("Y", id);
			req.setAttribute("accountList", accountList);
			
			
			
		}else {
			req.setAttribute("url", "로그인이 필요합니다. 로그인 화면으로 넘어가겠습니까?");
		}
		
		
		
		return "/account/selectAllStatement.jsp";
	}

}
