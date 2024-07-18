<%-- 
    Document   : shopping
    Created on : Jun 14, 2024, 7:44:51 AM
    Author     : KuroZabulus
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            .container{
                margin: 0 auto;
                width: 1000px;
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
        <title>Diep's Store</title>
    </head>
    <body>
        <div class="content">
            <div class="container">
                <h1 style="text-align: center; padding: 10px 0 0 0">
                    Welcome to Diep's Store
                </h1>
                <p style="text-align: center; padding: 10px 0 0 0">
                    Select your clothes:
                </p>
                <div class="main">  
                <c:if test="${sessionScope.LIST_PRODUCT!=null&&sessionScope.LIST_DISPLAY!=null}">
                    <c:if test="${not empty sessionScope.LIST_PRODUCT&&not empty sessionScope.LIST_DISPLAY}">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Item Name</th>
                                    <th>Item Price</th>
                                    <th>Quantity In Stock</th>
                                    <th>Quantity to Buy</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <c:forEach var="item" varStatus="counter" items="${sessionScope.LIST_PRODUCT}">
                                <c:set var="itemParts" value="${fn:split(item, '_')}"/>
                                <tbody>
                                <form action="MainController" method="POST">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>
                                            <input type="text" name="clothesName" value="${itemParts[1]}" readonly=""/>
                                        </td>
                                        <td>
                                            <input type="text" name="clothesPrice" value="${itemParts[2]}" readonly=""/>
                                        </td>
                                        <td>${itemParts[3]}</td>
                                        <td>
                                            <select name="cmbQuantity">
                                                <option value="1">1pc</option>
                                                <option value="2">2pc</option>
                                                <option value="3">3pc</option>
                                                <option value="4">4pc</option>
                                                <option value="5">5pc</option>
                                                <option value="10">10pc</option>
                                                <option value="50">50pc</option>
                                                <option value="100">100pc</option>
                                            </select>
                                        </td>
                                        <td>
                                            <input type="hidden" name="clothesId" value="${itemParts[0]}"/>
                                            <input type="submit" name="action" value="Add"/>
                                        </td>
                                    </tr>
                                </form>
                                </tbody>
                            </c:forEach>
                        </table>
                    </c:if>
                </c:if>
                ${requestScope.MESSAGE}
                </br></br>
                <a style="margin-bottom:50px" href="MainController?action=View_Cart">View Cart</a>
                </div>
            </div>
        </div>
    </body>
</html>
