<%-- 
    Document   : checkout
    Created on : Mar 9, 2024, 12:14:11 AM
    Author     : KuroZabulus
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="sample.shopping.ClothesDTO"%>
<%@page import="sample.shopping.CartDTO"%>
<%@page import="java.util.Map"%>
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
        <title>Checkout</title>
    </head>
    <body>
        <div class="content">
            <div class="container">
                <h1 style="text-align: center; padding: 10px 0 0 0">
                    Checkout
                </h1>
                <div class="main">
                    <c:if test="${sessionScope.CART.cart!=null}">
                        <c:if test="${not empty sessionScope.CART.cart}">
                            <table border="1">
                                <thead>
                                    <tr>
                                        <th>No.</th>
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Price</th>
                                        <th>Quantity</th>
                                        <th>Item Total</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <c:forEach var="item" varStatus="counter" items="${sessionScope.CART.cart.values()}">
                                        <tr>
                                            <td>${counter.count}</td>
                                            <td>${item.ID}</td>
                                            <td>${item.name}</td>
                                            <td>${item.price}</td>
                                            <td>${item.quantity}</td>
                                            <td>
                                                ${item.price*item.quantity}
                                            </td>
                                        </tr>
                                    </tbody>
                                    <c:set var="total" value="0"></c:set>
                                    <c:forEach var="item" items="${sessionScope.CART.cart.values()}">
                                        <c:set var="total" value="${total+item.getPrice()*item.getQuantity()}"></c:set>
                                    </c:forEach>
                                </c:forEach>
                            </table>
                        </c:if>
                    </c:if>
                    <h3>Total Price: ${total}</h3>   
                    </br>
                    <form action="MainController">
                        <input type="hidden" name="total" value="${total}"/>
                        <input type="submit" name="action" value="Order"/>
                    </form>
                    </br>
                    <div>
                        ${requestScope.MESSAGE}
                    </div>
                    <a style="margin-bottom:50px" href="MainController?action=View">Return to Cart View</a>
                </div>
            </div>
        </div>
    </body>
</html>
