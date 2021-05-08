package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import member.model.Member;
import member.model.MemberDao;

@Controller
public class MemberFindPwController {
	
	private final String command = "/findPw.me";
	private final String getPage = "findPwForm";
	private final String gotoPage = "memberLoginForm";
			
	@Autowired
	private MemberDao memberDao;
	
	@RequestMapping(value = command, method=RequestMethod.GET)
	public String doAction() {
		return getPage;
	}
	
	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView doAction(Member member, HttpServletResponse response) throws IOException{
		ModelAndView mav = new ModelAndView();
		
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html; charset=UTF-8");
		
		Member findPw = memberDao.findPw(member);
//		System.out.println("���:"+ findPw);
//		out.println("<script>alert('ȸ������ ��й�ȣ��'+ <%=findPw.getPassword()%>+'�Դϴ�.');</script>");
		
		if(findPw == null) {
			System.out.println("��й�ȣ�� �������� �ʽ��ϴ�.");
			pw.print("<script type='text/javascript'>");
			pw.print("alert('��й�ȣ�� �������� �ʽ��ϴ�.')");
			pw.print("</script>");
			pw.flush();
			mav.setViewName(getPage);
		} 
		else {
			System.out.println("���:"+ findPw.getPassword());
			String msg = "ȸ�� ��й�ȣ�� "+findPw.getPassword()+"�Դϴ�.";
			pw.print("<script type='text/javascript'>");
			pw.print("alert('"+msg+"')");
			pw.print("</script>");
			pw.flush();
			mav.setViewName(gotoPage);
		}
		
		return mav;
		
	}
		
	
}