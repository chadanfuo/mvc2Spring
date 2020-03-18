<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<p class="w3-left" style="padding-left: 30px;">>
</p>
<div class="w3-container">
	<span class="w3-center  w3-large"><h3>게시판 (전체 글:${count }):${pageNum}:</h3>	</span>
	<p class="w3-right w3-padding-right-large">	<a href="${pageContext.request.contextPath}/board/write">글쓰기</a></p>
<c:if test="${count==0 }">
	<table class="table-bordered" width="700">
		<tr class="w3-grey"><td align="center">게시판에 저장된 글이 없습니다.</td>
	</table></c:if>	
	
	
	<c:if test="${count!=0 }">
<table class="w3-table-all" >
		<tr class="w3-grey">
			<td align="center" >번호</td>
			<td align="center">제 목</td>
			<td align="center">작성자</td>
			<td align="center">작성일</td>
			<td align="center">조 회</td>
			<td align="center">IP</td>
			<td align="center">filename</td>
			<td align="center">filesize</td>
		</tr>


<c:forEach   var="article"  items="${li}">
      <tr >
			<td align="center" >${number}
			<c:set var="number"  value= "${number-1 }"/>
			</td>
			<td align="center">
				<c:if test="${article.re_level > 0 }">
				<img src="<%=request.getContextPath()%>/images/level.gif" 
				width="${30*article.re_level }" height="16"> 
				   <img src="<%=request.getContextPath()%>/images/re.gif">
				
				</c:if>
				
				<c:if test="${article.re_level == 0 }">
				 <img src="${pageContext.request.contextPath}/images/level.gif" 
				width="${0}" height="16">
				</c:if>
			
			
			<a href="${pageContext.request.contextPath}/board/content?num=${article.num}">
			${article.subject}</a></td>
			
			
			
			
			
			<td align="center">${article.writer}</td>
			<td align="center"><fmt:formatDate value="${article.reg_date}" type="date"/></td>
			<td align="center">${article.readcount}</td>
			<td align="center">${article.ip}</td>
			<td align="center">${article.filename}</td>
			<td align="center">${article.filesize}</td>
		</tr>

</c:forEach> 

</table>
<p align="center">

   <c:if test="${startPage > bottomLine}">
  
   <a href="list?pageNum=${startPage - bottomLine}">[이전]</a>
   </c:if>
   <c:forEach var="i" begin="${startPage}"  end="${endPage}">
   <a href="list?pageNum=${i}">[${i}]</a>
   </c:forEach>



   <c:if test="${endPage < pageCount}">
<a href="list?pageNum=${startPage + bottomLine}">[다음]</a>
  </c:if> 
  
  
  </c:if>




</div>



</body>
</html>