<%@page import="com.sds.mall.domain.Paymethod"%>
<%@page import="com.sds.mall.domain.Receiver"%>
<%@page import="com.sds.mall.domain.Cart"%>
<%@page import="com.sds.mall.model.common.FormatManager"%>
<%@page import="com.sds.mall.domain.Product"%>
<%@page import="com.sds.mall.domain.SubCategory"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	//장바구니 목록 꺼내기
	List<Cart> cartList = (List)request.getAttribute("cartList");
	FormatManager formatManager = (FormatManager)request.getAttribute("formatManager");
	
	List<Receiver> receiverList = (List)request.getAttribute("receiverList");
	List<Paymethod> paymethodList = (List)request.getAttribute("paymethodList");
%>
<!DOCTYPE html>
<html lang="zxx">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ashion Template">
    <meta name="keywords" content="Ashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Ashion | Template</title>
	
	<%@ include file="../inc/header_link.jsp" %>	
	
</head>

<body>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>
	
	<%@ include file="../inc/header.jsp" %>
	

    <!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="./index.html"><i class="fa fa-home"></i> Home</a>
                        <span>Shopping cart</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->

    <!-- Checkout Section Begin -->
    <section class="checkout spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h6 class="coupon__link"><span class="icon_tag_alt"></span> <a href="#">Have a coupon?</a> Click
                    here to enter your code.</h6>
                </div>
            </div>
            <form id="form1" class="checkout__form">
                <div class="row">
                    <div class="col-lg-8">
                        <h5>Billing detail</h5>
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>First Name <span>*</span></p>
                                    <input type="text" value="<%=member.getNickname()%>">
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>Address <span>*</span></p>
                                    <input type="text" value="<%=member.getMemberDetail().getAddr()%>">
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>Phone <span>*</span></p>
                                    <input type="text" value="<%=member.getMemberDetail().getPhone()%>">
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>Email <span>*</span></p>
                                    <input type="text" value="<%=member.getEmail()%>">
                                </div>
                            </div>
                            
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>받는 사람 <span>*</span></p>
                                    <select name="receiver.receiver_idx">
                                    	<option>받는사람 선택 ▼</option>
                                    	<%for(Receiver receiver : receiverList){ %>
                                    	<option value="<%=receiver.getReceiver_idx()%>"><%=receiver.getAddr() %></option>
                                    	<%} %>
                                    </select>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>결제방법<span>*</span></p>
                                    <select name="paymethod.paymethod_idx">
                                    	<option>결제방법 선택 ▼</option>
                                    	<%for(Paymethod paymethod : paymethodList){ %>
                                    	<option value="<%=paymethod.getPaymethod_idx()%>"><%=paymethod.getPayname() %></option>
                                    	<%} %>
                                    </select>

                                </div>
                            </div>
                            
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="checkout__order">
                                <h5>Your order</h5>
                                <div class="checkout__order__product">
                                    <ul>
                                        <li>
                                            <span class="top__text">Product</span>
                                            <span class="top__text__right">Total</span>
                                        </li>
                                        <%
                                        	//할인 기간이므로, 10% 할인해주자
                                        	int subTotal=0;
                                        	int total=0;
                                        %>
										<%for(int i=0; i<cartList.size();i++){ %>
										<%Cart cart = cartList.get(i);//리스트에서 장바구니 DTO 꺼내기 %>
										<%Product product  = cart.getProduct(); //Cart에서 Product DTO 꺼내기 %>
                                        <li><%=i+1%>. <%=product.getProduct_name()%> 
                                        	<span><%=formatManager.getCurrency(product.getPrice()) %></span>
                                        </li>
                                        <%
                                        	subTotal += product.getPrice();
                                        %>
										<%} %>	
										<%
											//10% 할인 
										%>
                                    </ul>
                                </div>
                                <div class="checkout__order__total">
                                	<input type="hidden" name="total_buy" value="<%=subTotal%>">
                                	<input type="hidden" name="total_pay" value="<%=subTotal%>">
                                    <ul>
                                        <li>Subtotal <span><%=formatManager.getCurrency(subTotal) %></span></li>
                                        <li>Total <span><%=formatManager.getCurrency(subTotal) %></span></li>
                                    </ul>
                                </div>
                                
                                <button type="button" class="site-btn" onClick="order()">Place oder</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </section>
    <!-- Checkout Section End -->


		

	<%@ include file="../inc/insta.jsp" %>
	
	<!-- Footer Section Begin -->
	<%@ include file="../inc/footer.jsp" %>
	<!-- Footer Section End -->
	
	<!-- Search Begin -->
	<%@ include file="../inc/search.jsp" %>
	<!-- Search End -->
	
	<!-- Js Plugins -->
	<%@ include file="../inc/footer_link.jsp" %>
</body>

</html>
<script type="text/javascript">

	function order(){
		if(confirm("결제하시겠습니까?")){
			$("#form1").attr({
				action:"/order/payment/pay", 
				method:"post"
			});
			$("#form1").submit();
		}	
	}
</script>









