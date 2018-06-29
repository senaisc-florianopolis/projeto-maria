package br.senai.sc.edu.projetomaria.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.model.Phase;
import br.senai.sc.edu.projetomaria.model.Produto;
import br.senai.sc.edu.projetomaria.dao.AbstractDAO;

public class ProdutoDAO extends AbstractDAO{
	private static final Logger LOGGER = LogManager.getLogger();
	
	public ArrayList<Produto> exportarProdutos(){
        Statement stmt = null;
        ResultSet rs = null;
        String sql = null;
        sql = "SELECT * FROM PRODUTO;";    
          
        try {
           stmt = getConnection().prepareStatement(sql);
           rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
        	e.printStackTrace();
        }               
        ArrayList<Produto> p = new ArrayList<>();
        
       try {
           while(rs.next()){
               Produto produtos = null;
               produtos = new Produto();
               produtos.setSku(rs.getInt("SKU"));
               produtos.setDescricao(rs.getString("NOME_PRODUTO"));
               produtos.setIdComercial(rs.getInt("ID_FAMILIA_COMERCIAL"));
               p.add(produtos);
           }
       } catch (SQLException e) {
    	   e.printStackTrace();
    	   LOGGER.debug(e);
       }             
       return p;
    }
	
	public void salvarProdutos(ArrayList<Produto> produtos){
        String sql = "";
        PreparedStatement stmt;
        int successes = 0;
		int failures = 0;
		
        for(Produto p : produtos){
            sql = "INSERT INTO PRODUTO("
                + "SKU,"
                + "NOME_PRODUTO,"
                + "ID_FAMILIA_COMERCIAL) VALUES ("+p.getSku()+",'"+p.getDescricao()+"',"+p.getIdComercial()+");";            
            
            try {
                stmt = getConnection().prepareStatement(sql);
                stmt.execute();
                successes++;
            } catch (SQLException e) {
            	e.printStackTrace();
            	failures++;
            	LOGGER.debug(e);
            }        
        } 
        if(failures == 0){
			LOGGER.info("Todos os " + successes+ " produtos inseridos com sucesso.");
		}else if (failures > 0 && successes > 0){
			LOGGER.info(successes+" produtos inseridos com sucesso e");
			LOGGER.info(failures+" produtos não inseridos.");
		}else if (successes == 0){
			LOGGER.info("Todos os " +failures+ " produtos não inseridos.");
		}
    }

	public void updateProduto(ArrayList<Produto> skuIgual) {	
		String sql= "";
		PreparedStatement stmt;
        int successes = 0;
		int failures = 0;
		
		for(Produto p : skuIgual){
			sql = "UPDATE produto SET NOME_PRODUTO = '"+p.getDescricao()+"', "+"ID_FAMILIA_COMERCIAL = "+p.getIdComercial()+" WHERE SKU = "+p.getSku()+";";
			try {
				stmt = getConnection().prepareStatement(sql);
				stmt.executeUpdate();
				successes++;
			} catch (SQLException e) {
				e.printStackTrace();
				failures++;
				LOGGER.debug(e);
			}
		}
		if(failures == 0){
			LOGGER.info("Todos os " + successes+ " produtos atualzados com sucesso.");
		}else if (failures > 0 && successes > 0){
			LOGGER.info(successes+" produtos atualizados com sucesso e");
			LOGGER.info(failures+" produtos não atualizados.");
		}else if (successes == 0){
			LOGGER.info("Todos os " +failures+ " produtos não atualizados.");
		}
	}
	
	public void insertSkuPhase(ArrayList<Phase> phase){
		String sql = "";
        PreparedStatement stmt;
        int successes = 0;
		int failures = 0;
		
        for(Phase p : phase){
        	System.out.println(p.getSkuNew() + " --" +p.getSkuOld());
            sql = "INSERT INTO sku_phase("
                + "SKU_PHASE_IN,"
                + "SKU_PHASE_OUT) VALUES ("+p.getSkuNew()+","+p.getSkuOld()+");";            
            
            try {
                stmt = getConnection().prepareStatement(sql);
                stmt.execute();
                successes++;
            } catch (SQLException e) {
            	e.printStackTrace();
            	failures++;
            	LOGGER.debug(e);
            }        
        }   
        if(failures == 0){
			LOGGER.info("Todos os " + successes+ " registros inseridos com sucesso.");
		}else if (failures > 0 && successes > 0){
			LOGGER.info(successes+" regitros inseridos com sucesso e");
			LOGGER.info(failures+" registros não inseridos.");
		}else if (successes == 0){
			LOGGER.info("Todos os " +failures+ " registros não deletados.");
		}
	}
	
	public void deleteProd(ArrayList<Produto> delete){
		String sql = "";
		PreparedStatement stmt;
		int successes = 0;
		int failures = 0;
		
		for(Produto p : delete){
			sql = "DELETE FROM PRODUTO WHERE SKU = "+p.getSku()+";";
			try {
				stmt = getConnection().prepareStatement(sql);
				stmt.execute();
				successes++;
			} catch (SQLException e) {
				failures++;
				e.printStackTrace();
				LOGGER.debug(e);
			}
		}
		if(failures == 0){
			LOGGER.info("Todos os " + successes+ " produtos deletados com sucesso.");
		}else if (failures > 0 && successes > 0){
			LOGGER.info(successes+" produtos deletados com sucesso e");
			LOGGER.info(failures+" produtos não deletados.");
		}else if (successes == 0){
			LOGGER.info("Todos os " +failures+ " produtos não deletados.");
		}
	}
}
