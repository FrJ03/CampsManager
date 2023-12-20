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
	String nextPage = "../../controller/login/login.jsp";
	String messageNextPage = request.getParameter("message");
	if (messageNextPage == null) messageNextPage = "";

	if (customerBean != null && !customerBean.getEmailUser().equals("")) {
		//No debería estar aquí -> flujo salta a index.jsp
		nextPage = "../../controller/login/login.jsp";
		%>
		<jsp:include page="<%=nextPage%>"></jsp:include>
	<% 
	} else {
	%>
	<%= messageNextPage %><br/><br/>
	<h1 class="Title">Login</h1>
<form class="Form" method="post" action="../../controller/login/login.jsp">
	<label for="email">Email: </label>
	<input type="text" name="email"><br/>
	<label for="password">Password: </label>
	<input type="password" name="password" ><br/>
	<input type="submit" value="Submit">
</form>
<%
}
%>

</body>
</html>