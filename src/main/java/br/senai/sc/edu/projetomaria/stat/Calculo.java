package br.senai.sc.edu.projetomaria.stat;

import br.senai.sc.edu.projetomaria.model.Resultado;

public class Calculo {
	private Integer[] vendas;
	private String tipo;
	private Integer periodo;
	private Integer eqm;
	private Integer[] periodoUtilizado;
	private Double resultadoMedia;
	private Double resultadoMedia2;
	private Double resultadoMedia3;
	private Double resultadoMedia4;
	private Double mediaVenda;
	private Double diferenca;
	private Double mediaMovelDif;
	private String resultado;
	private final static Double alpha = 0.2;
	private Resultado resultadoClass;

	public Calculo() {

	}

	public Calculo(Integer[] vendas, String tipo, Integer periodo) {
		this.vendas = vendas;
		this.tipo = tipo;
		this.periodo = periodo;
		this.periodoUtilizado = new Integer[periodo];
	}

	public Double getResultadoMedia4() {
		return resultadoMedia4;
	}

	public Double getResultadoMedia3() {
		return resultadoMedia3;
	}

	public String getResultado() {
		return resultado;
	}

	public Resultado getResultadoClass() {
		return resultadoClass;
	}

	public Double getResultadoMedia() {
		return resultadoMedia;
	}

	public Double getResultadoMedia2() {
		return resultadoMedia2;
	}

	public Double getErroQuadraticoMedio() {
		return diferenca;
	}

	public void calcularMedia() {
		/*
		 * Caso o tamanho do Array seja menor do que o periodo de analise a fun��o
		 * retorna uma mensagem de erro.
		 */
		if (vendas.length < periodo) {
			resultadoMedia = 0.00;
			resultadoMedia2 = 0.00;
			resultadoMedia3 = 0.00;
			resultadoMedia4 = 0.00;
			diferenca = 0.00;
			return;
		}

		/*
		 * Caso o tamanho do Array seja igual o tamanho do periodo de analise, o calculo
		 * do erro quadr�tico m�dio nao � realizado.
		 */
		if (vendas.length == periodo) {
			eqm = 0;
		} else {
			eqm = 1;
		}

		/* Caso o parametro tipo seja M = M�dia Movel. */
		if (tipo == "M") {
			if (vendas.length > 1) {
				mediaVenda = 0.00;
				int x = 0;
				/*
				 * Para cada posi��o do Array, utiliza-se uma vari�vel para agregar os
				 * valores.
				 */
				for (int i = (vendas.length - periodo); i < vendas.length; i++) {
					mediaVenda += vendas[i];
					periodoUtilizado[x] = vendas[i];
					x = x + 1;
				}
				/*
				 * O resultado da concatena��o � dividido pelo tamanho do array para se
				 * ter a m�dia m�vel do periodo recebido como parametro.
				 */
				resultadoMedia = mediaVenda / (vendas.length - (vendas.length - periodo));

				Integer[] vendas2 = new Integer[periodo];
				for (int j = 0; j < periodo - 1; j++) {
					vendas2[j] = vendas[vendas.length - periodo + j + 1];
				}
				vendas2[periodo - 1] = (int) Math.round(resultadoMedia);

				mediaVenda = 0.00;
				for (int y = 0; y < vendas2.length; y++) {
					mediaVenda += vendas2[y];
				}
				resultadoMedia2 = mediaVenda / vendas2.length;

				Integer[] vendas3 = new Integer[periodo];
				for (int h = 0; h < periodo - 1; h++) {
					vendas3[h] = vendas2[vendas2.length - periodo + h + 1];
				}
				vendas3[periodo - 1] = (int) Math.round(resultadoMedia2);

				mediaVenda = 0.00;
				for (int z = 0; z < vendas3.length; z++) {
					mediaVenda += vendas3[z];
				}
				resultadoMedia3 = mediaVenda / vendas3.length;

				Integer[] vendas4 = new Integer[periodo];
				for (int i = 0; i < periodo - 1; i++) {
					vendas4[i] = vendas3[vendas3.length - periodo + i + 1];
				}
				vendas4[periodo - 1] = (int) Math.round(resultadoMedia3);

				mediaVenda = 0.00;
				for (int i = 0; i < vendas4.length; i++) {
					mediaVenda += vendas4[i];
				}
				resultadoMedia4 = mediaVenda / vendas4.length;

				if (eqm == 1) {
					/* Para o c�lculo do erro quadr�tico m�dio. */
					diferenca = 0.00;
					for (int i = periodo; i < vendas.length; i++) {
						mediaMovelDif = 0.00;
						for (int j = 1; j < (periodo + 1); j++) {
							mediaMovelDif += vendas[i - j];
						}
						mediaMovelDif = mediaMovelDif / periodo;
						diferenca += (vendas[i] - mediaMovelDif) * (vendas[i] - mediaMovelDif);
					}
					diferenca = diferenca / (vendas.length - periodo);
				} else {
					diferenca = 0.0;
				}

			} else {
				if (vendas.length == 1) {
					resultado = "Array com apenas uma posi��o!";
				} else {
					resultado = "Array vazio!";
				}
			}
		} else {
			if (tipo == "E") {
				diferenca = 0.00;
				mediaVenda = 0.00;

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
							+ String.format("%.2f", f[(f.length - 1)]) + " Erro Quadr�tico M�dio: "
							+ String.format("%.2f", diferenca) + ".";
				} else {
					if (vendas.length == 1) {
						resultado = "Array com apenas uma posi��o!";
					} else {
						resultado = "Array vazio!";
					}
				}
			} else {
				resultado = "Parametro 'Tipo de M�dia' incorreto ou inexistente!";
			}
		}
		return;
	}
}
