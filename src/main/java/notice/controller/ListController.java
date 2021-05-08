package notice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import notice.model.Notice;
import notice.model.NoticeDao;
import utility.Paging;

@Controller
public class ListController {
	
	@Autowired
	NoticeDao noticeDao;
	
	@RequestMapping(value = "/list.nt")
	public ModelAndView doAction(
							@RequestParam(value = "pageNumber", required = false) String pageNumber,
							@RequestParam(value = "pageSize", required = false) String pageSize,
							HttpServletRequest request) {
		//����������
		int count = noticeDao.getTotalCount();
		String url = request.getContextPath() + "/list.nt";
		Paging pageInfo = new Paging(pageNumber, pageSize, count, url);
		  
		//����Ʈ ��������
		List<Notice> lists = noticeDao.getNoticeList(pageInfo); //������ ����Ʈ ��������
		List<Notice> imp_lists = noticeDao.getImpNotice(); //imp�� 1�� �������� ��������
		
		//�𵨿� ��� �ѱ��
		ModelAndView mav = new ModelAndView();
		mav.addObject("lists", lists);
		mav.addObject("imp_lists", imp_lists);
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("list");
		return mav;
	}
}