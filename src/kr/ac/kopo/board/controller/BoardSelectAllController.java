package kr.ac.kopo.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.Controller;
import kr.ac.kopo.board.dao.BoardDAO;
import kr.ac.kopo.board.vo.BoardVO;

public class BoardSelectAllController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		BoardDAO dao = new BoardDAO();
		List<BoardVO> list = dao.selectAllBoard();
		
		req.setAttribute("list", list);
		
		return "/board/boardList.jsp";
	}

}
