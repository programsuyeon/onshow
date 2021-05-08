package qna.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import qna.model.Qna;
import qna.model.QnaDao;
import utility.Paging;

@Controller
public class ListController {
	
	@Autowired
	QnaDao qnaDao;
	
	
	@RequestMapping(value = "/list.qna")
	public ModelAndView doAction(
						@RequestParam(value = "pageNumber", required = false) String pageNumber,
						@RequestParam(value = "category", required = false) String category,
						HttpServletRequest request ) throws UnsupportedEncodingException {
		//�׽�Ʈ
		System.out.println("category: " + category); 
		
		//����������
		int count = qnaDao.getTotalCount(); 
		String url = request.getContextPath() + "/list.qna"; 
		Paging pageInfo = new Paging(pageNumber, "10", count, url);
		 
		//����Ʈ �������� 
		List<Qna> lists = qnaDao.getQnaList(pageInfo, category);
		
		//�𵨿� ��� �ѱ�� 
		ModelAndView mav = new ModelAndView();
		mav.addObject("lists", lists); 
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("list");
		
		return mav;
	}
}