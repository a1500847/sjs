package pizzicato.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzicato.model.Tayte;
import pizzicato.model.dao.TayteDAO;


@WebServlet("/MuokkaaTayte")
public class MuokkaaTayteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsp ="/view/lisaa_uusi_tayte.jsp";
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
		dispather.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strId = request.getParameter("tayte_id");
		int tayteId = new Integer(strId);	
		
		String syotettyNimi = request.getParameter("nimi");
		//String syotettyHinta = request.getParameter("hinta");
		//syotettyHinta = syotettyHinta.replace(",", ".");
		//Double pHinta = new Double(syotettyHinta);
				
		Tayte modifiedTayte = new Tayte(tayteId, syotettyNimi);
		TayteDAO modifiedTaytedao = new TayteDAO();
		
		try {
			modifiedTaytedao.modifyTayte(modifiedTayte);
		} catch (SQLException e) {
			System.out.println("Sovelluksessa tapahtui virhe "+ e.getMessage());
			e.printStackTrace();
		}
		
		response.sendRedirect("ListaaTaytteet");
	}

}