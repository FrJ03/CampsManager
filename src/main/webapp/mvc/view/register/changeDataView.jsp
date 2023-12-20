<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log in</title>
<link rel="stylesheet" href="../../../css/styles.css" type="text/css">
</head>
<body>
<%
/* Posibles flujos:
	1) customerBean está logado (!= null && != "") -> Se redirige al index.jsp (no debería estar aquí pero hay que comprobarlo)
	2) customerBean no está logado:
		a) Hay parámetros en el request  -> procede del controlador /con mensaje 
		b) No hay parámetros en el request -> procede del controlador /sin mensaje
	*/
	String nextPage = "../../controller/login/changeData.jsp";
	String messageNextPage = request.getParameter("message");
	if (messageNextPage == null) 
		messageNextPage = "";

	if (customerBean == null && customerBean.getEmailUser().equals("")) {
		//No debería estar aquí -> flujo salta a index.jsp
		nextPage = "../../controller/login/changeData.jsp";
		%>
		<jsp:include page="<%=nextPage%>"></jsp:include>
	<% 
	} else {
	%>
	<%= messageNextPage %><br/><br/>
	<h1 class="Title">Change Information</h1>
<form class="Form" method="post" action="/Proyecto-Programacion-Web/mvc/controller/login/changeData.jsp">
	<label for="name">Name: </label>
	<input type="text" name="name" ><br/>
	<label for="surname">Surname: </label>
	<input type="text" name="surname"><br/>
	<label for="password">Password: </label>
	<input type="password" name="password" >	
	<br/>
	<%
	if(customerBean.getRol().equalsIgnoreCase("Client")){
		%>
		<label for="birthdate">Birthdate: </label>
		<input type="date" name="birthdate" ><br/>
		<label for="special">Need special aid?:</label>
	        <select name="special" id="special">
	            <option value="True">Yes</option>
	            <option value="False">No</option>
	        </select>
		<br>
		<%
	}
	%>
	<input type="submit" value="Submit">
</form>
<%
}
%>

</body>
</html>