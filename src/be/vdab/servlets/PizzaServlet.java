package be.vdab.servlets;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.dump.Pizza;
import be.vdab.repositories.PizzaRepository;

/**
 * Servlet implementation class PizzaServlet
 */
@WebServlet("/pizzas.htm")
public class PizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW ="/WEB-INF/JSP/pizzas.jsp";
	private static final String PIZZA_REQUESTS = "pizzaRequests";
	private final transient PizzaRepository pizzaRepository = new PizzaRepository();
	
	@Resource(name = PizzaRepository.JNDI_NAME)
	void setDataSource (DataSource dataSource) {
		pizzaRepository.setDataSource(dataSource);
	}
	
	@Override
	public void init() throws ServletException {
	this.getServletContext().setAttribute(PIZZA_REQUESTS, new AtomicInteger());	
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		((AtomicInteger) this.getServletContext().getAttribute(PIZZA_REQUESTS)).incrementAndGet();
		List<Pizza> pizzas = pizzaRepository.findAll();
		String pizzaFotosPad = this.getServletContext().getRealPath("/pizzafotos");
		Set<Long> pizzaIdsMetFoto = new HashSet<>();
		for (Pizza pizza : pizzas) {
			File file = new File(String.format("%s/%d.jpg", pizzaFotosPad,pizza.getId()));
			if (file.exists()) {
				pizzaIdsMetFoto.add(pizza.getId());
			}
		}
		request.setAttribute("pizzas", pizzas);
		request.setAttribute("pizzaIdsMetFoto", pizzaIdsMetFoto);
		request.getRequestDispatcher(VIEW).forward(request, response);
		
	}

}
