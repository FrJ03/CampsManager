<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/Proyecto-Programacion-Web/css/style.css">
        <meta charset="UTF-8">
        <title>Register in a camp</title>
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
        <jsp:include page="/Proyecto-Programacion-Web/include/templates/assistantNav.html"></jsp:include>
        <h1 class="Title">Register in a camp:</h1>
            <%= messageNextPage %><br><br>
<!-- EXISTE EL CONTROLADOR DE HACER REGISTROS?? -->
        <form class="Form" action="Proyecto-Programacion-Web/DoRegistration" method="post">

            <label for="type">Type:</label>
            <select name="type" id="type">
                <option value="Full">Full</option>
                <option value="Partial">Partial</option>
            </select>

            <label for="mod">Modality:</label>
            <select name="mod" id="mod">
                <option value="Early">Early</option>
                <option value="Late">Late (can't cancel registration)</option>
            </select>

            <!-- INFORMAR DE PRECIO DE CAMPAMENTO -->
        
            <button type="submit">Register</button>
        </form>

    </body>
</html>
