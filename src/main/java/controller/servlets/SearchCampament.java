package controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import view.beans.customer.CustomerBean;
import view.beans.customer.Rol;

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
	        CustomerBean customer = (CustomerBean) session.getAttribute("customer");
	        response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        out.println("<!DOCTYPE html>");
	        out.println("<html lang=\"es\">");
	        out.println("<head>");
	        out.println("<meta charset=\"UTF-8\">");
	        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
	        out.println("<title>Prueba</title>");
	        out.println("</head>");
	        out.println("<body>");
	        // Verificar si el CustomerBean existe y tiene el rol asignado de cliente
	        if (customer == null || customer.getRol() != Rol.Client) {
	        	// El cliente no existe o no tiene el rol de cliente
	        	out.println("<p>No esta bien!</p>");
	        } else {
	        	// El cliente existe y tiene el rol de cliente
	        	out.println("<p>Ta bien!</p>");
	        }
	        out.println("</body>");
	        out.println("</html>");
	        out.close();
		}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
