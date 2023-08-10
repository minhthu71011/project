

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        <jsp:include page="header.jsp"/>
        <!--Book Information-->
        <div class="container"> 
            <div class="single_top">
                <div class="single_grid">
                    <div class="grid images_3_of_2">
                        <ul id="etalage">
                            <li>
                                <a href="optionallink.html">
                                    <img class="etalage_thumb_image img-responsive" src="images/${bookID}.jpg"/>
                                    <img class="etalage_source_image img-responsive" src="images/${bookID}.jpg" title="" />
                                </a>
                            </li>
                            <li></li>                            
                        </ul>
                        <div class="clearfix"> </div>		
                    </div> 

                    <div class="desc1 span_3_of_2">                        
                        <h4 style="font-size: 2em; padding-bottom: 0px; margin-bottom: 0px;">${bName}</h4>
                        <div class="author">${author}</div>                                           
                        <div class="cart-b">
                            <div class="left-n ">${price/1000}00vnđ</div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="amount">

                            <div class="quantity-label">Số lượng:</div>

                            <div class="quantity">
                                <form action="cart.do?bookID=${bookID}" method="post">
                                    <button class="minus-btn" type="button" name="button" value=" - ">-</button>
                                    <input type="text" name="amount" value="1">
                                    <button class="plus-btn" type="button" name="button" value=" + ">+</button>
                                    <input type="submit" value="Thêm vào giỏ hàng">
                                </form>
                            </div>
                            <script type="text/javascript">
                                $('.minus-btn').on('click', function (e) {
                                    e.preventDefault();
                                    var $this = $(this);
                                    var $input = $this.closest('div').find('input[type=text]');
                                    var value = parseInt($input.val());

                                    if (value > 1) {
                                        value = value - 1;
                                    } else {
                                        value = 1;
                                    }

                                    $input.val(value);

                                });

                                $('.plus-btn').on('click', function (e) {
                                    e.preventDefault();
                                    var $this = $(this);
                                    var $input = $this.closest('div').find('input[type=text]');
                                    var value = parseInt($input.val());

                                    if (value < 100) {
                                        value = value + 1;
                                    } else {
                                        value = 99;
                                    }

                                    $input.val(value);
                                });

                                $('.like-btn').on('click', function () {
                                    $(this).toggleClass('is-active');
                                });
                            </script>

                        </div>
                        <p id="content_demo"></p>
                        <script>
                            var cont = "${content}";
                            var content = cont.substring(0, 300);
                            document.getElementById("content_demo").innerHTML = content + "... ";
                        </script>
                        <div class="share">
                            <h5>Chia sẻ sản phẩm</h5>
                            <ul class="share_nav">
                                <li><a href="#"><img src="images/facebook.png" title="facebook"></a></li>
                                <li><a href="#"><img src="images/twitter.png" title="Twiiter"></a></li>
                                <li><a href="#"><img src="images/rss.png" title="Rss"></a></li>
                                <li><a href="#"><img src="images/gpluse.png" title="Google+"></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="clearfix"> </div>
                </div>
            </div>
            <jsp:include page="category.jsp"/>
            <div class="single_mid">
                <ul id="flexiselDemo1">
                    <c:forEach var="book" items="${bookList}">
                        <li>
                            <div class="product-item">
                                <a href="<c:url value='book.do?${book.bookID}'/>" class="book-item-a" title="${book.bName}">
                                    <div class="content">
                                        <span class="book-image">
                                            <img class="book-item-img" src="images/${book.bookID}.jpg">
                                        </span>
                                        <p class="title">${book.bName}</p>
                                        <p class="author-search">${book.author}</p>
                                        <p class="price">${book.price/1000}00 vnđ</p>
                                    </div>
                                </a>
                            </div>
                        </li>
                        <c:set var="bookID" scope="request" value="NN0002"/>
                    </c:forEach>
                </ul>
                <script type="text/javascript">
                    $(window).load(function () {
                        $("#flexiselDemo1").flexisel({
                            visibleItems: 5,
                            animationSpeed: 1000,
                            autoPlay: false,

                            pauseOnHover: true,
                            enableResponsiveBreakpoints: true,
                            responsiveBreakpoints: {
                                portrait: {
                                    changePoint: 480,
                                    visibleItems: 1
                                },
                                landscape: {
                                    changePoint: 640,
                                    visibleItems: 2
                                },
                                tablet: {
                                    changePoint: 768,
                                    visibleItems: 3
                                }
                            }
                        });

                    });
                </script>
                <script type="text/javascript" src="js/jquery.flexisel.js"></script>

                <div class="toogle">
                    <h3 class="m_3">Giới thiệu sách: </h3>
                    <p class="m_text">${content}</p>
                </div>
                <div class="prefix"> </div>
            </div>
        </div>
        <!---->
        <jsp:include page="footer.jsp"/>
    </body>
</html>
