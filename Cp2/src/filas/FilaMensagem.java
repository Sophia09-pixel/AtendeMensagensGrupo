package filas;

import entidades.Mensagem;

public class FilaMensagem {
	Mensagem mensagem;
	
	public final int N = 3;
	Mensagem dados[] = new Mensagem[N];
	int ini, fim, cont;

	public void init() {
		ini = fim = cont = 0;
	}
	public boolean isEmpty() {
		return (cont==0);
	}
	public boolean isFull() {
		return (cont==N);
	}
	public void enqueue(Mensagem elem) {
		if (isFull())
			System.out.println("Fila cheia");
		else {
			dados[fim] = elem;
			fim = (fim+1) % N;
			cont++;
		}
	}
	public Mensagem dequeue() {
		Mensagem elem = dados[ini];
		ini = (ini+1) % N;
		cont--;
		return elem;
	}
	
	public Mensagem first() {
		return dados[ini];
	}
	
}
