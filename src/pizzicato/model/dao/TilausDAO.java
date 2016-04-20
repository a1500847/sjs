package pizzicato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import pizzicato.model.Pizza;
import pizzicato.model.Tilaus;

public class TilausDAO extends DataAccessObject{
	
	private Tilaus readTilaus(ResultSet rs) {
		try {
			int tilausId=rs.getInt("tilaus_id");
			int asiakasId=rs.getInt("asiakas_id");
			String status=rs.getString("status");
			Date tilAjankohta=rs.getDate("til_ajankohta");
			return new Tilaus(tilausId, asiakasId, status, tilAjankohta);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void addTilaus(Tilaus tilaus) throws SQLException {
		Connection connection = null;
		PreparedStatement stmtInsert = null;
		PreparedStatement stmtInsert2 = null;
		PreparedStatement stmtSelect = null;
		ResultSet rs = null;
		int lastId;
		try {
			connection = getConnection();
			String sqlInsert = "INSERT INTO tilaus(p_nimi, p_hinta, p_saatavuus) VALUES (?, ?, ?);";
			String sqlSelect = "SELECT LAST_INSERT_ID();";
			
			stmtInsert = connection.prepareStatement(sqlInsert);
			stmtSelect = connection.prepareStatement(sqlSelect);
			
			
			stmtInsert.setString(1, tilaus.());
			stmtInsert.setDouble(2, tilaus.());
			stmtInsert.setString(3, tilaus.());
			stmtInsert.executeUpdate();
			rs = stmtSelect.executeQuery();
			
			while(rs.next()){
				lastId = rs.getInt("last_insert_id()");
				tilaus.setTilausId(lastId);
			}
			/**for (int i=0; i < tilaus.getTayteLkm(pizza.getPizzaId()); i++) {
			String sqlInsert2 = "INSERT INTO pizzatayte (pizza_id, tayte_id) VALUES ("+pizza.getPizzaId()+", "+pizza.getTayte(i).getTayteId()+");";
			stmtInsert2 = connection.prepareStatement(sqlInsert2);
			stmtInsert2.executeQuery(sqlInsert2);
			}**/
					            
		}catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, connection); 
		}
		
	}
	
	public Tilaus deleteTilaus(int tilausId){
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sqlDelete ="DELETE FROM tilaus WHERE tilaus_id= ?;";
			String sqlDelete2="DELETE FROM pizzatilaus WHERE tilaus_id= ?;";
			stmt=conn.prepareStatement(sqlDelete);
			stmt2=conn.prepareStatement(sqlDelete2);
			stmt.setInt(1, tilausId);
			stmt2.setInt(1, tilausId);
			rs=stmt2.executeQuery();
			rs=stmt.executeQuery();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs,stmt,conn);
		}
		return null;
		
	}
	
	

}