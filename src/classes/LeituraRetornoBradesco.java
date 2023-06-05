package classes;

import interfaces.LeituraRetorno;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LeituraRetornoBradesco implements LeituraRetorno {
    @Override
    public Boleto processarLinhaArquivo(String[] vetor) {
        var boleto = new Boleto();
        boleto.setId(Integer.parseInt(vetor[0]));
        boleto.setCodBanco(vetor[1]);
        boleto.setAgencia(vetor[2]);
        boleto.setContaBancaria(vetor[3]);
        var formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var formatoDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        boleto.setDataVencimento(LocalDate.parse(vetor[4], formatoData));
        boleto.setDataPagamento(LocalDateTime.parse(vetor[5], formatoDateTime));
        boleto.setCpfCliente(vetor[6]);
        boleto.setValor(Double.parseDouble(vetor[7]));
        boleto.setMulta(Double.parseDouble(vetor[8]));
        boleto.setJuros(Double.parseDouble(vetor[9]));

        return boleto;
    }
}
