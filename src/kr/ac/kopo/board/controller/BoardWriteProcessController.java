package kr.ac.kopo.board.controller;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import kr.ac.kopo.Controller;
import kr.ac.kopo.board.dao.BoardDAO;
import kr.ac.kopo.board.vo.BoardFileVO;
import kr.ac.kopo.board.vo.BoardVO;
import kr.ac.kopo.util.KopoFileNamePolicy;

public class BoardWriteProcessController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("utf-8");
		
		String saveFolder = "E:\\BankMore\\WebContent\\upload";
		
		MultipartRequest multi = new MultipartRequest(req, saveFolder, 1024 * 1024 * 3, "utf-8", new KopoFileNamePolicy());
		
		String title = multi.getParameter("title");
		String writer = multi.getParameter("writer");
		String content = multi.getParameter("content");
		content = content.replace("\r\n","<br>");
		String type = multi.getParameter("type");
		
		
		
		BoardDAO dao = new BoardDAO();
		int boardNo = dao.selectBoardNo();
		
		BoardVO board = new BoardVO();
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		board.setNo(boardNo);
		board.setType(type);
		if(type.equals("A")) {
			int groupNo = Integer.parseInt(multi.getParameter("groupNo"));
			board.setGroupNo(groupNo);
		}
		
		
		dao.insertBoard(board);
		
		Enumeration<String> files = multi.getFileNames();
		while(files.hasMoreElements()) {
			String fileName = files.nextElement();
			File f = multi.getFile(fileName);
			if(f != null) {
				String fileOriName = multi.getOriginalFileName(fileName);
				String fileSaveName = multi.getFilesystemName(fileName);
				int fileSize = (int)f.length();
				
				BoardFileVO fileVO = new BoardFileVO();
				fileVO.setFileOriName(fileOriName);
				fileVO.setFileSaveName(fileSaveName);
				fileVO.setFileSize(fileSize);
				fileVO.setBoardNo(boardNo);	
				
				dao.insertFile(fileVO);
			}
		}

		return "redirect:" + req.getContextPath() + "/boardList.do";
	}

}
