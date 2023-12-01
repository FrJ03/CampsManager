package controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
     * @see HttpServlet#HttpServlet()
     */
    public AssociateActivitytoCamp() {
        super();
        // TODO Auto-generated constructor stub
    }

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
	        	RequestDispatcher dispatcher = request.getRequestDispatcher("/mvc/view/admin/associateActivitytoCampForm.jsp");
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
		if (customer == null || !customer.getRol().equals("Admin")) {
	            // Redirect to an error page if the user doesn't have the required role
				RequestDispatcher dispatcher = request.getRequestDispatcher("/include/errors/errorRol.jsp");
	            dispatcher.forward(request, response);
	    }
	        // Get the form parameters
	        String camp = request.getParameter("camp");
	        String activity = request.getParameter("activity");
	    if ((camp == "" || activity == "") ) {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/mvc/view/admin/associateActivitytoCampForm.jsp");
        	request.setAttribute("message", "Error, the activity/camp doesn't exist.");
            dispatcher.forward(request, response);
	    }
	    
//	    if(!GestorCampamentos.getInstance().asociarActividadCampamento(Integer.parseInt(camp), Integer.parseInt(activity))) {
//    	RequestDispatcher dispatcher = request.getRequestDispatcher("/mvc/view/admin/associateActivitytoCampForm.jsp");
//        	request.setAttribute("message", "The activity and camp level should be equal)");
//            dispatcher.forward(request, response);
//	    }
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/mvc/view/admin/associateActivitytoCampForm.jsp");
	    request.setAttribute("message", "The activity <strong>"+activity+"</strong> was added to the camp <strong>"+camp+"</strong> succesfully.");
        dispatcher.forward(request, response);
	
	}

}
