// Grupo:
// Filipe Augusto RA 1421631
// Juliana Duarte RA 1420539

// O programa simula o funcionamento do sistema de transferência de valores entre contas de um mesmo Banco.

package threadBanco;

public class Main {
	public static void main(String[] args) {
		//Instancia a classe Banco
		Banco b = new Banco();
		//Cria uma thread de transferência para cada conta
        for(int i=0; i < Banco.contas; i++) {
        	new Transferencia(b, i).start();
        }
	}
}