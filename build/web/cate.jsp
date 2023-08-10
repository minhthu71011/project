

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
        <div class="container">
            <div class="result_right">
                <div class="result_top">
                    <h1>${category}</h1><span>(${noOfRecords} kết quả)</span>
                </div>
                <div class="result_bottom">
                    <c:forEach var="book" items="${listBook}">
                        <div class="book-item product-item">
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
                    </c:forEach>
                </div>
                <div class="clearfix"> </div>
                <div class="list-pager">
                    <ul>
                        <c:forEach begin="1" end="${noOfPages}" var="i">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                    <li><span class="current-page">${i}</span></li>
                                </c:when>
                                <c:otherwise>
                                <li><a href="<c:url value='products.do?cate=${cate}&page=${i}'/>" class="list-pager-a">${i}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        
                    </ul>
                </div>
            </div>
            <jsp:include page="category.jsp"/>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>