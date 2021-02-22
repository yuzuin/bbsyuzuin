package com.mybbs.yuzuin;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mybbs.DAO.commentDAO;
import com.mybbs.DAO.memberDAO;
import com.mybbs.DAO.postDAO;
import com.mybbs.DAOIF.IF_BBSDAO;
import com.mybbs.DTO.commentDTO;
import com.mybbs.DTO.memberDTO;
import com.mybbs.DTO.postDTO;
import com.mybbs.util.PageNumber;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private postDAO postdao = new postDAO();
	private commentDAO codao = new commentDAO();
	private memberDAO memdao = new memberDAO();
	
	@Inject
	private IF_BBSDAO bbsdao2;  // 인터페이스는 객체를 만들 수 없다. 인터페이스를 구현 받은 객체만 생성이 가능. mybatis 에서는 인터페이스를
    							// 한개의 클래스만 구성할 수 있다.
    							// @Inject는 컨테이너에서 객체를 주입받는다. 그러므로 IF_BBSDAO 객체가 컨테이너에 있어야 한다.
    							// root-context.xml을 수정해야 합니다.
    							// <context:component-scan base-package="com.human.DAOIF"></context:component-scan>
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "redirect:list";
	}
	
	/* 글 쓰기 폼 */
	@RequestMapping(value = "write", method = RequestMethod.GET)
	public String writeForm(HttpServletRequest request) {
		//로그인 검사
		HttpSession session = request.getSession();
		String nowUser = (String)session.getAttribute("userid");
		if(nowUser!=null) {
			System.out.println("나우유저 !=널");
			return "write";
		}else {
			System.out.println("나우유저 널");
			return "redirect:login";
		}
	}
	
	/* 글쓰기 버튼 눌렀을시 */
	@RequestMapping(value = "writePost", method = RequestMethod.GET)
	public String writePost(HttpServletRequest h,Model m, postDTO dto) {
		if(bbsdao2.insertPost(dto)>0) {
			System.out.println("ok리턴");
		}
		return "redirect:list";	//	리다이렉트로 
	}
	
	/* 글 리스트 보기 + 페이징*/
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String viewList(Model m, HttpServletRequest request) {
		//	로그인 검사. 세션을 찾아본다
		HttpSession session = request.getSession();
		String nowUser = (String)session.getAttribute("userid");
		//	page
		int nowPage=1;
		if(request.getParameter("page")!=null) {	//	클라이언트가 클릭하면 파라미터 받음
			nowPage=Integer.valueOf(request.getParameter("page"));
		}
		int pageTotal = postdao.allcount();	//	기존 dao 왜냐하면 현재 mybatis에는 select count(*) from ~ 가 없음 추후 추가
		PageNumber pagemaker = new PageNumber();
		pagemaker.setPage(nowPage);
		pagemaker.setCount(pageTotal);
		
		m.addAttribute("pageMaker",pagemaker);
		m.addAttribute("nowUser",nowUser);
		m.addAttribute("postList",bbsdao2.selectAll(pagemaker));
//		m.addAttribute("postList",postdao.allPost(pagemaker.getNowPageStart(),pagemaker.getPageCnt()));
		return "list";
	}
	
	/* 글 상세보기 + 댓글*/
	@RequestMapping(value = "viewPost", method = RequestMethod.GET)
	public String viewPost(@RequestParam("viewNum") int vnum,Model m) {
		m.addAttribute("post",bbsdao2.selectOne(vnum));
		m.addAttribute("commentList",bbsdao2.selectCommentAll(vnum));
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
	public String modPostOK(postDTO post,Model m) {
		postdao.modPost(post);
		
		m.addAttribute("post",postdao.selectPost(post.getNum()));
		m.addAttribute("commentList",codao.allComment(post.getNum()));
		return "viewPost";
//		return "redirect:list";
	}
	
	/* 댓글 쓰기 */
	@RequestMapping(value = "writeComment", method = RequestMethod.GET)
	public String writeComment(commentDTO dto,Model m) {
		codao.insertComment(dto);
		
		m.addAttribute("post",postdao.selectPost(dto.getPostNum()));
		m.addAttribute("commentList",codao.allComment(dto.getPostNum()));
		return "viewPost";
//		return "redirect:list";
	}
	
	/* 로그인 화면 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	/* 로그인 폼 제출 */
	@RequestMapping(value = "enter", method = RequestMethod.GET)
	public String goLogin(memberDTO dto, HttpServletRequest request) {
		int ok = memdao.login(dto);
		if(ok==1) {
			//	세션 등록
			HttpSession session = request.getSession();
			session.setAttribute("userid", dto.getId()); //	세션에 "userid"로 id 넘겨줌
			System.out.println(dto.getName()+"님 로그인 완료");
			return "redirect:list";
		}else {
			return "login";
		}
	}
	
	/* 로그아웃 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();	//	현재 쿠키값으로 설정되어있는 모든 세션 비움
		return "login";
	}
}
