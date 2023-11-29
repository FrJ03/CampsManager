<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Camp</title>
</head>
<body>
		<% 
			if (customerBean == null || !customerBean.getRol().equals("Client")) {
			//No debería estar aquí -> flujo salta a index.jsp
				String nextPage = "/include/errors/errorRol.jsp";
		%>
				<jsp:forward page="<%=nextPage%>"></jsp:forward>
		<% 
			}
		%>
    <h2>Search Camp</h2>
	
    <form action="/Proyecto-Programacion-Web/SearchCampament" method="post">
        <label for="educationalLevel">Search by Educational Level:</label>
        <select name="educationalLevel" id="educationalLevel">
            <option value="">Any</option>
            <option value="Primary">Primary</option>
            <option value="Secondary">Secondary</option>
            <option value="University">University</option>
        </select>
        <button type="submit">Search</button>
    </form>

    <form action="/Proyecto-Programacion-Web/SearchCampament" method="post">
        <label for="availableSeats">Search by Available Seats:</label>
        <input type="number" name="availableSeats" id="availableSeats" min="0">
        <button type="submit">Search</button>
    </form>

</body>
</html>