package controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;

import controller.gestores.GestorCampamentos;
import view.beans.customer.CustomerBean;

public class CreateActivity extends HttpServlet{

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
