package reserve.controller;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import exhibition.model.Exhibition;
import exhibition.model.ExhibitionDao;
import order.model.CartList;
import order.model.ShoppingInfo;

@Controller
public class CartListContoller { // ��ٱ��� ��� ���(��ǰ �߰�, ��ٱ��� ��ũ)

	private final String command = "/cart.re";
	private final String getPage = "cartList";

	@Autowired
	ExhibitionDao edao; // ��ǰ ���� ��������
	
	@RequestMapping(command)
	public String doAction(HttpSession session) {
		if(session.getAttribute("loginInfo") == null) {
			session.setAttribute("destination", "redirect:"+command);
			return "redirect:/loginForm.me";		
		}
		
		CartList cart = (CartList) session.getAttribute("cart"); // ��ٱ��� ȣ��
		//System.out.println("controller => "+cart);
		ArrayList<ShoppingInfo> cartList = new ArrayList<ShoppingInfo>(); // ��ٱ��� ���� ����
		
		int totalAmount = 0;
		
		if(cart != null) {
			Map<String, Integer> mapCart = cart.getAllOrderLists();
			
			Set<String> keys = mapCart.keySet();
			for(String key : keys) {
				String[] info = key.split("/");
				int pnum = Integer.parseInt(info[0]);
				Exhibition exhi = edao.DetailExhibition(pnum);
				
				ShoppingInfo sh = new ShoppingInfo();
				
				sh.setPnum(pnum);
				
				String pname = exhi.getName() +" ( "+info[1] + " / " + info[2] + ":00 )";
				sh.setPname(pname);
				sh.setOqty(mapCart.get(key));
				sh.setPrice(exhi.getPrice());  
				sh.setAmount(sh.getOqty() * sh.getPrice());
				
				cartList.add(sh);
				
				totalAmount += sh.getAmount();
			}
		}
		
		session.setAttribute("cartList", cartList);
		session.setAttribute("totalAmount", totalAmount);
		
		return getPage;
	}
	
}
