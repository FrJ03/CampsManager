<%@page import="javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Camp View</title>
</head>
<body>
<%  String id = (String)request.getAttribute("idCamp"); 
	String nivel = (String)request.getAttribute("nivelCamp");
	String responsable = (String)request.getAttribute("responsableCamp");
	String monitorEsp = (String)request.getAttribute("monEspecialCAmp");
	String fechaIni = (String)request.getAttribute("fechaIniMaxCamp");
	String fechaFinal = (String)request.getAttribute("fechaFinalCamp");
	String asistMax = (String)request.getAttribute("asistMaxCamp");
%>
    <div >
    
        <h1>Camp Details</h1>
        <div >
            <strong>ID:</strong> <%=id %>
        </div>
         <% if (responsable != null){%>
        <div >
            <strong>Responsible monitor:</strong> <%= responsable %>
        </div>
         <%} if (monitorEsp != null){%>
        <div >
            <strong>Special monitor:</strong> <%= monitorEsp %>
        </div>
        <%} if (fechaIni != null){%>
        <div >
             <strong>Camp Start Date:</strong> <%= fechaIni %>
        </div>
        <% }if (fechaFinal != null){%>
        <div >
            <strong>Camp End Date:</strong> <%= fechaFinal %>
        </div>
        <%} if (nivel != null){%>
        <div >
             <strong>Educational Level:</strong> <%= nivel %>
        </div>
         <%} if (asistMax != null){%>
         <div>
            <strong>Maximum Attendees:</strong> <%=asistMax%>
        </div>
        <%} %>
    </div>
</body>
</html>