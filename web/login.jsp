<%-- 
    Document   : login
    Created on : Jan 24, 2024, 5:13:36 PM
    Author     : Admin
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            #container{
                width: 25rem;
                height: 22rem;
                margin: 5vh auto;
                border: 2px solid gray;
                border-radius: 5px;
                background-color: rgba(191,191,191,0.75);
            }
            #container h1{
                text-align: center;
            }
            #container form{
                position: relative;
            }
            #container p{
                text-align: center;
                margin: 0 -5px;
            }
            #container .error{
                text-align: center;
                color: red;
            }
            #container .button{
                position: relative;
                top: 5%;
                margin: 1vw;
            }
            #container a{
                position: relative;
                text-align: center;       
            }

            .center{
                margin: 1.75rem 1.75rem;
                text-align: center;
            }

            .content {
                /* this is needed or the background will be offset by a few pixels at the top */
                overflow: auto;
                position: relative;
            }

            .content::before {
                content: "";
                position: fixed;
                left: 0;
                right: 0;
                z-index: -1;

                display: block;
                background-image:url('https://daihoc.fpt.edu.vn/wp-content/uploads/2020/08/campus7-1-910x587.jpg');
                background-size:cover;
                width: 100%;
                height: 100%;
                filter: blur(5px);
            }
            .g-recaptcha{
                margin: 2rem 3.25rem;
            }
        </style>
        <script>
            function generateCaptcha() {
                var captcha = Math.random().toString(36).substring(7);
                document.getElementById("captchaShow").textContent = captcha;
                document.getElementById("captchaParam").value = captcha;
            }
            function refreshCaptcha() {
                generateCaptcha();
            }
            window.onload = function () {
                generateCaptcha();
            };
        </script>
        <script src="https://www.google.com/recaptcha/api.js"></script>
        <title>Login Page</title>
    </head>
    <body>
        <div class="content">

            <div id="container">
                <h1>Login Information</h1>
                <div>
                    <form action="MainController" method="POST">
                        <p>User ID<p>
                            <input type="text" name="userID"/></br>
                        <p>Password<p>
                            <input type="password" name="password"/></br>
                            <input class="button" type="submit" name="action" value="Login"/>
                            <input class="button" type="reset" name="Reset" value="Reset"/>
                            </br>
                            <a class="register" href="MainController?action=Create_User_Page">Account Registration</a>
                            </br>
                            <div class="g-recaptcha"
			data-sitekey="6Lf1OwwqAAAAAO40zNOunSiIQyFHhimJTneQ4kCf"></div>
                        <%--<div class="captcha-container">
                            <div class="captcha-box" id="captchaShow"></div>
                            <div class="captcha-refresh" onclick="refreshCaptcha()">
                                <i class="fas fa-sync"></i>
                            </div>
                        </div>
                        <input type="hidden" id="captchaParam" name="captcha" />
                        <label for="captchaInput">Enter the CAPTCHA code:</label>
                        <input type="text" name="captchaInput" placeholder="Enter the CAPTCHA code"/><br>--%>
                    </form>               
                    </br>
                    </br> 
                    <div class="error">${requestScope.ERROR}</div>  
                    <form action="MainController">
                        <input type="submit" name="action" value="Bottom One">
                    </form>
                </div>
            </div>
            </br>
        </div>
    </body>
</html>
