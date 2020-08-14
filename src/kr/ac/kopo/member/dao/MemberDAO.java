package kr.ac.kopo.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.util.ConnectionFactory;

public class MemberDAO {
	
	/**
	 * 로그인 기능
	 * @param memberVO
	 * @return
	 */
	public MemberVO login(MemberVO memberVO) {
		MemberVO userVO = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select id, name, type ");
		sql.append(" from bm_t_member ");
		sql.append(" where id = ? and password = ? ");
		

		try(
			Connection conn = new ConnectionFactory().getConnection();	
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, memberVO.getId());
			pstmt.setString(2, memberVO.getPassword());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				userVO = new MemberVO();
				userVO.setId(rs.getString("id"));
				userVO.setName(rs.getString("name"));
				userVO.setType(rs.getString("type"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userVO;
	}
	
	/**
	 * KaKao id로 로그인
	 * @param kakaoId
	 * @return 가능하면 memberVO 넘겨서 로그인시킴
	 */
	public MemberVO loginKakao(String kakaoId) {
		MemberVO member = null;
		String sql = "select * from bm_t_member where kakao_id = ? ";
		try(
				Connection conn = new ConnectionFactory().getConnection();	
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
				pstmt.setString(1, kakaoId);
				ResultSet rs = pstmt.executeQuery();
				
				
				if(rs.next()) {
					member = new MemberVO();
					member.setId(rs.getString("id"));
					member.setType(rs.getString("type"));
					member.setName(rs.getString("name"));
					//member.setOpenCheck(rs.getString("open_check"));
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		return member;
	}
	
	/**
	 * 회원가입 하는 기능
	 * @param member
	 */
	public void signin(MemberVO member) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("insert into bm_t_member(id, name, password, email, tel ");
		sql.append(" 						,post, basic_addr, detail_addr, kakao_id) ");
		sql.append(" 			values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		try(
				Connection conn = new ConnectionFactory().getConnection();	
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				pstmt.setString(1, member.getId());
				pstmt.setString(2, member.getName());
				pstmt.setString(3, member.getPassword());
				pstmt.setString(4, member.getEmail());
				pstmt.setString(5, member.getTel());
				pstmt.setString(6, member.getPost());
				pstmt.setString(7, member.getBasicAddr());
				pstmt.setString(8, member.getDetailAddr());
				pstmt.setString(9, member.getKakaoId());
				
				pstmt.executeUpdate();
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	

	
	/**
	 * 1. 로그인 중복체크  - return이 null이면 사용가능함
	 * 2. myPage 화면에서 정보 보여줌
	 * @param id
	 * @return memberVO 객체
	 */
	public MemberVO myPageInfo(String id) {
		
		String sql = " select * from bm_t_member where id = ?";
		MemberVO member = null;
		try(
			Connection conn = new ConnectionFactory().getConnection();	
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
			
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new MemberVO(rs.getString("id"), rs.getString("name"), rs.getString("password"), 
						rs.getString("email"), rs.getString("tel"), rs.getString("post"), rs.getString("basic_addr"), 
						rs.getString("detail_addr"), rs.getString("type"), rs.getString("reg_date"), rs.getString("kakao_id"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return member;
		
	}
	
	/**
	 * 기존회원 카카오 연동 기능
	 * @param id
	 * @param kakaoId
	 */
	public void addKakao(String id, String kakaoId) {
		
		String sql = " update bm_t_member set kakao_id=? where id=? ";
		try(
			Connection conn = new ConnectionFactory().getConnection();	
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) { 
			
			pstmt.setString(1, kakaoId);
			pstmt.setString(2, id);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 회원탈퇴 기능
	 * @param id
	 */
	public void exitMember(String id) {
		
		String sql = " delete from bm_t_member where id=?  ";
		try(
			Connection conn = new ConnectionFactory().getConnection();	
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) { 
			
			pstmt.setString(1, id);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
