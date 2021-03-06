package pizzicato.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pizzicato.model.Pizza;
import pizzicato.model.Tilaus; 
import pizzicato.model.PizzaTilaus; 
import pizzicato.model.dao.TilausDAO; 
import pizzicato.model.dao.PizzaDAO; 
import pizzicato.model.dao.PizzaTilausDAO; 



@WebServlet("/ostoskori")
public class OstoskoriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Hakee session, poistaa viesti-attribuutin sessiosta
	 * Ohjaa jsphen
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		session.removeAttribute("viesti");
		String jsp = "/view/pizzicato_ostoskori.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}


	/**
	 * Hakee session, hakee tilaus attribuutin sessiosta
	 * Jos tilausta ei ole, luodaan uusi
	 * Luodaan pizzatilausolio, haetaan pizzadaolla pizzantietoja ja lisätään pizzatilaukseen
	 * tilaus-olioon lisätään pizzatilauksetlista
	 * Ohjataan takaisin etusivuservlettiin
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PizzaDAO pizzadao = new PizzaDAO();
		Tilaus tilaus;
		PizzaTilaus pizzatilaus;		
		
		
		//avataan uusi sessio
		HttpSession session = request.getSession(true);
		tilaus = (Tilaus) session.getAttribute("tilaus");
		
		//jos tilausta ei ole, luodaan uusi tilaus
		if(tilaus ==null) {
			tilaus=new Tilaus();
			session.setAttribute("tilaus", tilaus);
		}

		//luodaan pizzatilaus-olio
		pizzatilaus= new PizzaTilaus();
		//lis�t��n pt-oliolle aseta lkm, pizzaId
		String StrLkm = request.getParameter("pizzamaara");
		int lkm = Integer.parseInt(StrLkm);
	
		//haetaan valittu pizza ja lis�t��n pizzatilaukseen
		String StrPizzaId = request.getParameter("pizza_id");
		int pizza_id = Integer.parseInt(StrPizzaId);
		Pizza pizza =pizzadao.findCertainPizza(pizza_id);
		pizzatilaus.setPizza(pizza);
				
		//tilaus-olion pizzatilaukset-listaan lis�t��n pizzatilausolio
		for (int i = 0; i < lkm; i++) {		
			tilaus.addPizzaTilaus(pizzatilaus);
		}
		if (tilaus != null){
			session.setAttribute("viesti", "Pizzasi on lisätty ostoskoriin!");
		}
		response.sendRedirect("Etusivu#pizzamenu");
	}
	
}
