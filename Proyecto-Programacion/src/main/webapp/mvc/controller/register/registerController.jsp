<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="business.user.User,business.user.Rol" %>
<jsp:useBean  id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String nextPage = "../../../index.jsp";
String mensajeNextPage = "";
//Caso 2
if (customerBean == null || customerBean.getEmailUser().equals("")) {
	String usernameUser = request.getParameter("username");
	String emailUser = request.getParameter("email");
	String surNameUser = request.getParameter("surname");
	String passwordUser = request.getParameter("password");
	String rolUser = request.getParameter("rol");
	String nameUser = request.getParameter("name");
	if (emailUser != null) {
		//Se accede a bases de datos para obtener el usuario
	
		mensajeNextPage="Furula";
		User user = new User(emailUser, usernameUser, passwordUser, Rol.Client, nameUser, surNameUser);
		
		//Se realizan todas las comprobaciones necesarias del dominio
		//Aquí sólo comprobamos que exista el usuario
		if (user != null && user.getEmail().equalsIgnoreCase(emailUser)) {
			// Usuario válido		
%>
<jsp:setProperty property="emailUser" value="<%=emailUser%>" name="customerBean"/>
<%
		} else {
			// Usuario no válido
			nextPage = "../../view/register/registerView.jsp";
			mensajeNextPage = "The user is not valid or exsist";

		}
	//Caso 2.b -> se debe ir a la vista por primera vez
	} else {
		nextPage = "../../view/register/registerView.jsp";

	}
}
%>

<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=mensajeNextPage%>" name="message"/>
</jsp:forward>