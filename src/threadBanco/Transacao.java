// Grupo:
// Filipe Augusto RA 1421631
// Honorio Martins RA1421554
// Jefferson Inacio RA: 1423234
// Juliana Duarte RA 1420539

// O programa simula o funcionamento do sistema de transações de sacar, depositar, transferir e ver saldo em contas de um Banco.

package threadBanco;

public class Transacao extends Thread {
	private Banco b;
	private int idConta;
	
	public Transacao(Banco b, int id) {
		idConta = id;
		this.b = b;
	}
	
	public void run() {
		for(int i = 0; i< 20; i++) {  //serão feitas até 20 transações em cada conta (poderia ser um while true, que rodaria até dar stop no programa)
			int codTransacao = 1+(int)(3 * Math.random()); // gera uma transação aleatória entre as três disponíveis
			int quantidade = 1+(int)(50 * Math.random()); // são feitas transações com valores aleatórios de 1 a 50 reais
			b.fazerTransacao(codTransacao, idConta, quantidade);
		}
	}
}