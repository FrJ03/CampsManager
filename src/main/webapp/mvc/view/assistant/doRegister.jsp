<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
     <style>
        body {
            text-align: center;
            padding: 20px;
        }

        h1 {
            margin-bottom: 20px;
        }

        form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        input[type="submit"] {
            margin-bottom: 10px;
            padding: 10px 20px; /* Ajusta el tamaño de los botones según sea necesario */
            font-size: 16px;   /* Ajusta el tamaño del texto dentro de los botones */
        }
    </style>
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
   

<h1>¿Cómo quieres hacer la reserva?</h1>
    
    <!-- Botones de opción -->
    <form action="doRegisterPartialView.jsp" method="post">
        <input type="submit" name="tipo_reserva" value="Reserva Parcial">
    </form>
    <form action="doRegisterCompleteView.jsp" method="post">
         <input type="submit" name="tipo_reserva" value="Reserva Completa">  
    </form>
    
    <p><a href="/Proyecto-Programacion-Web/index.jsp">Return</a></p>
</body>
</html>
