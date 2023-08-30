<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<nav id="sub_menu">
	<h1>Admin Setting</h1>
	<ul>
		<li><a href='${contextPath}/adminProduct/admin_product_list'> 상품리스트</a></li>
		<li><a href='${contextPath}/adminOrder/admin_order_list'> 주문리스트</a></li>
		<li><a href='${contextPath}/adminMembers/admin_member_list'> 회원리스트</a></li>
		<li><a href='${contextPath}/adminqnas/adminlist'>Q&amp;A리스트</a></li>
	</ul>
</nav>
