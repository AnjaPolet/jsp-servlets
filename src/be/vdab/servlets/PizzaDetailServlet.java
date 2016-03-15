package be.vdab.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.repositories.PizzaRepository;

/**
 * Servlet implementation class PizzaDetailServlet
 */
@WebServlet("/pizzas/detail.htm")
public class PizzaDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/pizzadetail.jsp";
	private final transient PizzaRepository pizzaRepository = new PizzaRepository();
	
	@Resource(name = PizzaRepository.JNDI_NAME)
	void setDataSource (DataSource dataSource) {
		pizzaRepository.setDataSource(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("pizza", pizzaRepository.read(Long.parseLong(request.getParameter("id"))));
		} catch (NullPointerException | NumberFormatException ex) {
			request.setAttribute("fout", "Nummer niet correct");
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
