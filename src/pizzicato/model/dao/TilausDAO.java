package pizzicato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import pizzicato.model.Pizza;
import pizzicato.model.PizzaTilaus;
import pizzicato.model.Tayte;
import pizzicato.model.Tilaus;

public class TilausDAO extends DataAccessObject{
	
	private Tilaus readTilaus(ResultSet rs) {
		try {
			int tilausId=rs.getInt("tilaus_id");
			String status=rs.getString("status");
			Date tilAjankohta=rs.getDate("til_ajankohta");
			String aEtunimi=rs.getString("a_etunimi");
			String aSukunimi=rs.getString("a_sukunimi");
			String aPuh=rs.getString("a_puh");
			String aOsoite=rs.getString("a_osoite");
			int aPostiNro=rs.getInt("a_posti_nro");
			String aPostiTmp=rs.getString("a_posti_tmp");
			return new Tilaus(tilausId, status, tilAjankohta, aEtunimi, aSukunimi, aPuh, aOsoite, aPostiNro, aPostiTmp);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void addTilaus(Tilaus tilaus) throws SQLException {
		Connection connection = null;
		PreparedStatement stmtInsert = null;		
		try {
			connection = getConnection();
			String sqlInsert = "INSERT INTO tilaus(a_etunimi, a_sukunimi, a_puh, a_osoite, a_posti_nro, a_posti_tmp) VALUES ('"+tilaus.getaEtunimi()+"','"+tilaus.getaSukunimi()+"','"+tilaus.getaPuh()+"','"+tilaus.getaOsoite()+"',"+tilaus.getaPostiNro()+",'"+tilaus.getaPostiTmp()+"');";
			stmtInsert = connection.prepareStatement(sqlInsert);		
			stmtInsert.executeUpdate();
					            
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
	
	public ArrayList<Tilaus> findAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;
		ArrayList<Tilaus> tilaukset = new ArrayList<Tilaus>();
		Tilaus tilaus=null;
		//ArrayList<PizzaTilaus> pizzatilaukset = new ArrayList<PizzaTilaus>();
		PizzaTilausDAO pizzatildao = new PizzaTilausDAO();
		PizzaTilaus pizzatil;
		int edellinenTilausId=0;
		int nykyinenTilausId=0;
		try {
			conn = getConnection();
			String sqlSelect ="SELECT t.tilaus_id, t.asiakas_id, puh, osoite, status, til_ajankohta, pt.pizza_id, p_nimi, lkm FROM tilaus t INNER JOIN asiakas a ON t.asiakas_id = a.asiakas_id INNER JOIN pizzatilaus pt ON t.tilaus_id = pt.tilaus_id INNER JOIN pizza p ON p.pizza_id = pt.pizza_id ORDER BY til_ajankohta;";
			
			stmt=conn.prepareStatement(sqlSelect);
			
			rs=stmt.executeQuery(sqlSelect);
			
			while(rs.next()) {
				nykyinenTilausId = rs.getInt("tilaus_id");
				if (nykyinenTilausId != edellinenTilausId) {
					tilaus = readTilaus(rs);
					tilaukset.add(tilaus);
					edellinenTilausId = nykyinenTilausId;
				}
				pizzatil = pizzatildao.readPizzaTilaus(rs);
				tilaus.addPizzaTilaus(pizzatil);
			}

		
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close2(rs,stmt,stmt2,conn);
		}
		
		return tilaukset;
	}
	
	public ArrayList<Tilaus> omistajaFindAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;
		ArrayList<Tilaus> tilaukset = new ArrayList<Tilaus>();
		Tilaus tilaus=null;
		//ArrayList<PizzaTilaus> pizzatilaukset = new ArrayList<PizzaTilaus>();
		PizzaTilausDAO pizzatildao = new PizzaTilausDAO();
		PizzaTilaus pizzatil;
		int edellinenTilausId=0;
		int nykyinenTilausId=0;
		try {
			conn = getConnection();
			String sqlSelect ="SELECT t.tilaus_id, status, til_ajankohta, a_etunimi, a_sukunimi, a_puh, a_osoite, a_posti_nro, a_posti_tmp, pt.pizza_id, p_nimi, p_hinta, p_saatavuus, lkm FROM tilaus t JOIN pizzatilaus pt ON t.tilaus_id = pt.tilaus_id INNER JOIN pizza p ON p.pizza_id = pt.pizza_id ORDER BY til_ajankohta;";
			
			stmt=conn.prepareStatement(sqlSelect);
			
			rs=stmt.executeQuery(sqlSelect);
			
			while(rs.next()) {
				nykyinenTilausId = rs.getInt("tilaus_id");
				if (nykyinenTilausId != edellinenTilausId) {
					tilaus = readTilaus(rs);
					tilaukset.add(tilaus);
					edellinenTilausId = nykyinenTilausId;
				}
				pizzatil = pizzatildao.readPizzaTilaus(rs);
				tilaus.addPizzaTilaus(pizzatil);
			}

		
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close2(rs,stmt,stmt2,conn);
		}
		
		return tilaukset;
	}
	
	
	public ArrayList<Tilaus> kuskiFindAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;
		ArrayList<Tilaus> tilaukset = new ArrayList<Tilaus>();
		Tilaus tilaus=null;
		//ArrayList<PizzaTilaus> pizzatilaukset = new ArrayList<PizzaTilaus>();
		PizzaTilausDAO pizzatildao = new PizzaTilausDAO();
		PizzaTilaus pizzatil;
		int edellinenTilausId=0;
		int nykyinenTilausId=0;
		try {
			conn = getConnection();
			String sqlSelect ="SELECT tilaus_id, etunimi, sukunimi, puh, osoite, postinro, postitmp, status, til_ajankohta, p_nimi, lkm FROM tilaus t INNER JOIN pizzatilaus pt ON t.tilaus_id = pt.tilaus_id INNER JOIN pizza p ON p.pizza_id = pt.pizza_id ORDER BY til_ajankohta;";
			
			stmt=conn.prepareStatement(sqlSelect);
			
			rs=stmt.executeQuery(sqlSelect);
			
			while(rs.next()) {
				nykyinenTilausId = rs.getInt("tilaus_id");
				if (nykyinenTilausId != edellinenTilausId) {
					tilaus = readTilaus(rs);
					tilaukset.add(tilaus);
					edellinenTilausId = nykyinenTilausId;
				}
				pizzatil = pizzatildao.readPizzaTilaus(rs);
				tilaus.addPizzaTilaus(pizzatil);
			}

		
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close2(rs,stmt,stmt2,conn);
		}
		
		return tilaukset;
	}
	
	public ArrayList<Tilaus> kokkiFindAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;
		ArrayList<Tilaus> tilaukset = new ArrayList<Tilaus>();
		Tilaus tilaus=null;
		//ArrayList<PizzaTilaus> pizzatilaukset = new ArrayList<PizzaTilaus>();
		PizzaTilausDAO pizzatildao = new PizzaTilausDAO();
		PizzaTilaus pizzatil;
		int edellinenTilausId=0;
		int nykyinenTilausId=0;
		try {
			conn = getConnection();
			String sqlSelect ="SELECT tilaus_id, status, til_ajankohta, pizza_id, lkm FROM tilaus t JOIN pizzatilaus pt ON t.tilaus_id = pt.tilaus_id  ORDER BY til_ajankohta;";
			
			stmt=conn.prepareStatement(sqlSelect);
			
			rs=stmt.executeQuery(sqlSelect);
			
			while(rs.next()) {
				nykyinenTilausId = rs.getInt("tilaus_id");
				if (nykyinenTilausId != edellinenTilausId) {
					tilaus = readTilaus(rs);
					tilaukset.add(tilaus);
					edellinenTilausId = nykyinenTilausId;
				}
				pizzatil = pizzatildao.readPizzaTilaus(rs);
				tilaus.addPizzaTilaus(pizzatil);
			}

		
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close2(rs,stmt,stmt2,conn);
		}
		
		return tilaukset;
	}
	
