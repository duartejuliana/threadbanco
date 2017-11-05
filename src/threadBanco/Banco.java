// Grupo:
// Filipe Augusto RA 1421631
// Juliana Duarte RA 1420539

// O programa simula o funcionamento do sistema de transfer�ncia de valores entre contas de um mesmo Banco.

package threadBanco;

public class Banco {

	public static int total_conta = 100;  //Cada conta come�a o programa com 100 reais
	public static int contas = 5;  //Existem 5 contas no banco
	private int conta[];  //Array que armazenar� o valor que cada conta possui
	private int transferencia;  //Quantidade de transfer�ncias banc�rias

	public Banco() {
		//Preenchendo os valores do array "conta"
		conta = new int[contas];
		for(int i = 0; i < contas; i++) {
			conta[i] = total_conta;
		}
		//Verifica o saldo das contas em 0 transfer�ncias (come�o do programa)
		transferencia = 0;
		verSaldo();
	}
	
	//M�todo que realiza a transfer�ncia. O synchronized garante que s� entra uma Thread por vez no m�todo
	public synchronized void transferir(int de, int para, int quantidade) {
		System.out.println("Transferindo de: " + (de + 1) + " para: " + (para + 1) + ", valor: " + quantidade);
		//Se a conta n�o tiver valor para transferir, a Thread aguarda as outras tranfer�ncias para ver se consegue realizar a transfer�ncia
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
        //Notifica a libera��o para a Thread que est� aguardando
        notify();
	}

	// M�todo que verifica o saldo
	public void verSaldo() {
		int soma = 0;
		for(int i = 0; i < contas; i++) {
			soma += conta[i];
			System.out.println("Conta numero: " + (i + 1) + " Saldo: " + conta[i]);
        }
		// Abaixo o total de transa��es realizadas e a confer�ncia se a soma do valor em cada conta bate com o total de dinheiro no banco (500 reais)
		System.out.println("Transa��es: " + transferencia + " Soma: " + soma + "\n");
	}
}