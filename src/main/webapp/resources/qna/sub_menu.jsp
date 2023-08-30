<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath }" />

<meta charset="UTF-8">
  <nav id="sub_menu">
    <ul>    
      <li><a href="${contextPath}/qnas/list.do">Q&amp;A 게시글 리스트</a></li>
      <li><a href="${contextPath}/qnas/writeForm.do">Q&amp;A 게시글 쓰기</a></li> 
    </ul>
  </nav>