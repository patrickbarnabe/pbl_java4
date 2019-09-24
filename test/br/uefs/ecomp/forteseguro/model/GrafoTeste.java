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

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Patrick e Esdras
 */
public class GrafoTeste 
{
    private Grafo grafo;
    private Vertice v1, v2, v3, v4, v5, v6, v7;
    private Aresta a1, a2, a3, a4, a5, a6, a7, a8;
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
        v2 = new Vertice("origem", "B");
        v3 = new Vertice("chegada", "C");
        v4 = new Vertice("estacionamento", "D");
        v5 = new Vertice("origem", "E");
        v6 = new Vertice("chegada", "F");
        v6 = new Vertice("chegada", "G");
        
        a1 = new Aresta(10, v1, v2);
        a2 = new Aresta(14, v1, v3);
        a3 = new Aresta(3, v2, v3);
        a4 = new Aresta(10, v1, v2);
        a5 = new Aresta(14, v1, v3);
        a6 = new Aresta(3, v2, v3);
        a7 = new Aresta(10, v1, v2);
        a8 = new Aresta(14, v1, v3);
    }
    
    /**
     * Teste de unidade que verifica se os vertice de um grafo são
     * inseridos corretamente.
     */
    @Test
    public void testInserirVertice() 
    {
        grafo.adcionaVertice(v1.getNome(), v1.getTipo());
        
        assertEquals(v1.getNome(), grafo.get("A").getNome());
        assertEquals(v1.getTipo(), grafo.get("A").getTipo());
        
        grafo.adcionaVertice(v2.getNome(), v2.getTipo());
        
        assertEquals(v2.getNome(), grafo.get("B").getNome());
        assertEquals(v2.getTipo(), grafo.get("B").getTipo());
        
        grafo.adcionaVertice(v3.getNome(), v3.getTipo());
        
        assertEquals(v3.getNome(), grafo.get("C").getNome());
        assertEquals(v3.getTipo(), grafo.get("C").getTipo());
    }
    
    /**
     * Teste de unidade que verifica se os vertice de um grafo são
     * removidos corretamente.
     */
    @Test
    public void testRemoverVertice() 
    {
        this.testInserirVertice();
        
        grafo.removerVertice(v1);
        grafo.removerVertice(v2);
        grafo.removerVertice(v3);
        
        assertNull(grafo.get(v1.getNome()));
        assertNull(grafo.get(v2.getNome()));
        assertNull(grafo.get(v3.getNome()));
    }
    
    /**
     * Teste de unidade que verifica se os aresta de um grafo são
     * inseridos corretamente.
     */
    @Test
    public void testInserirAresta() 
    {
        this.testInserirVertice();
        
        grafo.adicionaAresta(a1.getVerticeOrigem(), a1.getVerticeDestino(), a1.getPeso());
        grafo.adicionaAresta(a2.getVerticeOrigem(), a2.getVerticeDestino(), a2.getPeso());
        grafo.adicionaAresta(a3.getVerticeOrigem(), a3.getVerticeDestino(), a3.getPeso());
        
        assertEquals(a1.getVerticeOrigem().getNome() ,grafo.getListArestas().get(0).getVerticeOrigem().getNome());
        assertEquals(a1.getVerticeDestino().getNome() ,grafo.getListArestas().get(0).getVerticeDestino().getNome());
        
        assertEquals(a2.getVerticeOrigem().getNome() ,grafo.getListArestas().get(1).getVerticeOrigem().getNome());
        assertEquals(a2.getVerticeDestino().getNome() ,grafo.getListArestas().get(1).getVerticeDestino().getNome());
        
        assertEquals(a3.getVerticeOrigem().getNome() ,grafo.getListArestas().get(2).getVerticeOrigem().getNome());
        assertEquals(a3.getVerticeDestino().getNome() ,grafo.getListArestas().get(2).getVerticeDestino().getNome());
    }
    
    /**
     * Teste de unidade que verifica se os aresta de um grafo são
     * removidos corretamente.
     */
    @Test
    public void testRemoverAresta() 
    {
        this.testInserirAresta();
        
        grafo.removerAresta(v1, v2);        
        grafo.removerAresta(v3, v1);
        grafo.removerAresta(v3, v2);
        
    }
    
}
