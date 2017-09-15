package br.com.pjbank.sdk;

import java.io.IOException;
import java.util.Calendar;

import br.com.pjbank.sdk.exceptions.PJBankException;
import br.com.pjbank.sdk.models.common.Cliente;
import br.com.pjbank.sdk.models.common.Endereco;
import br.com.pjbank.sdk.models.recebimento.BoletoRecebimento;
import br.com.pjbank.sdk.recebimento.BoletosManager;

public class Teste {
	public static void main(String args[]) throws IOException, PJBankException {
		BoletosManager boletos = new BoletosManager("4cf241f56af796df9b65b0e8272fcd6959a32cd4", "19f9cb139e97af62ad7dfc395ceb2011e3e06adf");
		Cliente cliente = new Cliente("paulo","06971437675", new Endereco("Rua Turfa", 1109, "apto 302", "barroca", "Belo Horizonte", "mg", "30411110"),31,999843379,"paulo.vitor@opatecnologia.com.br");
		Calendar vencimento = Calendar.getInstance();
		vencimento.set(Calendar.DAY_OF_MONTH, 29);
		BoletoRecebimento aCriar = new BoletoRecebimento(cliente, 100.01, 0.0, 0.0, 0.0, vencimento.getTime(), "", "-", "1", "1");
		BoletoRecebimento boleto = boletos.create(aCriar);
		System.out.println(boleto);
	}
}
