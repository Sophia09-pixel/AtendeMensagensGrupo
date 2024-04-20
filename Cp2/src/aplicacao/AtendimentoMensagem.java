package aplicacao;

import java.util.Scanner;

import entidades.Mensagem;
import filas.FilaMensagem;

public class AtendimentoMensagem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Aluno: Livia Gallafrio rm: 99508
		// Aluno: Leonardo Mazzuca rm:99430
		// Aluno: Luis Rodrigues rm: 551234
		// Aluno: Sophia de Sousa rm: 551442
		// Aluno: Victor Yudi rm: 98046

		Scanner entrada = new Scanner(System.in);
		Mensagem mensagem = null;
		FilaMensagem filaReclamacao = new FilaMensagem();
		filaReclamacao.init();

		FilaMensagem filaSugestao = new FilaMensagem();
		filaSugestao.init();

		FilaMensagem filaResolucao = new FilaMensagem();
		filaResolucao.init();

		int opcao = 0;
		do {
			System.out.println("0 - Encerrar");
			System.out.println("1 - Recebimento de Mensagem");
			System.out.println("2 - Atendimento de Mensagem");
			System.out.println("3 - Recebimento e Encaminhamento de Resolução");
			opcao = entrada.nextInt();
			switch (opcao) {

			case 0:
				if (filaResolucao.isEmpty() && filaReclamacao.isEmpty() && filaSugestao.isEmpty()) {
					System.out.println("Encerrado o atendimento");
					break;
				} else {
					System.out.println("ainda ha mensagens para serem atendidas");
					opcao = 4;
				}
				break;

			case 1:
				String nome = "";
				String identificacao = "";
				int motivo = 0;
				String mensagemTexto = "";

				System.out.println("Nome (opcional)");
				nome = entrada.nextLine();
				entrada.nextLine();

				System.out.println("Email/Telefone");
				identificacao = entrada.nextLine();

				System.out.println("Motivo do contato (1-reclamação/ 2-sugestão):");
				motivo = entrada.nextInt();
				while (motivo < 1 || motivo > 2) {
					System.out.println("Opcao Inválida!");
					System.out.println("Motivo do contato (1-reclamação/ 2-sugestão):");
					motivo = entrada.nextInt();
				}

				System.out.println("Mensagem (texto): ");
				mensagemTexto = entrada.nextLine();
				mensagemTexto = entrada.nextLine();

				System.out.println("Mensagem recebida com sucesso!");
				System.out.println("");
				mensagem = new Mensagem(identificacao, motivo, mensagemTexto);

				if (motivo == 1) {
					filaReclamacao.enqueue(mensagem);
				} else {
					if (motivo == 2) {
						filaSugestao.enqueue(mensagem);
					}
				}
				break;

			case 2:
				String resposta;
				int motivoAtendimento;
				System.out.println("Digite a opção a ser atendida (1-reclamação/ 2-sugestão):");
				motivoAtendimento = entrada.nextInt();
				while (motivoAtendimento < 1 || motivoAtendimento > 2) {
					System.out.println("Opcao Inválida!");
					System.out.println("(1-reclamação/ 2-sugestão):");
					motivoAtendimento = entrada.nextInt();
				}
				if (motivoAtendimento == 1) {
					if (!filaReclamacao.isEmpty()) {
						System.out.println("Cliente: " + filaReclamacao.first().getIdentificacao() + " - Mensagem: "
								+ filaReclamacao.first().getMensagem());
						System.out.println();
						System.out.println("O assunto pode ser prontamente respondido? (Sim/Não)");
						resposta = entrada.next();
						while(!resposta.equalsIgnoreCase("Sim")&& !resposta.equalsIgnoreCase("Não")) {
							System.out.println("Resposta Inválida! (Sim/Não)");
							resposta = entrada.next();
						}
						if (resposta.equalsIgnoreCase("Sim")) {
							System.out.println(
									"Enviada resposta para cliente: " + filaReclamacao.first().getIdentificacao()
											+ " - Sua solicitação já foi resolvida. Obrigado!!!");
							filaReclamacao.dequeue();
						} else {
							System.out.println("Aguardar resposta do setor responsável pelo assunto");
							filaResolucao.enqueue(filaReclamacao.dequeue());
						}
					} else {
						System.out.println("Não há reclamações aguardando na fila");

					}
				} else {
					if (motivoAtendimento == 2) {
						if (!filaSugestao.isEmpty()) {
							System.out.println("Cliente: " + filaSugestao.first().getIdentificacao() + " - Mensagem: "
									+ filaSugestao.first().getMensagem());
							System.out.println();
							System.out.println("O assunto pode ser prontamente respondido? (Sim/Não)");
							resposta = entrada.next();
							if (resposta.equalsIgnoreCase("Sim")) {
								System.out.println(
										"Enviada resposta para cliente: " + filaSugestao.first().getIdentificacao()
												+ " - Sua solicitação já foi resolvida. Obrigado!!!");
								filaSugestao.dequeue();
							} else {
								System.out.println("Aguardar resposta do setor responsável pelo assunto");
								filaResolucao.enqueue(filaSugestao.dequeue());
							}
						} else {
							System.out.println("Não há sugestões aguardando na fila");
						}
					}
				}
				break;

			case 3:
				if (!filaResolucao.isEmpty()) {
					System.out.println("Cliente: " + filaResolucao.first().getIdentificacao() + " - Mensagem: "
							+ filaResolucao.first().getMensagem());
					System.out.println("");
					System.out.println("Enviada resposta para cliente: " + filaResolucao.first().getIdentificacao()
							+ " solicitação já foi resolvida pelo setor responsável. Obrigado!!!");
					filaResolucao.dequeue();
				} else {
					System.out.println("Não há mensagens na fila de resolução");
				}

				break;

			default:
				System.out.println("Opção inválida");
			}

		} while (opcao != 0);

		entrada.close();
	}

}
