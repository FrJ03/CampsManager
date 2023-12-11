package controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;

import controller.gestores.GestorCampamentos;
import view.beans.customer.CustomerBean;

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
                      			bool special = request.getParameter("special");

                            MonitorDTO mon=new MonitorDTO(name,last,Boolean.parseBoolean(special));
                			
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
