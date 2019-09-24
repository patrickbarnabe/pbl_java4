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

import java.util.Objects;

public class Aresta {

    private int peso;
    private Vertice verticeDestino;
    private Vertice verticeOrigem;

    public Aresta(int peso, Vertice v2, Vertice v1) {
        this.peso = peso;
        this.verticeDestino = v1;
        this.verticeOrigem = v2;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Vertice getVerticeDestino() {
        return verticeDestino;
    }

    public void setVerticeDestino(Vertice v1) {
        this.verticeDestino = v1;
    }
     public Vertice getVerticeOrigem() {
        return verticeOrigem;
    }

    public void setVerticeOrigem(Vertice verticeOrigem) {
        this.verticeOrigem = verticeOrigem;
    }   
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aresta other = (Aresta) obj;
        if (this.peso != other.peso) {
            return false;
        }
        if (!Objects.equals(this.verticeDestino, other.verticeDestino)) {
            return false;
        }
        if (!Objects.equals(this.verticeOrigem, other.verticeOrigem)) {
            return false;
        }
        return true;
    }
}
