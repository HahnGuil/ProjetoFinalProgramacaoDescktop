package br.com.empresa.vo.enums;

public enum TipoPessoaEnum {
	F("Física"), J("Jurídica");

	private String tipoPessoa;

	private TipoPessoaEnum(String descricao) {
		this.tipoPessoa = descricao;
	}

	@Override
	public String toString() {
		return tipoPessoa;
	}
}
