<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Camp by available seats</title>
	<link rel="stylesheet" href="/Proyecto-Programacion-Web/css/styles.css">
</head>
<body>
		<% 
		String messageNextPage = (String)request.getAttribute("message");
		
			if (customerBean == null || !customerBean.getRol().equals("Client")) {
			//No debería estar aquí -> flujo salta a index.jsp
				String nextPage = "/include/errors/errorRol.jsp";
		%>
				<jsp:forward page="<%=nextPage%>"></jsp:forward>
		<% 
			}
		%>
	<jsp:include page="../../../include/templates/assistantNav.html"></jsp:include>
    <h1 class="Title">Search Camp</h1>
	<%= messageNextPage %><br/><br/>

    <form class="Form" action="/Proyecto-Programacion-Web/SearchCampamentByAvailableSeats" method="post">
        <label for="availableSeats">Search by Available Seats:</label>
        <input type="number" name="availableSeats" id="availableSeats" min="1" requiered>
        <input type="submit" value="Search">
    </form>

</body>
</html>