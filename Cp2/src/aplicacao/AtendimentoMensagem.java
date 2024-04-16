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
				mensagemTexto = entrada.next();
				entrada.nextLine();
				System.out.println("Mensagem recebida com sucesso!");
				System.out.println("");
				Mensagem mensagem = new Mensagem(identificacao, motivo, mensagemTexto);

				if (motivo == 1) {
					filaReclamacao.enqueue(identificacao);
					filaResolucao.enqueue(identificacao);
				} else {
					if (motivo == 2) {
						filaSugestao.enqueue(identificacao);
					}
				}
				break;

			case 2:
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
						System.out.println("Enviada resposta para cliente: " + filaReclamacao.dequeue()
								+ " - Sua solicitação já foi resolvida. Obrigado!!!");
					} else {
						System.out.println("Não ha reclamações aguardando na fila");
					}
				} else {
					if (motivoAtendimento == 2) {
						if (!filaSugestao.isEmpty()) {
							System.out.println("Enviada resposta para cliente: " + filaSugestao.dequeue()
									+ " - Sua solicitação já foi resolvida. Obrigado!!!");
						} else {
							System.out.println("Não ha sugestões aguardando na fila");
						}
					}
				}
				break;

			case 3:

				break;

			default:
				System.out.println("Opção inválida");

			}

		} while (opcao != 0);

	}

}
