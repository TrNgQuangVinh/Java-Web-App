<%-- 
    Document   : user
    Created on : Jan 24, 2024, 5:09:05 PM
    Author     : Admin
--%>

<%@page import="sample.users.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
        <style>
            #container{
                width: 25rem;
                height: 20rem;
                margin: 5vh auto;
                border: 2px solid gray;
                border-radius: 5px;
                background-color: rgba(191,191,191,0.75);
            }
            #container h2{
                text-align: center;
            }
            #container a{
                position: relative;
            }
            #container p{
                text-align: center;
                margin: 0 -10px;
            }
            #container .button{
                position: relative;
                top: 5%;
                margin: 5vh 19vw;
            }
            #container a{
                position: relative;
                text-align: center;
                right: -11.25rem;
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
        </style>
    </head>
    <body>  
        <div class="content">
            <c:if test="${sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.roleID ne 'US'}">
                <c:redirect url="login.jsp"></c:redirect>
            </c:if>
            <div id="container">
                <h2>User information:</h2>
                <p>User ID:${sessionScope.LOGIN_USER.userID}</p>
                </br><p>Password:${sessionScope.LOGIN_USER.password}</p>
                </br><p>Role ID:${sessionScope.LOGIN_USER.fullName}</p>
                </br><p>Full name:${sessionScope.LOGIN_USER.roleID}</p>
                </br>
                <form action="MainController">
                    <a href="MainController?action=Shopping_Page"/>Shop</a>
                    </br>
                    <%--<a href="MainController?action=Logout">Logout</a>--%>
                    <input class="button" type="submit" name="action" value="Logout"/>
                </form> 
            </div>
            </br></br>
        </div>
    </body>
</html>
