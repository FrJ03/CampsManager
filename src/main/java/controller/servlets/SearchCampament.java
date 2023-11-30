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
import view.beans.customer.CustomerBean;


/**
 * Servlet implementation class SearchCampament
 */
@WebServlet("/SearchCampament")
public class SearchCampament extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SearchCampament() {
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
	        if (customer == null || !customer.getRol().equals("Client")) {
	        	// El cliente no existe o no tiene el rol de cliente
	        	RequestDispatcher dispatcher = request.getRequestDispatcher("/include/errors/errorRol.jsp");
	            dispatcher.forward(request, response);
	        } else {
	        	// El cliente existe y tiene el rol de cliente
	        	RequestDispatcher dispatcher = request.getRequestDispatcher("/mvc/view/assistant/searchCampamentForm.jsp");
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
		if (customer == null || !customer.getRol().equals("Client")) {
	            // Redirect to an error page if the user doesn't have the required role
				RequestDispatcher dispatcher = request.getRequestDispatcher("/include/errors/errorRol.jsp");
	            dispatcher.forward(request, response);
	    }
	        // Get the form parameters
	        String educationalLevel = request.getParameter("educationalLevel");
	        String availableSeatsStr = request.getParameter("availableSeats");
	    if ((educationalLevel == null && availableSeatsStr == "") ) {
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/mvc/view/assistant/searchCampamentForm.jsp");
        	request.setAttribute("message", "The value of the request is invalid");
            dispatcher.forward(request, response);
	    }
	    
	    if(educationalLevel != null) {
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/include/templates/campTemplate.jsp");
            dispatcher.include(request, response);
	    	CampDTO camp = new CampDTO();
	    	camp.setId(1);
	        camp.setInicioCampamento(LocalDate.of(2023, 1, 1));
	        camp.setFinCampamento(LocalDate.of(2023, 1, 10));
	        camp.setNivel(Nivel.Adolescente);
	        camp.setAsistentesMax(50);

	        // Create and set a MonitorDTO for the responsible
	        MonitorDTO responsible = new MonitorDTO();
	        responsible.setId(101);
	        responsible.setNombre("John Doe");
	        camp.setResponsable(responsible);

	        // Create and set a MonitorDTO for the special responsible
	        MonitorDTO specialResponsible = new MonitorDTO();
	        specialResponsible.setId(102);
	        specialResponsible.setNombre("Jane Doe");
	        camp.setResponsableEspecial(specialResponsible);
	        response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        out.println("<ul>\n");
	        out.println("<li><strong>Id:</strong> "+camp.getId()+"</li>\n");
	        out.println("<li><strong>Start Date:</strong> " + camp.getInicioCampamento() +"</li>\n");
	        out.println("<li><strong>End Date:</strong> "+camp.getFinCampamento() + "</li>\n");
	        out.println("<li><strong>Academic Level:</strong> " + camp.getNivel() + "</li>\n");
	        out.println("<li><strong>Maximum Number of Attendees:</strong>  " +camp.getAsistentesMax() + "</li>\n");
	        out.println("<li><strong>Responsible Monitor:</strong> "+camp.getResponsable()+"</li>\n");

	        // Monitor Especial information
	        out.println("<li><strong>Monitor Especial:</strong> ");
	        if (camp.getResponsableEspecial() != null) {
	            out.println(camp.getResponsableEspecial());
	        } else {
	            out.println("Empty");
	        }
	        out.println("</li>\n");

	        out.println("</ul>");
	        dispatcher = request.getRequestDispatcher("/include/templates/returnToIndex.jsp");
            dispatcher.include(request, response);
	    }
       
	}

}
