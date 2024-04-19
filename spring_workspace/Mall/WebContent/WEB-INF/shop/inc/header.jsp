<%@page import="com.sds.mall.domain.Member"%>
<%@page import="com.sds.mall.domain.TopCategory"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	//컨트롤러가 저장한 topList 가져오기 
	List<TopCategory> topList =(List)request.getAttribute("topList");

	//로그인 한 회원의 경우  session에 Member가 담겨져 있으므로, Member DTO를 꺼내어 사용해보자 
	Member member = (Member)session.getAttribute("member");
%>
    <!-- Offcanvas Menu Begin -->
    <div class="offcanvas-menu-overlay"></div>
    <div class="offcanvas-menu-wrapper">
        <div class="offcanvas__close">+</div>
        <ul class="offcanvas__widget">
            <li><span class="icon_search search-switch"></span></li>
            <li><a href="#"><span class="icon_heart_alt"></span>
                <div class="tip">2</div>
            </a></li>
            <li><a href="/order/cart/list"><span class="icon_bag_alt"></span>
                <div class="tip">2</div>
            </a></li>
        </ul>
        <div class="offcanvas__logo">
            <a href="/"><img src="/static/shop/img/logo.png" alt=""></a>
        </div>
        <div id="mobile-menu-wrap"></div>
        <div class="offcanvas__auth">
            <a href="/member/loginform">로그인</a>
            <a href="/member/registform">회원가입</a>
        </div>
    </div>
    <!-- Offcanvas Menu End -->

    <!-- Header Section Begin -->
    <header class="header">
        <div class="container-fluid">
            <div class="row">
                <div class="col-xl-3 col-lg-2">
                    <div class="header__logo">
                        <a href="/"><img src="/static/shop/img/logo.png" alt=""></a>
                    </div>
                </div>
                <div class="col-xl-6 col-lg-7">
                    <nav class="header__menu">
                        <ul>
                            <li class="active"><a href="/">Home</a></li>
                            
                            <%for(TopCategory topCategory : topList){%>
                            	<li><a href="/product/list?topcategory_idx=<%=topCategory.getTopcategory_idx()%>"><%=topCategory.getTopname() %></a></li>
                            <%} %>
                            
                            <li><a href="#">Pages</a>
                                <ul class="dropdown">
                                    <li><a href="/static/shop/product-details.html">Product Details</a></li>
                                    <li><a href="/static/shop/shop-cart.html">Shop Cart</a></li>
                                    <li><a href="/static/shop/checkout.html">Checkout</a></li>
                                    <li><a href="/static/shop/blog-details.html">Blog Details</a></li>
                                </ul>
                            </li>
                            <li><a href="/static/shop/blog.html">Blog</a></li>
                            <li><a href="/static/shop/contact.html">Contact</a></li>
                        </ul>
                    </nav>
                </div>
                <div class="col-lg-3">
                    <div class="header__right">
                        <div class="header__right__auth">
                        	
                        	<%if(member !=null){ //세션에 member 담겨져 있다면 %>
	                        	<span> <%=member.getNickname() %>님 </span>
					            <a href="/member/logout">로그아웃</a>
					            <a href="/member/mypage/main">MyPage</a>
				            <%}else{ %>
					            <a href="/member/loginform">로그인</a>
					            <a href="/member/registform">회원가입</a>
				            <%} %>
				            
                        </div>
                        <ul class="header__right__widget">
                            <li><span class="icon_search search-switch"></span></li>
                            <li><a href="#"><span class="icon_heart_alt"></span>
                                <div class="tip">2</div>
                            </a></li>
                            
                            <li>
                            	<a href="/order/cart/list"><span class="icon_bag_alt"></span>
                                	<div class="tip">2</div>
                            	</a>
                            </li>
                            
                        </ul>
                    </div>
                </div>
            </div>
            <div class="canvas__open">
                <i class="fa fa-bars"></i>
            </div>
        </div>
    </header>
    <!-- Header Section End -->
