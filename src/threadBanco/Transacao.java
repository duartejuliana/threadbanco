// Grupo:
// Filipe Augusto RA 1421631
// Honorio Martins RA1421554
// Jefferson Inacio RA: 1423234
// Juliana Duarte RA 1420539

// O programa simula o funcionamento do sistema de transa��es de sacar, depositar, transferir e ver saldo em contas de um Banco.

package threadBanco;

public class Transacao extends Thread {
	private Banco b;
	private int idConta;
	
	public Transacao(Banco b, int id) {
		idConta = id;
		this.b = b;
	}
	
	public void run() {
		for(int i = 0; i< 20; i++) {  //ser�o feitas at� 20 transa��es em cada conta (poderia ser um while true, que rodaria at� dar stop no programa)
			int codTransacao = 1+(int)(3 * Math.random()); // gera uma transa��o aleat�ria entre as tr�s dispon�veis
			int quantidade = 1+(int)(50 * Math.random()); // s�o feitas transa��es com valores aleat�rios de 1 a 50 reais
			b.fazerTransacao(codTransacao, idConta, quantidade);
		}
	}
}