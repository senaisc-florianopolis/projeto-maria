package br.senai.sc.edu.projetomaria.io;

import java.util.ArrayList;
import java.util.List;

import br.senai.sc.edu.projetomaria.dao.EstimativaDAO;
import br.senai.sc.edu.projetomaria.model.Resultado;
import br.senai.sc.edu.projetomaria.stat.Calculo;

public class Estimativa {
	public List<Resultado> Calculo(int periodoUtilizado) {
		List<Resultado> result = new ArrayList<>();
		Integer Periodo = periodoUtilizado;
		String TipoMedia = "M";

		EstimativaDAO a = new EstimativaDAO();

		List<Integer> lista_SKU = new ArrayList<>();
		lista_SKU = (ArrayList<Integer>) a.listarSKU();

		List<Integer> lista_historico = new ArrayList<>();

		for (int i = 0; i < lista_SKU.size(); i++) {
			lista_historico = (ArrayList<Integer>) a.listaHistorico(lista_SKU.get(i));
			Integer[] vendas = lista_historico.toArray(new Integer[lista_historico.size()]);
			
			Calculo medias = new Calculo(vendas, TipoMedia, Periodo);
			medias.calcularMedia();

			Integer[] Periodo_Utilizado = new Integer[Periodo];
			for (int j = 0; j < Periodo_Utilizado.length; j++) {
				Periodo_Utilizado[j] = vendas[vendas.length - Periodo + j];
			}

			Resultado r = new Resultado();
			r.setSKU(lista_SKU.get(i));
			r.setPeriodo_total(lista_historico.size());
			r.setPeriodo_utilizado(Periodo_Utilizado);
			r.setResultado_media(medias.getResultado_media());
			r.setResultado_media2(medias.getResultado_media2());
			r.setResultado_media3(medias.getResultado_media3());
			r.setResultado_media4(medias.getResultado_media4());
			r.setErro_quadratico_medio(medias.getErroQuadraticoMedio());

			result.add(r);
			
			for (int x = 0; x < Periodo; x++) {
				System.out.println(r.getPeriodo_utilizado()[x]);
			}		
			System.out.println("============");
		}
		return result;
	}
}