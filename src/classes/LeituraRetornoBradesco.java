package classes;

import interfaces.LeituraRetorno;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class LeituraRetornoBradesco implements LeituraRetorno {
    @Override
    public List<Boleto> lerArquivo(String nomeArquivo) {
        System.out.println ("Lendo arquivo Bradesco: " + nomeArquivo);

        var listaBoletos = new LinkedList<Boleto>();
        try {
            var linhas = Files.readAllLines(Paths.get(nomeArquivo));
            for (String linha : linhas) {
                var vetor = linha.split(";");
                var boleto = new Boleto();
                boleto.setId(Integer.parseInt(vetor[0]));
                boleto.setCodBanco(vetor[1]);
                boleto.setAgencia(vetor[2]);
                boleto.setContaBancaria(vetor[3]);
                var formatoData = DateTimeFormatter.ofPattern ("dd/MM/yyyy");
                var formatoDateTime = DateTimeFormatter.ofPattern ("dd/MM/yyyy HH:mm:ss");
                boleto.setDataVencimento(LocalDate.parse(vetor[4], formatoData));
                boleto.setDataPagamento(LocalDateTime.parse(vetor[5], formatoDateTime));
                boleto.setCpfCliente(vetor[6]);
                boleto.setValor(Double.parseDouble(vetor[7]));
                boleto.setMulta(Double.parseDouble(vetor[8]));
                boleto.setJuros(Double.parseDouble(vetor[9]));

                listaBoletos.add(boleto);
            }
        } catch (IOException e) {
            throw new RuntimeException (e);
        }
        return listaBoletos;
    }
}
