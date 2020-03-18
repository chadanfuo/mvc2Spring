<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>게시판</title>
</head>
<body>
	
	<div class="w3-container w3-display-middle w3-card w3-padding">
		<b>글삭제</b><br>
		<form method="POST" name="delForm" action="deletePro">
			<table class="table-bordered" width="360">
				<tr height="30">
					<td align=center><b>비밀번호를 입력해 주세요.</b></td>
				</tr>
				<tr height="30">
					<td align=center>비밀번호 : <input type="password" name="passwd"
						size="8" maxlength="12">
						 <input type="hidden" name="num"  value="${num}">
					</td>				</tr>
				<tr height="30">
					<td align=center><input type="submit" value="글삭제"> <input
						type="button" value="글목록"
						onclick="document.location.href='list'">
					</td>
				</tr></table>		</form>	</div></body></html>
				
				