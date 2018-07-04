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

	public String getResultado_media4() {
		return String.format("%.2f",resultadoMedia4);
	}

	public void setResultado_media4(Double resultadoMedia4) {
		this.resultadoMedia4 = resultadoMedia4;
	}

	public String getResultado_media2() {
		return String.format("%.2f",resultadoMedia2);
	}

	public void setResultado_media2(Double resultadoMedia2) {
		this.resultadoMedia2 = resultadoMedia2;
	}

	public Integer getSKU() {
		return sku;
	}

	public void setSKU(Integer SKU) {
		this.sku = SKU;
	}

	public Integer getPeriodo_total() {
		return periodoTotal;
	}

	public void setPeriodo_total(Integer periodoTotal) {
		this.periodoTotal = periodoTotal;
	}

	public Integer[] getPeriodo_utilizado() {
		return periodoUtilizado;
	}

	public void setPeriodo_utilizado(Integer[] periodoUtilizado) {
		this.periodoUtilizado = periodoUtilizado;
	}

	public String getResultado_media() {
		return String.format("%.2f",resultadoMedia);
	}

	public void setResultado_media(Double resultadoMedia) {
		this.resultadoMedia = resultadoMedia;
	}

	public String getErro_quadratico_medio() {
		return String.format("%.2f",erroQuadraticoMedio);
	}

	public void setErro_quadratico_medio(Double erroQuadraticoMedio) {
		this.erroQuadraticoMedio = erroQuadraticoMedio;
	}
	public String getResultado_media3() {
		return String.format("%.2f",resultadoMedia3);
	}

	public void setResultado_media3(Double resultadoMedia3) {
		this.resultadoMedia3 = resultadoMedia3;
	}
}
