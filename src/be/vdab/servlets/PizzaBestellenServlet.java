package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.dump.Pizza;
import be.vdab.repositories.PizzaRepository;

@WebServlet("/pizzas/bestellen.htm")
public class PizzaBestellenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/pizzabestellen.jsp";
	private static final String MANDJE = "mandje";
	private final PizzaRepository pizzaRepository = new PizzaRepository();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("allePizzas", pizzaRepository.findAll());
		//als de gebruiker al een sessie heeft, wordt die opgehaald, indien niet wordt er geen aangemaakt
		HttpSession session = request.getSession(false);
		if (session != null) {
			@SuppressWarnings("unchecked")
			//attribuutwaarden van een session krijg je terug als instances van Object
			//er wordt niet gecontroleerd of het Object effectief een Set<Long> is => compiler waarschuwing onderdrukken
			Set<Long> mandje = (Set<Long>) session.getAttribute(MANDJE);
			if(mandje != null) {
				List<Pizza> pizzasInMandje = new ArrayList<>();
				for (Long id : mandje) {
					pizzasInMandje.add(pizzaRepository.read(id));
				}
				request.setAttribute("pizzasInMandje", pizzasInMandje);
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameterValues("id") != null) {
			//sessie ophalen, als er nog geen bestaat moet er een nieuwe gemaakt worden
			HttpSession session = request.getSession();		
			@SuppressWarnings("unchecked")
			Set<Long> mandje = (Set<Long>)session.getAttribute(MANDJE);
			//als er nog geen mandje is, moet het aangemaakt worden
			if (mandje == null) {
				mandje = new LinkedHashSet<>();
			}
			//de geselecteerde pizza's (request parameters) moeten aan het mandje worden toegevoegd
			for (String id : request.getParameterValues("id")) {
				mandje.add(Long.parseLong(id));
			}
			//geupdate mandje toevoegen
			session.setAttribute(MANDJE, mandje);
		}
		response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
		}

}
