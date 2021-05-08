package member.controller;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;

import cate.model.Cate;
import cate.model.CateDao;
import exhibition.model.Exhibition;
import exhibition.model.ExhibitionDao;
import member.model.Member;
import member.model.MemberDao;

@Controller
public class MemberUpdateController {
	private final String command = "/update.me";
	private final String getPage = "memberUpdateForm";
	private final String gotoPage = "redirect:/info.me";
	
	@Autowired
	private CateDao cdao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private ExhibitionDao edao;
	
	@RequestMapping(value=command, method=RequestMethod.GET)
	public String doAction(@RequestParam(value="num")int num, HttpSession session, Model model) {
		List<Cate> list = cdao.ListCate();
		Member member = memberDao.getData((String)session.getAttribute("loginId"));
		model.addAttribute("member", member);
		model.addAttribute("list", list);
		
//		Member member = MemberDao.getDataByNum(num);
//		model.addAttribute("member",member);
		return getPage;
	}
	
	@RequestMapping(value=command,method=RequestMethod.POST)
	public ModelAndView doAction(@ModelAttribute("member") @Valid Member member, BindingResult result, HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		System.out.println("num:"+member.getYear());
		
		if(result.hasErrors()) {
			System.out.println("��ȿ�� �˻� ����");
			List<Cate> list = cdao.ListCate();
			mav.addObject("list", list);
			mav.setViewName(getPage);
			return mav;
		}else {
			System.out.println("������Ʈ �Ƴ�?");

			int cnt = memberDao.updateData(member);
			mav.setViewName(gotoPage);
			if(cnt==1) {
			System.out.println("������Ʈ �ƴ�");
			List<Integer> styleNum = memberDao.yourStyle(member.getId());
			session.setAttribute("loginStyle", member.getStyle());
			session.setAttribute("loginInfo", member);
			
			if(styleNum.size()==0) {
				System.out.println("��Ÿ��x �ֽ���ǰ3�� ����");
				List<Exhibition> clists = edao.ListExhibition();
				session.setAttribute("clists", clists);
				session.setAttribute("lists", null);
				
			} else {
				System.out.println("��Ÿ����õ?"+ styleNum);
				List<Exhibition> lists = new ArrayList<Exhibition>();
				 for(int num : styleNum){
					 Exhibition exhibit = edao.DetailExhibition(num); 
					 lists.add(exhibit);
					 System.out.println("����ȸ��: "+exhibit.getName()); 
				}
				session.setAttribute("lists", lists);
			}
			return mav;
			}//int==1 if��
			return mav;
			
		}//������Ʈ�Ǿ���?
	}
}