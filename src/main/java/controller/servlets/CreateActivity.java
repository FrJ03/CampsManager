package controller.servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import controller.gestores.GestorCampamentos;
import view.beans.customer.CustomerBean;

/**
 * Servlet implementation class to create a new activity
 * @author Francisco José Mellado Ortiz
 */
@WebServlet("/CreateActivity")
public class CreateActivity extends HttpServlet{

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
        	RequestDispatcher dispatcher= request.getRequestDispatcher("/mvc/view/admin/createActivityView.jsp");
        	request.setAttribute("path", "/Proyecto-Programacion-Web/CreateActivity");
            dispatcher.include(request, response);
            //createPage(request, response);

        }
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		CustomerBean customer = (CustomerBean) session.getAttribute("customerBean");
		if(customer == null || !customer.getRol().equals("Admin")) {
			RequestDispatcher disp = request.getRequestDispatcher("/include/errors/errorRol.jsp");
			disp.forward(request, response);
		}
		else {
			String name = request.getParameter("name");
			String level = request.getParameter("level");
			String maxAssistants = request.getParameter("maxAssistants");
			String maxMonitors = request.getParameter("maxMonitors");
			String turn = request.getParameter("turn");
			
			if(!isInteger(maxAssistants) || !isInteger(maxMonitors) ||!GestorCampamentos.getInstance().crearActividad(name, level, Integer.parseInt(maxAssistants), Integer.parseInt(maxMonitors), turn)) {
				RequestDispatcher disp = request.getRequestDispatcher("/include/errors/errorAddActivity.html");
				disp.forward(request, response);
			}
		}
	}
	private boolean isInteger(String number) {
		return number != null && number.matches("[0-9]+");
	}
}
