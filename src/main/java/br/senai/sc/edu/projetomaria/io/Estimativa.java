package br.senai.sc.edu.projetomaria.io;

import java.util.ArrayList;
import java.util.List;

import br.senai.sc.edu.projetomaria.dao.EstimativaDAO;
import br.senai.sc.edu.projetomaria.model.Resultado;
import br.senai.sc.edu.projetomaria.stat.Calculo;


public class Estimativa {
	String res = "";
	public ArrayList<Resultado> Calculo (int periodoUtilizado){
		ArrayList<Resultado> result = new ArrayList<>();
		Integer Periodo = periodoUtilizado;
		String TipoMedia = "M";

		EstimativaDAO a = new EstimativaDAO();		

		List<Integer> lista_SKU = new ArrayList<>();
		
		lista_SKU = (ArrayList<Integer>) a.listarSKU();
		
		for(Integer i: a.listarSKU()){
			System.out.println("SKU :----------"+i);
		}

		ArrayList<Integer> lista_historico = new ArrayList<>();

		for (int i=0;i<lista_SKU.size();i++)
		{
			lista_historico = (ArrayList<Integer>) a.listaHistorico(lista_SKU.get(i));

			Integer[] vendas = lista_historico.toArray(new Integer[lista_historico.size()]);

			Calculo medias = new Calculo(vendas,TipoMedia,Periodo);	

			medias.calcularMedia();
			
			Integer[] Periodo_Utilizado = new Integer[Periodo];
			for (int j=0;j<Periodo_Utilizado.length;j++) {
				res += Periodo_Utilizado[j] = vendas[vendas.length-Periodo+j];
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
			
			/*System.out.println("SKU:");
			System.out.println(r.getSKU());
			System.out.println("Periodo total analizado:");
			System.out.println(r.getPeriodo_total());
			System.out.println("Resultado Media 1:");
			System.out.println(r.getResultado_media());
			System.out.println("Resultado Media 2:");
			System.out.println(r.getResultado_media2());
			System.out.println("Resultado Media 3:");
			System.out.println(r.getResultado_media3());
			System.out.println("Resultado Media 4:");
			System.out.println(r.getResultado_media4());
			System.out.println("Erro Quadrático Médio:");
			System.out.println(r.getErro_quadratico_medio());			
			System.out.println("-------------------");
			System.out.println("Dados utilizados para média:");*/
			for (int x=0;x<Periodo;x++) {
				System.out.println(r.getPeriodo_utilizado()[x]);
			}
			
		}
		return result;
	}
	
}
