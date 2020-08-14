package kr.ac.kopo.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.board.vo.BoardFileVO;
import kr.ac.kopo.board.vo.BoardVO;
import kr.ac.kopo.util.ConnectionFactory;

public class BoardDAO {
	
	/**
	 * 모든 게시물 조회
	 * @return BoardVO list 객체
	 */
	public List<BoardVO> selectAllBoard() {
		
		List<BoardVO> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select rownum, a.*  ");
		sql.append("   from ( select no, group_no, title, writer, content, type, view_cnt, questioner,  ");
		sql.append(" 				case when to_char(reg_date, 'dd') = to_char(sysdate, 'dd') ");
		sql.append("  			 				then to_char(reg_date, 'HH24:MI:SS') ");
		sql.append("   			 				else to_char(reg_date, 'YYYY-MM-DD') end as reg_date, ");
		sql.append("   				case when to_char(reg_date, 'dd') = to_char(sysdate, 'dd') ");
		sql.append("   							then 'Y'");
		sql.append("   							else 'N' end as today_check");
		sql.append("		from bm_t_board order by group_no desc, no) a");
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setNo(rs.getInt("no"));
				vo.setGroupNo(rs.getInt("group_no"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setContent(rs.getString("content"));
				vo.setType(rs.getString("type"));
				vo.setViewCnt(rs.getInt("view_cnt"));
				vo.setRegDate(rs.getString("reg_date"));
				vo.setTodayCheck(rs.getString("today_check"));
				vo.setRowNum(rs.getInt("rownum"));
				if(rs.getString("type").equals("A")) {
					vo.setQuestioner(rs.getString("questioner"));
				}
				
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
	
	
	/**
	 * seq_t_board_no 생성하는 기능
	 * @return 시퀀스 넘버
	 */
	public int selectBoardNo() {
		
		String sql = "select seq_bm_t_board_no.nextval from dual";
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
		) {
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			return rs.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	/**
	 * 새로운 게시물 등록 (질문, 답변)
	 * @param BoardVO board
	 */
	public void insertBoard(BoardVO board) {
		
		StringBuilder sql = new StringBuilder();
		if(board.getType().equals("Q")) {
			sql.append("insert into bm_t_board(no, title, writer, content, type, group_no, questioner) ");
			sql.append(" values(?, ?, ?, ?, ?, seq_bm_t_board_group_no.nextval, null) ");			
		}else {
			System.out.println("sql 시작안함");
			sql.append("insert into bm_t_board(no, title, writer, content, type, group_no, questioner) ");
			sql.append("	select ?, ?, ?, ?, ?, ?, writer from bm_t_board ");
			sql.append("									where group_no = ? ");
			sql.append("										  and type = 'Q' ");
			System.out.println("sql 끝");
		}
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setInt(1, board.getNo());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getWriter());
			pstmt.setString(4, board.getContent());
			pstmt.setString(5, board.getType());
			if(board.getType().equals("A")) {
				pstmt.setInt(6, board.getGroupNo());
				pstmt.setInt(7, board.getGroupNo());
			}
			pstmt.executeUpdate();
			System.out.println("sql 업데이트됨");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 게시물에 첨부파일 등록
	 * @param fileVO
	 */
	public void insertFile(BoardFileVO fileVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into bm_t_board_file(no, board_no, file_ori_name, file_save_name, file_size) ");
		sql.append(" 				  values(seq_bm_t_board_file_no.nextval, ?, ?, ?, ? ) ");
		
		try( 
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {

			pstmt.setInt(1, fileVO.getBoardNo());
			pstmt.setString(2, fileVO.getFileOriName());
			pstmt.setString(3, fileVO.getFileSaveName());
			pstmt.setInt(4, fileVO.getFileSize());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 게시물 번호에 해당 게시물 조회 서비스
	 * @param no 조회할 번호
	 * @return 해당 게시물
	 */
	public BoardVO selectByNo(int no) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * ");
		sql.append(" from bm_t_board");
		sql.append(" where no = ? ");
		
		BoardVO board = null;
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				
		) {
			pstmt.setInt(1, no);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				board = new BoardVO();
				board.setNo(rs.getInt("no"));
				board.setGroupNo(rs.getInt("group_no"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setViewCnt(rs.getInt("view_cnt"));
				board.setRegDate(rs.getString("reg_date"));	
				
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return board;
	}
	
	/**
	 * 게시물 번호에 해당하는 게시물 파일 조회 서비스
	 * @param boardNo 
	 * @return boardFileVO list
	 */
	public List<BoardFileVO> selectFilebyNo(int boardNo){
		
		List<BoardFileVO> fileList = new ArrayList<BoardFileVO>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select no, file_ori_name, file_save_name, file_size ");
		sql.append("       from bm_t_board_file ");
		sql.append("       where board_no = ? ");
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setInt(1, boardNo);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardFileVO file = new BoardFileVO();
				file.setNo(rs.getInt("no"));
				file.setFileOriName(rs.getString("file_ori_name"));
				file.setFileSaveName(rs.getString("file_save_name"));
				file.setFileSize(rs.getInt("file_size"));
				
				fileList.add(file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fileList;
	}
	
	

}
