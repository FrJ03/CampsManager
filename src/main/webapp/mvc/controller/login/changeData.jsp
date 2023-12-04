<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="controller.dto.customer.*" %>
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
if (customerBean != null || !customerBean.getEmailUser().equals("")) {
	String birthdate = request.getParameter("birthdate");
	String surNameUser = request.getParameter("surname");
	String passwordUser = request.getParameter("password");
	String nameUser = request.getParameter("name");
	String special = request.getParameter("special");
	String aux = request.getParameter("aux");
	if (special != null && birthdate!=null && surNameUser!=null && passwordUser != null && nameUser != null) {
		//Se accede a bases de datos para crear el usuario
	
		mensajeNextPage="Furula";
		CustomerDTO user = new CustomerDTO("pepito@gmail.com", "pepito", Rol.Client);
		
		//Se realizan todas las comprobaciones necesarias del dominio
		//Aquí sólo comprobamos que exista el usuario
		if (user != null  ) {
			// Usuario válido		
%>
<jsp:setProperty property="emailUser" value="Pepe" name="customerBean"/>
<jsp:setProperty property="password" value="<%=passwordUser%>" name="customerBean"/>
<%
		} else {
			// Usuario no válido
			nextPage = "../../view/register/changeDataView.jsp";
			mensajeNextPage = "The user is not valid or exsist";

		}
	//Caso 2.b -> se debe ir a la vista por primera vez
	} else {
		nextPage = "../../view/register/changeDataView.jsp";

	}
}
%>

<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=mensajeNextPage%>" name="message"/>
</jsp:forward>