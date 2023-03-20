import classes.LeituraRetornoBancoBrasil;
import classes.LeituraRetornoBradesco;
import classes.ProcessarBoletos;
import interfaces.LeituraRetorno;

public class Main {
    public static void main(String[] args) {

        var processador = new ProcessarBoletos(new LeituraRetornoBancoBrasil());
        var processaBradesco = new ProcessarBoletos(new LeituraRetornoBradesco());

        //processador.setLeituraRetorno(new LeituraRetornoBancoBrasil());
        processador.processar("banco-brasil-1.csv");
        processaBradesco.processar("bradesco-1.csv");

    }
}