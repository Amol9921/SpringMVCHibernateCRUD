<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <title>Welcome to Eprocurement</title>
        </head>
        <body>
            <form:form id="loginForm" modelAttribute="employee" action="verify" method="post">
                <table align="center">
                    <tr>
                        <td>
                            <form:label path="telephone">Telephone: </form:label>
                        </td>
                        <td>
                            <form:input path="telephone" name="telephone" id="telephone" />
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                     <td align="left">
                            <form:button id="employee" name="employee">verify</form:button>
                        </td>
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