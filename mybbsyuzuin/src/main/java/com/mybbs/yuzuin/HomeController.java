package com.mybbs.yuzuin;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mybbs.DAO.postDAO;
import com.mybbs.DTO.postDTO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private postDAO postdao = new postDAO();
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "list";
	}
	
	/* 글 쓰기 폼 */
	@RequestMapping(value = "write", method = RequestMethod.GET)
	public String writeForm() {
		return "write";
	}
	
	/* 글쓰기 버튼 눌렀을시 */
	@RequestMapping(value = "writePost", method = RequestMethod.GET)
	public String writePost(HttpServletRequest h,Model m, postDTO dto) {
		postdao.insertPost(dto);
		return "redirect:list";	//	리다이렉트로 
	}
	
	/* 글 리스트 보기 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String viewList(Model m) {
		m.addAttribute("postList",postdao.allPost());
		return "list";
	}
	
	/* 글 상세보기 */
	@RequestMapping(value = "viewPost", method = RequestMethod.GET)
	public String viewPost(@RequestParam("viewNum") int vnum,Model m) {
		m.addAttribute("post",postdao.selectPost(vnum));
		return "viewPost";
	}
	
	/* 글 삭제 */
	@RequestMapping(value = "delPost", method = RequestMethod.GET)
	public String deletePost(@RequestParam("delNum") int dnum) {
		postdao.delPost(dnum);
		return "redirect:list";
	}
	
	/* 글 수정 클릭 */
	@RequestMapping(value = "modPost", method = RequestMethod.GET)
	public String modPostView(@RequestParam("modNum") int mnum,Model m) {
		m.addAttribute("post",postdao.selectPost(mnum));
		return "mod";
	}
	
	/* 글 수정 완료 버튼 클릭 */
	@RequestMapping(value = "modPostOK", method = RequestMethod.GET)
	public String modPostOK(postDTO post) {
		postdao.modPost(post);
		return "redirect:list";
	}
}
