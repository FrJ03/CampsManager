<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
    <head>
    <link rel="stylesheet" href="../../css/style.css">
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
                <jsp:forward page="<%=nextPage%>"><jsp:forward>
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
                    <jsp:forward page="<%=nextPage%>"><jsp:forward>
        <%
                }
            }
        %>

        <ul>
            <li><a href="../../controller/assistant/assistant.jsp"></a>User index</li>
            <li><a href="../../controller/logout/logout.jsp"></a>Logout</li>
            <li><a href="../register/changeDataView.jsp"></a>Modify user info</li>
        </ul>
<br>

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
