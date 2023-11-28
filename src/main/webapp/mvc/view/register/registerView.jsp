<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log in</title>
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
<form method="post" action="../../controller/register/registerController.jsp">
	<label for="username">Username: </label>
	<input type="text" name="username" ><br/>
	<label for="email">Email: </label>
	<input type="text" name="email"><br/>
	<label for="name">Name: </label>
	<input type="text" name="name" ><br/>
	<label for="surname">Surname: </label>
	<input type="text" name="surname"><br/>
	<label for="rol">Rol: </label>
	<input type="text" name="rol"><br/>
	<label for="password">Password: </label>
	<input type="text" name="password" >	
	<br/>
	<input type="submit" value="Submit">
</form>
<%
}
%>

</body>
</html>