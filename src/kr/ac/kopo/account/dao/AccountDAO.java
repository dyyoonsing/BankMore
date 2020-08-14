package kr.ac.kopo.account.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.account.vo.StatementVO;
import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.util.JDBCClose;

public class AccountDAO {
	
	/**
	 * 계좌 조회 기능
	 * @param
	 * type="ALL" :  OpenBanking platform 자체에 있는 유저명의의 모든 계좌
	 * type="N" : BankMore에 추가안한 계좌
	 * type="Y" : BankMore에 등록한 모든 계좌를 불러오는 기능
	 * id : 유저 id
	 * @return AccountVO List
	 */
	public List<AccountVO> selectAllAccount(String type, String id){
		
		List<AccountVO> list = new ArrayList<AccountVO>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select account_no, bank_name, name, password, balance, ");
		sql.append("  		alias, open_check, to_char(open_date, 'yyyy/mm/dd') as open_date, id ");
		sql.append("  		 from bm_t_account ");
		sql.append(" 		where id=? ");
		if(type.equals("Y")) {
			sql.append("        and open_check='Y' ");
		}else if (type.equals("N")) {
			sql.append("        and open_check='N' ");
		}
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				AccountVO account = new AccountVO(rs.getString("account_no"), rs.getString("bank_name"), rs.getString("name"), rs.getString("password"), 
						rs.getInt("balance"), rs.getString("alias"),
						rs.getString("open_check"), rs.getString("open_date"), rs.getString("id")); 
				list.add(account);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * bankmore에 추가한 계좌의 총 balance 구하는 기능
	 * @param id
	 * @return 총 금액
	 */
	public int myTotalBalance(String id) {
		int totalBalance = 0;
		String sql = " select sum(balance) as sum_balance from bm_t_account where id=? and open_check='Y' ";
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				pstmt.setString(1, id);
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					totalBalance = rs.getInt("sum_balance");
					System.out.println(totalBalance);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		return totalBalance;
	}
	
	
	/**
	 * 오픈뱅킹 관리(추가등록/삭제)
	 * @param accountNo 관리할 계좌번호
	 * @param toChange 
	 * 		  "Y" : 계좌를 새롭게 등록
	 * 		  "N" : 계좌 관리 목록에서 삭제
	 */
	public void updateAccountStatus(String accountNo, String toChange) {
		
		String sql = " update bm_t_account set open_check=? where account_no=? "; 
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
				pstmt.setString(1, toChange);
				pstmt.setString(2, accountNo);
				
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * 존재하는 계좌인지 확인 및 계좌주 이름 확인하는 기능
	 * @param accountNo 계좌명
	 * @return string 계좌주 이름 (존재하지 않을 경우 null)
	 */
	public String checkAccountNo(String accountNo) {
		String sql = "select name from bm_t_account where account_no=? ";
		String name = null;
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
				pstmt.setString(1, accountNo);
				
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					name = rs.getString("name");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return name;
	}
	
	
	
	/**
	 * 현재 계좌의 잔액 알려주는 기능
	 * @param accountNo 계좌번호
	 * @return 잔액
	 */
	public int checkBalance(String accountNo) {
		
		String sql = "select balance from bm_t_account where account_no=? ";
		int balance = 0;
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
				pstmt.setString(1, accountNo);
				
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {  
					balance = rs.getInt("balance");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return balance;
	}
	
	
	
	/**
	 * 계좌이체 기능
	 * @param state 거래내역VO
	 */
	public void transfer(StatementVO state) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			
			//1. 내 계좌에서 빠짐 debit
			sql.append(" update bm_t_account set balance = ( balance - ? ) ");
			sql.append("		where account_no = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, state.getAmount());
			pstmt.setString(2, state.getAccountNo());
			pstmt.executeUpdate();
			//preparedStatement랑 sql 초기화하기
			sql.setLength(0);
			JDBCClose.closePstmt(pstmt);
			

			//2. 상대방 계좌에 입금 credit
			sql.append(" update bm_t_account set balance = ( balance + ? ) ");
			sql.append("		where account_no = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, state.getAmount());
			pstmt.setString(2, state.getTransAccountNo());
			pstmt.executeUpdate();
			
			conn.commit();
			
		} catch (Exception e) {
			System.out.println("오류발생");
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				System.out.println("오류 발생 : " + e2.toString());
				e.printStackTrace();
			}
		} finally {
			JDBCClose.close(conn, pstmt);
		}
	}
	
	/**
	 * 계좌이체 내역 거래내역 테이블에 저장하는 기능
	 * @param state
	 */
	public void transferStatement(StatementVO state) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			
			
			//1. 남은 잔액 (balance) 받아오기
			sql.append(" select balance from bm_t_account where account_no=?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, state.getAccountNo());
			ResultSet rs = pstmt.executeQuery();
			int myBalance = 0;
			if(rs.next()) {
				myBalance = rs.getInt("balance");
				//System.out.println("rs.next 후 : " + rs.getInt("balance"));
			}
			sql.setLength(0);
			JDBCClose.closePstmt(pstmt);
			
			//2. statement table에 출금 기록
			sql.append(" insert into bm_t_statement(no, type, account_no, trans_account_no, ");
			sql.append(" 							amount, my_desc, balance )  ");
			sql.append("  		values( seq_bm_t_statement_no.nextval, 'D', ?, ?, ?, ?, ? ) ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, state.getAccountNo());
			pstmt.setString(2, state.getTransAccountNo());
			pstmt.setInt(3, state.getAmount());
			if(state.getMyDesc() == null) {
				pstmt.setNull(4, Types.VARCHAR);
			}else {
				pstmt.setString(4, state.getMyDesc());
			}
			pstmt.setInt(5, myBalance);
			pstmt.executeUpdate();
			
			//preparedStatement랑 sql 초기화하기
			sql.setLength(0);
			JDBCClose.closePstmt(pstmt);
			
			//3. 상대방 계좌 남은 잔액 확인
			sql.append(" select balance from bm_t_account where account_no=?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, state.getTransAccountNo());
			ResultSet rs2 = pstmt.executeQuery();
			int yourBalance = 0;
			if(rs2.next()) {
				yourBalance = rs2.getInt("balance");
			}
			sql.setLength(0);
			JDBCClose.closePstmt(pstmt);
			
			//4. statement table에 입금 기록
			sql.append(" insert into bm_t_statement(no, type, account_no, trans_account_no, ");
			sql.append(" 							amount, balance )  ");
			sql.append("  		values( seq_bm_t_statement_no.nextval, 'C', ?, ?, ?, ? ) ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, state.getTransAccountNo());
			pstmt.setString(2, state.getAccountNo());
			pstmt.setInt(3, state.getAmount());
			pstmt.setInt(4, yourBalance);
			pstmt.executeUpdate();
			conn.commit();
			
		} catch (Exception e) {
			System.out.println("오류발생");
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				System.out.println("오류 발생 : " + e2.toString());
				e.printStackTrace();
			}
		} finally {
			JDBCClose.close(conn, pstmt);
		}
	}
	
	
	public List<StatementVO> selectAllStatement(String accountNo){
		
		StringBuilder sql = new StringBuilder();
		List<StatementVO> list = new ArrayList<>();
		
		sql.append(" select a.account_no, to_char(a.trans_date, 'YYYY/MM/DD HH24:MI:SS') as trans_date, a.type,  ");
		sql.append("        case when a.type = 'C' then a.amount else 0 end as credit, ");
		sql.append("		case when a.type = 'D' then a.amount else 0 end as debit, ");
		sql.append("		a.balance, a.my_desc, a.trans_account_no, b.name, b.bank_name ");
		sql.append(" from bm_t_statement a, bm_t_account b");
		sql.append(" where a.trans_account_no = b.account_no and a.account_no = ? ");
		sql.append(" order by a.trans_date desc");
		
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				pstmt.setString(1, accountNo);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					StatementVO st = new StatementVO();
					st.setAccountNo(rs.getString("account_no"));
					st.setTransDate(rs.getString("trans_date").replace(" ", "<br>"));
					st.setType(rs.getString("type"));
					st.setCredit(rs.getInt("credit"));
					st.setDebit(rs.getInt("debit"));
					st.setBalance(rs.getInt("balance"));
					st.setMyDesc(rs.getString("my_desc"));
					st.setTransAccountNo(rs.getString("trans_account_no"));
					st.setTransName(rs.getString("name"));
					st.setTransBank(rs.getString("bank_name"));
					
					list.add(st);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
	}
	
	

}
