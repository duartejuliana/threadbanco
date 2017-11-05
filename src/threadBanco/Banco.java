// Grupo:
// Filipe Augusto RA 1421631
// Juliana Duarte RA 1420539

// O programa simula o funcionamento do sistema de transferência de valores entre contas de um mesmo Banco.

package threadBanco;

public class Banco {

	public static int total_conta = 100;  //Cada conta começa o programa com 100 reais
	public static int contas = 5;  //Existem 5 contas no banco
	private int conta[];  //Array que armazenará o valor que cada conta possui
	private int transferencia;  //Quantidade de transferências bancárias

	public Banco() {
		//Preenchendo os valores do array "conta"
		conta = new int[contas];
		for(int i = 0; i < contas; i++) {
			conta[i] = total_conta;
		}
		//Verifica o saldo das contas em 0 transferências (começo do programa)
		transferencia = 0;
		verSaldo();
	}
	
	//Método que realiza a transferência. O synchronized garante que só entra uma Thread por vez no método
	public synchronized void transferir(int de, int para, int quantidade) {
		System.out.println("Transferindo de: " + (de + 1) + " para: " + (para + 1) + ", valor: " + quantidade);
		//Se a conta não tiver valor para transferir, a Thread aguarda as outras tranferências para ver se consegue realizar a transferência
		while(conta[de] < quantidade) {
			try {
				wait();
			}
			catch(InterruptedException e) {}
		}
		conta[de] -= quantidade;
		conta[para] += quantidade;
        transferencia++;
        verSaldo();
        //Notifica a liberação para a Thread que está aguardando
        notify();
	}

	// Método que verifica o saldo
	public void verSaldo() {
		int soma = 0;
		for(int i = 0; i < contas; i++) {
			soma += conta[i];
			System.out.println("Conta numero: " + (i + 1) + " Saldo: " + conta[i]);
        }
		// Abaixo o total de transações realizadas e a conferência se a soma do valor em cada conta bate com o total de dinheiro no banco (500 reais)
		System.out.println("Transações: " + transferencia + " Soma: " + soma + "\n");
	}
}