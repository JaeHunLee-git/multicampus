<%@page import="com.sds.mall.domain.Cart"%>
<%@page import="com.sds.mall.model.common.FormatManager"%>
<%@page import="com.sds.mall.domain.Product"%>
<%@page import="com.sds.mall.domain.SubCategory"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	//장바구니 목록 꺼내기
	List<Cart> cartList = (List)request.getAttribute("cartList");
	FormatManager formatManager = (FormatManager)request.getAttribute("formatManager");
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

    <!-- Shop Cart Section Begin -->
    <section class="shop-cart spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="shop__cart__table">
                    	<form id="form1">
                        <table>
                            <thead>
                                <tr>
                                    <th>Product</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Total</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                            	<%int total=0; %>
                            	<%for(int i=0;i<cartList.size();i++){ %>
                            	<%Cart cart =  cartList.get(i);%>
                            	<% 
                            		//Cart 가 보유한 Product 를 꺼내기
                            		Product product = cart.getProduct();
                            	%>
                                <tr>
                                    <td class="cart__product__item">
                                        <img src="/static/product_img/<%=product.getFilename() %>" width="55px" height="50px">  
                                        <div class="cart__product__item__title">
                                            <h6><%=product.getProduct_name() %></h6>
                                            <div class="rating">
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="cart__price"><%=formatManager.getCurrency(product.getPrice()) %></td>
                                    <td class="cart__quantity">
                                        <div class="pro-qty">
                                        	<input type="hidden" name="product_idx" value="<%=product.getProduct_idx()%>">
                                            <input type="text" name="ea" value="<%=cart.getEa()%>">
                                        </div>
                                    </td>
                                    <td class="cart__total">
                                    <% //가격 *  ea 
                                    	int subTotal = product.getPrice() * cart.getEa();
                                    	out.print(formatManager.getCurrency(subTotal));
                                    	
                                    	total += subTotal; //소계를 합산하여 총계에 대입
                                    %>
                                    </td>
                                    <td class="cart__close"><span class="icon_close" onClick="delCart(<%=cart.getCart_idx()%>)"></span></td>
                                </tr>
                            	<%} %>
                            </tbody>
                        </table>
                    	</form>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="cart__btn">
                        <a href="#">Continue Shopping</a>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="cart__btn update__btn">
                        <a href="javascript:updateCart()"><span class="icon_loading"></span> Update cart</a>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <div class="discount__content">
                        <h6>Discount codes</h6>
                        <form action="#">
                            <input type="text" placeholder="Enter your coupon code">
                            <button type="submit" class="site-btn">Apply</button>
                        </form>
                    </div>
                </div>
                <div class="col-lg-4 offset-lg-2">
                    <div class="cart__total__procced">
                        <h6>Cart total</h6>
                        <ul>
                            <li>Subtotal <span><%=formatManager.getCurrency(total)%></span></li>
                            <li>Total <span><%=formatManager.getCurrency(total) %></span></li>
                        </ul>
                        <a href="javascript:checkout()" class="primary-btn">Proceed to checkout</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shop Cart Section End -->

		

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
	function checkout(){
		location.href="/order/payment/payform";
	}

	function delCart(cart_idx){
		
		if(confirm("삭제하시겠어요?")){
			location.href="/order/cart/delete?cart_idx="+cart_idx;
		}	
	}
	
	//장바구니 갯수 수정 사항을 서버로 전송하자 (동기방식)
	function updateCart(){
		$("#form1").attr({
			action:"/order/cart/update",
			method:"post"
		});
		
		$("#form1").submit();
	}
</script>