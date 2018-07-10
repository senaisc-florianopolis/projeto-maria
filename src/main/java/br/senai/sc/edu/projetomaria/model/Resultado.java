package br.senai.sc.edu.projetomaria.model;

public class Resultado {
	private Integer sku;
	private Integer periodoTotal;
	private Integer[] periodoUtilizado;
	private Double resultadoMedia;
	private Double resultadoMedia2;
	private Double resultadoMedia3;
	private Double resultadoMedia4;
	private Double erroQuadraticoMedio;
		
	public Resultado() {		
	}
	
	public Resultado(Integer sku, Integer periodoTotal, Integer[] periodoUtilizado, Double resultadoMedia,
			Double erroQuadraticoMedio) {
		this.sku = sku;
		this.periodoTotal = periodoTotal;
		this.periodoUtilizado = periodoUtilizado;
		this.resultadoMedia = resultadoMedia;
		this.erroQuadraticoMedio = erroQuadraticoMedio;
	}

	public String getResultadoMedia4() {
		return String.format("%.2f",resultadoMedia4);
	}

	public void setResultadoMedia4(Double resultadoMedia4) {
		this.resultadoMedia4 = resultadoMedia4;
	}

	public String getResultadoMedia2() {
		return String.format("%.2f",resultadoMedia2);
	}

	public void setResultadoMedia2(Double resultadoMedia2) {
		this.resultadoMedia2 = resultadoMedia2;
	}

	public Integer getSKU() {
		return sku;
	}

	public void setSKU(Integer sku) {
		this.sku = sku;
	}

	public Integer getPeriodoTotal() {
		return periodoTotal;
	}

	public void setPeriodoTotal(Integer periodoTotal) {
		this.periodoTotal = periodoTotal;
	}

	public Integer[] getPeriodoUtilizado() {
		return periodoUtilizado;
	}

	public void setPeriodoUtilizado(Integer[] periodoUtilizado) {
		this.periodoUtilizado = periodoUtilizado;
	}

	public String getResultadoMedia() {
		return String.format("%.2f",resultadoMedia);
	}

	public void setResultadoMedia(Double resultadoMedia) {
		this.resultadoMedia = resultadoMedia;
	}

	public String getErroQuadraticoMedio() {
		return String.format("%.2f",erroQuadraticoMedio);
	}

	public void setErroQuadraticoMedio(Double erroQuadraticoMedio) {
		this.erroQuadraticoMedio = erroQuadraticoMedio;
	}
	public String getResultadoMedia3() {
		return String.format("%.2f",resultadoMedia3);
	}

	public void setResultadoMedia3(Double resultadoMedia3) {
		this.resultadoMedia3 = resultadoMedia3;
	}
}
