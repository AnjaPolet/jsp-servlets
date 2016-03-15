package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.dump.Pizza;
import be.vdab.repositories.PizzaRepository;

@WebServlet("/pizzas/voorkeuren.htm")
public class VoorkeurPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/voorkeurpizzas.jsp";
	private final transient PizzaRepository pizzaRepository = new PizzaRepository();
	
	@Resource(name = PizzaRepository.JNDI_NAME)
	void setDataSource (DataSource dataSource) {
		pizzaRepository.setDataSource(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("pizzas", pizzaRepository.findAll());
		if (request.getParameterValues("id") != null){
			List<Pizza> voorkeurpizzas = new ArrayList<>();
			for (String id : request.getParameterValues("id")){
				voorkeurpizzas.add(pizzaRepository.read(Long.parseLong(id)));
			}
			request.setAttribute("voorkeurpizzas", voorkeurpizzas);
		}
		
		request.getRequestDispatcher(VIEW).forward(request, response);

	}

}
