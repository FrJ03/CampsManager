<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log in</title>
<link rel="stylesheet" href="../../../css/styles.css" type="text/css">
<script src="../../../js/unhiddeRegistrationInput.js"></script>
</head>
<body>
<%
/* Posibles flujos:
	1) customerBean está logado (!= null && != "") -> Se redirige al index.jsp (no debería estar aquí pero hay que comprobarlo)
	2) customerBean no está logado:
		a) Hay parámetros en el request  -> procede del controlador /con mensaje 
		b) No hay parámetros en el request -> procede del controlador /sin mensaje
	*/
	String nextPage = "../../controller/login/login.jsp";
	String messageNextPage = request.getParameter("message");
	if (messageNextPage == null) messageNextPage = "";

	if (customerBean != null && !customerBean.getEmailUser().equals("")) {
		//No debería estar aquí -> flujo salta a index.jsp
		nextPage = "../../controller/register/register.jsp";
		%>
		<jsp:include page="<%=nextPage%>"></jsp:include>
	<% 
	} else {
	%>
	<%= messageNextPage %><br/><br/>
<form class="Form" method="post" action="/Proyecto-Programacion-Web/mvc/controller/login/registerController.jsp">
	<label for="email">Email: </label>
	<input type="text" name="email"><br/>
	<label for="name">Name: </label>
	<input type="text" name="name" ><br/>
	<label for="surname">Surname: </label>
	<input type="text" name="surname"><br/>
	
	<label for="password">Password: </label>
	<input type="password" name="password" >
	<label for="rol">Rol: </label>
		<select name="rol" id="rol" onchange="showRegistrationInput()">
            <option value="Admin">Admin</option>
            <option value="Client" selected >Client</option>
        </select><br/>	
	<br/>
	<div id="toHide"><label for="birthdate" id="birthlabel">Birthdate: </label>
	<input type="date" name="birthdate" id="birthdate" >
	<label for="special" id="speciallabel">Need special aid?:</label>
        <select name="special" id="special">
            <option value="True">Yes</option>
            <option value="False">No</option>
        </select>
	</div>
	<input type="submit" value="Submit">
</form>
<%
}
%>

</body>
</html>