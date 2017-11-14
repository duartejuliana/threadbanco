// Grupo:
// Filipe Augusto RA 1421631
// Honorio Martins RA 1421554
// Jefferson Inacio RA 1423234
// Juliana Duarte RA 1420539

// O programa simula o funcionamento do sistema de transa��es de sacar, depositar, transferir e ver saldo em contas de um Banco.

package threadBanco;

public class Banco {

	public static int total_conta = 100;  //Cada conta come�a o programa com 100 reais
	public static int contas = 5;  //Existem 5 contas no banco
	private int conta[];  //Array que armazenar� o valor que cada conta possui
	private int quantTransacao;  //Quantidade de transa��es banc�rias

	public Banco() {
		//Preenchendo os valores do array "conta"
		conta = new int[contas];
		for(int i = 0; i < contas; i++) {
			conta[i] = total_conta;
		}
		//Verifica o saldo das contas em 0 transfer�ncias (come�o do programa)
		quantTransacao = 0;
		verSaldo();
	}
	
	//M�todo que chama as transfer�ncias. O synchronized garante que s� entra uma Thread por vez
	public synchronized void fazerTransacao (int codTransacao, int idConta, int quantidade) {
		switch (codTransacao) {
		case 1:
			sacar(idConta, quantidade);
			break;
		case 2:
			depositar(idConta, quantidade);
			break;
		case 3:
			int para = (int)(Banco.contas * Math.random()); //escolhe uma conta aleat�ria de destino da transa��o
			if(para != idConta) {  //s� transfere se for entre contas diferentes
				transferir(idConta, para, quantidade);
			}
			break;
		default:
			System.out.println("N�o gerou transa��o");
			break;
		}
	}
	
	//M�todo que realiza transfer�ncia
	private void transferir(int de, int para, int quantidade) {
		System.out.println("Transferir de: " + (de + 1) + " para: " + (para + 1) + ", valor: " + quantidade);
		//Se a conta n�o tiver valor para transferir, a Thread aguarda as outras tranfer�ncias para ver se consegue realizar a transfer�ncia
		while(conta[de] < quantidade) {
			System.out.println("No momento esta conta n�o possui saldo.");
			try {
				wait();
			}
			catch(InterruptedException e) {}
		}
		conta[de] -= quantidade;
		conta[para] += quantidade;
		quantTransacao++;
        verSaldo();
        //Notifica a libera��o para a Thread que est� aguardando
        notify();
	}
	
	//M�todo que realiza saque
	private void sacar (int idConta, int quantidade) {
		System.out.println("Sacar de: " + (idConta + 1) + ", valor: " + quantidade);
		if (conta[idConta] < quantidade) {
			System.out.println("No momento esta conta n�o possui saldo.");
			return;
		}
		conta[idConta] -= quantidade;
		quantTransacao++;
        verSaldo();
	}
	
	//M�todo que realiza dep�sito
	private void depositar (int idConta, int quantidade) {
		System.out.println("Depositar para: " + (idConta + 1) + ", valor: " + quantidade);
		conta[idConta] += quantidade;
		quantTransacao++;
        verSaldo();
	}

	// M�todo que verifica o saldo
	private void verSaldo() {
		for(int i = 0; i < contas; i++) {
			System.out.println("Conta numero: " + (i + 1) + " Saldo: " + conta[i]);
        }
		// Abaixo o total de transa��es realizadas
		System.out.println("Transa��es: " + quantTransacao + "\n");
	}
}