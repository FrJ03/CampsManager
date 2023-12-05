<%@page import="model.dao.AsistenteDAO"%>
<%@page import="controller.gestores.GestorAsistentes"%>
<%@page import="controller.gestores.GestorCustomer"%>
<%@page import="controller.gestores.GestorAdmin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="controller.dto.customer.*, controller.dto.admin.*, controller.dto.assistant.*" %>
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
	String birthdate = request.getParameter("birthdate");
	String emailUser = request.getParameter("email");
	String surnameUser = request.getParameter("surname");
	String passwordUser = request.getParameter("password");
	String rolUser = request.getParameter("rol");
	String nameUser = request.getParameter("name");
	String special = request.getParameter("special");
	if (emailUser != null) {
		//Se accede a bases de datos para crear el usuario
	
		mensajeNextPage="Furula";
		CustomerDTO user = new CustomerDTO(emailUser, passwordUser);
		user.setRol(rolUser);
		
		//Se realizan todas las comprobaciones necesarias del dominio
		if (!user.setRol(rolUser)) {
			nextPage = "../../view/register/registerView.jsp";
			mensajeNextPage = "The rol is not valid";
		}
		else{
			
			GestorCustomer gc = GestorCustomer.getInstance();
			boolean aux = gc.darAltaCustomer(user);
			
			if(!aux){
				nextPage = "../../view/register/registerView.jsp";
				mensajeNextPage = "The user coudn't be register";
			}
			
			else{
				boolean aux2 = false;
				if(user.getRolName().equalsIgnoreCase("Client")){
					GestorAsistentes ga = GestorAsistentes.getInstance();
					aux2 = ga.darAltaAsistente(emailUser, nameUser, surnameUser, birthdate, special);
				}
				else{
					GestorAdmin ga = GestorAdmin.getInstance();
					aux2 = ga.darAltaAdmin(emailUser, nameUser, surnameUser, birthdate, special);
				}
				
				if(aux2){
					%>
					<jsp:setProperty property="emailUser" value="<%=emailUser%>" name="customerBean"/>
					<jsp:setProperty property="rol" value="<%=user.getRolName()%>" name="customerBean"/>
					<jsp:setProperty property="password" value="<%=passwordUser%>" name="customerBean"/>
					<%
				}
				else{
					nextPage = "../../view/register/registerView.jsp";
					mensajeNextPage = "The admin/assistant coudn't be register";
				}
			}
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