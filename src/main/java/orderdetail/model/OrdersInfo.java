package orderdetail.model;
// �ֹ� �� ���� Ȯ��
public class OrdersInfo {

	private int pnum;
	private String pname;
	private String pimg;
	private String oday; // ���� ��¥
	private String otime; // ���� �ð�
	private int oqty;
	private int price;
	private int amount;

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPimg() {
		return pimg;
	}

	public void setPimg(String pimg) {
		this.pimg = pimg;
	}

	public String getOday() {
		return oday;
	}

	public void setOday(String oday) {
		this.oday = oday;
	}

	public String getOtime() {
		return otime;
	}

	public void setOtime(String otime) {
		this.otime = otime;
	}

	public int getOqty() {
		return oqty;
	}

	public void setOqty(int oqty) {
		this.oqty = oqty;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
