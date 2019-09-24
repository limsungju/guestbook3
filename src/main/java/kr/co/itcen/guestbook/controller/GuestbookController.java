package kr.co.itcen.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.itcen.guestbook.dao.GuestbookDao;
import kr.co.itcen.guestbook.vo.GuestbookVo;

@Controller
public class GuestbookController {
	// applicationContext.xml 설정 -> GuestbookDao에 annotation 설정
	@Autowired
	private GuestbookDao guestbookDao;
	
	
	@RequestMapping({"","/list"}) // 병렬로 처리 가능
	//@RequestMapping("/list")
	public String index(Model model) {
		List<GuestbookVo> list = guestbookDao.getList();
		model.addAttribute("list", list);
		return "/WEB-INF/views/index.jsp";
	}
	
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no") Long no, Model model) {
		model.addAttribute("no", no);
		return "/WEB-INF/views/delete.jsp";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(@ModelAttribute GuestbookVo vo) {
		guestbookDao.delete(vo);
		return "redirect:/";
	}
}
