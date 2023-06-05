package interfaces;

import classes.Boleto;

public interface LeituraRetorno {
    Boleto processarLinhaArquivo(String[] nomeArquivo);
}
