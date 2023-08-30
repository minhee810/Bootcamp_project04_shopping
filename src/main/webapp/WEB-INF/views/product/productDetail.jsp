<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  
<%@ include file="../header.jsp" %>  
<%@ include file="/resources/product/sub_img.html"%> 
<%@ include file="/resources/product/sub_menu.html" %>       
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<script type="text/javascript">
	function go_carts(contextPath) {
		if (document.formm.quantity.value == "") {
			alert("수량을 입력하여 주세요.");
			document.formm.quantity.focus();
		} else {
			document.formm.action = contextPath + "/cart/cartInsert";
			document.formm.submit();
		}
	}
	function go_back(){
		history.back();
	}
</script>

  <article>
    <h1> Item </h1>
    <div id="itemdetail" >
      <form  method="post" name="formm">    
        <fieldset>
          <legend> Item detail Info</legend>  
         
          <a href="${contextPath}/products/detail?pseq=${productList.pseq}">         
            <span style="float: left;">
              <img src="${contextPath}/resources/product_images/${productList.image}"  />
            </span>              
            <h2> ${productList.name} </h2>  
          </a>    
          <label> 가 격 :  </label>  
          <p> ${productList.price2} 원</p>  
          <label> 수 량 : </label>
          <input  type="text"      name="quantity"  size="2"      value="1"><br>
          <input  type="hidden"    name="pseq"       value="${productList.pseq}"><br>
        
        </fieldset>
        
        <div class="clear"></div>
        <div id="buttons">
          <input type="button" value="장바구니에 담기" class="submit" onclick="go_carts('${contextPath}')" > 
          <input type="button" value="즉시 구매" class="submit" onclick="go_order('${contextPath}')" > 
          <input type="reset"  value="취소" class="cancel" onclick="go_back()">
        </div>
      </form>  
    </div>
  </article>
<%@ include file="../footer.jsp" %>    