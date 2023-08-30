<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../header.jsp" %> 
    <c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
if(${findID != null} && ${findPW == null}){
	alert("요청하신 아이디 입니다  : \" ${findID} \"");
	location.href="${contextPath}/members/login_form";
	
}else if(${findID == null} && ${findPW != null}){
	alert("요청하신 비밀번호 입니다 : \" ${findPW} \"");
	location.href="${contextPath}/members/login_form";
	
}else{
	alert("정보와 일치하는 내용이 존재하지 않습니다");
	location.href="${contextPath}/members/findIdPwd";
}
	
</script>
</head>
<body>

</body>
</html>