package entidades;

public class Mensagem {

	private String nome;
	private String identificacao;
	private int motivo;
	private String mensagem;

	public Mensagem(String identificacao, int motivo, String mensagem) {
		this.identificacao = identificacao;
		this.motivo = motivo;
		this.mensagem = mensagem;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getIdentificacao() {
		return identificacao;
	}

	public int getMotivo() {
		return motivo;
	}

	public String getMensagem() {
		return mensagem;
	}

	
	
}
