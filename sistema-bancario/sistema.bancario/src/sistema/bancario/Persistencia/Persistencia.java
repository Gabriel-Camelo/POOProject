package sistema.bancario.Persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import sistema.bancario.Cliente.Cliente;

public class Persistencia {

	private List<Cliente> clientesCadastrados = new ArrayList<>();
	
	public Persistencia() {
		carregarDados();
	}
		
	public void salvarDados() {
		try {
			FileOutputStream fos = new FileOutputStream("persistencia.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(clientesCadastrados);
			oos.close();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	private void carregarDados() {
		try {
			FileInputStream fis = new FileInputStream("persistencia.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			clientesCadastrados = (ArrayList<Cliente>) ois.readObject();
			ois.close();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void adicionarCliente(Cliente c) {
		if (!clientesCadastrados.contains(c)) {
			clientesCadastrados.add(c);
			salvarDados();
		}else {
			System.err.println("Cliente já cadastrado");
		}
	}
	
	public void removerCliente(Cliente c) {
		if (clientesCadastrados.contains(c)) {
			clientesCadastrados.remove(c);
			salvarDados();
		}else {
			System.err.println("Cliente não cadastrado");
		}
	}
	
	public Cliente localizarClienteCPF(Long cpf)
	{
		Cliente temp = new Cliente(cpf);
		
		if(clientesCadastrados.contains(temp)) {
			temp = clientesCadastrados.get(clientesCadastrados.indexOf(temp));
			return temp;
		}else {
			return null;
		}
	}
	
	public List<Cliente> getClientes(){
		return this.clientesCadastrados;
	}
}

