/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelagem;

/**
 *
 * @author pedro
 */
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Metodos {

    public static void processarXML() {
        List<String> fromLista = new ArrayList<>();
        List<String> toLista = new ArrayList<>();

        try {
            File arq = new File("map.net.xml"); 
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(arq);
            doc.getDocumentElement().normalize();

            NodeList listaCon = doc.getElementsByTagName("connection");

            for (int i = 0; i < listaCon.getLength(); i++) {
                Node node = listaCon.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) node;
                    String from = elemento.getAttribute("from");
                    String to = elemento.getAttribute("to");
                    fromLista.add(from);
                    toLista.add(to);
                }
            }

            
            criarArquivoTrips(fromLista, toLista);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void criarArquivoTrips(List<String> fromLista, List<String> toLista) {
        try {
            FileWriter writer = new FileWriter("map.trips.xml");

            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<routes xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"http://sumo.dlr.de/xsd/routes_file.xsd\">\n");

            for (int i = 0; i < fromLista.size(); i++) {
                int tripId = i;
                double tempoGerado = (double) i;

                String xmlTrip = String.format("\t<trip id=\"%s\" depart=\"%.2f\" from=\"%s\" to=\"%s\"/>\n",
                        tripId, tempoGerado, fromLista.get(i), toLista.get(i));

                writer.write(xmlTrip);
            }

            writer.write("</routes>");
            writer.close();
            System.out.println("Arquivo 'trips.xml' criado com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
