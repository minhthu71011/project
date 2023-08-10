
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>BookStore</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
        <!--theme-style-->
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />	
        <link rel="stylesheet" href="css/etalage.css" type="text/css" media="all" />
        <!--//theme-style-->
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!--fonts-->
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
        <!--//fonts-->
        <script src="js/jquery.min.js"></script>

        <script src="js/jquery.etalage.min.js"></script>
        <script>
            jQuery(document).ready(function ($) {

                $('#etalage').etalage({
                    thumb_image_width: 300,
                    thumb_image_height: 400,
                    source_image_width: 900,
                    source_image_height: 1200,
                    show_hint: true,
                    click_callback: function (image_anchor, instance_id) {
                        alert('Callback example:\nYou clicked on an image with the anchor: "' + image_anchor + '"\n(in Etalage instance: "' + instance_id + '")');
                    }
                });

            });
        </script>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <div class="container">
            <div class="cartt">GIỎ HÀNG <span> (${amount} sản phẩm)</span></div>
            <div class="single-left">
                <c:forEach var="cart" items="${bookCart}">
                    <div class="book_cart">
                        <img class="image_demo" src="images/${cart.bookID}.jpg">
                        <div class="book_info">
                            <p><a href="book.do?${cart.bookID}">${cart.bName}</a></p>
                            <div class="book_author">Tác giả: ${cart.author}</div>
                            <a href="<c:url value='cart.do?bookID=${cart.bookID}'/>" id="delete">Xóa</a> 
                        </div>
                        <div class="money">
                            ${cart.price/1000}00vnđ
                            <p>Số lượng: ${cart.amount}</p>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="single_right">                
                <div class="single-right">
                    <div class="money_into">
                        <div class="into">Thành tiền: </div>
                        <div class="moneyy">${total/1000}00vnđ</div>
                    </div>
                        <a class="pay" href="<c:url value='pay.do?money=${total}'/>">Thanh toán</a>                    
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
