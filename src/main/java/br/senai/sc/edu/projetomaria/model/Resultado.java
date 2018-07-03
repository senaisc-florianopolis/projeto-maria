package br.senai.sc.edu.projetomaria.model;

public class Resultado {
	private Integer SKU;
	private Integer periodo_total;
	private Integer[] periodo_utilizado;
	private Double resultado_media;
	private Double resultado_media2;
	private Double resultado_media3;
	private Double resultado_media4;
	private Double erro_quadratico_medio;
		
	public Resultado() {		
	}
	
	public Resultado(Integer SKU, Integer periodo_total, Integer[] periodo_utilizado, Double resultado_media,
			Double erro_quadratico_medio) {
		this.SKU = SKU;
		this.periodo_total = periodo_total;
		this.periodo_utilizado = periodo_utilizado;
		this.resultado_media = resultado_media;
		this.erro_quadratico_medio = erro_quadratico_medio;
	}

	public String getResultado_media4() {
		return String.format("%.2f",resultado_media4);
	}

	public void setResultado_media4(Double resultado_media4) {
		this.resultado_media4 = resultado_media4;
	}

	public String getResultado_media2() {
		return String.format("%.2f",resultado_media2);
	}

	public void setResultado_media2(Double resultado_media2) {
		this.resultado_media2 = resultado_media2;
	}

	public Integer getSKU() {
		return SKU;
	}

	public void setSKU(Integer SKU) {
		this.SKU = SKU;
	}

	public Integer getPeriodo_total() {
		return periodo_total;
	}

	public void setPeriodo_total(Integer periodo_total) {
		this.periodo_total = periodo_total;
	}

	public Integer[] getPeriodo_utilizado() {
		return periodo_utilizado;
	}

	public void setPeriodo_utilizado(Integer[] periodo_utilizado) {
		this.periodo_utilizado = periodo_utilizado;
	}

	public String getResultado_media() {
		return String.format("%.2f",resultado_media);
	}

	public void setResultado_media(Double resultado_media) {
		this.resultado_media = resultado_media;
	}

	public String getErro_quadratico_medio() {
		return String.format("%.2f",erro_quadratico_medio);
	}

	public void setErro_quadratico_medio(Double erro_quadratico_medio) {
		this.erro_quadratico_medio = erro_quadratico_medio;
	}
	public String getResultado_media3() {
		return String.format("%.2f",resultado_media3);
	}

	public void setResultado_media3(Double resultado_media3) {
		this.resultado_media3 = resultado_media3;
	}
}
