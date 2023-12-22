package controller.servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

import controller.gestores.GestorInscripciones;
import model.dao.AsistenteDAO;
import controller.dto.assistant.AssistantDTO;
import controller.gestores.GestorAsistentes;
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
		if(customer == null || !customer.getRol().equals("Client")) {
			RequestDispatcher disp = request.getRequestDispatcher("/include/errors/errorRol.jsp");
			disp.forward(request, response);
		}
		else {
			AssistantDTO a = GestorAsistentes.getInstance().leerAsistente(customer.getEmailUser());
				
			String idCamp = request.getParameter("idCamp");	
			
			if(!GestorInscripciones.getInstance().realizarRegistroCompleto(Integer.parseInt(idCamp), a.getId())) {
					RequestDispatcher disp = request.getRequestDispatcher("/include/errors/errordoRegistration.html");
					disp.forward(request, response);
			}
		}
	}
}
