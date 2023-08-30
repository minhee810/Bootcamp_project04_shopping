<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %> 

<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<!DOCTYPE html>
<article>
	<form method="post" name="findId" action="${contextPath}/members/findID">
	<table>
	<tr>
		<td align="right"><label> 이름</label></td>
		<td><input type="text" name="name" value="" required="required"></td>
	</tr>	
	<tr>
		<td align="right"><label> 이메일</label></td>
		<td><input type="text" name="email" value="" required="required"></td>
	</tr>
	<tr>
		<td align="center" colspan="2"><input type="submit" value="아이디 찾기" ></td>
	</tr>
	</table>
	</form>
	<p><p><p><p><p><p><p><p><p><p><p><p>
	
	
	<form method="post" name="findPW" action="${contextPath}/members/findPW">
	<table>
	<tr>
		<td align="right"><label> 아이디</label></td>
		<td><input type="text" name="id" value="" required="required"></td>
	</tr>	
	<tr>
		<td align="right"><label> 이름</label></td>
		<td><input type="text" name="name" value="" required="required"></td>
	</tr>	
	<tr>
		<td align="right"><label> 이메일</label></td>
		<td><input type="text" name="email" value="" required="required"></td>
	</tr>
	<tr>
		<td align="center" colspan="2"><input type="submit" value="비밀번호 찾기" ></td>
	</tr>
	</table>
	</form>
</article>

<%@ include file="../footer.jsp" %>  