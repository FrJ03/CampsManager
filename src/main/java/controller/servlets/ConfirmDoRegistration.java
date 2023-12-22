package controller.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.dto.assistant.AssistantDTO;
import controller.gestores.GestorAsistentes;
import controller.gestores.GestorInscripciones;
import view.beans.customer.CustomerBean;

/**
 * Servlet implementation class ConfirmDoRegistration
 */
@WebServlet("/ConfirmDoRegistration")
public class ConfirmDoRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		CustomerBean customer = (CustomerBean) session.getAttribute("customerBean");
		if(customer == null || !customer.getRol().equalsIgnoreCase("Client")) {
			RequestDispatcher disp = request.getRequestDispatcher("/include/errors/errorRol.jsp");
			disp.forward(request, response);
		}
		else {

			AssistantDTO a = GestorAsistentes.getInstance().leerAsistente(customer.getEmailUser());
			String idCamp = request.getParameter("idCamp");
			String type = request.getParameter("type");
			
			if(type.equalsIgnoreCase("Partial")) {
				if(!GestorInscripciones.getInstance().realizarRegistroParcial(Integer.parseInt(idCamp), a.getId())) {
					RequestDispatcher disp = request.getRequestDispatcher("/mvc/view/assistant/doRegister.jsp");
					request.setAttribute("path", "/Proyecto-Programacion-Web/DoRegistration");
					request.setAttribute("message", "the inscription cannot be done");
					disp.forward(request, response);
				}
				else {
					RequestDispatcher dispatcher= request.getRequestDispatcher("/mvc/view/assistant/doRegister.jsp");
		        	request.setAttribute("path", "/Proyecto-Programacion-Web/DoRegistration");
					request.setAttribute("message", "the inscription was done successfully");
					dispatcher.forward(request, response);
				}
			}
			if(type.equalsIgnoreCase("Full")) {
				if(!GestorInscripciones.getInstance().realizarRegistroCompleto(Integer.parseInt(idCamp), a.getId())) {
					RequestDispatcher disp = request.getRequestDispatcher("/mvc/view/assistant/doRegister.jsp");
					request.setAttribute("path", "/Proyecto-Programacion-Web/DoRegistration");
					request.setAttribute("message", "the inscription cannot be done");
					disp.forward(request, response);
				}
				else {
					RequestDispatcher dispatcher= request.getRequestDispatcher("/mvc/view/assistant/doRegister.jsp");
		        	request.setAttribute("path", "/Proyecto-Programacion-Web/DoRegistration");
					request.setAttribute("message", "the inscription was done successfully");
					dispatcher.forward(request, response);
				}
			}
		}
	}


}
