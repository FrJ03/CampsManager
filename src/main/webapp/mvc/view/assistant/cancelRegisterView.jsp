<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/Proyecto-Programacion-Web/css/style.css">
        <meta charset="UTF-8">
        <title>Cancel registration</title>
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
        <h1 class="Title">Cancel registration</h1>
            <%= messageNextPage %><br><br>
        <form class="Form" action="/Proyecto-Programacion-Web/CancelRegistration" method="post">
            
            <label for="idCamp">Input camp you want to cancel your registration to:</label>
            <input type="number" name="idCamp" id="idCamp" required>

            <label for="idAssistant">Input your Assistant ID:</label>
            <input type="number" name="idAssistant" id="idAssistant" required>
            
            <button type="submit">Cancel</button>
        </form>

    </body>
</html>
