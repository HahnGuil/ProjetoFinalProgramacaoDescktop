package br.com.empresa.vo.enums;

public enum EstadoEnum {
	AC("ACRE"), AL("ALAGOAS"),AP("AMAPA"),AM("AMAZONAS"),BA("BAHIA"),CE("CEARA"),DF("DISTRITO FEDERAL"),ES("ESPIRITO SANTO"),GO("GOIAS"),MA("MARANHAO"),
	MT("MATO GROSSO"),MS("MATO GROSSO DO SUL"),MG("MINAS GERAIS"),PA("PARA"),PB("PARAIBA"),PR("PARANA"),PE("PERNAMBUCO"),PI("PIAUI"),RJ("RIO DE JANEIRO"),
	RN("RIO GRANDE DO NORTE"),RS("RIO GRANDE DO SUL"),RO("RONDONIA"),RR("RORAIMA"),SC("SANTA CATARINA"),SP("SAO PAULO"),SE("SERGIPE"),TO("TOCANTINS");

	private String estado;

	private EstadoEnum(String descricao) {
		this.estado = descricao;
	}

	@Override
	public String toString() {
		return estado;
	}
}
