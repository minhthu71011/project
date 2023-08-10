

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
                <div class="account_grid">
                    <div class="login-right">
                        <h3>Thông tin tài khoản</h3>
                        <p>${message}</p>
                        <form action="account.do" method="post">
                            <div>
                                <span>Địa chỉ Email</span>
                                <input type="hidden" name="mail" value="${acc.mail}"> 
                                <h5>${acc.mail}</h5>
                            </div>
                            <div>
                                <span>Tên</span>
                                <input type="text" name="name" value="${acc.name}"> 
                            </div>
                            <div>
                                <span>Số điện thoại</span>
                                <input type="text" name="phone" value="${acc.phone}"> 
                            </div>
                            <input type="submit" value="Thay đổi thông tin tài khoản">

                        </form>
                    </div>
                </div>
            <jsp:include page="category.jsp"/>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
