package controller.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import controller.gestores.GestorInscripciones;
import controller.dto.assistant.AssistantDTO;
import controller.gestores.GestorAsistentes;

import view.beans.customer.CustomerBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class to cancel a registration
 * @author Francisco José Mellado Ortiz
 */
public class CancelRegistration extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		CustomerBean customer = (CustomerBean) session.getAttribute("customerBean");
		if(customer == null || !customer.getRol().equals("Admin")) {
			RequestDispatcher disp = request.getRequestDispatcher("/include/errors/errorRol.jsp");
			disp.forward(request, response);
		}
		else {			
			String idCamp = request.getParameter("idCamp");
			//String idAssistant = request.getParameter("idAssistant");
			AssistantDTO a = GestorAsistentes.getInstance().leerAsistente(customer.getEmailUser());
			
			if(!isInteger(idCamp) || !GestorInscripciones.getInstance().deleteRegistration(Integer.parseInt(idCamp), a.getId())) {
				RequestDispatcher disp = request.getRequestDispatcher("/include/errors/errorCancelRegistration.html");
				disp.forward(request, response);
			}
		}
	}
	private boolean isInteger(String number) {
		return number != null && number.matches("[0-9]+");
	}
}
