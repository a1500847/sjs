package pizzicato.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzicato.model.Pizza;
import pizzicato.model.Tilaus;
import pizzicato.model.dao.PizzaDAO;
import pizzicato.model.dao.TilausDAO;


@WebServlet("/ListaaTilauksetKokki")
public class ListaaTilauksetKokki extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * Hakee tilausdaon avulla tilaukset tietokannasta
	 * Hakee pizzadaon avulla pizzojen tiedot tietokannasta 
	 * Liittää pizzat tilaukseen
	 * Tarkistaa oreganon ja valkosipulin tiedot ja muuntaa kyllä/ei muotoon
	 * Asettaa tilaukset attribuutiksi ja välittää tilaukset listan selaimelle. Ohjaa jsphen
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TilausDAO tilausdao = new TilausDAO();
		PizzaDAO pizzadao = new PizzaDAO();
		Pizza pizza;
		
		ArrayList<Tilaus> tilaukset = tilausdao.kokkiFindAll();		
		String oregano;
		String valkosipuli;
		
		for(int i=0; i<tilaukset.size();i++){			
			for(int j=0; j<tilaukset.get(i).getPizzatilaukset().size(); j++){				
				pizza = pizzadao.findCertainPizzaKokki(tilaukset.get(i).getPizzaTilaus(j).getPizzaId());
				tilaukset.get(i).getPizzaTilaus(j).setPizza(pizza);
				
				oregano = tilaukset.get(i).getPizzaTilaus(j).getOregano();
				valkosipuli = tilaukset.get(i).getPizzaTilaus(j).getValkosipuli();
				if(oregano == null||oregano.equals(false)|| oregano.equals(" ")){
					oregano = "ei";
					tilaukset.get(i).getPizzaTilaus(j).setOregano(oregano);
				}else{
					oregano = "kyllä";
					tilaukset.get(i).getPizzaTilaus(j).setOregano(oregano);
				}
				if(valkosipuli == null ||valkosipuli.equals(false) || valkosipuli.equals(" ")){
					valkosipuli = "ei";
					tilaukset.get(i).getPizzaTilaus(j).setValkosipuli(valkosipuli);
				}else{
					valkosipuli = "kyllä";
					tilaukset.get(i).getPizzaTilaus(j).setValkosipuli(valkosipuli);
				}
			}			
		}		
		request.setAttribute("tilaukset", tilaukset);	
	
		String jsp = "/view/tilaukset_kokille.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}
	
	/**
	 * Jos paistettu nappia painetaan selain ohjautuu dopostiin.
	 * Muuttaa tilausdaon avulla tilauksen statuksen tietokantaan
	 * Ohjaa selaimen takaisin kokin näkymään
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TilausDAO tilausdao = new TilausDAO();
		String strTilId = request.getParameter("nappi");
		int tilausId = new Integer(strTilId);
		try {
			tilausdao.modifyStatusKokki(tilausId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("ListaaTilauksetKokki");
		
	}

}
