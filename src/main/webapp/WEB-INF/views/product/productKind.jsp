<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<%@ include file="../header.jsp" %>  
<%@ include file="/resources/product/sub_img.html"%> 
<%@ include file="/resources/product/sub_menu.html" %> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
      
  <article>
    <h2> Item</h2>     
    <c:forEach items="${productKindList}"  var="productVO">
      <div id="item">
        <a href="${contextPath}/products/detail?pseq=${productVO.pseq}"> 
          <img src="${contextPath}/resources/product_images/${productVO.image}" />
          <h3>${productVO.name} </h3>        
          <p>${productVO.price2} </p>
        </a>  
      </div>
    </c:forEach>    
    <div class="clear"></div>
  </article>
<%@ include file="../footer.jsp" %>    