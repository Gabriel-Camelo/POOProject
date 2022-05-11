package sistema.bancario.Persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import sistema.bancario.Conta.Conta;

public class ListaConta {
private List<Conta> contasCadastradas = new ArrayList<>();
	
	public ListaConta() {
		carregarDados();
	}
		
	private void salvarDados() {
		try {
			FileOutputStream fos = new FileOutputStream("contas.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(contasCadastradas);
			oos.close();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	private void carregarDados() {
		try {
			FileInputStream fis = new FileInputStream("contas.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			contasCadastradas = (ArrayList<Conta>) ois.readObject();
			ois.close();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void adicionarConta(Conta c) {
		if (!contasCadastradas.contains(c)) {
			contasCadastradas.add(c);
			salvarDados();
			System.out.println("Conta cadastrada com sucesso!");
		}else {
			System.err.println("Conta já cadastrada");
		}
	}
	
	public void removerConta(Conta c) {
		if (contasCadastradas.contains(c)) {
			contasCadastradas.remove(c);
			salvarDados();
			System.out.println("Conta removida com sucesso!");
		}else {
			System.err.println("Conta não cadastrada");
		}
	}
	
	public Conta localizarConta(Long conta)
	{
		Conta temp = new Conta(conta);
		
		if(contasCadastradas.contains(temp)) {
			temp = contasCadastradas.get(contasCadastradas.indexOf(temp));
			return temp;
		}else {
			return null;
		}
	}
	
	public List<Conta> getContas(){
		return this.contasCadastradas;
	}
}
