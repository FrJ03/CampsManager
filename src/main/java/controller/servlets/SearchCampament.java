package controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	        	RequestDispatcher dispatcher = request.getRequestDispatcher("/mvc/view/assistant/searchCampament.jsp");
	            dispatcher.forward(request, response);
	        }
	        
		}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html"); 
		PrintWriter writer = response.getWriter();
		writer.println("<!DOCTYPE html>");
        writer.println("<html lang=\"es\">");
        writer.println("<head>");
        writer.println("<meta charset=\"UTF-8\">");
        writer.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        writer.println("<title>Página de Saludo</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>Hola</h1>");
        writer.println("</body>");
        writer.println("</html>");
       
	}

}
