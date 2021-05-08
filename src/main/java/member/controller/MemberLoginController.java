package member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import exhibition.model.Exhibition;
import exhibition.model.ExhibitionDao;
import member.model.Member;
import member.model.MemberDao;

@Controller
public class MemberLoginController {
	
	private final String command = "/loginForm.me";
	private final String getPage = "memberLoginForm";
	
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private ExhibitionDao edao;
	
	@RequestMapping(value=command, method=RequestMethod.GET) 
	public String doAction() {
		return getPage;
	}
	
	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView doAction( 	Model model,
									HttpServletRequest request, 
									HttpServletResponse response,
									HttpSession session) throws IOException {
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		Member member = memberDao.getData(id);
		
		ModelAndView mav = new ModelAndView();
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html; charset=UTF-8");
		
		//���̵� ���� ���
		if(member == null) {
			System.out.println("�������� �ʴ� ȸ��");
			pw.print("<script type='text/javascript'>");
			pw.print("alert('�ش� ���̵� �������� �ʽ��ϴ�.')");
			pw.print("</script>");
			pw.flush();
			mav.setViewName(getPage);
		}
		//���̵� �����ϴ� ���
		else {
			if(password.equals(member.getPassword()) ) {// id, pw��ġ
				session.setAttribute("loginInfo", member);
				session.setAttribute("loginId", member.getId());
				session.setAttribute("loginStyle", member.getStyle());
				
				if(session.getAttribute("loginId").equals("penguin")) {
					mav.setViewName("redirect:/main.jsp");
			
				} else {
					System.out.println("ȸ���� ���̵�� "+member.getId());
					List<Integer> styleNum = memberDao.yourStyle(member.getId());
					 
					if(styleNum.size()==0) {
						System.out.println("��Ÿ��x �ֽ���ǰ3�� ����");
						List<Exhibition> clists = edao.ListExhibition();
						session.setAttribute("clists", clists);
						session.setAttribute("lists", null);
						mav.setViewName("redirect:/user.jsp");

					} else {
						System.out.println("��Ÿ����õ?"+ styleNum);
						List<Exhibition> lists = new ArrayList<Exhibition>();
						 for(int num : styleNum){
							 Exhibition exhibit = edao.DetailExhibition(num); 
							 lists.add(exhibit);
							 System.out.println("����ȸ��: "+exhibit.getName()); 
						}
						session.setAttribute("lists", lists);
						mav.setViewName("redirect:/user.jsp");
					}
				}
			}	
			else {//��� ����ġ
				pw.print("<script type='text/javascript'>");
				pw.print("alert('����� ��ġ���� �ʽ��ϴ�.')");
				pw.print("</script>");
				pw.flush();
				mav.setViewName(getPage);
			}
		}
		return mav;
		
	}
}
