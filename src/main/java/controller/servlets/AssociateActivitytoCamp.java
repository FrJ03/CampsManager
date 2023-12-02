package controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.dto.activity.ActivityDTO;
import controller.dto.activity.Nivel;
import controller.dto.camp.CampDTO;
import controller.dto.monitor.MonitorDTO;
import controller.gestores.GestorAsistentes;
import controller.gestores.GestorCampamentos;
import view.beans.customer.CustomerBean;

/**
 * Servlet implementation class AssociateActivitytoCamp
 */
@WebServlet("/AssociateActivitytoCamp")
public class AssociateActivitytoCamp extends HttpServlet {
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
	        	// El cliente no existe o no tiene el rol de cliente
	        	RequestDispatcher dispatcher = request.getRequestDispatcher("/include/errors/errorRol.jsp");
	            dispatcher.forward(request, response);
	        } else {
	        	// El cliente existe y tiene el rol de admin
	        	RequestDispatcher dispatcher;
	        	String message ="";
	    	    dispatcher = setAttributes(message, request);
	            dispatcher.include(request, response);
	        	//GestorCampamentos gc = GestorCampamentos.getInstance();
	        	ArrayList<ActivityDTO> list = new ArrayList<ActivityDTO>();
	        	ActivityDTO a = new ActivityDTO();
	        	a.setId(1);
	        	a.setMonitoresMax(5);
	        	a.setName("Baloncesto");
	        	a.setNivel(Nivel.Adolescente);
	        	list.add(a);
	        	//gc.getListaActividades();
	        	for(ActivityDTO aux : list) {
	        		dispatcher = activityView(aux, request);
	        		dispatcher.include(request, response);
	        	}
	        	//gc.getListaCampamentos();
	        	ArrayList<CampDTO> listc = new ArrayList<CampDTO>();
	        	CampDTO camp = new CampDTO();
		    	camp.setId(1);
		        camp.setInicioCampamento(LocalDate.of(2023, 1, 1));
		        camp.setFinCampamento(LocalDate.of(2023, 1, 10));
		        camp.setNivel(Nivel.Adolescente);
		        camp.setAsistentesMax(50);
		        listc.add(camp);
	        	for(CampDTO aux : listc) {
	        		dispatcher = campView(aux, request);
	        		dispatcher.include(request, response);
	        	}
	        	
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
	        String camp = request.getParameter("Campament");
	        String activity = request.getParameter("Activity");
	    if ((camp == "" || activity == "") ) {
        	String message ="Error, the activity/camp doesn't exist.";
    	    RequestDispatcher dispatcher = setAttributes(message, request);
            dispatcher.forward(request, response);
           
	    }
	    
//	    if(!GestorCampamentos.getInstance().asociarActividadCampamento(Integer.parseInt(camp), Integer.parseInt(activity))) {
//    	RequestDispatcher dispatcher = request.getRequestDispatcher("/mvc/view/admin/associateActivitytoCampForm.jsp");
//        	request.setAttribute("message", "The activity and camp level should be equal)");
//            dispatcher.forward(request, response);
//	    }
	    String message ="The activity <strong>"+activity+"</strong> was added to the camp <strong>"+camp+"</strong> succesfully.";
	    RequestDispatcher dispatcher = setAttributes(message, request);
        dispatcher.forward(request, response);
	
	}

	private RequestDispatcher setAttributes(String message, HttpServletRequest request) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/include/templates/associateTemplate.jsp");
	    request.setAttribute("message", message);
	    request.setAttribute("atribute1", "Campament");
    	request.setAttribute("atribute2", "Activity");
    	request.setAttribute("path", "/Proyecto-Programacion-Web/AssociateActivitytoCamp");
    	request.setAttribute("h2", "Asociate an activity to a campament");
    	return dispatcher;
	}
	
	private RequestDispatcher activityView(ActivityDTO act, HttpServletRequest request) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/include/templates/activityTemplate.jsp");
		String aux = String.valueOf(act.getId());
	    request.setAttribute("idAct", aux);
	    request.setAttribute("nameAct", act.getName());
    	request.setAttribute("nivelAct", act.getNivel().toString());
    	request.setAttribute("monitoresMax", act.getMonitoresMax());
    	return dispatcher;
	}
	private RequestDispatcher campView(CampDTO act, HttpServletRequest request) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/include/templates/campTemplate.jsp");
		String aux = String.valueOf(act.getId());
	    request.setAttribute("idCamp", aux);
	    request.setAttribute("nivelCamp", act.getNivel().toString());
    	return dispatcher;
	}
}
