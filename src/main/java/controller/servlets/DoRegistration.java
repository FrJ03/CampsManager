package controller.servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import controller.gestores.GestorInscripciones;
import model.dao.CampamentoDAO;
import controller.gestores.GestorAsistentes;
import controller.dto.assistant.AssistantDTO;
import controller.dto.camp.CampDTO;
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
        if (customer == null || !customer.getRol().equalsIgnoreCase("Client")) {
        	// El cliente no existe o no tiene el rol de cliente
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/include/errors/errorRol.jsp");
            dispatcher.forward(request, response);
        } else {
        	// El cliente existe y tiene el rol de cliente
        	RequestDispatcher dispatcher= request.getRequestDispatcher("/mvc/view/assistant/doRegister.jsp");
        	request.setAttribute("path", "/Proyecto-Programacion-Web/DoRegistration");
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
			String type = request.getParameter("type");
			GestorInscripciones gi = GestorInscripciones.getInstance();
			
			if(type.equalsIgnoreCase("Partial")) {
					float res = gi.calcularPrecioParcial(Integer.parseInt(idCamp));
					RequestDispatcher disp = request.getRequestDispatcher("/mvc/view/assistant/ConfirmDoRegistration.jsp");
					request.setAttribute("price", Float.toString(res));
					request.setAttribute("idCamp", idCamp);
					request.setAttribute("type", type);
					request.setAttribute("modalidad", gi.calcularModalidad(Integer.parseInt(idCamp)));
					disp.forward(request, response);
			}
			if(type.equalsIgnoreCase("Full")) {
				
					float res = gi.calcularPrecioParcial(Integer.parseInt(idCamp));
					RequestDispatcher disp = request.getRequestDispatcher("/mvc/view/assistant/ConfirmDoRegistration.jsp");
					request.setAttribute("price", Float.toString(res));
					request.setAttribute("type", type);
					request.setAttribute("idCamp", idCamp);
					request.setAttribute("modalidad", gi.calcularModalidad(Integer.parseInt(idCamp)));
					disp.forward(request, response);
				
			}
		}
	}
}

