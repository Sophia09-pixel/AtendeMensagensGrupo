package filas;

public class FilaMensagem {

	public final int N = 3;
	String dados[] = new String[N];
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
	public void enqueue(String elem) {
		if (isFull())
			System.out.println("Fila cheia");
		else {
			dados[fim] = elem;
			fim = (fim+1) % N;
			cont++;
		}
	}
	public String dequeue() {
		String elem = dados[ini];
		ini = (ini+1) % N;
		cont--;
		return elem;
	}
	
	public String first() {
		return dados[ini];
	}
	
}
