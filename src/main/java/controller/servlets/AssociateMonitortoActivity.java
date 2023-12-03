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

import controller.dto.activity.ActivityDTO;
import controller.dto.monitor.MonitorDTO;
import controller.gestores.GestorCampamentos;
import view.beans.customer.CustomerBean;

/**
 * Servlet implementation class AssociateMonitortoActivity
 */
@WebServlet("/AssociateMonitortoActivity")
public class AssociateMonitortoActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Obtener la sesión actual o crear una si no existe
        HttpSession session = request.getSession(true);

        // Obtener el CustomerBean de la sesión
        CustomerBean customer = (CustomerBean) session.getAttribute("customerBean");
        // Verificar si el CustomerBean existe y tiene el rol asignado de admin
        if (customer == null || !customer.getRol().equals("Admin")) {
        	// El admin no existe o no tiene el rol de admin
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/include/errors/errorRol.jsp");
            dispatcher.forward(request, response);
        } else {
        	// El admin existe y tiene el rol de admin

        	String message ="";
        	setAttributes(message, request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		CustomerBean customer = (CustomerBean) session.getAttribute("customerBean");
		if (customer == null || !customer.getRol().equals("Admin")) {
	            // Redirect to an error page if the user doesn't have the required role
				RequestDispatcher dispatcher = request.getRequestDispatcher("/include/errors/errorRol.jsp");
	            dispatcher.forward(request, response);
	    }
	        // Get the form parameters
	        String activity = request.getParameter("Activity");
	        String monitor = request.getParameter("Monitor");
	    if ((monitor == "" || activity == "") ) {
        	String message ="Error, the activity/monitor doesn't exist.";
        	setAttributes(message, request, response);
           
	    }
	    
		 if(!GestorCampamentos.getInstance().asociarMonitorActividad(Integer.parseInt(activity), Integer.parseInt(monitor))) {
		    	
	        	String message ="";
	    	    setAttributes(message, request, response);
	            
		    }
		    else {
		    	String message ="The activity <strong>"+monitor+"</strong> was added to the camp <strong>"+activity+"</strong> succesfully.";
			    setAttributes(message, request, response);
		    }
        
	}
	private void setAttributes(String message, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/include/templates/associateTemplate.jsp");
	    request.setAttribute("message", message);
	    request.setAttribute("atribute1", "Activity");
    	request.setAttribute("atribute2", "Monitor");
    	request.setAttribute("path", "/Proyecto-Programacion-Web/AssociateMonitortoActivity");
    	request.setAttribute("h2", "Asociate a monitor to an activity");
    	dispatcher.include(request, response);
        dispatcher = request.getRequestDispatcher("/include/templates/returnToIndex.jsp");
        dispatcher.include(request, response);
    	GestorCampamentos gc = GestorCampamentos.getInstance();
    	ArrayList<ActivityDTO> list = gc.getListaActividades();
    	String turno = "true";
    	for(ActivityDTO aux : list) {
    		dispatcher = activityView(aux, turno, request);
    		dispatcher.include(request, response);
    		turno = "false";
    	}
    	ArrayList<MonitorDTO> listm = gc.getListaMonitores();
    	turno = "true";
    	for(MonitorDTO aux : listm) {
    		dispatcher = monitorView(aux, turno, request);
    		dispatcher.include(request, response);
    		turno = "false";
    	}
    
	}
	
	private RequestDispatcher activityView(ActivityDTO act, String turno, HttpServletRequest request) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/include/templates/activityTemplate.jsp");
		String aux = String.valueOf(act.getId());
	    request.setAttribute("idAct", aux);
	    request.setAttribute("nameAct", act.getName());
    	request.setAttribute("nivelAct", act.getNivel().toString());
    	request.setAttribute("monitoresMax", act.getMonitoresMax());
    	request.setAttribute("turnoAct", turno);
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
}
