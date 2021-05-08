package inquiry.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import inquiry.model.Inquiry;
import inquiry.model.InquiryDao;

@Controller
public class InqUpdateController {

	@Autowired
	InquiryDao inqDao;
	
	@RequestMapping(value = "/update.inq", method = RequestMethod.GET)
	public String doAction(
							@RequestParam(value = "num") int num,
							@RequestParam(value = "ref") int ref,
							@RequestParam(value = "restep") int restep,
							@RequestParam(value = "pageNumber") int pageNumber,
							Model model, HttpServletResponse response) throws IOException {
		
		int count = inqDao.getRefCount(ref);
		
		if(count>1) { //��۴޸� ��� �Խñ� ��
			if(restep==0) { //���� -> �����Ұ�
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('�亯�� �޸� �Խñ��� ������ �� �����ϴ�.'); history.go(-2);</script>");
				out.flush();
			} else { // �亯 -> �������� (�����ڸ�)
				Inquiry inq = inqDao.getInqByNum(num);
				model.addAttribute("inq", inq);
				model.addAttribute("pageNumber", pageNumber);
				return "reply_update";
			}
		} else { //����� �ȴ޸� ����
			Inquiry inq = inqDao.getInqByNum(num);
			model.addAttribute("inq", inq);
			model.addAttribute("pageNumber", pageNumber);
		} //if��
		
		return "inquiry_update_form";
	}
	
	
	@RequestMapping(value = "/update.inq", method = RequestMethod.POST)
	public String doAction(
							@ModelAttribute("inq") @Valid Inquiry inq, BindingResult result,
							@RequestParam(value = "pwcheck", required = false) String pwcheck,
							@RequestParam(value = "pageNumber") int pageNumber,
							Model model, HttpServletResponse response, HttpSession session) throws IOException {
		
		if(result.hasErrors()){
			model.addAttribute("inq", inq);
			model.addAttribute("pwcheck", pwcheck);
			model.addAttribute("pageNumber", pageNumber);
			return "inquiry_update_form";
		}
		
		//�����ڴ� �����ϴµ� ��й�ȣ �ʿ�x
		if(!session.getAttribute("loginId").equals("penguin")) {
			//��й�ȣ ��ġ���� �ʴ� ���
			if(!inq.getPw().equals(pwcheck)) { 
				response.setContentType("text/html; charset=UTF-8");
			    PrintWriter out = response.getWriter();
			    out.println("<script>alert('��й�ȣ�� ��ġ���� �ʽ��ϴ�.'); history.go(-1); document.getElementById(pwcheck).focus();</script>");
			    out.flush();
			//��ġ�ϴ� ���
			} else {
				inqDao.updateInq(inq);
				model.addAttribute("num", inq.getNum());
				model.addAttribute("pageNumber", pageNumber);
			}
		} else {
			System.out.println("������");
			inqDao.updateInq(inq);
			model.addAttribute("num", inq.getNum());
			model.addAttribute("pageNumber", pageNumber);
		} //if��
		
		return "redirect:/listdetail.inq";
	}
}
