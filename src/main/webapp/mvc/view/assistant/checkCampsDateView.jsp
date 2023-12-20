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
		String messageNextPage = (String)request.getAttribute("message");
		
			if (customerBean == null || !customerBean.getRol().equals("Client")) {
			//No debería estar aquí -> flujo salta a index.jsp
				String nextPage = "/include/errors/errorRol.jsp";
		%>
				<jsp:forward page="<%=nextPage%>"></jsp:forward>
		<% 
			}
		%>

        <h2>Check available camps</h2>
            <%= messageNextPage %><br><br>
<!-- EXISTE EL CONTROLADOR CHECKCAMPSDATE?? -->
        <form action="/Proyecto-Programacion-Web/CheckCampsDate" method="post">
            
            <label for="dateStart">Date of beginning of camp:</label>
            <input type="date" name="dateStart" id="dateStart" required>
            
            <label for="dateEnd">Date of ending of camp:</label>
            <input type="date" name="dateEnd" id="dateEnd" required>
        
            <button type="submit">Search</button>
        </form>

    </body>
</html>


    </body>
</html>