	/**public ArrayList<Tilaus> omistajaFindAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;
		ArrayList<Tilaus> tilaukset = new ArrayList<Tilaus>();
		Tilaus tilaus=null;
		//ArrayList<PizzaTilaus> pizzatilaukset = new ArrayList<PizzaTilaus>();
		PizzaTilausDAO pizzatildao = new PizzaTilausDAO();
		PizzaTilaus pizzatil;
		int edellinenTilausId=0;
		int nykyinenTilausId=0;
		try {
			conn = getConnection();
			String sqlSelect ="SELECT tilaus_id, sukunimi, puh, osoite, postinro, postitmp, status, til_ajankohta, p_nimi, lkm FROM tilaus t INNER JOIN pizzatilaus pt ON t.tilaus_id = pt.tilaus_id INNER JOIN pizza p ON p.pizza_id = pt.pizza_id ORDER BY til_ajankohta;";
			
			stmt=conn.prepareStatement(sqlSelect);
			
			rs=stmt.executeQuery(sqlSelect);
			
			while(rs.next()) {
				nykyinenTilausId = rs.getInt("tilaus_id");
				if (nykyinenTilausId != edellinenTilausId) {
					tilaus = readTilaus(rs);
					tilaukset.add(tilaus);
					edellinenTilausId = nykyinenTilausId;
				}
				pizzatil = pizzatildao.readPizzaTilaus(rs);
				tilaus.addPizzaTilaus(pizzatil);
			}

		
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close2(rs,stmt,stmt2,conn);
		}
		
		return tilaukset;
	}**/
	
	
	/**public void modifyTilaus(Tilaus tilaus) throws SQLException { // pizzatilausdaossa muokataan mitä pizzoja ja kuinka monta
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			String sqlUpdate = "UPDATE tilaus SET p_nimi='"+pizza.getpNimi()+"', p_hinta="+pizza.getpHinta()+", p_saatavuus='"+pizza.getpSaatavuus()+"' WHERE pizza_id="+pizza.getPizzaId()+";";
			stmt = conn.prepareStatement(sqlUpdate);
			stmt.executeUpdate();
			String sqlDelete = "DELETE FROM pizzatayte WHERE pizza_id ="+pizza.getPizzaId()+";";
			stmt2=conn.prepareStatement(sqlDelete);
			rs=stmt.executeQuery(sqlDelete);
			for (int i=0; i < pizza.getTayteLkm(pizza.getPizzaId()); i++) {
				String sqlInsert = "INSERT INTO pizzatayte (pizza_id, tayte_id) VALUES ("+pizza.getPizzaId()+", "+pizza.getTayte(i).getTayteId()+");";
				stmt=conn.prepareStatement(sqlInsert);
				rs=stmt.executeQuery(sqlInsert);
				
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			close2(rs,stmt,stmt2,conn);
		}
	}**/

}
