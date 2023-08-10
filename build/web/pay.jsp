<
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
        <script>
            var message = ${message};
            if (message != null && message != '')
                window.alert(message);
        </script>
        <jsp:include page="header.jsp"/>
        <div class="container">
            <div class="products-pay">
                <h3 class="latest-product">Thanh Toán ${message}</h3><br>
            </div>
            <h4>Điền vào dưới đây để hoàn thành mua bán của bạn !</h4>
            <div>
                <a href="login.jsp">
                    <span></span>
                    <h5>Bạn đã là thành viên? Đăng nhâp/ Đăng kí</h5>
                </a>
                <div class="ttt">
                    <div class="billing billing-address col">
                        <div class="tt">
                            <h3 class="tt-h3">Địa chỉ thanh toán</h3>
                            <h4 class="tt-h4">Tên *</h4>
                            <input class="tt-bt" type ="text" required>
                            <h4 class="tt-h4">Họ *</h4>
                            <input class="tt-bt" type ="text" required>
                            <h4 class="tt-h4">Địa chỉ Email *</h4>
                            <input class="tt-bt" type ="text" required>
                            <h4 class="tt-h4">Điện thoại *</h4>
                            <input class="tt-bt" type ="text" required>
                            <h4 class="tt-h4">Nhập lại điện thoại *</h4>
                            <input class="tt-bt" type ="text" required>
                        </div>
                    </div>
                    <div class="billing billing-address col">
                        <div class="tt">
                            <h3 class="tt-h3">Phương thức vận chuyển</h3>
                            <h4 class="tt-h4">Địa chỉ giao nhận</h4>
                            <input class="tt-bt">
                            <h4 class="tt-h4">Lựa chọn phương thức vận chuyển</h4>
                            <input type ="radio" class="tt-ch">Giao hàng nhanh (2h)</br>
                            <input type ="radio" class="tt-ch">Giao hàng thông thường 
                        </div></br></br></br>
                        <div class="tt">
                            <h3 class="tt-h3">Thông tin xuất hóa đơn</h3>
                            <input type="checkbox" class="tt-ch">Xuất hóa đơn VAT
                        </div>
                    </div></br>

                    <div class="billing billing-address col">
                        <div class="tt">
                            <h3 class="tt-h3">Phương thức thanh toán</h3>
                            <input type="checkbox" class="tt-ch">Thanh toán bằng tiền mặt</br>
                            <input type="checkbox" class="tt-ch">Thanh toán bằng phương thức khác
                        </div>
                        <button class="submit tt-button">${money/1000}00 vnđ</button>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
