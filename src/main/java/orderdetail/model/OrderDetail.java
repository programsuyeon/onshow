package orderdetail.model;

public class OrderDetail {

	private int odnum; // �ֹ� �� ��ȣ
	private int onum; // �ֹ� ��ȣ
	private int pnum; // ��ǰ ��ȣ
	private String oday; // ���� ��¥
	private String otime; // ���� �ð�
	private int oqty; // �ֹ� ����

	public int getOdnum() {
		return odnum;
	}

	public void setOdnum(int odnum) {
		this.odnum = odnum;
	}

	public int getOnum() {
		return onum;
	}

	public void setOnum(int onum) {
		this.onum = onum;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
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
	
}
