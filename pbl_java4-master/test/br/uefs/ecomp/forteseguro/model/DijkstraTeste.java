/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.forteseguro.model;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
/**
 *
 * @author xstri
 */
public class DijkstraTeste {
    private Grafo grafo;
    private Vertice a,b,c,d,e,f,g,h,i;
    private Aresta a1, a2, a3, a4, a5, a6, a7, a8, a9, a10,a11;
    
    
    /**
     * Este método é executado antes de cada teste de unidade (testes a seguir),
     * e serve para inicializar objetos que são utilizados nos testes.
     */
    @Before
    public void setUp() throws Exception{
        a = new Vertice("estacionamento", "A");
        b = new Vertice("cruzamento", "B");
        c = new Vertice("cruzamento", "C");
        d = new Vertice("coleta", "D");
        e = new Vertice("cruzamento", "E");
        f = new Vertice("cruzamento", "F");
        g = new Vertice("cruzamento", "G");
        h = new Vertice("cruzamento", "H");
        i = new Vertice("Banco", "I");
      
        
        a1 = new Aresta(6, a, b);
        a2 = new Aresta(11, a, d);
        a3 = new Aresta(1, d, c);
        a4 = new Aresta(3, c, b);
        a5 = new Aresta(2, b, e);
        a6 = new Aresta(16, b, f);
        a7 = new Aresta(1, b, g);
        a8 = new Aresta(20, f, i);
        a9 = new Aresta(2, g, h);
        a10 = new Aresta(4, h, i);
        a11 = new Aresta(12, g, i);
      
    }
    
    public void chamaGrafo(){
        grafo.adcionaVertice("A", "estacionamento");
        grafo.adcionaVertice("B", "cruzamento");
        grafo.adcionaVertice("C", "cruzamento");
        grafo.adcionaVertice("D", "estacionamento");
        grafo.adcionaVertice("E", "cruzamento");
        grafo.adcionaVertice("F", "cruzamento");
        grafo.adcionaVertice("G", "cruzamento");
        grafo.adcionaVertice("H", "cruzamento");
        grafo.adcionaVertice("I", "banco");
        
        grafo.adicionaAresta(a, b, 6);
        grafo.adicionaAresta(a, d, 11);
        grafo.adicionaAresta(d, c, 0);
        grafo.adicionaAresta(c, b, 0);
        grafo.adicionaAresta(b, e, 0);
        grafo.adicionaAresta(b, f, 0);
        grafo.adicionaAresta(b, g, 0);
        grafo.adicionaAresta(f, i, 0);
        grafo.adicionaAresta(g, h, 0);
        grafo.adicionaAresta(h, i, 0);
        grafo.adicionaAresta(g, i, 0);
    }
    
    /**
     * 
     */
    @Test
    public void menorDistancia(){
       
        
    }
    
    /**
     * 
     */
    @Test
    public void getDistancia(){
        
    }
    
    /**
     * 
     */
     @Test
    public void getVizinhos(){
        
    }
    
    /**
     * 
     */
    @Test
    public void getVerticeDaMenorDistancia(){
        
    }
    
    /**
     * 
     */
    @Test
    public void isVisitado(){
        
    }
    
    /**
     * 
     */
    @Test
    public void getMenorDistancia(){
        
    }
    
     @Test
    public void testBasic() {
        assertEquals("A",a.getNome());
        assertEquals("estacionamento",a.getTipo());
 
    }
    
    /**
     * 
     */
     @Test
    public void getCaminho(){
        chamaGrafo();
       
        
    }
}
