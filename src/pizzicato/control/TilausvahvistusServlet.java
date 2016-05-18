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
import javax.servlet.http.HttpSession;

import pizzicato.model.Tilaus;
import pizzicato.model.dao.TilausDAO;

@WebServlet("/tilausvahvistus")
public class TilausvahvistusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TilausvahvistusServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//servlet tarkastaa, että tilaus menee perille ja ilmoittaa tilauksen onnistuneen tai epäonnistuneen
		String jsp = "/view/tilaus_epaonnistui.jsp";
		HttpSession session = request.getSession(true);
		Tilaus tilaus = (Tilaus) session.getAttribute("tilaus");
		TilausDAO tilausdao = new TilausDAO();
		String viesti = null;
		request.setAttribute("viesti", viesti);
		try {
			tilausdao.addTilaus(tilaus);
			jsp = "/view/tilausvahvistus.jsp";
		} catch (SQLException e) {
			jsp = "/view/tilaus_epaonnistui.jsp";
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//hakee session ja tyhjentää tilauksen
		HttpSession session = request.getSession(true);
		session.removeAttribute("tilaus");
	}

}
