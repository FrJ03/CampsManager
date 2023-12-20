<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean  id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Asociate activity to camp</title>
	<link rel="stylesheet" href="/Proyecto-Programacion-Web/css/style.css">
</head>
<body>
	<body>
		<% 
		String messageNextPage = (String)request.getAttribute("message");
		
			if (customerBean == null || !customerBean.getRol().equals("Admin")) {
			//No deber�a estar aqu� -> flujo salta a index.jsp
				String nextPage = "/include/errors/errorRol.jsp";
		%>
				<jsp:forward page="<%=nextPage%>"></jsp:forward>
		<% 
			}
		%>
	<jsp:include page="</Proyecto-Programacion-Web/include/templates/adminNav.html>"></jsp:include>
	<h1 class="Form">Asociate the camp and the activity</h1>
    <form class="Form" method="post" action="../../">
	<label for="camp">Campament: </label>
	<input type="text" name="camp"><br/>
	<label for="activity">Activity: </label>
	<input type="text" name="activity" ><br/>
	<input type="submit" value="Submit">
</form>

</body>
</body>
</html>