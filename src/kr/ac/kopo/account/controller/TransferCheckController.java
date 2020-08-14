package kr.ac.kopo.account.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.Controller;
import kr.ac.kopo.account.dao.AccountDAO;
import kr.ac.kopo.account.vo.StatementVO;

public class TransferCheckController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		
		req.setCharacterEncoding("utf-8");
		String accountNo = req.getParameter("no");
		String transAccountNo = req.getParameter("sendNo");
		int amount = Integer.parseInt(req.getParameter("amount"));
		String myDesc = req.getParameter("desc");
		
		
		AccountDAO dao = new AccountDAO();
		
		//송금할 계좌가 존재하는 계좌인지 확인
		if(dao.checkAccountNo(transAccountNo) == null) {
			//Account Not Found
			return "redirect:" + req.getContextPath() + "/transfer.do?status=ANF";
		}
		
		//본인 계좌 잔액 확인
		if(dao.checkBalance(accountNo) < amount) {
			//Balance Not Enough
			return "redirect:" + req.getContextPath() + "/transfer.do?status=BNE";
		}
		
		String transName = dao.checkAccountNo(transAccountNo);
		StatementVO state = new StatementVO();
		state.setAccountNo(accountNo);
		state.setTransAccountNo(transAccountNo);
		state.setAmount(amount);
		state.setMyDesc(myDesc);
		
		//받는 사람 이름
		req.setAttribute("transName", transName);
		//거래내역
		req.setAttribute("statement", state);
		
		
		//계좌이체
		/*
		 * 1. 본인 돈 빠져나가고
		 * 2. 상대방 돈 들어오고
		 * 
		 * 1. 거래 찍히고
		 * 2. 상대방 거래 찍힘
		 */
		
		dao.transfer(state);
		dao.transferStatement(state);
		
		
		return "/account/transferCheck.jsp";
	}

}
