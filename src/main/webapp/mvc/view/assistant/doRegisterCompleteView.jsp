<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register Partial</title>
 <style>
        body {
            text-align: center;
            padding: 20px;
        }

        h1 {
            margin-bottom: 20px;
        }

        form {
            max-width: 400px;
            margin: 0 auto;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
        }
    </style>
    <script>
   		RegisterDTO p = new RegisterDTO;
   		
    
    </script>
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
   

<h1>Registro Parcial</h1>
    
    <!-- Formulario de Registro Parcial -->
    <form class="Form" method="post" action="Proyecto-Programacion-Web/DoRegistrationComplete">
        <label for="numero_campamento">ID of the camp you want to register:</label>
        <input type="text" id="numero_campamento" name="numero_campamento" required>

        <input type="submit" value="Submit">
    </form>
    
    <p><a href="/Proyecto-Programacion-Web/index.jsp">Return</a></p>

</body>
</html>
