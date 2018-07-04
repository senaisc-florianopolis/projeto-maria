package br.senai.sc.edu.projetomaria.io;

import java.util.ArrayList;
import java.util.List;

import br.senai.sc.edu.projetomaria.dao.EstimativaDAO;
import br.senai.sc.edu.projetomaria.model.Resultado;
import br.senai.sc.edu.projetomaria.stat.Calculo;

public class Estimativa {
	public List<Resultado> calculo(int periodoUtilizado) {
		List<Resultado> result = new ArrayList<>();
		Integer periodo = periodoUtilizado;
		String tipoMedia = "M";

		EstimativaDAO dao = new EstimativaDAO();

		List<Integer> listaSku = (ArrayList<Integer>) dao.listarSKU();

		for (int i = 0; i < listaSku.size(); i++) {
			List<Integer>listarHistorico = (ArrayList<Integer>) dao.listaHistorico(listaSku.get(i));
			Integer[] vendas = listarHistorico.toArray(new Integer[listarHistorico.size()]);
			
			Calculo medias = new Calculo(vendas, tipoMedia, periodo);
			medias.calcularMedia();

			
			Integer[] periodoUtilizadoList = new Integer[periodo];
			if (vendas.length > periodo){
				for (int j = 0; j < periodoUtilizadoList.length; j++) {
					periodoUtilizadoList[j] = vendas[vendas.length - periodo + j];
				}
			}else{
				for (int j = 0; j < periodoUtilizadoList.length; j++) {
					periodoUtilizadoList[j] = 0;
				}	
			}

			Resultado r = new Resultado();
			r.setSKU(listaSku.get(i));
			r.setPeriodo_total(periodo);
			r.setPeriodo_utilizado(periodoUtilizadoList);
			r.setResultado_media(medias.getResultado_media());
			r.setResultado_media2(medias.getResultado_media2());
			r.setResultado_media3(medias.getResultado_media3());
			r.setResultado_media4(medias.getResultado_media4());
			r.setErro_quadratico_medio(medias.getErroQuadraticoMedio());

			result.add(r);
		}
		return result;
	}
}