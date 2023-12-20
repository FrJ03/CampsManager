<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean  id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Asociate activity to camp</title>
</head>
<body>
	<body>
		<% 
		String messageNextPage = (String)request.getAttribute("message");
		
			if (customerBean == null || !customerBean.getRol().equals("Admin")) {
			//No debería estar aquí -> flujo salta a index.jsp
				String nextPage = "/include/errors/errorRol.jsp";
		%>
				<jsp:forward page="<%=nextPage%>"></jsp:forward>
		<% 
			}
		%>
	<h2>Asociate the camp and the activity</h2>
    <form method="post" action="../../">
	<label for="camp">Campament: </label>
	<input type="text" name="camp"><br/>
	<label for="activity">Activity: </label>
	<input type="text" name="activity" ><br/>
	<input type="submit" value="Submit">
</form>

</body>
</body>
</html>