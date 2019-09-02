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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Esdras Abreu e Patrick Barbabé
 */
public class Grafo {
    
    /**
     * numero de vertices
     */
    private final int n;
    
    /**
     * numero de arestas
     */
    private int m;
    
    /**
     * Ao invés de utilizar array, vamos usar um hashmap<vertice, arestas>
     * onde arestas = arraydeque<vertice>
     * para obter a vizinhança do vertice 0, usamos adj.get(0)
     */
    private HashMap<Integer,ArrayDeque<Integer>> adj;
    
    /**
     * cria o grafo com o numero de vertices, com a lista de adjacencia vazia
     * @param n 
     */
    public Grafo(int n){
        if(n < 0)
            throw new IllegalArgumentException("o numéro de vertices deve ser maior que 0");
        this.n = n;
        this.m = 0;
        this.adj = new HashMap<>();
        
        for (int i=0;i<n;i++){
            adj.put(i, new ArrayDeque<Integer>());
        }
    }
    
    /**
     * ler um grafo de um arquivo
     * @param nomeArquivo arquivo que contém o grafo
     * @throws FileNotFoundException 
     */
    public Grafo(String nomeArquivo) throws FileNotFoundException, Exception{
        this(new Scanner (new File(nomeArquivo)));
        
    }
    
    /**
     * Ler os dados a partir da classe Scanner()
     * formato:<br>
     * numero de vertices\n numero de arestas\n vertce1 vertice2
     * @param in
     * @throws java.lang.Exception
     */
    public Grafo(Scanner in)throws Exception {
        if(null == in)
            throw new IllegalArgumentException("Scanner deve está inicializado");
        
        //vertificando o arquivo
        if(!in.hasNextInt())
            throw new Exception("ERRO: Primeiro parametro é um numero inteiro de vertices");
        int numVertices = in.nextInt();
        
        if(0 > numVertices)
            throw new IllegalArgumentException("numero de vertices deve ser maior que 0");
        in.nextLine();
        
        if(!in.hasNextInt())
            throw new Exception("ERRO: Segundo parametro é um numero inteiro de arestas");
        int numArestas = in.nextInt();
        
        if(0 > numArestas){
            throw new IllegalArgumentException("numero de arestas deve ser maior que 0");
        }
        in.nextLine();
        
        //iniciando a adjancencia
        adj = new HashMap <>();
        
        for (int i=0;i<numVertices;i++){
            adj.put(i, new ArrayDeque<Integer>());
        }
        
        for(int i=0;i<numArestas;i++){
            int u =in.nextInt();
            int v =in.nextInt();
            adicionaAresta(u,v);
        }
        if(in.hasNextLine())
            in.nextLine();
        
        /**
         * clona o grafo
         * @param g grafo que irá gerar objeto
         */
        public Grafo(Grafo g){
            if(null == g)
                throw new IllegalArgumentException("grafo de origem não existe");
            this.n = g.n();
            this.m = g.m();
            for (int i=0;i<this.n;i++){
                adj.put(i, g.adj.get(i).clone());
            }
        }
        
        /**
         * numero de vertices de um grafo
         * @return 
         */
        public int n (){
            return n;
        }
        /**
         * numero de arestas de um grafo
         * @return 
         */
        public int m (){
            return m;
        }
        
        /**
         * verifica se o valor do vertice está na faixa permitida
         * @param u numero de vertices
         * @return True se tiver na faixa
         */
        public boolean verificaVertice(int u){
            return ((u >= 0) && (u < n));
        }
        
        /**
         * Acrescenta uma aresta não direcionada no grafo
         * @param u vertice de origem
         * @param v vertice de destino
         */
        public final void adicionaAresta(int u, int v){
            if(!verificaVertice(u))
                throw new IndexOutBoundsException("Vertice de origem fora da faixa");
            if(!verificaVertice(v))
                throw new IndexOutBoundsException("Vertice de origem fora da faixa");
            m++;//contagem de arestas
            adj.get(u).add(v);//aresta (u,v)
            adj.get(v).add(u);//aresta (v,u)
        }
        
        /**
         * Acrescenta uma aresta direcionada no grafo
         * @param u vertice de origem
         * @param v vertice de destino
         */
        public final void adicionaArestaDirecionada(int u, int v){
            if(!verificaVertice(u))
                throw new IndexOutBoundsException("Vertice de origem fora da faixa");
            if(!verificaVertice(v))
                throw new IndexOutBoundsException("Vertice de origem fora da faixa");
            m++;//contagem de arestas
            adj.get(u).add(v);//aresta (u,v)
            
            
        }
        
        /**
         * retorna a adjacencia do vertice v
         * @param v vertice
         * @return lista de adjacencia
         */
        public Iterable <Integer> adj(int v){
            if (!verificaVertice(v))
                throw new IndexOutBoundsException("Vertice de origem fora da faixa");
            return adj.get(v);
        }
        
        /**
         * retorna o grafo como uma string
         * com o seguinte formato -> v: v1 v2 v3 ...
         * @return uma string que representa um grafo
         */
        @Override
        public String toString(){
            //armazena o resultado
            StringBuilder s = new StringBuilder();
            String SL = System.getProperty("line.separator");
            s.append(n).append(" vertices ").append(m).append(" arestas ").append(SL);
            for (int v = 0; v < n; v++){
                s.append(v).append(" : ");
                for (int u : adj(v))
                  s.append(u).append("  ");  
            }
            s.append(SL);
            return s.toString();
        }
    }  
}
