/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.forteseguro.model;

import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Patrick Santos
 */
public class DijkstraTeste 
{
    private Grafo grafo;
    private Vertice v1, v2, v3, v4, v5, v6, v7;
    private Dijkstra dijkstra;

    /**
     * Este método é executado antes de cada teste de unidade (testes a seguir),
     * e serve para inicializar objetos que são utilizados nos testes.
     */
    @Before
    public void setUp() throws Exception
    {
        grafo = new Grafo();
        
        v1 = new Vertice("estacionamento", "A");
        v2 = new Vertice("", "B");
        v3 = new Vertice("", "C");
        v4 = new Vertice("", "D");
        v5 = new Vertice("", "E");
        v6 = new Vertice("", "F");
        v7 = new Vertice("", "G");
    }
    
    
    /**
     * Teste resposavel por verificar o menor caminho entre dois vertices
     */
    @Test
    public void testMenorCaminho() 
    {        
        grafo.adcionaVertice(v1.getNome(), v1.getTipo());
        grafo.adcionaVertice(v2.getNome(), v2.getTipo());
        grafo.adcionaVertice(v3.getNome(), v3.getTipo());
        grafo.adcionaVertice(v4.getNome(), v4.getTipo());
        grafo.adcionaVertice(v5.getNome(), v5.getTipo());
        grafo.adcionaVertice(v6.getNome(), v6.getTipo());
        grafo.adcionaVertice(v7.getNome(), v7.getTipo());
        
        grafo.adicionaAresta(v1, v2, 5); //a1
        grafo.adicionaAresta(v1, v4, 6); //a2
        grafo.adicionaAresta(v2, v3, 2); //a3
        grafo.adicionaAresta(v3, v4, 4); //a4
        grafo.adicionaAresta(v4, v5, 3); //a5
        grafo.adicionaAresta(v3, v5, 2); //a6
        grafo.adicionaAresta(v3, v7, 6); //a7
        grafo.adicionaAresta(v5, v6, 4); //a8
        grafo.adicionaAresta(v2, v7, 10); //a9
        grafo.adicionaAresta(v6, v7, 2); //a10
        
        dijkstra = new Dijkstra(grafo);
        dijkstra.executar(v1);
        
        List<List<Vertice>> l = dijkstra.getCaminho(grafo.getAdj(), v6);
        
        assertEquals(v1.getNome(), l.get(0).get(0).getNome());
        assertEquals(v2.getNome(), l.get(0).get(1).getNome());
        assertEquals(v3.getNome(), l.get(0).get(2).getNome());
        assertEquals(v5.getNome(), l.get(0).get(3).getNome());
        assertEquals(v6.getNome(), l.get(0).get(4).getNome());
        
    }
    
}
