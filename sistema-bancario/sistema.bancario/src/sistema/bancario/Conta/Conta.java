package sistema.bancario.Conta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Conta implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final long validOperation = 7164209229879745741L;

	private Long conta;
	
	private Boolean status;
	
	private Long cpf_cliente = 000l;
	
	private BigDecimal saldo;
	
	public Conta() {
		
	}
	
	public Conta(Long conta, Long cpf_cliente) {
		this.conta = conta;
		this.cpf_cliente = cpf_cliente;
		this.saldo = new BigDecimal("0");
		this.status = true;
	}
	
	public Conta(Long conta) {
		this.conta = conta;
		this.cpf_cliente = 000l;
		this.saldo = new BigDecimal("0");
		this.status = true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(conta);
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
		Conta other = (Conta) obj;
		return Objects.equals(conta, other.conta);
	}
	
	@Override
	public String toString() {
		return "Conta [conta=" + conta + ", status=" + status + ", cpf_cliente=" + cpf_cliente + ", saldo=" + saldo
				+ "]";
	}

	public BigDecimal getSaldo() {
		return this.saldo;
	}
	
	public Long getCpf() throws Exception {
		return this.cpf_cliente;		
	}
	
	public Long getConta() {
		return this.conta;
	}
	
	public Boolean getStatus() {
		return this.status;
	}
	
	protected void setSaldo(Long uniID, Boolean tipo, BigDecimal valor) {
		if (uniID == validOperation) {
			if (tipo) {
				this.saldo.add(valor);
			}else {
				this.saldo.subtract(valor);
			}
		}
	}
	
	public void UniqueIDForUseSetCpf(long ID) {
		this.cpf_cliente = ID;
	}
	
	
}
