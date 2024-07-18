<%-- 
    Document   : getUser
    Created on : Jul 16, 2024, 8:36:46 AM
    Author     : KuroZabulus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Get USer</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    <c:if test="${requestScope.LIST_USER!=null}">
        <p>User ID: ${requestScope.USER.userID}</p>
        <p>Role ID: ${requestScope.USER.roleID}</p>
        <p>Full Name: ${requestScope.USER.fullName}</p>
    </c:if>
</body>
</html>
