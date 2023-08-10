
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Category</title>
    </head>
    <body>
        <div class="sub-cate">
                <div class=" top-nav rsidebar span_1_of_left">
                    <h3 class="cate">THỂ LOẠI </h3>
                    <ul class="menu">
                        <li><a href="products.do?cate=sgk">Sách giáo khoa</a></li>
                        <li><a href="<c:url value='products.do?cate=ttr'/>">Truyện tranh</a></li>
                        <li><a href="<c:url value='products.do?cate=tvn'/>">Thơ ca Việt Nam</a></li>
                        <li><a href="<c:url value='products.do?cate=vvn'/>">Văn học Việt Nam</a></li>                       
                        <li><a href="<c:url value='products.do?cate=tth'/>">Tiểu thuyết</a></li>
                        <li><a href="<c:url value='products.do?cate=vnn'/>">Văn học thế giới</a></li>
                    </ul>
                </div>
                <a class="view-all all-product" href="products.do">Xem tất cá các sản phẩm<span> </span></a> 	
            </div>
        <div class="clearfix"> </div>
    </body>
</html>
