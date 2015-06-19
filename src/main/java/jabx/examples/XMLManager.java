/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jabx.examples;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
/**
 *
 * @author Leandro
 *
 * Fonte de pesquisa:
 * http://www.joimporturnaldev.com/1234/jaxb-tutorial-example-to-convert-object-to-xml-and-xml-to-object
 * https://jaxb.java.net/tutorial/. 
 * 
 * Exemplo de como utilizar a biblioteca JAXB para a manipulacao de arquivos XML.
 * 
 * String XML para objeto;
 * Arquivo XML para Objeto;
 * Objeto para String XML;
 * Objeto para arquivo XML.
 * 
 */
public class XMLManager {    
    // Colocar aqui as classes que serao utilizadas
    private static final Class[] classes = {
        XMLClass.class,
    };
    
    public static void main(String[] args) {  
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><RootElement>\n" +
                        "    <element1 id=\"1\" atributo=\"atributo\">\n" +
                        "        <elemento2>ele2</elemento2>\n" +
                        "    </element1>\n" +
                        "    <pessoas>\n" +
                        "        <endereco>Rua</endereco>\n" +
                        "    </pessoas>\n" +
                        "    <pessoas>\n" +
                        "        <endereco>Rua</endereco>\n" +
                        "    </pessoas>\n" +
                        "</RootElement>"; 
        XMLClass objeto = (XMLClass) jaxbXMLStringToObject(xml);
        System.out.println(objeto.getElement1().getAtributo());
    }
    
    /* String XML para objeto */
    public static Object jaxbXMLStringToObject(String xml) {
        try {
            JAXBContext context = JAXBContext.newInstance(classes);
            Unmarshaller un = context.createUnmarshaller();
            StringReader reader = new StringReader(xml);
            Object emp = (Object) un.unmarshal(reader);
            return emp;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /* Arquivo de extensao XML para Objeto utilizando caminho do arquivo como parametro */
    public static Object jaxbXMLFileToObject(String file_path) {
        try {
            JAXBContext context = JAXBContext.newInstance(classes);
            Unmarshaller un = context.createUnmarshaller();
            Object emp = (Object) un.unmarshal(new File(file_path));
            return emp;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /* Arquivo de extensao XML para Objeto utilizando um objeto File como parametro */
    public static Object jaxbXMLFileToObject(File xml_file) {
        try {
            JAXBContext context = JAXBContext.newInstance(classes);
            Unmarshaller un = context.createUnmarshaller();
            Object emp = (Object) un.unmarshal(xml_file);
            return emp;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
 
    /* Objeto para String XML */
    public static String jaxbObjectToString(Object object) {
        try {
            StringWriter sw = new StringWriter();
            sw.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            JAXBContext context = JAXBContext.newInstance(classes);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.marshal(object, sw);
            return sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return "";
    }
 
    /* Objeto para arquivo XML */
    public static void jaxbObjectToXML(String FILE_NAME, Object object) { 
        try {
            JAXBContext context = JAXBContext.newInstance(classes);
            Marshaller marshaller = context.createMarshaller();
            // So para ver bonitinho no console
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); 
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            // Imprime no console
            marshaller.marshal(object, System.out);             
            File file = new File(FILE_NAME);
            // se a pasta do arquivo não exisir...
            if(!file.exists()) {
                // crie ela!
                new File(file.getParent()).mkdir();
            }
            // Imprime no arquvo rs
            marshaller.marshal(object, file);            
        } catch (JAXBException e) {
            e.printStackTrace();
        } 
    }
}
