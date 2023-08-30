<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<meta charset="UTF-8">   
  <nav id="sub_menu">
    <ul>
      <li><a href="${contextPath}/cart/cartList">장바구니(cart)내역</a></li>
      <li><a href="${contextPath}/mypages/mypage">진행중인 주문내역</a></li>
      <li><a href="${contextPath}/order/order_all">총 주문내역</a></li>
    </ul>
  </nav> 