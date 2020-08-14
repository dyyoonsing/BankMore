package kr.ac.kopo.board.vo;

public class BoardVO {
	
	private int no;
	private int groupNo;
	private String title;
	private String writer;
	private String content;
	private String type;
	private int viewCnt;
	private String regDate;
	private int rowNum;
	private String todayCheck;
	private String questioner;
	
	public BoardVO() {
		super();
	}

	
	
	public String getQuestioner() {
		return questioner;
	}


	public void setQuestioner(String questioner) {
		this.questioner = questioner;
	}


	public int getRowNum() {
		return rowNum;
	}



	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}



	public String getTodayCheck() {
		return todayCheck;
	}



	public void setTodayCheck(String todayCheck) {
		this.todayCheck = todayCheck;
	}



	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	
	
	
}
