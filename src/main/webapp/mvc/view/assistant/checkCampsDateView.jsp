<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/Proyecto-Programacion-Web/css/style.css">
        <meta charset="UTF-8">
        <title>Check available camps</title>
    </head>
    <body>
        <%
            String nextPage="";
            String messageNextPage = (String)request.getAttribute("message");

            if(customerBean != null || !customerBean.getEmailUser().equals("")){
                //No debería estar aquí, lo mando al index
                nextPage="../../../webapp/index.jsp";
        %>
                <jsp:forward page="<%=nextPage%>"/>
        <%
            }
            if(customerBean.getRol() == "Admin"){
                //Lo mando a la página de admin
                nextPage="../../controller/admin/admin.jsp";
        %>
                <jsp:forward page="<%=nextPage%>"></jsp:forward>
        <%
            }
            else{
                if(customerBean.getRol() == "None"){
                    nextPage="../../../webapp/index.jsp";
        %>
                    <jsp:forward page="<%=nextPage%>"/>
        <%
                }
            }
        %>
        <jsp:include page="</Proyecto-Programacion-Web/include/templates/assistantNav.html>"></jsp:include>
        <h1 class="Title">Check available camps</h1>
            <%= messageNextPage %><br><br>
<!-- EXISTE EL CONTROLADOR CHECKCAMPSDATE?? -->
        <form class="Form" action="/Proyecto-Programacion-Web/CheckCampsDate" method="post">
            
            <label for="dateStart">Date of beginning of camp:</label>
            <input type="date" name="dateStart" id="dateStart" required>
            
            <label for="dateEnd">Date of ending of camp:</label>
            <input type="date" name="dateEnd" id="dateEnd" required>
        
            <button type="submit">Search</button>
        </form>

    </body>
</html>
