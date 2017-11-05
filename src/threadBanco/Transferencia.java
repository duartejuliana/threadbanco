// Grupo:
// Filipe Augusto RA 1421631
// Juliana Duarte RA 1420539

// O programa simula o funcionamento do sistema de transfer�ncia de valores entre contas de um mesmo Banco.

package threadBanco;

public class Transferencia extends Thread {
	private Banco b;
	private int de;
	
	public Transferencia(Banco b, int id) {
		de = id;
		this.b = b;
	}
	
	public void run() {
		for(int i = 0; i< 20; i++) {  //ser�o feitas at� 20 transa��es em cada conta
			int para = (int)(Banco.contas * Math.random()); //escolhe uma conta aleat�ria de destino da transfer�ncia
			if(para != de) {  //s� transfere se for entre contas diferentes
				int quantidade = 1+(int)(50 * Math.random()); // s�o transferidos valores de 1 a 50 reais
				b.transferir(de, para, quantidade);
			}
		}
	}
}