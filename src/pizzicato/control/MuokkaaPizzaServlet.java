package pizzicato.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzicato.model.Pizza;
import pizzicato.model.dao.PizzaDAO;

@WebServlet("/MuokkaaPizza")
public class MuokkaaPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
	/**MuokkaaPizzaServletin doGet metodi hakee muokattavan pizzan tiedot tietokannasta PizzaDAOn metodilla ja luo k�ytt�j�n n�kym�n selaimelle**/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsp = "/view/muokkaa_pizza.jsp";		
		
		String idString = request.getParameter("pizza_id");
		int pizzaId = new Integer(idString);
		PizzaDAO pizzadao = new PizzaDAO();
		Pizza pizza = pizzadao.findCertainPizza(pizzaId);
		request.setAttribute("pizza", pizza);
				
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
		
	}

	/**MuokkaaPizzaServletin doPost metodi hakee k�ytt�j�n sy�tt�m�t tiedot selaimelta ja l�hett�� muokatt tiedot PizzaDAOon.**/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String jsp = "/view/pizzalista_omistajalle.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);		
		
		Map<String, String> errors = validate(request);
	      if (errors.isEmpty()) {
			  PizzaDAO modifiedPizzadao = new PizzaDAO();
			  Pizza pizza = (Pizza) request.getAttribute("pizza");
			  try {
					modifiedPizzadao.modifyPizza(pizza);
				} catch (SQLException e) {
					System.out.println("Sovelluksessa tapahtui virhe "+ e.getMessage());
					e.printStackTrace();
				}
		
	    	  response.sendRedirect("ListaaPizzat");
	         return;
	      }else{
	    	  response.sendRedirect("MuokkaaPizza");
	    	  System.out.println(errors);
	      }

	   }
	   
	   public static Map<String, String> validate(HttpServletRequest request)
	   {
	      Pizza pizza = new Pizza();
	      Map<String, String> errors = new HashMap<String, String>();
	      request.setAttribute("errors", errors);
	      request.setAttribute("pizza", pizza);
	      
	      //haetaan id
	       String idString = request.getParameter("pizza_id");
	       int pizzaId = new Integer(idString);
	       pizza.setPizzaId(pizzaId);	 
	      
	      // nimen validointi
	      String syotettyNimi = request.getParameter("nimi");
	      if (syotettyNimi == null || syotettyNimi.trim().length() == 0)
	      {
	         errors.put("nimi", "Nimi vaaditaan.");
	      }
	      pizza.setpNimi(syotettyNimi);

	      // hinnan validointi
	      String syotettyHinta = request.getParameter("hinta");
	      syotettyHinta = syotettyHinta.replace(",", ".");
		  Double pHinta = new Double(syotettyHinta);
	      if (pHinta == null  || pHinta == 0 || pHinta > 100)
	      {
	    	 String strHinta = String.valueOf(pHinta);
	         errors.put("pHinta", "Hinta vaaditaan.");
	      }
	      pizza.setpHinta(pHinta);
	      
	      
	      String pSaatavuus = request.getParameter("valikoimassa");
	      if (pSaatavuus.equalsIgnoreCase("kyll�")){
	    	  pSaatavuus = "true";
	    	  pizza.setpSaatavuus(pSaatavuus);
	      }if (pSaatavuus.equalsIgnoreCase("ei")){
	    	  pSaatavuus = "false";
	    	  pizza.setpSaatavuus(pSaatavuus);
	      } else{
	    	  errors.put("pSaatavuus", "Saatavuus vaaditaan.");
	      }
	      return errors;
	   }
	
		
}
