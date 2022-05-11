package sistema.bancario.Cliente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import sistema.bancario.Conta.Conta;

public class Cliente implements Serializable{
	
	private static final long validOperation = 7164209229879745741L;
	
	private static final long serialVersionUID = 1L;
	
	private String first_name;
	private String last_name;
	
	private Long cpf;
	
	private String email;
	
	private Date data_nasc;
	private Date data_cad;
	
	public List<Conta> contas = new ArrayList<>();
	
	private Boolean status;
	
	public Cliente() {
		
	}
	
	public Cliente(String f_name, String l_name, Long cpf, String email, Date data_nasc) {
		this.first_name = f_name;
		this.last_name = l_name;
		this.cpf = cpf;
		this.email = email;
		this.data_nasc = data_nasc;
		this.data_cad = new Date();
		this.status = true;
	}
	
	public Cliente(String f_name, String l_name, Long cpf, Date data_nasc) {
		this.first_name = f_name;
		this.last_name = l_name;
		this.cpf = cpf;
		this.email = null;
		this.data_nasc = data_nasc;
		this.data_cad = new Date();
		this.status = true;
	}
	
	public Cliente(Long cpf) {
		this.cpf = cpf;
	}
	
	private Cliente(String f_name, String l_name, Long cpf, String email, Date data_nasc, Date data_cad, Boolean status) {
		this.first_name = f_name;
		this.last_name = l_name;
		this.cpf = cpf;
		this.email = email;
		this.data_nasc = data_nasc;
		this.data_cad = data_cad;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Cliente [first_name=" + first_name + ", last_name=" + last_name + ", cpf=" + cpf + ", email=" + email
				+ ", data_nasc=" + data_nasc + ", data_cad=" + data_cad + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Cliente other = (Cliente) obj;
		return Objects.equals(cpf, other.cpf);
	}
	
	public String getNome() {
		return this.first_name + " " + this.last_name;
	}
	
	public String getFirstName() {
		return this.first_name;
	}
	
	public String getLastName() {
		return this.last_name;
	}
	
	public Long getCpf() {
		return this.cpf;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public Date getDataCad() {
		return this.data_cad;
	}
	
	public Date getDataNasc() {
		return this.data_nasc;
	}
	
	public Boolean getStatus() {
		return this.status;
	}
	
	public List<Conta> getContas() {
		return contas;
	}
	
	public void adicionarConta(Conta c) throws Exception {
		if (this.status) {
			if (contas.contains(c)) {
				newExc("Conta já cadastrada para este cliente!");
			}else {
				contas.add(c);
			}
		}else {
			newExc("Cliente desativado!");
		}
	}
	
	public void removerConta(Conta c) throws Exception {
		if (!contas.contains(c)) {
			newExc("Conta não está cadastrada para este cliente!");
		}else {
			contas.remove(c);
		}
	}
	
	public void newExc(String msg) throws Exception {
		throw new Exception(msg);
	}
	
	public Cliente returnClient(Long valid, String f_name, String l_name, Long cpf, String email, Date data_nasc, Date data_cad, Boolean status) {
		if (valid == validOperation) {
			Cliente retrn = new Cliente(f_name, l_name, cpf, email, data_nasc, data_cad, status);
			return retrn;
		}else {
			return null;
		}
	}
}
