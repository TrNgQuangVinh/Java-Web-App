<%-- 
    Document   : admin
    Created on : May 28, 2024, 9:05:17 AM
    Author     : KuroZabulus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="sample.users.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            .container{
                margin: 0 auto;
                width: 1000p
                    
                    px;
                border: 3px;
                border-radius: 5px;
                background-color: rgba(255,255,255,0.9);
                z-index: 9999;

            }
            .main{
                display: flex;
                flex-flow:column;
                align-content: center;
                align-items: center;
            }

            .table{
                display: inline-block;
            }

            .center{
                padding: 1.25rem 1.25rem;
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
            .error{
                margin: 15px auto;
                font-weight: 700;
                font-size: 20px;
                color: red;
            }

            .button{
                width: 75px;
                margin-left: 1rem;
                margin-bottom: 1rem;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <div class="content">
            <%--c:if test="${sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.roleID ne 'AD'}">
                <c:redirect url="login.jsp"></c:redirect>
            </c:if--%>
            <c:url var="logoutLink" value="MainController">
                <c:param name="action" value="Logout"></c:param>
            </c:url>
            <div class="container">
                <h1 style="font-size: 36px; text-align: center; padding: 15px 0 0 0">
                    Welcome: ${sessionScope.LOGIN_USER.fullName}
                </h1>

                <h2 style="text-align: center;">
                    Searched:${param.search}
                </h2>
                <a href="MainController?action=Create_User_Page">
                    <p style="text-align: center; padding: 10px 0 0 0">
                        Create User Account
                    </p>  
                </a>
                </br>
                c
                    <div style="margin-bottom: 1rem;">
                        <form action="MainController" class="table">
                            Search <input id="searchbar" type="text" name="search" value="${param.search}" onclick="this.select()" >
                            <input type="submit" name="action" value="Search"/>
                        </form>
                    </div>
                    <c:if test="${requestScope.LIST_USER!=null}">
                        <c:if test="${not empty requestScope.LIST_USER}">
                            <table border="1" class="table">
                                <thead>
                                    <tr>
                                        <th>No.</th>
                                        <th>User ID</th>
                                        <th>Full Name</th>
                                        <th>Role ID</th>
                                        <th>Password</th>
                                        <th>Delete</th>
                                        <th>Update</th>
                                    </tr>
                                </thead>
                                <c:forEach var="user" varStatus="counter" items="${requestScope.LIST_USER}">
                                    <tbody>
                                    <form action="MainController" method="POST"> 
                                        <tr>
                                            <td>${counter.count}</td>
                                            <td><input type="text" name="userID" value="${user.userID}" readonly=""/></td>
                                            <td><input type="text" name="fullName" value="${user.fullName}" required=""/></td>
                                            <td><input type="text" name="roleID" value="${user.roleID}" required=""/></td>
                                            <td>${user.password}</td>
                                            <td>
                                                <%--<a href="MainController?action=Delete&userID=${user.userID}&search=${param.search}">Delete</a>--%>
                                                <c:url var="deleteLink" value="MainController">
                                                    <c:param name="action" value="Delete"></c:param>
                                                    <c:param name="userID" value="${user.userID}"></c:param>
                                                    <c:param name="search" value="${param.search}"></c:param>
                                                </c:url>
                                                <a href="${deleteLink}">Delete</a>
                                            </td>
                                            <td>
                                                <input type="submit" name="action" value="Update"/>
                                                <%-- <input type="hidden" name="userID" value="<%=user.getUserID()%>"/> --%>
                                                <input type="hidden" name="search" value="${param.search}"/>
                                            </td>
                                        </tr>
                                    </form>    
                                    </tbody>
                                </c:forEach>  
                            </table>
                        </c:if>
                    </c:if>
                            </br>
                <a href="MainController?action=Shopping_Page">
                    <p style="text-align: center">
                        Shop
                    </p>
                </a>
                </br>
                <form class="button" action="MainController" style="">
                    <input type="submit" name="action" value="Logout"/>
                </form>
                </div>
                <div class="error">${requestScope.ERROR}</div>
                <%--<a href="MainController?action=Logout">Logout</a>--%>
            </div>
        </div>
    </body>
</html>
