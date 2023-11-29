<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="controller.dto.*" %>
<jsp:useBean  id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>
<%
/* Posibles flujos:
	1) customerBean está logado (!= null && != "") -> Se redirige al index.jsp
	2) customerBean no está logado:
		a) Hay parámetros en el request  -> procede de la vista 
		b) No hay parámetros en el request -> procede de otra funcionalidad o index.jsp
	*/
//Caso 1: Por defecto, vuelve al index
String nextPage = "../../../index.jsp";
String mensajeNextPage = "";
//Caso 2
if (customerBean == null || customerBean.getEmailUser().equals("")) {
	String passwordUser = request.getParameter("password");
	String emailUser = request.getParameter("email");
	//Caso 2.a: Hay parámetros -> procede de la VISTA
	if (emailUser != null) {
		//Se accede a bases de datos para obtener el usuario
	
		mensajeNextPage="Furula";
		CustomerDTO user = new CustomerDTO("pepito@gmail.com", "pepito", "pepito", Rol.Client, "Pepe", "Flores");
		
		//Se realizan todas las comprobaciones necesarias del dominio
		//Aquí sólo comprobamos que exista el usuario
		if (user != null && user.getEmail().equalsIgnoreCase("pepito@gmail.com")) {
	// Usuario válido
%>
<jsp:setProperty property="emailUser" value="<%=emailUser%>" name="customerBean"/>
<%
		} else {
			// Usuario no válido
			nextPage = "../../view/login/loginView.jsp";
			mensajeNextPage = "The user is not valid/doesn't exsist";

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