package sistema.bancario.Model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Scanner;

import sistema.bancario.Cliente.Cliente;
import sistema.bancario.Conta.Conta;
import sistema.bancario.Conta.Operacao;
import sistema.bancario.Persistencia.ListaConta;
import sistema.bancario.Persistencia.Persistencia;

public class App {
	public static void main(String[] args) throws Exception {		
		Persistencia p = new Persistencia();
		ListaConta listac = new ListaConta();
		@SuppressWarnings("resource")
		Scanner entrada = new Scanner(System.in);
		System.out.println(listac.getContas());
		do {
			System.out.println("************************************************");
			System.out.println("*************** SISTEMA BANCÁRIO ***************");
			System.out.println("************************************************");
			System.out.println("Data: " + new Date() + "");
		
			System.out.println(" ");
			System.out.println(" ");
			System.out.println("Escolha a opção desejada:");
			System.out.println("0 - Cadastrar nova conta");
			System.out.println("1 - Relaizar deposito");
			System.out.println("2 - Realizar saque");
			System.out.println("3 - Realizar Transferencia");
			System.out.println("4 - Adicionar cliente");
			System.out.println("5 - Adicionar conta");
			System.out.println("6 - Listar clientes cadastrados");
			System.out.println("7 - Listar contas de cliente");
			System.out.println("8 - Localizar cliente pelo cpf");
			System.out.println("9 - Remover Conta");
			System.out.println("10 - Remover Cliente");
			System.out.println(" ");
			System.out.println("Opção: ");
			int opc = entrada.nextInt();
		
			switch(opc) {
			case 0:
				System.out.println("Digite um número para a nova conta: (Apenas numeros)");
				Long numContaN = entrada.nextLong();
				Conta newCon = new Conta(numContaN);
				if (!listac.getContas().contains(newCon)) {
					listac.adicionarConta(newCon);
				}else {
					System.out.println("Conta já cadastrada!");
				}
				break;
			case 1:
				System.out.println("Insira a conta a qual deseja realizar o deposito:");
				Long nmrConta = entrada.nextLong();
				System.out.println("Insira a quantia a ser depositada:");
				BigDecimal quantia = new BigDecimal(entrada.nextFloat());
			
				Conta contadps = listac.localizarConta(nmrConta);
				
				Cliente clientedps = p.localizarClienteCPF(contadps.getCpf());
			
				if (clientedps.getContas().contains(contadps)) {
					int index = clientedps.getContas().indexOf(contadps);
					Operacao.deposito(clientedps, clientedps.contas.get(index), quantia);
					p.salvarDados();
					System.out.println("Deposito realizado com sucesso");
				}else {
					System.err.println("Conta não localizada!");
				}
				break;
			case 2:
				System.out.println("Insira a conta a qual deseja realizar o saque:");
				Long nmrContaS = entrada.nextLong();
				System.out.println("Insira a quantia a ser depositada:");
				BigDecimal quantiaS = new BigDecimal(entrada.nextFloat());
			
				Conta contasaque = listac.localizarConta(nmrContaS);
				Cliente clientesaque = p.localizarClienteCPF(contasaque.getCpf());
			
				if (clientesaque.getStatus()) {
					if (clientesaque.getContas().contains(contasaque)) {
						int index = clientesaque.getContas().indexOf(contasaque);
						if (clientesaque.getContas().get(index).getSaldo().doubleValue() < quantiaS.doubleValue()) {
							System.out.println("Saldo em conta insuficiente!");
						}else {
							Operacao.saque(clientesaque, clientesaque.contas.get(index), quantiaS);
							p.salvarDados();
							System.out.println("Saque realizado com sucesso");
						}
					}else {
						System.err.println("Conta não localizada!");
					}
				}
				break;
				
			case 3:
				System.out.println("Insira a conta da qual deseja enviar a quantia:");
				Long nmrContaT = entrada.nextLong();
				System.out.println("Insira a conta a qual deseja enviar a quantia:");
				Long nmrContaT2 = entrada.nextLong();
				System.out.println("Insira a quantia que deseja enviar:");
				BigDecimal quantiaT = new BigDecimal(entrada.nextFloat());
			
				Conta contaEnviar = listac.localizarConta(nmrContaT);
				Conta contaReceber = listac.localizarConta(nmrContaT2);
				Cliente clienteT1 = p.localizarClienteCPF(contaEnviar.getCpf());
				Cliente clienteT2 = p.localizarClienteCPF(contaReceber.getCpf());
			
				if (clienteT1.getStatus()) {
					if (clienteT2.getStatus()) {
						if (clienteT1.getContas().contains(contaEnviar)) {
							if (clienteT2.getContas().contains(contaReceber)) {
								int index1 = clienteT1.getContas().indexOf(contaEnviar);
								if (clienteT1.getContas().get(index1).getSaldo().doubleValue() < quantiaT.doubleValue()) {
									System.out.println("Saldo em conta insuficiente!");
								}else {
									Operacao.saque(clienteT1, clienteT1.contas.get(index1), quantiaT);
									int index2 = clienteT2.getContas().indexOf(contaEnviar);
									Operacao.deposito(clienteT2, clienteT2.contas.get(index2), quantiaT);
									p.salvarDados();
									System.out.println("Transferência realizada com sucesso");
								}
							}else {
								System.err.println("Conta destino não localizada!");
							}
						}else {
							System.err.println("Conta não localizada!");
						}
					}else {
						System.err.println("Conta destino desativada!");
					}
				}else {
					System.err.println("Conta desativada!");
				}
				break;
			case 4:
				System.out.println("Insira o primeiro nome do cliente:");
				String fname = entrada.next();
				System.out.println("Insira o sobrenome do cliente");
				String lname = entrada.next();
				System.out.println("Insira o CPF do cliente (Apenas números):");
				Long cpf = entrada.nextLong();
				System.out.println("Insira o email do cliente:");
				String email = entrada.next();
				System.out.println("Insira a data de nascimento do cliente:");
				Date dataNasc = new Date(entrada.nextInt());
				
				Cliente newClient = new Cliente(fname, lname, cpf, email, dataNasc);
				
				p.adicionarCliente(newClient);

				break;
			case 5:
				System.out.println("Insira o CPF do novo titular da conta:");
				long cpfcli = entrada.nextLong();
				System.out.println("Insira o número da conta:");
				Long numeroC = entrada.nextLong();
				
				Conta cadConta = listac.localizarConta(numeroC);
				Cliente cadCliente = p.localizarClienteCPF(cpfcli);
				if (listac.getContas().contains(cadConta)) {
					if (cadConta.getCpf() == null) {
						if (!cadCliente.getContas().contains(cadConta)) {
							cadCliente.adicionarConta(cadConta);
						}else {
							cadConta.UniqueIDForUseSetCpf(cpfcli);
							System.out.println("Conta ja cadastrada, cpf atualizado!");
						}
					}else {
						System.err.println("Esta conta já pertence a um cliente!");
					}
				}
				
				break;
			case 6:
				if (p.getClientes().size() > 0) {
					for (int i = 0; i < p.getClientes().size(); i++) {
						System.out.println(p.getClientes().get(i).toString());
					}
				}else {
					System.out.println("Nenhum Cliente cadastrado!");
				}
				
				break;
			case 7:
				System.out.println("Insira o CPF do cliente a ser consultado:");
				Long cpfCons = entrada.nextLong();
				
				Cliente res = p.localizarClienteCPF(cpfCons);
				
				System.out.println(res.toString());
				
				for (int h = 0; h < listac.getContas().size(); h++) {
					System.out.println(listac.getContas().get(h).toString());
				}
				break;
			case 8:
				System.out.println("Insira o CPF do cliente a ser consultado:");
				Long cpfCons2 = entrada.nextLong();
				
				Cliente res2 = p.localizarClienteCPF(cpfCons2);
				
				System.out.println(res2.toString());
				break;
			case 9:
				System.out.println("Insira o número da conta a ser removida:");
				Long nconta = entrada.nextLong();
				
				Conta contatemp = new Conta(nconta);
				
				if (listac.getContas().contains(contatemp)) {
					listac.removerConta(contatemp);
				}else {
					System.out.println("Conta não encontrada nos registros!");
				}
				break;
			case 10:
				System.out.println("Insira o CPF do cliente a ser removido: (Apenas números)");
				long cpfrem = entrada.nextLong();
				
				Cliente clienteTemp = new Cliente(cpfrem);
				
				if (p.getClientes().contains(clienteTemp)) {
					p.removerCliente(clienteTemp);
					System.out.println("Cliente removido com sucesso!");
				}else {
					System.out.println("Cliente não encontrado nos registros!");
				}
				break;
			case 11:
				if (listac.getContas().size() == 0) {
					System.out.println("Nenhuma conta cadastrada!");
				}else {
					System.out.println(listac.getContas().toString());
				}
				break;
			default:
				System.out.println("Opção inválida, escolha uma das opções apresentadas");
				break;
			}
		}while(true);
	}
}