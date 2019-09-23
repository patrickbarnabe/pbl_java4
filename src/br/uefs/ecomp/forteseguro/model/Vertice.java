/*
* Componente Curricular: Módulo Integrado de Programação II
 * Autores: <Patrick Barnabé Moreira Santos e Esdras Abreu Silva>
 * Data:  <23/09/2019>
 *
 * Declaro que este código foi elaborado por mim de forma individual e
 * não contém nenhum trecho de código de outro colega ou de outro autor, 
 * tais como provindos de livros e apostilas, e páginas ou documentos 
 * eletrônicos da Internet. Qualquer trecho de código de outra autoria que
 * uma citação para o  não a minha está destacado com  autor e a fonte do
 * código, e estou ciente que estes trechos não serão considerados para fins
 * de avaliação. Alguns trechos do código podem coincidir com de outros
 * colegas pois estes foram discutidos em sessões tutorias.
 */
package br.uefs.ecomp.forteseguro.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author xstri
 */
public class Vertice {
    
    private String tipo;
    private String nome;
    private boolean visitado;
    private List<Aresta> listaAdjacencias;

    public Vertice(String tipo, String nome) {
        this.tipo = tipo;
        this.nome = nome;
        this.listaAdjacencias = new ArrayList<>();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Aresta> getListaAdjacencias() {
        return listaAdjacencias;
    }

    public void setListaAdjacencias(List<Aresta> listaAdjacencias) {
        this.listaAdjacencias = listaAdjacencias;
    }
    public boolean getVisitado() {
        return visitado;
    }

    public void setVisitar(boolean visitado) {
        this.visitado = visitado;
    }
    
    /**
    * Metodo responsavel por visitar o vertice
    */
    public void visitar(){
        this.visitado = true;
    }
    
}
