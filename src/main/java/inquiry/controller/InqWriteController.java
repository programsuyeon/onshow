package inquiry.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
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
public class InqWriteController {

	@Autowired
	InquiryDao inqDao;
	
	@RequestMapping(value = "/inqwrite.inq", method = RequestMethod.GET)
	public String doAction(HttpSession session) {
		if(session.getAttribute("loginId")==null) {
			return "redirect:/loginForm.me";
		}
		return "inquiry_form";
	}
	
	@RequestMapping(value = "/inqwrite.inq", method = RequestMethod.POST)
	public String doAction(
							@ModelAttribute("inq") @Valid Inquiry inq, BindingResult result,
							@RequestParam("pwcheck") String pwcheck,
							HttpServletResponse response, HttpServletRequest request, HttpSession session,
							Model model) throws IOException {
		
		if(result.hasErrors()){
			model.addAttribute("inq", inq);
			return "inquiry_form";
		}
		
		//��й�ȣ ��ġ���� �ʴ� ���
		if(!inq.getPw().equals(pwcheck)) { 
			response.setContentType("text/html; charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    out.println("<script>alert('��й�ȣ�� ��ġ���� �ʽ��ϴ�.'); history.go(-1); document.getElementById(pwcheck).focus();</script>");
		    out.flush();
		    
		//��ġ�ϴ� ���
		} else {
			
			//������ ����
			String ip = request.getRemoteAddr();
			inq.setIp(ip);
			
			//���̵�� ���� ��Ű��
			String id = (String) session.getAttribute("loginId");
			inq.setId(id);
			
			inqDao.insertInquiry(inq);
		}
		return "redirect:/list.inq";
	}
}


