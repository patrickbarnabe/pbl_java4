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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 *
 * @author Esdras Abreu e Patrick Barbabé
 */
public class Grafo {
    
    /**
     * numero de vertices
     */
    private final int numVertices;
    
    /**
     * numero de arestas
     */
    private int numArestas;
    
    /**
     * Ao invés de utilizar array, vamos usar um hashmap<vertice, arestas>
     * onde arestas = arraydeque<vertice>
     * para obter a vizinhança do vertice 0, usamos adj.get(0)
     */
    private final List<Vertice> adj;

    /**
     * Cria um grafo vazio
     */
    public Grafo() 
    {
        this.numArestas = 0;
        this.numVertices = 0;
        this.adj = new ArrayList<>();
    }
    
    /**
     * Acrescenta uma aresta não direcionada no grafo
     * @param u vertice de origem
     * @param v vertice de destino
     * @param peso peso da aresta
     */
    public final void adicionaAresta(Vertice u, Vertice v, int peso)
    {
        if(!verificaVertice(u))
            throw new IndexOutOfBoundsException("Vertice de origem fora da faixa");
        
        if(!verificaVertice(v))
            throw new IndexOutOfBoundsException("Vertice de origem fora da faixa");
        
        numArestas++;//contagem de arestas
        
        this.get( u.getNome() ).getListaAdjacencias().add( new Aresta(peso, v) );//aresta (u,v)
        
        this.get( v.getNome() ).getListaAdjacencias().add( new Aresta(peso, u) );//aresta (v,u)
    }
    
    /**
     * Método responsavel por excluir um vertice do grafo e todas as suas arestas tanto no vertice
     * excluido quanto nos vertices que são adjacentes a ele
     * @param v
     * @return 
     */
    public Vertice removerVertice( Vertice v )
    {
        List<Vertice> vertices = this.adj;
        int pos = 0;
        
        for( Vertice vertice : vertices )
        {
            if( vertice == v )
            {               
                Deque<Aresta> listaAdj = vertice.getListaAdjacencias();
                
                for( Aresta aresta : listaAdj )
                {
                    Vertice verticeDestino = aresta.getVerticeDestino();
                    Deque<Aresta> listaAdjDestino = verticeDestino.getListaAdjacencias();
                    
                    for( Aresta a : listaAdjDestino )
                    {
                        if( a.getVerticeDestino() == verticeDestino )
                        {
                            verticeDestino.getListaAdjacencias().remove(a);
                            break;
                        }
                    }
                }
                
                Vertice remove = this.adj.remove(pos);
                return remove;
            }
            pos++;
        }
        
        return null;
    }
    
    public void removerAresta( Vertice v1, Vertice v2 )
    {
        if(!verificaVertice(v1))
            throw new IndexOutOfBoundsException("Vertice de origem fora da faixa");
        
        if(!verificaVertice(v2))
            throw new IndexOutOfBoundsException("Vertice de origem fora da faixa");
        
        Deque<Aresta> listaAdj = v1.getListaAdjacencias();
        
        for( Aresta aresta : listaAdj ){
            if( aresta.getVerticeDestino() == v2 )
            {
                v1.getListaAdjacencias().remove(aresta);
                break;
            }
        }
        
        listaAdj = v2.getListaAdjacencias();
        for( Aresta aresta : listaAdj ){
            if( aresta.getVerticeDestino() == v1 )
            {
                v2.getListaAdjacencias().remove(aresta);
                break;
            }
        }
    }

    /**
     * retorna a adjacencia do vertice v
     * @param v vertice
     * @return lista de adjacencia
     */
    public Deque<Aresta> adj(Vertice v)
    {
        if (!verificaVertice(v))
            throw new IndexOutOfBoundsException("Vertice de origem fora da faixa");
        
        return this.get(v.getNome()).getListaAdjacencias();
    }

    /**
     * verifica se o valor do vertice está na faixa permitida
     * @param u numero de vertices
     * @return True se tiver na faixa
     */
    public boolean verificaVertice(Vertice u)
    {
        return this.get(u.getNome()) != null;
    }
    
    /**
     * Método que percorre o ArrayList e retorna se o Vertice existe
     * @param name Recebe uma string com o nome do Vertice
     * @return Retorna o vertice caso ele exista ou null se não existir
     */
    private Vertice get(String name){
        
        List<Vertice> vertices = this.adj;
        
        //forEach percorre cada item do ArrayList para procurar o vertice
        for(Vertice vertice : vertices )
            if( vertice.getNome().equals(name) )
                    return vertice;
        
        return null;
    }

    /**
     * numero de vertices de um grafo
     * @return 
     */
    public int getNumVertices ()
    {
        return numVertices;
    }
    
    /**
     * numero de arestas de um grafo
     * @return 
     */
    public int getNumArestas ()
    {
        return numArestas;
    }
    
}  
   
