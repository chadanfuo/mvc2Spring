package controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import model.BoardDataBean;
import mybatis.MybatisBoardDBBean;

@Controller
@RequestMapping("/board/")
public class BoardController {
	String ip;
	String boardid;
	String pageNum;
	
	@Autowired
	MybatisBoardDBBean dbPro;

	@ModelAttribute
	public void initProcess(HttpServletRequest request) {
		// TODO Auto-generated method stub
		System.out.println("=================initProcess");
		
		HttpSession session =request.getSession();
		ip=request.getRemoteAddr();

	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (request.getParameter("boardid") != null) {
			session.setAttribute("boardid", request.getParameter("boardid"));
			session.setAttribute("pageNum", 1);
		}

		boardid = (String) session.getAttribute("boardid");

		if (boardid == null) {
			boardid = "1";
			session.setAttribute("boardid", "1");

		}

	}

	// board/list -> board_list
	@RequestMapping(value = "list")
	public String board_list(HttpServletRequest request) {

		System.out.println("=================board_list");
		HttpSession session = request.getSession();
		int pageSize = 3;
		int num = 9;
		int currentPage = 1;
		if (session.getAttribute("pageNum") == null) {
			session.setAttribute("pageNum", 1);
			System.out.println("2 " + currentPage);
		}
		try {
			currentPage = Integer.parseInt(request.getParameter("pageNum"));
			session.setAttribute("pageNum", currentPage);
			System.out.println("1 " + currentPage);
		} catch (Exception e) {

		}

		currentPage = (int) session.getAttribute("pageNum");

		String boardid = (String) session.getAttribute("boardid");

		int count = dbPro.getArticleCount(boardid);

		System.out.println(count);
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);

		if (currentPage > pageCount) {
			currentPage = pageCount;
			session.setAttribute("pageNum", currentPage);
		}

		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;
		// int endRow = currentPage * pageSize;

		System.out.println(startRow + ":" + endRow + ":" + boardid);
		List li = dbPro.getArticles(startRow, endRow, boardid);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int number = count - (currentPage - 1) * pageSize;

		int bottomLine = 3;

		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount)
			endPage = pageCount;

		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startRow", startRow);
		request.setAttribute("endRow", endRow);
		request.setAttribute("count", count);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("number", number);
		request.setAttribute("bottomLine", bottomLine);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);

		request.setAttribute("li", li);

		return "board/list";
	}

	@RequestMapping(value = "write", method = RequestMethod.GET)
	public String board_writeForm(@ModelAttribute("article") BoardDataBean article) {
		return "board/writeUploadForm";
	}

	@RequestMapping(value = "write", method = RequestMethod.POST)
	public String board_writePro(BoardDataBean article) {
		article.setIp(ip);
		dbPro.insertArticle(article);
		return "redirect:/board/list?pageNum="+pageNum;
	}
	

	@RequestMapping("content")
	public ModelAndView board_content(int num) {

		ModelAndView mv = new ModelAndView();
		BoardDataBean article = dbPro.getArticle(num);

		mv.addObject("article", article);
		mv.setViewName("board/content");

		return mv;
	}

	public String board_deleteForm(HttpServletRequest request, HttpServletResponse res) {
		// TODO Auto-generated method stub

		request.setAttribute("num", request.getParameter("num"));

		return "/view/board/deleteForm.jsp";
	}

	public String board_deletePro(HttpServletRequest request, HttpServletResponse res) throws Exception {
		int num = Integer.parseInt(request.getParameter("num"));
		String passwd = request.getParameter("passwd");

		int check = dbPro.deleteArticle(num, passwd);

		request.setAttribute("check", check);
		return "/view/board/updatePro.jsp";
	}

	/*
	 * @RequestMapping(value="write" , method=RequestMethod.POST) public String
	 * board_writePro(HttpServletRequest request, HttpServletResponse res)
	 * throws Exception { String realFolder = ""; String saveFolder =
	 * "uploadFile"; String encType = "UTF-8";
	 * System.out.println("******************8888"+request.getParameter("writer"
	 * )); int maxSize = 10 * 1024 * 1024;//10M ServletContext context =
	 * request.getServletContext(); realFolder =
	 * context.getRealPath(saveFolder); try { MultipartRequest multi = new
	 * MultipartRequest(request, realFolder, maxSize, encType, new
	 * DefaultFileRenamePolicy()); BoardDataBean article = new BoardDataBean();
	 * Enumeration files = multi.getFileNames();
	 * 
	 * if (files.hasMoreElements()) { String name = (String)
	 * files.nextElement(); File file = multi.getFile(name); if (file != null) {
	 * article.setFilename(file.getName()); article.setFilesize((int)
	 * file.length()); } else { article.setFilename(""); article.setFilesize(0);
	 * } }
	 * 
	 * article.setNum(Integer.parseInt(multi.getParameter("num")));
	 * article.setRef(Integer.parseInt(multi.getParameter("ref")));
	 * article.setRe_step(Integer.parseInt(multi.getParameter("re_step")));
	 * article.setRe_level(Integer.parseInt(multi.getParameter("re_level")));
	 * article.setWriter(multi.getParameter("writer"));
	 * article.setContent(multi.getParameter("content"));
	 * article.setPasswd(multi.getParameter("passwd"));
	 * article.setSubject(multi.getParameter("subject"));
	 * article.setEmail(multi.getParameter("email"));
	 * article.setBoardid((String)
	 * request.getSession().getAttribute("boardid"));
	 * article.setIp(request.getRemoteAddr()); MybatisBoardDBBean service =
	 * MybatisBoardDBBean.getInstance(); service.insertArticle(article); } catch
	 * (Exception e) { e.printStackTrace(); }
	 * 
	 * 
	 * 
	 * return "redirect:/board/list"; }
	 */

	public String board_updateForm(HttpServletRequest request, HttpServletResponse res) {
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");

		BoardDataBean article = dbPro.updateGetArticle(num);

		request.setAttribute("article", article);

		return "/view/board/updateForm.jsp";
	}

	public String board_updatePro(HttpServletRequest request, HttpServletResponse res) throws Exception {

		BoardDataBean article = new BoardDataBean();
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setWriter(request.getParameter("writer"));
		article.setContent(request.getParameter("content"));
		article.setPasswd(request.getParameter("passwd"));
		article.setSubject(request.getParameter("subject"));
		article.setEmail(request.getParameter("email"));
		System.out.println(article);

		int check = dbPro.updateArticle(article);
		System.out.println(check);
		request.setAttribute("check", check);

		return "/view/board/updatePro.jsp";
	}

}