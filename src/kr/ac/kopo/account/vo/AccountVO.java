package kr.ac.kopo.account.vo;

public class AccountVO {
	
	private String accountNo;
	private String bankName;
	private String name;
	private String password;
	private int balance;
	private String alias;
	private String openCheck;
	private String openDate;
	private String id;
	
	public AccountVO() {
		super();
	}
	
	

	public AccountVO(String accountNo, String bankName, String name, String password, int balance, String alias,
			String openCheck, String openDate, String id) {
		super();
		this.accountNo = accountNo;
		this.bankName = bankName;
		this.name = name;
		this.password = password;
		this.balance = balance;
		this.alias = alias;
		this.openCheck = openCheck;
		this.openDate = openDate;
		this.id = id;
	}



	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getOpenCheck() {
		return openCheck;
	}

	public void setOpenCheck(String openCheck) {
		this.openCheck = openCheck;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
	

}
