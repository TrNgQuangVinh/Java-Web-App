<%-- 
    Document   : viewCart
    Created on : Jun 14, 2024, 8:43:19 AM
    Author     : KuroZabulus
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="sample.shopping.CartDTO"%>
<%@page import="sample.shopping.ClothesDTO"%>
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
        <title>View Cart</title>
    </head>
    <body>
        <div class="content">
            <div class="container">
                <h1 style="text-align: center; padding: 10px 0 0 0">
                    Your shopping cart
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
                                        <th>Edit</th>
                                        <th>Remove</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <c:set var="count" value="1"/>
                                    <c:set var="total" value="0" scope="page"/>

                                    <c:forEach var="item" items="${sessionScope.CART.cart.values()}">
                                    <form action="MainController" method="POST">
                                        <c:set var="itemTotal" value="${item.quantity * item.price}" scope="page"/>
                                        <tr>
                                            <td>${count}</td>
                                            <td>
                                                <input type="text" name="id" value="${item.ID}" readonly=""/>
                                            </td>
                                            <td>${item.name}</td>
                                            <td>${item.price}</td>
                                            <td>
                                                <input type="number" name="quantity" value="${item.quantity}" required="" min="1">
                                            </td>
                                            <td>${itemTotal}</td>
                                            <td>
                                                <input type="submit" name="action" value="Edit"/>
                                                <%--<a href="MainController?action=Edit&id=<%=computer.getId()%>&quantity=<%=computer.getQuantity()%>">Edit</a>--%>
                                            </td>
                                            <td>
                                                <input type="submit" name="action" value="Remove"/>
                                            </td>
                                        </tr>
                                    </form>
                                    <c:set var="total" value="0"></c:set>
                                    <c:forEach var="computer" items="${sessionScope.CART.cart.values()}">
                                        <c:set var="total" value="${total+computer.getPrice()*computer.getQuantity()}"></c:set>
                                    </c:forEach>
                                    <c:set var="count" value="${count + 1}"/>
                                </c:forEach>
                                </tbody>
                            </table>
                            <h3>Total Price: ${total}</h3>  
                        </c:if>
                    </c:if>
                    <div>
                        ${requestScope.MESSAGE}
                    </div>
                    <a href="MainController?action=Shopping_Page">Return to Diep's store</a>
                    </br>
                    <form action="MainController" method="POST">
                        <input style="margin-bottom:50px" type="submit" name="action" value="Checkout"/>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
