<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Camp by Level</title>
    <link rel="stylesheet" href="/Proyecto-Programacion-Web/css/style.css">
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
    <form class="Form" action="/Proyecto-Programacion-Web/SearchCampamentByEducationalLevel" method="post">
        <label for="educationalLevel">Search by Educational Level:</label>
        <select name="educationalLevel" id="educationalLevel">
            <option value="Infantil">Infants</option>
            <option value="Juvenil">Young teens</option>
            <option value="Adolescente">Teenagers</option>
        </select>
        <button type="submit">Search</button>
    </form>

</body>
</html>