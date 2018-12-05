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

		List<Integer> listaSku = dao.listarSKU();

		for (int i = 0; i < listaSku.size(); i++) {
			List<Integer> listarHistorico = dao.listaHistorico(listaSku.get(i));
			Integer[] vendas = listarHistorico.toArray(new Integer[listarHistorico.size()]);

			Calculo medias = new Calculo(vendas, tipoMedia, periodo);
			medias.calcularMedia();

			Integer[] periodoUtilizadoList = new Integer[periodo];
			if (vendas.length > periodo) {
				for (int j = 0; j < periodoUtilizadoList.length; j++) {
					periodoUtilizadoList[j] = vendas[vendas.length - periodo + j];
				}
			} else {
				for (int j = 0; j < periodoUtilizadoList.length; j++) {
					periodoUtilizadoList[j] = 0;
				}
			}

			Resultado r = new Resultado();
			r.setSKU(listaSku.get(i));
			r.setPeriodoTotal(periodo);
			r.setPeriodoUtilizado(periodoUtilizadoList);
			r.setResultadoMedia(medias.getResultadoMedia());
			r.setResultadoMedia2(medias.getResultadoMedia2());
			r.setResultadoMedia3(medias.getResultadoMedia3());
			r.setResultadoMedia4(medias.getResultadoMedia4());
			r.setErroQuadraticoMedio(medias.getErroQuadraticoMedio());

			result.add(r);
		}
		return result;
	}
}