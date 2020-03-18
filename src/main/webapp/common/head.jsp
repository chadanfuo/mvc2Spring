<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>쌍용프로젝트</title>
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css"></head>
<style>a { text-decoration: none; }</style>
<script>
  window.onload = function() {
	 
 var atag = document.getElementsByTagName("a");
 var url = document.location.href.split("/"); 
 alert(url[url.length-1]);
 if (url[url.length-1]=="main"){
	 atag[0].className +=" w3-red";  } 
 else if (url[url.length-1]=="inputForm"){
	 atag[4].className +=" w3-red";  }
 else if (url[url.length-1]=="loginForm"){
	 atag[7].className +=" w3-red";  }
 else if (url[url.length-1]=="logout"){
	 atag[8].className +=" w3-red";  }
 else if (url[url.length-1]=="bar_sample"){
	 atag[5].className +=" w3-red";  }
 else if (url[url.length-1]=="awesomeCloud"){
	 atag[6].className +=" w3-red";  }
 else if (1==<%=session.getAttribute("boardid")%>){
	 atag[1].className +=" w3-red";  }
 else if (2==<%=session.getAttribute("boardid")%>){
	 atag[2].className +=" w3-red";  }
 else if (3==<%=session.getAttribute("boardid")%>){
	 atag[3].className +=" w3-red";  }
  }
</script>

<body>
<div class="w3-bar w3-green  w3-large">
  <a href="<%=request.getContextPath()%>/member/main"  
  class="w3-bar-item w3-button">HOME</a>
  <a href="<%=request.getContextPath()%>/board/list?boardid=1" class="w3-bar-item w3-button">공지사항</a>
  <a href="<%=request.getContextPath()%>/board/list?boardid=2" class="w3-bar-item w3-button">자유게시판</a>
  <a href="<%=request.getContextPath()%>/board/list?boardid=3" class="w3-bar-item w3-button">Q&A</a>
  <a href="<%=request.getContextPath()%>/member/inputForm"  
    class="w3-bar-item w3-button">회원가입</a>
  <a href="<%=request.getContextPath()%>/chart/bar_sample"    class="w3-bar-item w3-button">BarGraph</a>
  <a href="<%=request.getContextPath()%>/chart/awesomeCloud"    class="w3-bar-item w3-button">WordCloud</a>
  <% if (session.getAttribute("memId")==null)  {  %>
  <a href="<%=request.getContextPath()%>/member/loginForm"  
    class="w3-bar-item w3-button">로그인</a>
    
   <% } else { %> 
   <span class="w3-bar-item w3-button"><%=session.getAttribute("memId") %></span>
   <a href="<%=request.getContextPath()%>/member/logout"  
    class="w3-bar-item w3-button">로그아웃</a>
  <%} %>
 <span><%=session.getAttribute("boardid") %></span>
</div>






