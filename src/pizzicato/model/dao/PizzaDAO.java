package pizzicato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pizzicato.model.Pizza;
import pizzicato.model.Tayte;
import pizzicato.model.dao.DataAccessObject;

public class PizzaDAO extends DataAccessObject {

		
		public void addPizza(Pizza pizza) throws SQLException {
			Connection connection = null;
			PreparedStatement stmtInsert = null;
		

			try {
				connection = getConnection();
				String sqlInsert = "INSERT INTO pizza(pizzaId, pNimi, pHinta, pSaatavuus) VALUES (?, ?, ?, ?)";
				stmtInsert = connection.prepareStatement(sqlInsert);
				stmtInsert.setInt(1, pizza.getPizzaId());
				stmtInsert.setString(2, pizza.getpNimi());
				stmtInsert.setDouble(3, pizza.getpHinta());
				stmtInsert.setBoolean(4, pizza.ispSaatavuus());
				stmtInsert.executeUpdate();
			}catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				close(stmtInsert, connection); 
			}
			}
		
		public ArrayList<Pizza> findAll() {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			ArrayList<Pizza> pizzat = new ArrayList<Pizza>();
			Pizza pizza=null;
			try {
				conn = getConnection();
				String sqlSelect ="SELECT pizzaId, pNimi, pHinta, pSaatavuus FROM pizza;";
				stmt=conn.prepareStatement(sqlSelect);
				rs=stmt.executeQuery(sqlSelect);
				while(rs.next()) {
					pizza = readPizza(rs);
					pizzat.add(pizza);
				}
			} catch(SQLException e) {
				throw new RuntimeException(e);
			} finally {
				close(rs,stmt,conn);
			}
			
			return pizzat;
		}
		private Pizza readPizza(ResultSet rs) {
			try {
				int id=rs.getInt("pizza_id");
				String nimi=rs.getString("p_nimi");
				double hinta=rs.getDouble("p_hinta");
				boolean saatavuus=rs.getBoolean("p_saatavuus");
				ArrayList<Tayte> taytteet = new ArrayList<Tayte>();
				return new Pizza(id, nimi, hinta, saatavuus, taytteet);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

	

}