
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <div class="register">
                    <form action="admin.do" method="post"> 
                        <div class="register-top-grid">
                            <h3>THÔNG TIN SÁCH</h3>
                            <p>${message}</p>
                        <div class="mation">
                            <span>Tên sách<label>*</label></span>
                            <input type="text" name="bName" value="${bName}"> 

                            <span>Tác giả<label>*</label></span>
                            <input type="text" name="author" value="${author}"> 

                            <span>Thể loại<label>*</label></span>
                            <select class="" name="category">
                                <option value="sgk">Sách giáo khoa</option>
                                <option value="ttr">Truyện tranh</option>
                                <option value="vvn">Văn học Việt Nam</option>
                                <option value="tvn">Thơ ca Việt Nam</option>
                                <option value="tth">Tiểu thuyết</option>
                                <option value="vnn">Văn học nước ngoài</option>
                            </select>


                            <span>Số lượng<label>*</label></span>
                            <input type="number" name="amount">

                            <span>Giá tiền<label>*</label></span>
                            <input type="number" name="price">

                            <span>Nội dung<label></label></span>
                            <textarea class="content-text-area" name="content" placeholder="Content...">${content}</textarea>
                        </div>
                    </div>
                    <div class="clearfix"> </div>
                    <div class="register-but">
                        <input type="submit" value="Thêm">
                        <div class="clearfix"> </div> 
                    </div>
                </form>
            </div>
            <div class="sub-cate">
                <div class=" top-nav rsidebar span_1_of_left">
                    <h3 class="cate">ADMIN</h3>
                    <ul class="menu">
                        <li><a href="#">Thêm sách vào database</a></li> 
                        <li><a href="#">Sửa thông tin sách</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>

