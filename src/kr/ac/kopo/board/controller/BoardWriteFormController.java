package kr.ac.kopo.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.Controller;

public class BoardWriteFormController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		String type = req.getParameter("type");
		req.setAttribute("type", type);
		String groupNo = req.getParameter("groupNo");
		req.setAttribute("groupNo", groupNo);
		
		return "/board/boardWriteForm.jsp";
	}

}
