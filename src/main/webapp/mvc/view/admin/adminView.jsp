<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
    <head>
    <%--PONER CSS--%>
    <link rel="stylesheet" href="../../css/style.css">
        <meta charset="UTF-8">
        <title>Welcome, admin!</title>
    </head>
    <body>
        <%
            String nextPage="";
            String messageNextPage="";

            if(customerBean != null || !customerBean.getEmailUser().equals("")){
                //No debería estar aquí, lo mando al index
                nextPage="../../../webapp/index.jsp";
                <jsp:include page="<%=nextPage--%>"><jsp:include>;
            }
            if(customerBean.getRolName() == "Client"){
                //Lo mando a la página de admin
                nextPage="../../controller/assistant/assistant.jsp";
                <jsp:include page="<%=nextPage%>"></jsp:include>
            }
            else{
                if(customerBean.getRolName() == "None"){
                    nextPage="../../../webapp/index.jsp";
                    <jsp:include page="<%=nextPage%>"><jsp:include>;
                }
            }
        %>

        <ul>
            // LINKAR EL JSP DE OPERACIONES DE ADMIN
            <li><a href=""></a>What you can do</li>
            <li><a href="../../controller/logout/logout.jsp"></a>Logout</li>
            // LINKAR MODIFICAR DATOS
            <li><a href=""></a>Modify user info</li>
        </ul>

//poner listado con los nombres de los campamentos y el número de personas inscritas en cada uno
//hasta la fecha actual, diferenciando si son completas o parciales

    </body>
</html>
