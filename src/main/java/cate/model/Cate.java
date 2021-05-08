package cate.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class Cate {
	private int num;
	@NotBlank(message = "�Է´����Դϴ�.")
	private String kind;
	@NotBlank(message = "�Է´����Դϴ�.")
	@Pattern(regexp = "[A-Z0-9]*", message = "�빮�ڿ� ������������ �Է����ּ���.")
	private String code;
	
	public Cate() {
		super();
	}
	
	public Cate(int num, String kind, String code) {
		super();
		this.num = num;
		this.kind = kind;
		this.code = code;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
