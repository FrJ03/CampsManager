<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
    <head>
    <link rel="stylesheet" href="css/styles.css">
        <meta charset="UTF-8">
        <title>Create Monitor</title>
    </head>
    <body>
        <%
            String nextPage="";
            String messageNextPage = (String)request.getAttribute("message");

            if(customerBean == null || customerBean.getEmailUser().equals("")){
                //No debería estar aquí, lo mando al index
                nextPage="../../../index.jsp";
        %>
                <jsp:forward page="<%=nextPage%>"/>
        <%
            }
            if(customerBean.getRol() == "Client"){
                //Lo mando a la página de admin
                nextPage="../../controller/assistant/assistant.jsp";
        %>
                <jsp:forward page="<%=nextPage%>"></jsp:forward>;
        <%
            }
            else{
                if(customerBean.getRol() == "None"){
                    nextPage="../../../index.jsp";
        %>
                    <jsp:forward page="<%=nextPage%>"/>
        <%
                }
            }
        %>
	
		<jsp:include page="../../../include/templates/adminNav.html"></jsp:include>
<!-- formulario para crear mon -->
              <h1 class="Title">Create Monitor</h1>
              <form class="Form" action="/Proyecto-Programacion-Web/CreateMonitor" method="post">
                  <label for="name">Name</label>
                  <input type="text" id="name" name="name" required>
          
                  <label for="lastName">Last names:</label>
                  <input type="text" id="lastName" name="lastName" required>
          
                  <label for="special">Special Monitor?:</label>
                  <select id="special" name="special" required>
			            <option value="True">Yes</option>
			            <option value="False">No</option>
		      		</select>
          
                  <input type="submit" value="Create Monitor">
              </form>

    </body>
</html>
