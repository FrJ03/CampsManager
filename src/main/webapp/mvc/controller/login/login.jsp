<%@page import="controller.gestores.GestorCustomer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="controller.dto.customer.*" %>
<jsp:useBean  id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>
<%
/* Posibles flujos:
	1) customerBean está logado (!= null && != "") -> Se redirige al index.jsp
	2) customerBean no está logado:
		a) Hay parámetros en el request  -> procede de la vista 
		b) No hay parámetros en el request -> procede de otra funcionalidad o index.jsp
	*/
//Caso 1: Por defecto, vuelve al index
String nextPage = "/Proyecto-Programacion-Web/index.jsp";
String mensajeNextPage = "";
//Caso 2
if (customerBean == null || customerBean.getEmailUser().equals("")) {
	String passwordUser = request.getParameter("password");
	String emailUser = request.getParameter("email");

	//Caso 2.a: Hay parámetros -> procede de la VISTA
	if (emailUser != null) {
		
		CustomerDTO user = new CustomerDTO();
		GestorCustomer gc = GestorCustomer.getInstance();
		user = gc.readCustomer(emailUser);
		//Se realizan todas las comprobaciones necesarias del dominio
		
		//Aquí sólo comprobamos que exista el usuario
		if (user != null && user.getPassword().equalsIgnoreCase(passwordUser)) {
			// Usuario válido		
%>
<jsp:setProperty property="emailUser" value="<%=emailUser%>" name="customerBean"/>
<jsp:setProperty property="rol" value="<%=user.getRolName()%>" name="customerBean"/>
<jsp:setProperty property="password" value="<%=passwordUser%>" name="customerBean"/>
<%
		} else {
			// Usuario no válido
			nextPage = "../../view/login/loginView.jsp";
			mensajeNextPage = "The user/password is not valid";

		}
	//Caso 2.b -> se debe ir a la vista por primera vez
	} else {
		nextPage = "../../view/login/loginView.jsp";

	}
}
%>

<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=mensajeNextPage%>" name="message"/>
</jsp:forward>