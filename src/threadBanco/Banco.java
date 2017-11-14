// Grupo:
// Filipe Augusto RA 1421631
// Honorio Martins RA 1421554
// Jefferson Inacio RA 1423234
// Juliana Duarte RA 1420539

// O programa simula o funcionamento do sistema de transações de sacar, depositar, transferir e ver saldo em contas de um Banco.

package threadBanco;

public class Banco {

	public static int total_conta = 100;  //Cada conta começa o programa com 100 reais
	public static int contas = 5;  //Existem 5 contas no banco
	private int conta[];  //Array que armazenará o valor que cada conta possui
	private int quantTransacao;  //Quantidade de transações bancárias

	public Banco() {
		//Preenchendo os valores do array "conta"
		conta = new int[contas];
		for(int i = 0; i < contas; i++) {
			conta[i] = total_conta;
		}
		//Verifica o saldo das contas em 0 transferências (começo do programa)
		quantTransacao = 0;
		verSaldo();
	}
	
	//Método que chama as transferências. O synchronized garante que só entra uma Thread por vez
	public synchronized void fazerTransacao (int codTransacao, int idConta, int quantidade) {
		switch (codTransacao) {
		case 1:
			sacar(idConta, quantidade);
			break;
		case 2:
			depositar(idConta, quantidade);
			break;
		case 3:
			int para = (int)(Banco.contas * Math.random()); //escolhe uma conta aleatória de destino da transação
			if(para != idConta) {  //só transfere se for entre contas diferentes
				transferir(idConta, para, quantidade);
			}
			break;
		default:
			System.out.println("Não gerou transação");
			break;
		}
	}
	
	//Método que realiza transferência
	private void transferir(int de, int para, int quantidade) {
		System.out.println("Transferir de: " + (de + 1) + " para: " + (para + 1) + ", valor: " + quantidade);
		//Se a conta não tiver valor para transferir, a Thread aguarda as outras tranferências para ver se consegue realizar a transferência
		while(conta[de] < quantidade) {
			System.out.println("No momento esta conta não possui saldo.");
			try {
				wait();
			}
			catch(InterruptedException e) {}
		}
		conta[de] -= quantidade;
		conta[para] += quantidade;
		quantTransacao++;
        verSaldo();
        //Notifica a liberação para a Thread que está aguardando
        notify();
	}
	
	//Método que realiza saque
	private void sacar (int idConta, int quantidade) {
		System.out.println("Sacar de: " + (idConta + 1) + ", valor: " + quantidade);
		if (conta[idConta] < quantidade) {
			System.out.println("No momento esta conta não possui saldo.");
			return;
		}
		conta[idConta] -= quantidade;
		quantTransacao++;
        verSaldo();
	}
	
	//Método que realiza depósito
	private void depositar (int idConta, int quantidade) {
		System.out.println("Depositar para: " + (idConta + 1) + ", valor: " + quantidade);
		conta[idConta] += quantidade;
		quantTransacao++;
        verSaldo();
	}

	// Método que verifica o saldo
	private void verSaldo() {
		for(int i = 0; i < contas; i++) {
			System.out.println("Conta numero: " + (i + 1) + " Saldo: " + conta[i]);
        }
		// Abaixo o total de transações realizadas
		System.out.println("Transações: " + quantTransacao + "\n");
	}
}