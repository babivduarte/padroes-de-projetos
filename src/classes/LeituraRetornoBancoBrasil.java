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

public class LeituraRetornoBancoBrasil implements LeituraRetorno {
    @Override
    public List<Boleto> lerArquivo(String nomeArquivo) {
        System.out.println ("Lendo arquivo BB: " + nomeArquivo);

        var listaBoletos = new LinkedList<Boleto>();
        try {
            var linhas = Files.readAllLines(Paths.get(nomeArquivo));
            for (String linha : linhas) {
                var vetor = linha.split(";");
                var boleto = new Boleto();
                boleto.setId(Integer.parseInt(vetor[0]));
                boleto.setCodBanco(vetor[1]);
                var formatoData = DateTimeFormatter.ofPattern ("dd/MM/yyyy");
                boleto.setDataVencimento(LocalDate.parse(vetor[2], formatoData));
                boleto.setDataPagamento(LocalDate.parse(vetor[3], formatoData).atTime(0,0));
                boleto.setCpfCliente(vetor[4]);
                boleto.setValor(Double.parseDouble(vetor[5]));
                boleto.setMulta(Double.parseDouble(vetor[6]));
                boleto.setJuros(Double.parseDouble(vetor[7]));

                listaBoletos.add(boleto);
            }
        } catch (IOException e) {
            throw new RuntimeException (e);
        }
        return listaBoletos;
    }
}
