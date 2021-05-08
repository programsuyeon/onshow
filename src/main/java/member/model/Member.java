package member.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class Member {
	
	
	private int num;

	@NotBlank(message = "���̵� �Է����ּ���.")
	private String id;
	
	@NotBlank(message = "��й�ȣ�� �Է����ּ���.")
	private String password;
	
	@NotBlank(message = "��й�ȣ�� Ȯ�����ּ���.")
	private String repassword;
	
	@NotBlank(message = "�̸��� �Է����ּ���.")
	private String name;
	
	@NotEmpty(message = "��������� �Է����ּ���.")
	private String year;
	@NotEmpty(message = "")
	private String month;
	@NotEmpty(message = "")
	private String day;
	
	@NotBlank(message = "�̸����� �Է����ּ���.")
	private String email;
	
	@NotNull(message = "�� �ڵ��� ��ȣ�� �Է����ּ���.")
	private Integer hp1;
	
	@NotNull(message = "�߰� �ڵ��� ��ȣ�� �Է����ּ���.")
	private Integer hp2;
	
	@NotNull(message = "�� �ڵ��� ��ȣ�� �Է����ּ���.")
	private Integer hp3;
	
	@NotBlank(message = "�ּҸ� �Է����ּ���.")
	private String add1;
	@NotBlank(message = "���ּҸ� �Է����ּ���.")
	private String add2;
	
	@NotEmpty(message = "������ �������ּ���.")
	private String style;
	
	@NotEmpty(message = "���� ī�װ��� �������ּ���.")
	private String cat;
	
	@NotNull(message = "���̸� �Է����ּ���.")
	private Integer age;
	
	@NotEmpty(message = "������ �������ּ���.")
	private String gender;
	
	public Member() {
		super();
	}

	public Member(int num, String id, String password, String repassword, String name, String year, String month,
			String day, String email, Integer hp1, Integer hp2, Integer hp3, String add1, String add2, String style,
			String cat, Integer age, String gender) {
		super();
		this.num = num;
		this.id = id;
		this.password = password;
		this.repassword = repassword;
		this.name = name;
		this.year = year;
		this.month = month;
		this.day = day;
		this.email = email;
		this.hp1 = hp1;
		this.hp2 = hp2;
		this.hp3 = hp3;
		this.add1 = add1;
		this.add2 = add2;
		this.style = style;
		this.cat = cat;
		this.age = age;
		this.gender = gender;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getHp1() {
		return hp1;
	}

	public void setHp1(Integer hp1) {
		this.hp1 = hp1;
	}

	public Integer getHp2() {
		return hp2;
	}

	public void setHp2(Integer hp2) {
		this.hp2 = hp2;
	}

	public Integer getHp3() {
		return hp3;
	}

	public void setHp3(Integer hp3) {
		this.hp3 = hp3;
	}

	public String getAdd1() {
		return add1;
	}

	public void setAdd1(String add1) {
		this.add1 = add1;
	}

	public String getAdd2() {
		return add2;
	}

	public void setAdd2(String add2) {
		this.add2 = add2;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
