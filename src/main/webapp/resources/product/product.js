function go_cart(contextPath) {
  if (document.formm.quantity.value == "") {
    alert("수량을 입력하여 주세요.");
    document.formm.quantity.focus();
  } else {
    document.formm.action ="/team1_shopping/cart/cartInsert.do";
    document.formm.submit();
  }
}

function go_cart_delete(contextPath) {
  var count = 0;
  for ( var i = 0; i < document.formm.cseq.length; i++) {
    if (document.formm.cseq[i].checked == true) {
      count++;
    }
  }
  if (count == 0) {
    alert("삭제할 항목을 선택해 주세요.");

  } else {
    document.formm.action = contextPath + "/cart/cartDelete.do";
    document.formm.submit();
  }
}

function go_order_insert(contextPath) {
  document.formm.action = contextPath + "/order/order_insert.do";
  document.formm.submit();
}

function go_order_delete(contextPath) {
  var count = 0;
  for ( var i = 0; i < document.formm.oseq.length; i++) {
    if (document.formm.oseq[i].checked == true) {
      count++;
    }
  }
  if (count == 0) {
    alert("삭제할 항목을 선택해 주세요.");

  } else {
    document.formm.action = contextPath + "/order/order_delete";
    document.formm.submit();
  }
}

function go_order(contextPath) {
  document.formm.action = contextPath + "/mypages";
  document.formm.submit();
}