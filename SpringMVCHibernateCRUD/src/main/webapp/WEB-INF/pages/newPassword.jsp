<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <title>Change Password</title>
        </head>
        <body>
            <form:form id="loginForm" modelAttribute="employee" action="updatePassword" method="post">
                <table align="center">
                
                 <tr>
                        <td>
                            <form:label path="id">EmployeeId: </form:label>
                        </td>
                        <td>
                            <form:input path="id" name="id" id="id" value="${employee.id}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="password">New Password: </form:label>
                        </td>
                        <td>
                            <form:input path="password" name="password" id="password" />
                        </td>
                    </tr>
                     <tr>
                        <td>
                            <form:label path="newpassword"> Confirm Password: </form:label>
                        </td>
                        <td>
                            <form:input path="newpassword" name="newpassword" id="newpassword" />
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                         <td colspan="2" align="center"><input type="submit" value="Save"></td>
                    </tr>
                    
                              </table>
            </form:form>
            <table align="center">
                <tr>
                    <td style="font-style: italic; color: red;">${message}</td>
                </tr>
            </table>
        </body>
        </html>