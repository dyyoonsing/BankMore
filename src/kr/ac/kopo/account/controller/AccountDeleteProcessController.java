package kr.ac.kopo.account.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.Controller;
import kr.ac.kopo.account.dao.AccountDAO;

public class AccountDeleteProcessController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		req.setCharacterEncoding("UTF-8");
		
		String[] accountNoList = req.getParameterValues("no");
		for(String accountNo : accountNoList) {
			AccountDAO dao = new AccountDAO();
			dao.updateAccountStatus(accountNo, "N");
		}
		
		
		return "redirect:" + req.getContextPath() + "/accountAddDelete.do";
		
	}

}
