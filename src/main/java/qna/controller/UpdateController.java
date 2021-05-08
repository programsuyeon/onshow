package qna.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import qna.model.Qna;
import qna.model.QnaDao;
import utility.Paging;

@Controller
public class UpdateController {

	@Autowired
	QnaDao qnaDao;
	
	@RequestMapping(value = "/update.qna", method = RequestMethod.GET)
	public String doAction(
							@RequestParam(value = "no", required = true) int no,
							@RequestParam(value = "category", required = false) String category,
							@RequestParam(value = "pageNumber", required = true) String pageNumber,
							Model model, HttpServletRequest request) {
		
		//����������
		int count = qnaDao.getTotalCount(); 
		String url = request.getContextPath() + "/update.qna";
		Paging pageInfo = new Paging(pageNumber, "10", count, url);
		
		List<Qna> qna_lists = qnaDao.getQnaList(pageInfo, category); //��ü����Ʈ ��������
		Qna qna_update = qnaDao.getQnaByNo(no); //������ ����Ʈ�� ��������
		
		model.addAttribute("qna_update", qna_update);
		model.addAttribute("qna_lists", qna_lists);
		model.addAttribute("pageInfo", pageInfo);
		return "updateform";
	}
	
	@RequestMapping(value = "/update.qna", method = RequestMethod.POST)
	public String doAction(
							@ModelAttribute("qna") @Valid Qna qna, BindingResult result,
							@RequestParam(value = "category", required = false) String category,
							@RequestParam(value = "pageNumber") String pageNumber,
							Model model, HttpServletRequest request) {
		
		System.out.println("�ǳʿ����� Ȯ��");
		
		//����������
		if(result.hasErrors()) {
			//����������
			int count = qnaDao.getTotalCount(); 
			String url = request.getContextPath() + "/update.qna";
			Paging pageInfo = new Paging(pageNumber, "10", count, url);
			//��ü����Ʈ
			List<Qna> qna_lists = qnaDao.getQnaList(pageInfo, category);
			//�𵨴��
			model.addAttribute("qna_update", qna);
			model.addAttribute("qna_lists", qna_lists);
			model.addAttribute("pageInfo", pageInfo);
			
			return "updateform";
		}
		
		qnaDao.updateQna(qna);
		model.addAttribute("pageNumber", pageNumber);
		return "redirect:/list.qna";
	}
}
