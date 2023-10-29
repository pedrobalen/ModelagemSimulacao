/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package modelagem;

import java.util.List;

/**
 *
 * @author pedro
 */
public class Modelagem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Metodos m = new Metodos();
        List<String> listaRotas = m.lerRotas();
        String xmlArquivo = m.gerarXML(listaRotas);
        m.salvarXML(xmlArquivo, "arquivoTrips.xml");
    }
   
}
