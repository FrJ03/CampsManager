<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:useBean  id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Associate supervisor to camp</title>
</head>
<body>
	<% 
		String messageNextPage = (String)request.getAttribute("message");
		if (messageNextPage == null) messageNextPage = "";
			if (customerBean == null || !customerBean.getRol().equals("Admin")) {
			//No debería estar aquí -> flujo salta a index.jsp
				String nextPage = "/include/errors/errorRol.jsp";
		%>
				<jsp:forward page="<%=nextPage%>"></jsp:forward>
		<% 
			}
		%>
	 <h2>Associate a supervisor monitor to a camp</h2>
    <form action="/Proyecto-Programacion-Web/AssociateSupervisortoCamp" method="post">
        <label for="availableSeats">Search:</label>
        <input type="number" name="availableSeats" id="availableSeats" min="1">
        <button type="submit">Search</button>
    </form>
</body>
</html>