<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>homepage</title>
</head>
<body>
<% 
	if (request.getParameter("reset") != null) {
	%> 
	<jsp:setProperty property="emailUser" value="" name="customerBean"/>
	<%
	}
	if (customerBean == null || customerBean.getEmailUser()=="") {
%>
<p>What do you want to do?</p>
<p><a href="/Proyecto-Programacion-Web/mvc/controller/login/login.jsp">Log in</a></p>
<p><a href="/Proyecto-Programacion-Web/mvc/controller/login/registerController.jsp">Register</a></p>
<% } else { %>
	<p>Welcome <jsp:getProperty property="emailUser" name="customerBean"/>!! </p>
	<p><a href="/Proyecto-Programacion-Web/mvc/controller/logout/logout.jsp">Log out</a></p>
<% } %>
</body>
</html>