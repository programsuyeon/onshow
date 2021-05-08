package reserve.controller;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import member.model.Member;
import order.model.CartList;
import order.model.OrderDao;
import orderdetail.model.OrderDetail;
import orderdetail.model.OrderDetailDao;

@Controller
public class CartCalculatorController {

	private final String command = "/account.re";
	private final String gotoPage = "redirect:/order.re";
	
	@Autowired
	OrderDao odao; // �ֹ� �߰�
	
	@Autowired
	OrderDetailDao oddao; // �ֹ� �� �߰�
	
	@RequestMapping(command)
	public String doAction(HttpSession session, HttpServletResponse response) {
		CartList cart = (CartList) session.getAttribute("cart"); // ��ٱ��� ȣ��
		Map<String, Integer> mapCart = cart.getAllOrderLists();
		
		Set<String> keys = mapCart.keySet();
		
		Member mb = (Member) session.getAttribute("loginInfo");
		String mid = mb.getId(); // ȸ�� ���̵�
		//System.out.println("cart Cal => "+mid);
		odao.insertData(mid); // �ֹ� ���̺�(���Ÿ��)�� ���
		
		int maxOnum = odao.getMaxOrderOnum(); // �� ū(������) �ֹ� ��ȣ
		//System.out.println("cart Cal �ֹ���ȣ => "+maxOnum);
		for(String key : keys) {
			String[] info = key.split("/");
			OrderDetail odetail = new OrderDetail(); // �ֹ� �� ���̺� ���� ���� ó��
			odetail.setOnum(maxOnum); // ���� ����ϴ� �ֹ� ��ȣ
			odetail.setPnum(Integer.parseInt(info[0]));
			odetail.setOday(info[1]);
			odetail.setOtime(info[2]);
			odetail.setOqty(mapCart.get(key));
			//System.out.println("orderDetail : "+odetail);
			oddao.insertData(odetail); // �ֹ� �� ���̺� �߰�
		}
		
		session.setAttribute("cart", null); // ��ٱ��� ����
		
		return gotoPage;
	}
	
}
