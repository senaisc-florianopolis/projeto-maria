package br.senai.sc.edu.projetomaria.stat;

import br.senai.sc.edu.projetomaria.model.Resultado;

public class Calculo {
	private Integer[] vendas;
	private String tipo;
	private Integer periodo;
	private Integer eqm;
	private Integer[] periodo_utilizado;
	private Double resultado_media;
	private Double resultado_media2;
	private Double resultado_media3;
	private Double resultado_media4;
	private Double media_venda;
	private Double diferenca;
	private Double media_movel_dif;
	private String resultado;
	private final Double alpha = 0.2;
	private Resultado resultado_class;

	public Calculo() {

	}

	public Calculo(Integer[] vendas, String tipo, Integer periodo) {
		this.vendas = vendas;
		this.tipo = tipo;
		this.periodo = periodo;
		this.periodo_utilizado = new Integer[periodo];
	}

	public Double getResultado_media4() {
		return resultado_media4;
	}

	public Double getResultado_media3() {
		return resultado_media3;
	}

	public String getResultado() {
		return resultado;
	}

	public Resultado getResultadoClass() {
		return resultado_class;
	}

	public Double getResultado_media() {
		return resultado_media;
	}

	public Double getResultado_media2() {
		return resultado_media2;
	}

	public Double getErroQuadraticoMedio() {
		return diferenca;
	}

	public void calcularMedia() {
		/*
		 * Caso o tamanho do Array seja menor do que o periodo de analise a função
		 * retorna uma mensagem de erro.
		 */
		if (vendas.length < periodo) {
			resultado = "Periodo escolhido para analise maior do que o historico do produto.";
			return;
		}

		/*
		 * Caso o tamanho do Array seja igual o tamanho do periodo de analise, o calculo
		 * do erro quadrático médio nao é realizado.
		 */
		if (vendas.length == periodo) {
			eqm = 0;
		} else {
			eqm = 1;
		}

		/* Caso o parametro tipo seja M = Média Movel. */
		if (tipo == "M") {
			if (vendas.length > 1) {
				media_venda = 0.00;
				int x = 0;
				/*
				 * Para cada posição do Array, utiliza-se uma variável para agregar os valores.
				 */
				for (int i = (vendas.length - periodo); i < vendas.length; i++) {
					media_venda += vendas[i];
					periodo_utilizado[x] = vendas[i];
					x = x + 1;
				}
				/*
				 * O resultado da concatenação é dividido pelo tamanho do array para se ter a
				 * média móvel do periodo recebido como parametro.
				 */
				resultado_media = media_venda / (vendas.length - (vendas.length - periodo));

				Integer[] vendas2 = new Integer[periodo];
				for (int j = 0; j < periodo - 1; j++) {
					vendas2[j] = vendas[vendas.length - periodo + j + 1];
				}
				vendas2[periodo - 1] = (int) Math.round(resultado_media);

				media_venda = 0.00;
				for (int y = 0; y < vendas2.length; y++) {
					media_venda += vendas2[y];
				}
				resultado_media2 = media_venda / vendas2.length;

				Integer[] vendas3 = new Integer[periodo];
				for (int h = 0; h < periodo - 1; h++) {
					vendas3[h] = vendas2[vendas2.length - periodo + h + 1];
				}
				vendas3[periodo - 1] = (int) Math.round(resultado_media2);

				media_venda = 0.00;
				for (int z = 0; z < vendas3.length; z++) {
					media_venda += vendas3[z];
				}
				resultado_media3 = media_venda / vendas3.length;

				Integer[] vendas4 = new Integer[periodo];
				for (int i = 0; i < periodo - 1; i++) {
					vendas4[i] = vendas3[vendas3.length - periodo + i + 1];
				}
				vendas4[periodo - 1] = (int) Math.round(resultado_media3);

				media_venda = 0.00;
				for (int i = 0; i < vendas4.length; i++) {
					media_venda += vendas4[i];
				}
				resultado_media4 = media_venda / vendas4.length;

				if (eqm == 1) {
					/* Para o cálculo do erro quadrático médio. */
					diferenca = 0.00;
					for (int i = periodo; i < vendas.length; i++) {
						media_movel_dif = 0.00;
						for (int j = 1; j < (periodo + 1); j++) {
							media_movel_dif += vendas[i - j];
						}
						media_movel_dif = media_movel_dif / periodo;
						diferenca += (vendas[i] - media_movel_dif) * (vendas[i] - media_movel_dif);
					}
					diferenca = diferenca / (vendas.length - periodo);
				} else {
					diferenca = 0.0;
				}

			} else {
				if (vendas.length == 1) {
					resultado = "Array com apenas uma posição!";
				} else {
					resultado = "Array vazio!";
				}
			}
		} else {
			if (tipo == "E") {
				diferenca = 0.00;
				media_venda = 0.00;

				if (vendas.length > 1) {
					Double f[] = new Double[vendas.length];
					f[0] = 0.00;
					f[1] = (vendas[(vendas.length - periodo)] + 0.001);
					for (int i = 2 + periodo; i < f.length; i++) {
						f[i] = alpha * vendas[(vendas.length - periodo - 1 + i)] + (1 - alpha) * f[i - 1];
					}
					for (int i = 2; i < f.length; i++) {
						diferenca += (vendas[vendas.length - 12 + i] - f[i]) * (vendas[vendas.length - 12 + i] - f[i]);
					}
					diferenca = diferenca / (f.length - 2);
					resultado = "Resultado Media Exponencial " + f.length + " Meses: "
							+ String.format("%.2f", f[(f.length - 1)]) + " Erro Quadrático Médio: "
							+ String.format("%.2f", diferenca) + ".";
				} else {
					if (vendas.length == 1) {
						resultado = "Array com apenas uma posição!";
					} else {
						resultado = "Array vazio!";
					}
				}
			} else {
				resultado = "Parametro 'Tipo de Média' incorreto ou inexistente!";
			}
		}
		return;
	}
}
