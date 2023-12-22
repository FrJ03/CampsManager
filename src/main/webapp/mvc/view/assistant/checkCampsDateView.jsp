<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Camp</title>
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
        <h1 class="Title">Check available camps</h1>
<!-- EXISTE EL CONTROLADOR CHECKCAMPSDATE?? -->
        <form class="Form" action="/Proyecto-Programacion-Web/CheckCampsDate" method="post">
            
            <label for="dateStart">Date of beginning of camp:</label>
            <input type="date" name="dateStart" id="dateStart" required>
            
            <label for="dateEnd">Date of ending of camp:</label>
            <input type="date" name="dateEnd" id="dateEnd" required>
        
            <input type="submit" value="Search">
        </form>

    </body>
</html>