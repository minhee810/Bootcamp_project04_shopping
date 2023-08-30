<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%> 

<%@ include file="/WEB-INF/views/header.jsp" %>  
<%@ include file="/resources/member/sub_img.html"%> 
<%@ include file="/resources/member/sub_menu.html" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

  <article>
    <h1>Login</h1>
    <form method="post" action="${contextPath}/members/login">
        <fieldset>
        <legend></legend>
          <label>User ID</label>
          <input name="id" type="text" value="${id}" value="one"><br>
          <label>Password</label>
          <input name="pwd" type="password" value="1111"><br>
        </fieldset>
        <div class="clear"></div>
        <div id="buttons">
            <input type="submit" value="로그인" class="submit">
            <input type="button" value="회원가입" class="cancel"
                 onclick="location='${contextPath}/members/contract'">
            <input type="button" value="아이디 비밀번호 찾기" class="submit"
                 onclick="location='${contextPath}/members/findIdPwd'">
        </div>
    </form>
  </article>
<%@ include file="../footer.jsp" %>      
