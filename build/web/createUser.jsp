<%-- 
    Document   : createUser
    Created on : Jun 11, 2024, 8:22:50 AM
    Author     : KuroZabulus
--%>

<%@page import="sample.users.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            .container{
                margin: 0 auto;
                width: 400px;
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

            #button{
                width: 50px;
                margin-left: 3.5rem;
                margin-bottom: 1rem;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New User</title>
    </head>
    <body>
        <div class="content">
            <div class="container">
                <h1 style="text-align: center; padding: 10px 0 0 0">
                    Create user
                </h1>
                <div class="main">
                    <form action="MainController">
                        User ID<input type="text" name="userID" required=""/>${requestScope.USER_ERROR.userIDError}</br>
                        </br>Full Name<input type="text" name="fullName" required=""/>${requestScope.USER_ERROR.fullNameError}</br>
                        </br>Role ID<select name="roleID">
                            <option value="AD">Admin</option>
                            <option value="US">User</option>
                            <option value="OT">Other</option>
                        </select></br>
                        </br>Password<input type="password" name="password" required=""/></br>
                        </br>Confirm Password<input type="password" name="confirm" required=""/>${requestScope.USER_ERROR.confirmError}</br> 
                        </br><input style="align-content: center;" type="submit" name="action" value="Insert" id="button"/>
                        <input style="align-content: center;" type="reset" value="Reset" id="button"/> 
                    </form>
                    ${requestScope.USER_ERROR.error}
                </div>
            </div>
        </div>
    </body>
</html>
