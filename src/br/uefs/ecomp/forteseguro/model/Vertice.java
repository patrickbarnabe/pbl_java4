/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.forteseguro.model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author xstri
 */
public class Vertice {
    
    private String tipo;
    private String nome;
    private List<Aresta> listaAdjacencias;

    public Vertice(String tipo, String nome) {
        this.tipo = tipo;
        this.nome = nome;
        this.listaAdjacencias = new LinkedList<>();
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
    
}
