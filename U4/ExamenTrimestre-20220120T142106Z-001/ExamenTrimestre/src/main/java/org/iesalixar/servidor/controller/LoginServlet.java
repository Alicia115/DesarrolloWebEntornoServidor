package org.iesalixar.servidor.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.iesalixar.servidor.dao.DAOUsuarioImpl;
import org.iesalixar.servidor.model.Usuario;
import org.iesalixar.servidor.utils.PasswordHashGenerator;

/**
 * Servlet implementation class InicioServlet
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(LoginServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/loginServlet.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if (email!=null && password!=null) {
			
			DAOUsuarioImpl dao = new DAOUsuarioImpl();
			
			Usuario user = dao.getUsuario(email);
			
			if (user!=null) {
				if (PasswordHashGenerator.checkPassword(password, user.getPassword())) {
					
					HttpSession sesion = request.getSession();
					
					sesion.setAttribute("usuario", user.getUsuario());
					sesion.setAttribute("firstName", user.getFirstName());
					sesion.setAttribute("lastName", user.getLastName());
					sesion.setAttribute("email", user.getEmail());
					sesion.setAttribute("rol", user.getRol());
					
					if ("admin".equals(user.getRol())) {
						response.sendRedirect(request.getContextPath()+"/Home");
					} else {
						response.sendRedirect(request.getContextPath()+"/Home");
					}
					
					
				} else {
				
					request.setAttribute("error", "login inválido");
					doGet(request,response);
					return;
				}
			} else {
				   
				request.setAttribute("error", "Usuario no existente");
				doGet(request,response);
				return;
			}
			
			
		}
	}

}
