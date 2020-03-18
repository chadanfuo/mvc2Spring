<%@page import="java.io.FileInputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%  
//webContent에 있는 파일만 다운로드 받을수 있음

  String filename = "/view/hello.jsp";  
  ServletContext context = request.getServletContext();
  String realPath = context.getRealPath(filename);
   
  response.setContentType("APPLICATION/OCTET-STREAM");   
  response.setHeader("Content-Disposition","attachment; filename=\"" 
  + filename + "\"");   
  
  FileInputStream fileInputStream=new FileInputStream(realPath);  
            
  int i;   
  while ((i=fileInputStream.read()) != -1) {  
    out.write(i);   
  }   
  fileInputStream.close();   
%>   