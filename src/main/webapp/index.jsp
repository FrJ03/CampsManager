<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>homepage</title>
	<link rel="stylesheet" href="/Proyecto-Programacion-Web/css/styles.css" type="text/css">
</head>
<body>
<% 
	if (request.getParameter("reset") != null) {
	%> 
	<jsp:setProperty property="emailUser" value="" name="customerBean"/>
	<%
	}
if (customerBean == null || customerBean.getEmailUser().equals("")) {
%>
<h1 class="Title">Camps Manager</h1>
<div class="ButtonContainer">
<a class="Button" href="/Proyecto-Programacion-Web/mvc/controller/login/login.jsp">Log in</a>
<a class="Button" href="/Proyecto-Programacion-Web/mvc/controller/login/registerController.jsp">Sign up</a>
</div>
<% } else { %>

<% if(customerBean.getRol().equalsIgnoreCase("Admin")){ %>
	
	<jsp:include page="include/templates/adminNav.html"></jsp:include>
<% }else if(customerBean.getRol().equalsIgnoreCase("Client")){ %>

	<jsp:include page="include/templates/assistantNav.html"></jsp:include>
<% } %>
<% } %>
</body>
</html>