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
if (customerBean != null || !customerBean.getEmailUser().equals("")) {
	String birthdate = request.getParameter("birthdate");
	String surnameUser = request.getParameter("surname");
	String passwordUser = request.getParameter("password");
	String nameUser = request.getParameter("name");
	String special = request.getParameter("special");
	String aux = request.getParameter("aux");
	if (special != null && birthdate!=null && surnameUser!=null && passwordUser != null && nameUser != null) {
		//Se accede a bases de datos para crear el usuario
		
		GestorCustomer gc = GestorCustomer.getInstance();
		boolean a = gc.modificarCustomer(customerBean.getEmailUser(), passwordUser, customerBean.getRol());
		
		if(!a){
			nextPage = "../../view/register/changeDataView.jsp";
			mensajeNextPage = "The user doesn't exist";
		}
		
		else{
			boolean aux2 = false;
			if(customerBean.getRol().equalsIgnoreCase("Client")){
				GestorAsistentes ga = GestorAsistentes.getInstance();
				AssistantDTO adto = ga.leerAsistente(customerBean.getEmailUser());
				aux2 = ga.modificarAsistente(adto.getId(), customerBean.getEmailUser(), nameUser, surnameUser, birthdate, special);
			}
			else{
				GestorAdmin ga = GestorAdmin.getInstance();
				AdminDTO adto = ga.leerAdmin(customerBean.getEmailUser());
				aux2 = ga.modificarAdmin(adto.getId(), customerBean.getEmailUser(), nameUser, surnameUser, birthdate, special);
			}
			
			if(aux2){
				%>
				<jsp:setProperty property="emailUser" value="<%=customerBean.getEmailUser()%>" name="customerBean"/>
				<jsp:setProperty property="rol" value="<%=customerBean.getRol()%>" name="customerBean"/>
				<jsp:setProperty property="password" value="<%=passwordUser%>" name="customerBean"/>
				<%
			}
			else{
				nextPage = "../../view/register/registerView.jsp";
				mensajeNextPage = "The admin/assistant doesn't exist";
			}
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