package controller.servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import controller.dto.monitor.*;

import controller.gestores.GestorCampamentos;
import view.beans.customer.CustomerBean;

/**
 * Servlet implementation class to create a new monitor
 * @author Manuel Garcia Obrero
 */
public class CreateMonitor extends HttpServlet{

	    private static final long serialVersionUID = 1L;
	
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
