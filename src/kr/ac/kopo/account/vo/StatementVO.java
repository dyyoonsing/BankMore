package kr.ac.kopo.account.vo;

public class StatementVO {
	
	private int no;
	private String transDate;
	private String type;
	private String accountNo;
	private String transAccountNo;
	private int amount;
	private String myDesc;
	private int balance;
	private int credit;
	private int debit;
	private String transBank;
	private String transName;
	
	public StatementVO() {
		super();
	}
	
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}


	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTransDate() {
		return transDate;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getTransAccountNo() {
		return transAccountNo;
	}

	public void setTransAccountNo(String transAccountNo) {
		this.transAccountNo = transAccountNo;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getMyDesc() {
		return myDesc;
	}

	public void setMyDesc(String myDesc) {
		this.myDesc = myDesc;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public int getDebit() {
		return debit;
	}

	public void setDebit(int debit) {
		this.debit = debit;
	}

	public String getTransBank() {
		return transBank;
	}

	public void setTransBank(String transBank) {
		this.transBank = transBank;
	}

	public String getTransName() {
		return transName;
	}

	public void setTransName(String transName) {
		this.transName = transName;
	}

	
	
	
	
	
	

}
