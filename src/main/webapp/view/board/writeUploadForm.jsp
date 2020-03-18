<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <html>
<head><title>게시판</title></head>
<p class="w3-left"  style="padding-left:30px;"></p>
<div class="w3-container">
<b>글쓰기</b><br>

<form method="post" name="writeform"
action="${pageContext.request.contextPath}/board/write" >
<input type="hidden"  name="num" value="${article.num }">
<input type="hidden"  name="ref" value="${article.ref }">
<input type="hidden"  name="re_step" value="${article.re_step }">
<input type="hidden"  name="re_level" value="${article.re_level }">
<table class="w3-table-all"  style="width:70%;" >
 
   <tr>    <td align="right" colspan="2" >  
    <a href="${pageContext.request.contextPath}/board/list"> 글목록</a> </td> </tr>
   <tr>    <td  width="70"   align="center">이 름</td>
    <td  width="330">
       <input type="text" size="10" maxlength="10" name="writer"></td>  </tr>
  <tr>    <td  width="70"   align="center" >제 목    </td>    <td width="330">
       <c:if test="${article.num==0}">
        <input type="text" size="40" maxlength="50" name="subject"></c:if>
       <c:if test="${article.num!=0}">
       
        <input type="text" size="40" maxlength="50" name="subject"  value="답글>">
        
        </c:if>
          </td>  </tr>
  <tr>    <td  width="70"   align="center">Email</td>
    <td  width="330"><input type="text" size="40" maxlength="30" name="email" ></td></tr>
  
  <tr>   <td  width="70"   align="center" >내 용</td>
    <td  width="330" ><textarea name="content" rows="13" cols="40"></textarea> </td></tr>
 
 
 
 
  <tr>    <td  width="70"   align="center">file</td>
    <td  width="330"><input type="file" size="40" maxlength="30" 
    name="uploadfile" ></td></tr>
  
 
 
 
 
  
  <tr>    <td  width="70"   align="center" >비밀번호</td>
    <td  width="330" ><input type="password" size="8" maxlength="12" name="passwd"> 
	 </td>  </tr><tr>      
 <td colspan=2  align="center">   <input type="submit" value="글쓰기" >  
  <input type="reset" value="다시작성">
  <input type="button" value="목록보기" OnClick="window.location='${pageContext.request.contextPath}/board/list'">
</td></tr></table> </form>  </div> </body></html> 
     
