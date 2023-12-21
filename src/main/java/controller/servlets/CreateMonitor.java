package controller.servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import controller.dto.monitor.*;
import javax.servlet.annotation.WebServlet;
import controller.gestores.GestorCampamentos;
import view.beans.customer.CustomerBean;

/**
 * Servlet implementation class to create a new monitor
 * @author Manuel Garcia Obrero
 */
@WebServlet("/CreateMonitor")
public class CreateMonitor extends HttpServlet{

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
	        	RequestDispatcher dispatcher= request.getRequestDispatcher("/mvc/view/admin/createMonitorView.jsp");
	        	request.setAttribute("path", "/Proyecto-Programacion-Web/CreateMonitor");
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
                  			String last = request.getParameter("last_names");
                  			String special = request.getParameter("special");

                            MonitorDTO mon=new MonitorDTO(-1, name,last,Boolean.parseBoolean(special));
                				
                            if(!special.equalsIgnoreCase("true") && !special.equalsIgnoreCase("false")) {
                            	RequestDispatcher disp = request.getRequestDispatcher("/include/errors/errorAddMonitor.html");
            					disp.forward(request, response);
                			}
                            
                  			if(!isBoolean(special) || !GestorCampamentos.getInstance().crearMonitor(mon)) {
                        				RequestDispatcher disp = request.getRequestDispatcher("/include/errors/errorAddMonitor.html");
                        				disp.forward(request, response);
                  			}
	            		}
          	}
            private boolean isBoolean(String value) {
                    return value != null && (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false"));
            }
}
