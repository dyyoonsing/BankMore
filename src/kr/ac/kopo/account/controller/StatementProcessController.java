package kr.ac.kopo.account.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.Controller;
import kr.ac.kopo.account.dao.AccountDAO;
import kr.ac.kopo.account.vo.StatementVO;

public class StatementProcessController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {


		String accountNo = req.getParameter("accountNo");
		AccountDAO dao = new AccountDAO();
		List<StatementVO> stateList = dao.selectAllStatement(accountNo);
		
		req.setAttribute("accountNo", accountNo);
		req.setAttribute("stateList", stateList);
		
		return "/account/statementProcess.jsp";
	}

}
