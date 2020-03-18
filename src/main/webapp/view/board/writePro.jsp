<%@page import="board.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% request.setCharacterEncoding("UTF-8");%>
<jsp:useBean id="article"  class="board.BoardDataBean">
   <jsp:setProperty name="article" property="*"/>
</jsp:useBean>
<%

   article.setBoardid((String)session.getAttribute("boardid"));
   article.setIp(request.getRemoteAddr());
   BoardDao service = BoardDao.getInstance();
   service.insertArticle(article); 

  // response.sendRedirect(request.getContextPath()+"/board/list.jsp");
%>
<meta http-equiv="Refresh" 
content="0;url=list.jsp" >
</body>
</html>