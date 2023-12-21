package controller.servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import controller.gestores.GestorInscripciones;
import view.beans.customer.CustomerBean;

/**
 * Servlet implementation class to create a new activity
 * @author Manuel Garcia Obrero
 */
@WebServlet("/DoRegistration")
public class DoRegistration extends HttpServlet{

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
        	RequestDispatcher dispatcher= request.getRequestDispatcher("/mvc/view/admin/doRegistrationView.jsp");
        	request.setAttribute("path", "/Proyecto-Programacion-Web/DoRegistration");
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

