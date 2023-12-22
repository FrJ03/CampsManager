package controller.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controller.dto.assistant.AssistantDTO;
import controller.gestores.GestorAsistentes;
import controller.gestores.GestorInscripciones;
import view.beans.customer.CustomerBean;

/**
 * Servlet implementation class to cancel a registration
 * @author Francisco José Mellado Ortiz
 */
@WebServlet("/CancelRegistration")
public class CancelRegistration extends HttpServlet{
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
        if (customer == null || !customer.getRol().equals("Client")) {
        	// El cliente no existe o no tiene el rol de cliente
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/include/errors/errorRol.jsp");
            dispatcher.forward(request, response);
        } else {
        	// El cliente existe y tiene el rol de cliente
        	RequestDispatcher dispatcher= request.getRequestDispatcher("/mvc/view/assistant/cancelRegisterView.jsp");
        	request.setAttribute("path", "/Proyecto-Programacion-Web/CancelRegistration");
            dispatcher.include(request, response);
            //createPage(request, response);

        }
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		CustomerBean customer = (CustomerBean) session.getAttribute("customerBean");
		if(customer == null || !customer.getRol().equalsIgnoreCase("Client")) {
			RequestDispatcher disp = request.getRequestDispatcher("/include/errors/errorRol.jsp");
			disp.forward(request, response);
		}
		else {
			String idCamp = request.getParameter("idCamp");
			AssistantDTO a = GestorAsistentes.getInstance().leerAsistente(customer.getEmailUser());
			
			if(!isInteger(idCamp) || !GestorInscripciones.getInstance().deleteRegistration(Integer.parseInt(idCamp), a.getId())) {
				RequestDispatcher disp = request.getRequestDispatcher("/include/errors/errorCancelRegistration.html");
				disp.forward(request, response);
			}
			else {
				RequestDispatcher disp = request.getRequestDispatcher("/include/templates/returnToIndex.jsp");
				disp.forward(request, response);
			}
		}
	}
	private boolean isInteger(String number) {
		return number != null && number.matches("[0-9]+");
	}
}
