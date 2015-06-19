/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jabx.examples;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Leandro
 * 
 * IMPORTANTE!
 * Toda classe publica tem de estar especificada na lista de instancia do contexto 
 * do JABX
 * As classes internas tem de ser estaticas.
 */
@XmlRootElement(name = "RootElement")
@XmlType(propOrder = {"element1","pessoas"})
public class XMLClass {
    
    public static void main(String [] args) {
        System.out.println(XMLManager.jaxbObjectToString(new XMLClass()));
    }
    
    private Element element1;
    private List<Lista> pessoas;

    public XMLClass() {
        this.element1 = new Element();
        this.pessoas = new ArrayList<Lista>();
        pessoas.add(new Lista());
        pessoas.add(new Lista());
    }

    @XmlElement(type = Lista.class)
    public List<Lista> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Lista> pessoas) {
        this.pessoas = pessoas;
    }

    public Element getElement1() {
        return element1;
    }

    public void setElement1(Element element1) {
        this.element1 = element1;
    }
    
    @XmlRootElement(name = "Element")
    @XmlType(propOrder = {"id", "elemento2"})
    static class Element {
        private Integer id;
        private String elemento2;
        private String atributo;

        public Element() {
            this.id = 1;
            this.atributo = "atributo";
            this.elemento2 = "ele2";
        }       

        @XmlAttribute
        @XmlJavaTypeAdapter(IDAdapter.class)
        public Integer getId() {
            return id;
        }

        public void setId_element(int id_element) {
            this.id = id_element;
        }

        @XmlAttribute(name = "atributo")
        public String getAtributo() {
            return atributo;
        }

        public void setAtributo(String atributo) {
            this.atributo = atributo;
        }

        public String getElemento2() {
            return elemento2;
        }

        public void setElemento2(String elemento2) {
            this.elemento2 = elemento2;
        }
        
        
    }
    
    @XmlRootElement(name = "Pessoa")
    @XmlType(propOrder = {"endereco"})
    static class Lista {
        private String endereco;

        public Lista() {
            this.endereco = "Rua";
        }

        public String getEndereco() {
            return endereco;
        }

        public void setEndereco(String endereco) {
            this.endereco = endereco;
        }
        
    }
    
    private static class IDAdapter extends XmlAdapter<Integer, Integer> {
        @Override
        public Integer unmarshal(Integer v) throws Exception {
            return v;
        }
        @Override
        public Integer marshal(Integer v) throws Exception {
            return v;
        }
    }
}
