package br.senai.sc.edu.projetomaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.model.Phase;
import br.senai.sc.edu.projetomaria.model.Produto;
import br.senai.sc.edu.projetomaria.resource.Messages;
import br.senai.sc.edu.projetomaria.dao.AbstractDAO;

public class ProdutoDAO extends AbstractDAO{
	private static final Logger LOGGER = LogManager.getLogger();
	int total;
		
	public List<Produto> exportarProdutos(){
        Statement stmt = null;
        ResultSet rs = null;
        String sql = null;
        sql = "SELECT * FROM PRODUTO;";    
          
        List<Produto> p = new ArrayList<>();
        try (Connection conn = getConnection()) {
           stmt = conn.prepareStatement(sql);
           rs = stmt.executeQuery(sql);
           while(rs.next()){
        	   Produto produtos = null;
        	   produtos = new Produto();
        	   produtos.setSku(rs.getInt("SKU"));
        	   produtos.setDescricao(rs.getString("NOME_PRODUTO"));
        	   produtos.setIdComercial(rs.getInt("ID_FAMILIA_COMERCIAL"));
        	   p.add(produtos);
           }
        } catch (SQLException e) {
        	LOGGER.error(e);
        }
        
       return p;
    }
	
	public void salvarProdutos(List<Produto> list){
        String sql = "";
        PreparedStatement stmt;
        int successes = 0;
		total = 0;
		
        for(Produto p : list){
            sql = "INSERT INTO PRODUTO("
                + "SKU,"
                + "NOME_PRODUTO,"
                + "ID_FAMILIA_COMERCIAL) VALUES ("+p.getSku()+",'"+p.getDescricao()+"',"+p.getIdComercial()+");";            
            
            try (Connection conn = getConnection()){
                stmt = conn.prepareStatement(sql);
                stmt.execute();
                successes++;
            } catch (SQLException e) {           	
            	LOGGER.debug(e);
            }        
            total++;
        }        
        LOGGER.info(successes+" de "+total+" "+Messages.SUCCESS_PRODUTO);
    }

	public void updateProduto(ArrayList<Produto> skuIgual) {	
		String sql= "";
		PreparedStatement stmt;
        int successes = 0;
        total = 0;
		
		for(Produto p : skuIgual){
			sql = "UPDATE produto SET NOME_PRODUTO = '"+p.getDescricao()+"', "+"ID_FAMILIA_COMERCIAL = "+p.getIdComercial()+" WHERE SKU = "+p.getSku()+";";
			try (Connection conn = getConnection()) {
				stmt = conn.prepareStatement(sql);
				stmt.executeUpdate();
				successes++;
			} catch (SQLException e) {
				LOGGER.debug(e);
			}
			total++;
		}		
		LOGGER.info(successes+" de "+total+" "+Messages.SUCCESS_PRODUTO);
	}
	
	public void insertSkuPhase(ArrayList<Phase> phase){
		String sql = "";
        PreparedStatement stmt;
        int successes = 0;
		total = 0;
		
        for(Phase p : phase){
            sql = "INSERT INTO sku_phase("
                + "SKU_PHASE_IN,"
                + "SKU_PHASE_OUT) VALUES ("+p.getSkuNew()+","+p.getSkuOld()+");";            
            
            try (Connection conn = getConnection()) {
                stmt = conn.prepareStatement(sql);
                stmt.execute();
                successes++;
            } catch (SQLException e) {
            	LOGGER.debug(e);
            }        
            total++;
        }
        LOGGER.info(successes+" de "+total+" "+Messages.SUCCESS_PRODUTO);        
	}
	
	public void deleteProd(List<Produto> list){
		String sql = "";
		PreparedStatement stmt;
		int successes = 0;
		total = 0;
		
		for(Produto p : list){
			sql = "DELETE FROM PRODUTO WHERE SKU = "+p.getSku()+";";
			try (Connection conn = getConnection()) {
				stmt = conn.prepareStatement(sql);
				stmt.execute();
				successes++;
			} catch (SQLException e) {				
				LOGGER.debug(e);
			}
			total++;
		}
		LOGGER.info(successes+" de "+total+" "+Messages.SUCCESS_PRODUTO);
	}
}
