//function go_carts(contextPath) {
//	console.log("cart test")
//	
//	
//	if (document.formm.quantity.value == "") {
//		alert("수량을 입력하여 주세요.");
//		document.formm.quantity.focus();
//	} else {
//		document.formm.action = contextPath + "/cart/cartInsert.do";
//		document.formm.submit();
//	}
//}

function go_cart_delete(contextPath) {
	var count = 0;

	if (document.formm.cseq.length == undefined) {
		if (document.formm.cseq.checked == true) {
			count++;
		}
	}

	for (var i = 0; i < document.formm.cseq.length; i++) {
		if (document.formm.cseq[i].checked == true) {
			count++;
		}
	}
	if (count == 0) {
		alert("삭제할 항목을 선택해 주세요.");

	} else {
		document.formm.action = contextPath + "/cart/cartDelete";
		document.formm.submit();
	}
}
function go_order_insert(contextPath) {
	document.formm.action = contextPath + "/order/order_insert";
	document.formm.submit();
}

function go_order_delete(contextPath) {
	var count = 0;

	if (document.formm.oseq.length == undefined) {
		if (document.formm.oseq.checked == true) {
			count++;
		}
	}

	for (var i = 0; i < document.formm.oseq.length; i++) {
		if (document.formm.oseq[i].checked == true) {
			count++;
		}
	}
	if (count == 0) {
		alert("삭제할 항목을 선택해 주세요.");

	} else {
		document.formm.action = contextPath + "/cart/cartDelete";
		document.formm.submit();
	}
}

function go_order(contextPath) {
	  document.formm.action = contextPath + "/mypages";
	  document.formm.submit();
	}