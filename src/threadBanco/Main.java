// Grupo:
// Filipe Augusto RA 1421631
// Honorio Martins RA1421554
// Jefferson Inacio RA: 1423234
// Juliana Duarte RA 1420539

// O programa simula o funcionamento do sistema de transações de sacar, depositar, transferir e ver saldo em contas de um Banco.

package threadBanco;

public class Main {
	public static void main(String[] args) {
		//Instancia a classe Banco
		Banco b = new Banco();
		//Cria uma thread de transferência para cada conta
        for(int i=0; i < Banco.contas; i++) {
        	new Transacao(b, i).start();
        }
	}
}