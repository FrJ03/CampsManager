package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.dto.camp.CampDTO;
import controller.dto.monitor.MonitorDTO;
import controller.gestores.GestorCampamentos;
import view.beans.customer.CustomerBean;

/**
 * Servlet implementation class AssociateSpecialMonitorToCamp
 */
@WebServlet("/AssociateSpecialMonitorToCamp")
public class AssociateSpecialMonitorToCamp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Obtener la sesión actual o crear una si no existe
        HttpSession session = request.getSession(true);

        // Obtener el CustomerBean de la sesión
        CustomerBean customer = (CustomerBean) session.getAttribute("customerBean");
        // Verificar si el CustomerBean existe y tiene el rol asignado de cliente
        if (customer == null || !customer.getRol().equals("Admin")) {
        	// El cliente no existe o no tiene el rol de cliente
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/include/errors/errorRol.jsp");
            dispatcher.forward(request, response);
        } else {
        	// El cliente existe y tiene el rol de cliente
        	RequestDispatcher dispatcher;
        	String message ="";
        	String type = "Special-monitor";
    	    dispatcher = setAttributes(message,type, request);
            dispatcher.include(request, response);
            dispatcher = request.getRequestDispatcher("/include/templates/returnToIndex.jsp");
            dispatcher.include(request, response);
            createPage(request, response);

        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		CustomerBean customer = (CustomerBean) session.getAttribute("customerBean");
		GestorCampamentos gc = GestorCampamentos.getInstance();
		
		if (customer == null || !customer.getRol().equals("Admin")) {
	            // Redirect to an error page if the user doesn't have the required role
				RequestDispatcher dispatcher = request.getRequestDispatcher("/include/errors/errorRol.jsp");
	            dispatcher.forward(request, response);
	    }
	        // Get the form parameters
	        String camp = request.getParameter("Campament");
	        String special = request.getParameter("Special-monitor");
	        
	    if (  camp == "" || special=="") {
	    	String message="Error, the camp/monitor doesn't exist.";
        	String type = "Special-monitor";
        	RequestDispatcher dispatcher = setAttributes(message,type, request);
            dispatcher.include(request, response);
            dispatcher = request.getRequestDispatcher("/include/templates/returnToIndex.jsp");
            dispatcher.include(request, response);
            createPage(request, response);
	    }
	    
	    else {
    	
	    	if (!gc.asociarMonitorEspecial(Integer.parseInt(camp),Integer.parseInt(special))) {
	        	String type = "Special-monitor";
	        	String message ="Error, this monitor cannot be a special-monitor for this camp.";
	        	RequestDispatcher dispatcher = setAttributes(message,type, request);
	            dispatcher.include(request, response);
	            dispatcher = request.getRequestDispatcher("/include/templates/returnToIndex.jsp");
	            dispatcher.include(request, response);
	            createPage(request, response);
	        }
	    	else {
	    		String type = "Special-monitor";
	        	String message ="The monitor <strong>"+special+"</strong> is now the special monitor of the campament <strong>"+camp+"</strong>.";	          
	        	RequestDispatcher dispatcher = setAttributes(message,type, request);
	            dispatcher.include(request, response);
	            dispatcher = request.getRequestDispatcher("/include/templates/returnToIndex.jsp");
	            dispatcher.include(request, response);
	            createPage(request, response);
	    	}
	    }
	}
	
	private RequestDispatcher setAttributes(String message, String type, HttpServletRequest request) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/include/templates/associateTemplate.jsp");
	    request.setAttribute("message", message);
	    request.setAttribute("atribute1", "Campament");
    	request.setAttribute("atribute2", type);
    	request.setAttribute("path", "/Proyecto-Programacion-Web/AssociateSpecialMonitorToCamp");
    	request.setAttribute("h2", "Asociate a "+type+" to an activity");
    	return dispatcher;
	}
	
	private RequestDispatcher campView(CampDTO act, String turno, HttpServletRequest request) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/include/templates/campTemplate.jsp");
		String aux = String.valueOf(act.getId());
	    request.setAttribute("idCamp", aux);
	    request.setAttribute("nivelCamp", act.getNivel().toString());
	    request.setAttribute("turnoCamp", turno);
    	return dispatcher;
	}
	
	private RequestDispatcher monitorView(MonitorDTO mon,String turno, HttpServletRequest request) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/include/templates/monitorTemplate.jsp");
		String aux = String.valueOf(mon.getId());
	    request.setAttribute("idMon", aux);
	    request.setAttribute("nameMon", mon.getNombre());
    	request.setAttribute("surnameMon", mon.getApellidos());
    	request.setAttribute("especialMon", String.valueOf(mon.getEspecial()));
    	request.setAttribute("turnoMon", turno);
    	return dispatcher;
	}
	
	private void createPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GestorCampamentos gc = GestorCampamentos.getInstance();
      	ArrayList<CampDTO> listc = gc.getListaCampamentos();;
      	RequestDispatcher dispatcher;
        String turno = "true";		
      	for(CampDTO aux : listc) {
      		dispatcher = campView(aux, turno, request);
      		dispatcher.include(request, response);
      		turno = "false";
      	}
      	ArrayList<MonitorDTO> listm = new ArrayList<MonitorDTO>();
      	listm = gc.getListaMonitores();
      	turno = "true";
      	for(MonitorDTO aux : listm) {
      		dispatcher = monitorView(aux, turno, request);
      		dispatcher.include(request, response);
      		turno = "false";
      	}
	}

}
