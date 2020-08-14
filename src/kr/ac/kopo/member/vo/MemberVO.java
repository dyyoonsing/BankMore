package kr.ac.kopo.member.vo;

public class MemberVO {

	private String id;
	private String name;
	private String password;
	private String email;
	private String tel;
	private String post;
	private String basicAddr;
	private String detailAddr;
	private String type;
	private String regDate;
	private String kakaoId;
	
	public MemberVO() {
		
	}
	
	public MemberVO(String id, String name, String password, String email, String tel, String post, String basicAddr,
			String detailAddr, String type, String regDate, String kakaoId) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.tel = tel;
		this.post = post;
		this.basicAddr = basicAddr;
		this.detailAddr = detailAddr;
		this.type = type;
		this.regDate = regDate;
		this.kakaoId = kakaoId;
	}

	public String getKakaoId() {
		return kakaoId;
	}




	public void setKakaoId(String kakaoId) {
		this.kakaoId = kakaoId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getBasicAddr() {
		return basicAddr;
	}

	public void setBasicAddr(String basicAddr) {
		this.basicAddr = basicAddr;
	}

	public String getDetailAddr() {
		return detailAddr;
	}

	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}

	public String getType() {
		return type;
	}

	public void setType(String string) {
		this.type = string;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	
	
	
	
}
