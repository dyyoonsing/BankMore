package kr.ac.kopo.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.Controller;
import kr.ac.kopo.board.dao.BoardDAO;
import kr.ac.kopo.board.vo.BoardFileVO;
import kr.ac.kopo.board.vo.BoardVO;

public class BoardDetailController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		int no = Integer.parseInt(req.getParameter("no"));
		
		BoardDAO dao = new BoardDAO();
		
		String view = req.getParameter("view");
		if(view != null && view.equals("list")){
			//dao.viewUpBoard(no);
		}
		
		BoardVO board = dao.selectByNo(no);
		List<BoardFileVO> fileList = dao.selectFilebyNo(no);
		
		req.setAttribute("board", board);
		req.setAttribute("fileList", fileList);
		
		
		return "/board/boardDetail.jsp";
	}

}
