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
		if (messageNextPage == null) messageNextPage = "";
			if (customerBean == null || !customerBean.getRol().equals("Admin")) {
			//No debería estar aquí
				String nextPage = "/include/errors/errorRol.jsp";
		%>
				<jsp:forward page="<%=nextPage%>"></jsp:forward>
		<% 
			}
		%>
	<h2>Asociate a camp to an activity</h2>
	<p><%=messageNextPage%></p>
    <form method="post" action="/Proyecto-Programacion-Web/AssociateActivitytoCamp">
	<label for="camp">Campament: </label>
	<input type="text" name="camp"><br/>
	<label for="activity">Activity: </label>
	<input type="text" name="activity" ><br/>
	<input type="submit" value="Submit">
</form>
<jsp:include page="/include/templates/returnToIndex.jsp"></jsp:include>
</body>
</body>
</html>
