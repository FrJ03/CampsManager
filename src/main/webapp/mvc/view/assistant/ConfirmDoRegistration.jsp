<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
    <head>
    <link rel="stylesheet" href="../../css/style.css">
        <meta charset="UTF-8">
        <title>Register in a camp</title>
    </head>
    <body>
    	<%
        String type = (String)request.getAttribute("type");
    	String  modalidad= (String)request.getAttribute("modalidad");
    	String  price= (String)request.getAttribute("price");
    	String  idCamp= (String)request.getAttribute("idCamp");
        %>
       <h1>Do you want to join to this camp of the modality <%=modalidad%> for a price of <%=price %>€?</h1>
       <a href="/Proyecto-Programacion-Web/ConfirmDoRegistration?type=<%=type%>&idCamp=<%=idCamp%>">Accept</a>
       <a href="/Proyecto-Programacion-Web/DoRegistration?">Decline</a>  
    </body>
</html>