<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
    <head>
    <link rel="stylesheet" href="../../css/style.css">
        <meta charset="UTF-8">
        <title>Welcome, admin!</title>
    </head>
    <body>
        <%
            String nextPage="";
            String messageNextPage = (String)request.getAttribute("message");

            if(customerBean != null || !customerBean.getEmailUser().equals("")){
                //No debería estar aquí, lo mando al index
                nextPage="../../../webapp/index.jsp";
                <jsp:forward page="<%=nextPage%>"></jsp:forward>
            }
            if(customerBean.getRolName() == "Client"){
                //Lo mando a la página de admin
                nextPage="../../controller/assistant/assistant.jsp";
                <jsp:forward page="<%=nextPage%>"></jsp:forward>
            }
            else{
                if(customerBean.getRolName() == "None"){
                    nextPage="../../../webapp/index.jsp";
                    <jsp:forward page="<%=nextPage%>"></jsp:forward>
                }
            }
        %>

        <ul>
            <li><a href="./adminOperationsView.jsp"></a>What you can do</li>
            <li><a href="../../controller/logout/logout.jsp"></a>Logout</li>
            <li><a href="../register/changeDataView.jsp"></a>Modify user info</li>
        </ul>

//poner listado con los nombres de los campamentos y el número de personas inscritas en cada uno
//hasta la fecha actual, diferenciando si son completas o parciales

    </body>
</html>
