/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelagem;

/**
 *
 * @author pedro
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Metodos {
    public static List<String> lerRotas() {
        List<String> listaRotas = new ArrayList<String>();
        try {
            String local = "Rotas.txt";
            FileReader fr = new FileReader(new File(local));
            BufferedReader bf = new BufferedReader(fr);

            while (true) {
                String linha = bf.readLine();
                if (linha == null) {
                    break;
                }
                listaRotas.add(linha);
            }
            bf.close();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return listaRotas;
    }

    public static String gerarXML(List<String> listaRotas) {
            StringBuilder sb = new StringBuilder();
            sb.append("<routes xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"http://sumo.dlr.de/xsd/routes_file.xsd\">\n");

            for (int j = 0; j < listaRotas.size(); j++) {
                String tripId = Integer.toString(j);
                double tempoGerado = j;
                String viaValida = listaRotas.get(j);
                String viaDestino = listaRotas.get((j + 1) % listaRotas.size()); 

                String xmlTrip = String.format("<trip id=\"%s\" depart=\"%s\" from=\"%s\" to=\"%s\"/>\n",
                        tripId, tempoGerado, viaValida, viaDestino);

                sb.append(xmlTrip);
            }

            sb.append("</routes>");
            return sb.toString();
    }

    public static void salvarXML(String xmlArquivo, String nomeArquivo) {
        try {
            FileWriter writer = new FileWriter(nomeArquivo);
            writer.write(xmlArquivo);
            writer.close();
            System.out.println("Arquivo XML salvo como: " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo XML: " + e.getMessage());
        }
    }    
}

