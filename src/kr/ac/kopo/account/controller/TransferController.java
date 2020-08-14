package kr.ac.kopo.account.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.Controller;
import kr.ac.kopo.account.dao.AccountDAO;
import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.member.vo.MemberVO;

public class TransferController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO)session.getAttribute("userVO");
		
		AccountDAO dao = new AccountDAO();
		
		if(member != null) {
			String id = member.getId();
			
			List<AccountVO> selectedAccountList = dao.selectAllAccount("Y", id);
			req.setAttribute("selectedAccountList", selectedAccountList);
			
		}else {
			req.setAttribute("msg", "로그인이 필요합니다. 로그인 화면으로 넘어가겠습니까?");
			req.setAttribute("url", req.getContextPath() + "/login.do");
		}
		
		String status = req.getParameter("status");
		if(status != null) {
			req.setAttribute("url", req.getContextPath() + "/transfer.do");
			if(status.equals("ANF")) {
				//계좌가 존재하지 않음
				req.setAttribute("msg", "존재하지 않는 계좌입니다");
			}else if(status.equals("BNE")) {
				req.setAttribute("msg", "이체할 금액이 잔액보다 큽니다");
			}
			return "/account/transferAlert.jsp";
		}
		
		return "/account/transfer.jsp";
	}

}
