

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header</title>
    </head>
    <body>
        <div class="header">
            <div class="bottom-header">
                <div class="container">
                    <div class="header-bottom-left">
                        <div class="logo">
                            <a href="index.jsp"><img src="images/logo.png" alt=" " /></a>
                        </div>
                        <form action="search.do" method="get">
                            <div class="search">
                                <input type="text" name="key" value="${key}">
                                <input type="submit"  value="Tìm kiếm">
                            </div>
                        </form>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="header-bottom-right">					
                        <div class="account" id="login1"><a href="login.jsp"><span> </span>Tài khoản</a></div>
                        <div class="account" id="account1"><a href="account.do"><span> </span>${name}</a></div>
                        <ul class="login" id="login">
                            <li><a href="login.jsp"><span> </span>Đăng nhập</a></li> |
                            <li ><a href="register.jsp">Đăng kí</a></li>
                        </ul>
                        <ul class="login" id="logout">
                            <li><a href="logout.do"><span> </span>Đăng xuất</a></li>
                        </ul>
                        <div class="cart"><a href="cart.do"><span> </span>Giỏ hàng</a></div>
                        <div class="clearfix"> </div>
                        <script>
                            var acc = ${accID};
                            if (acc == null || acc == '') {
                                document.getElementById('logout').style.display='none';
                                document.getElementById('login').style.display='block';
                                document.getElementById('account1').style.display='none';
                                document.getElementById('login1').style.display='block';
                            } else {
                                document.getElementById('login').style.display='none';
                                document.getElementById('logout').style.display='block';
                                document.getElementById('account1').style.display='block';
                                document.getElementById('login1').style.display='none';
                            }
                        </script>
                    </div>
                    <div class="clearfix"> </div>	
                </div>
            </div>
        </div>
    </body>
</html>