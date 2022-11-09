package br.com.empresa.vo.enums;

public enum StatusEnum {
	A("Ativo"), I("Inativo");

	private String status;

	private StatusEnum(String descricao) {
		this.status = descricao;
	}

	@Override
	public String toString() {
		return status;
	}
}
