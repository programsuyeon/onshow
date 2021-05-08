package order.model;

import java.util.HashMap;
import java.util.Map;

public class CartList { // ��ٱ���

	private Map<String, Integer> cart = null; // ��ǰ��ȣ+��¥+�ð�, �ֹ� ����
	
	public CartList() {
		cart = new HashMap<String, Integer>();
	}
	
	public void addOrder(String pInfo, int oqty) {
		// ��ٱ��Ͽ� ����ִ� ��ǰ���� Ȯ��
		if(cart.containsKey(pInfo)) {
			oqty += cart.get(pInfo);
		}
		cart.put(pInfo, oqty);
	}
	
	public Map<String, Integer> getAllOrderLists() {
		return cart;
	}
	
	public int deleteOrder(String pInfo) {
		cart.remove(pInfo);
		return cart.size();
	}
	
}
