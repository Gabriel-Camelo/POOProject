package sistema.bancario.Conta;

import java.math.BigDecimal;

import sistema.bancario.Cliente.Cliente;

public class Operacao {
	
	private static final long validOperation = 7164209229879745741L;
	
	public static void deposito(Cliente cli, Conta c, BigDecimal valor) {
		if (c.getStatus()) {
			c.setSaldo(validOperation, true, valor);
		}	
	}
	
	public static void saque(Cliente cli, Conta c, BigDecimal valor) {
		if (c.getStatus()) {
			c.setSaldo(validOperation, false, valor);
		}
	}

}
