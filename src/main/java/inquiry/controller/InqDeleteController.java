package inquiry.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import inquiry.model.InquiryDao;

@Controller
public class InqDeleteController {

	@Autowired
	InquiryDao inqDao;
	
	@RequestMapping(value = "/delete.inq")
	public String doAction(
							@RequestParam(value = "num") int num,
							@RequestParam(value = "ref") int ref,
							@RequestParam(value = "restep") int restep,
							@RequestParam(value = "pageNumber") int pageNumber,
							Model model, HttpServletResponse response
							) throws IOException {
		
		int count = inqDao.getRefCount(ref);
		
		if(count>1) { //��۴޸� ����+�亯 �Խñ� ��
			if(restep==0) { // ���� -> ���� �� �� ����
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('�亯�� �޸� �Խñ��� ������ �� �����ϴ�.'); history.go(-2);</script>");
				out.flush();
			} else { // �亯 -> ���� ���� (�����ڸ�)
				inqDao.deleteInq(num);
				model.addAttribute("pageNumber", pageNumber);	
			}
		} else { //�亯�� �޸��� ���� �Խñ�
			inqDao.deleteInq(num);
			model.addAttribute("pageNumber", pageNumber);	
		} //if��
		
		return "redirect:/list.inq";
	}
}
