package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import member.model.Member;
import member.model.MemberDao;

@Controller
public class MemberFindIdController {
	
	private final String command = "/findId.me";
	private final String getPage = "findIdForm";
	private final String gotoPage = "memberLoginForm";
	
	@Autowired
	private MemberDao memberDao;
	
	@RequestMapping(value = command, method= RequestMethod.GET)
	public String doAction() {
		return getPage;
	}
	
	@RequestMapping(value=command, method=RequestMethod.POST)
	public String doAction(Member member,  
							HttpServletResponse response) throws IOException {
		
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html; charset=UTF-8");
		
		Member findId = memberDao.findId(member);
		
		//System.out.println("ȸ������:" + findId);

		if(findId == null) { // ��ȸ��
			System.out.println("���̵� �������� �ʽ��ϴ�.");
			pw.print("<script type='text/javascript'>");
			pw.print("alert('�ش� ���̵� �������� �ʽ��ϴ�.')");
			pw.print("</script>");
			pw.flush();
			return getPage;
		} 
		else { // ȸ��
			String msg = "ȸ�� ���̵�� "+findId.getId()+"�Դϴ�.";
			System.out.println("���̵�~:" + findId.getId());
			pw.print("<script type='text/javascript'>");
			pw.print("alert('"+msg+"')");
			pw.print("</script>");
			pw.flush();
			return gotoPage;
		}		
		
	}
}