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
import controller.gestores.GestorCampamentos;
import view.beans.customer.CustomerBean;

/**
 * Servlet implementation class SearchCampamentByAvailableSeats
 */
@WebServlet("/SearchCampamentByAvailableSeats")
public class SearchCampamentByAvailableSeats extends HttpServlet {
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
	        if (customer == null || !customer.getRol().equals("Client")) {
	        	// El cliente no existe o no tiene el rol de cliente
	        	RequestDispatcher dispatcher = request.getRequestDispatcher("/include/errors/errorRol.jsp");
	            dispatcher.forward(request, response);
	        } else {
	        	// El cliente existe y tiene el rol de cliente
	        	RequestDispatcher dispatcher = request.getRequestDispatcher("/mvc/view/assistant/searchCampamentByAvailableSeats.jsp");
	        	request.setAttribute("message", "");
	            dispatcher.forward(request, response);
	        }
	        
		}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		CustomerBean customer = (CustomerBean) session.getAttribute("customerBean");
		GestorCampamentos gc = GestorCampamentos.getInstance();
		if (customer == null || !customer.getRol().equals("Client")) {
	            // Redirect to an error page if the user doesn't have the required role
				RequestDispatcher dispatcher = request.getRequestDispatcher("/include/errors/errorRol.jsp");
	            dispatcher.forward(request, response);
	    }
	        // Get the form parameters
	        String availableSeatsStr = request.getParameter("availableSeats");
	    if (availableSeatsStr == "" ) {
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/mvc/view/assistant/searchCampamentByAvailableSeats.jsp");
        	request.setAttribute("message", "The value of the request is invalid");
            dispatcher.forward(request, response);
	    }
	    
	    else {
	    	ArrayList<CampDTO> listc = gc.getListaCampamentos(Integer.valueOf(availableSeatsStr));
	        String turno = "true";
	        RequestDispatcher dispatcher;
	        dispatcher = request.getRequestDispatcher("/mvc/view/assistant/searchCampamentByAvailableSeats.jsp");
        	request.setAttribute("message", "Campaments find:");
            dispatcher.forward(request, response);
	    	for(CampDTO aux : listc) {
	    		dispatcher = campView(aux, turno, request);
	    		dispatcher.include(request, response);
	    		turno = "false";
	    	}
	    }
       
	}
	
	private RequestDispatcher campView(CampDTO act, String turno, HttpServletRequest request) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/include/templates/campTemplate.jsp");
		String aux = String.valueOf(act.getId());
	    request.setAttribute("idCamp", aux);
	    request.setAttribute("nivelCamp", act.getNivel().toString());
	    request.setAttribute("turnoCamp", turno);
    	return dispatcher;
	}

}
