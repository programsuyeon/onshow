package inquiry.model;

import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class Inquiry{
	private int num;
	private String id;
	
	@Pattern(regexp = "^[0-9]{4,8}$", message = "�غ�й�ȣ�� �ٽ� Ȯ���ϼ���") 
	private String pw;
	
	@NotBlank(message = "��ī�װ� ���� ����")
	private String category;
	
	@NotBlank(message = "�������� �Է����ּ���")
	private String title;
	
	@NotBlank(message = "�س����� �Է����ּ���")
	private String content;
	
	private Date regdate;
	private int viewcnt;
	private int ref;
	private int restep;
	private String ip;
	
	public Inquiry() {
		super();
	}

	public Inquiry(int num, String id, String pw, String category, String title, String content, Date regdate,
			int viewcnt, int ref, int restep, String ip) {
		super();
		this.num = num;
		this.id = id;
		this.pw = pw;
		this.category = category;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.viewcnt = viewcnt;
		this.ref = ref;
		this.restep = restep;
		this.ip = ip;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getRestep() {
		return restep;
	}
	public void setRestep(int restep) {
		this.restep = restep;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}	
}
