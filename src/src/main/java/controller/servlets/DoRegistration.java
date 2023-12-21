package controller.servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

import controller.gestores.GestorInscripciones;
import view.beans.customer.CustomerBean;

/**
 * Servlet implementation class to create a new activity
 * @author Manuel Garcia Obrero
 */
public class DoRegistration extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		CustomerBean customer = (CustomerBean) session.getAttribute("customerBean");
		if(customer == null || !customer.getRol().equals("Admin")) {
			RequestDispatcher disp = request.getRequestDispatcher("/include/errors/errorRol.jsp");
			disp.forward(request, response);
		}
		else {
			String idUser = request.getParameter("idUser");
			String idCamp = request.getParameter("idCamp");
			String type = request.getParameter("type");
		
			
			if(type=="Parcial") {
				if(!GestorInscripciones.getInstance().realizarRegistroParcial(Integer.parseInt(idCamp), Integer.parseInt(idUser))) {
					RequestDispatcher disp = request.getRequestDispatcher("/include/errors/errordoRegistration.html");
					disp.forward(request, response);
				}
			}
			if(type=="Completa") {
				if(!GestorInscripciones.getInstance().realizarRegistroCompleto(Integer.parseInt(idCamp), Integer.parseInt(idUser))) {
					RequestDispatcher disp = request.getRequestDispatcher("/include/errors/errordoRegistration.html");
					disp.forward(request, response);
				}
			}
		}
	}
}

