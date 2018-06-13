package br.senai.sc.edu.projetomaria.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.senai.sc.edu.projetomaria.model.Canal;
import br.senai.sc.edu.projetomaria.model.Historico;
import br.senai.sc.edu.projetomaria.model.Produto;

public class HistoricoDAO extends AbstractDAO {

	
		public List<Historico> get() {
			ArrayList<Historico> registro = new ArrayList<>();	
			String query = "SELECT ID_CANAL, ID_HISTORICO, PRODUTO_SKU, MES_ANO, QUANTIDADE FROM Historico";
			
			Statement st;
			
			try {
				st = getConnection().createStatement();
				ResultSet rs = st.executeQuery(query);
				
				while (rs.next()) {
					Historico h = new Historico();
					Canal canal =new Canal();
					canal.setId(rs.getInt("ID_CANAL"));
					h.setCanal(canal);
					h.setId(rs.getInt("ID_HISTORICO"));
					Produto produto = new Produto();
					produto.setSku(rs.getInt("PRODUTO_SKU"));
					h.setProduto(produto);
					h.setPeriodo(rs.getDate("MES_ANO").toLocalDate());
					h.setQuantidade(rs.getInt("QUANTIDADE"));
					registro.add(h);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return registro;
		}
	}

