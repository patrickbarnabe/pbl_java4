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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author Esdras Abreu e Patrick Barbabé
 */
public class Grafo {
    
    /**
     * numero de vertices
     */
    private int numVertices;
    
    /**
     * numero de arestas
     */
    private int numArestas;
    
    private Vertice estacionamento;
    
    /**
     * Lista com os vertices pertencetes ao grafo
     */
    private final List<Vertice> adj;
    private final List<Aresta> listArestas;
    
    public List<Vertice> getAdj() {
        return adj;
    }

    /**
     * Cria um grafo vazio
     */
    public Grafo() 
    {
        this.numArestas = 0;
        this.numVertices = 0;
        this.adj = new ArrayList<>();
        this.listArestas = new ArrayList<>();
    }
    
    public final void adcionaVertice(String nome, String tipo)
    {
        Vertice v = new Vertice(tipo, nome);
        
        if( "estacionamento".equals(tipo.toLowerCase()))
        {
            this.setEstacionamento(v);   
        }
        
        adj.add(v);
        this.numVertices++;
    }
    
    public final void adcionaVertice(String nome, String tipo, List<Aresta> arestas)
    {
        Vertice v = new Vertice(tipo, nome);
        
        if( "estacionamento".equals(tipo.toLowerCase()) && this.getEstacionamento() == null)
        {
            
            this.setEstacionamento(v);
        }            
        
        v.setListaAdjacencias(arestas);
        adj.add(v);
        this.numVertices++;
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
        
        Aresta a = new Aresta(peso, u, v);
        Aresta b = new Aresta(peso, v, u);
        
        this.get( u.getNome() ).getListaAdjacencias().add( a );//aresta (u,v)
        this.get( v.getNome() ).getListaAdjacencias().add( b );//aresta (v,u)
        
        listArestas.add(a);
        this.numArestas++;
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
            if( vertice.getNome().toLowerCase().equals(v.getNome().toLowerCase()) && !vertice.getListaAdjacencias().isEmpty() )
            {               
                List<Aresta> listaAdj = vertice.getListaAdjacencias();
                
                for( Aresta aresta : listaAdj )
                {                    
                    Vertice verticeDestino = aresta.getVerticeDestino();
                    List<Aresta> listaAdjDestino = verticeDestino.getListaAdjacencias();

                    for( Aresta a : listaAdjDestino )
                    {
                        if( a.getVerticeDestino().getNome().equals(verticeDestino.getNome()) )
                        {
                            verticeDestino.getListaAdjacencias().remove(a);
                            break;
                        }
                    }
                }
                
                Vertice remove = this.adj.remove(pos);
                this.numVertices--;
                return remove;
            }
            pos++;
        }
        
        return null;
    }
    
    /**
     * Método responsavel por excluir um aresta com as coordenadas
     * dos seus vertices
     * @param v1
     * @param v2 
     */
    public void removerAresta( Vertice v1, Vertice v2 )
    {
        if(!verificaVertice(v1))
            throw new IndexOutOfBoundsException("Vertice de origem fora da faixa");
        
        if(!verificaVertice(v2))
            throw new IndexOutOfBoundsException("Vertice de origem fora da faixa");
        
        List<Aresta> listaAdj = v1.getListaAdjacencias();
        
        for( Aresta aresta : listaAdj )
        {
            if( aresta.getVerticeDestino().getNome().equals(v2.getNome()) )
            {
                v1.getListaAdjacencias().remove(aresta);
                break;
            }
        }
        
        listaAdj = v2.getListaAdjacencias();
        
        for( Aresta aresta : listaAdj )
        {
            if( aresta.getVerticeDestino().getNome().equals(v1.getNome()) )
            {
                v2.getListaAdjacencias().remove(aresta);
                break;
            }
        }
        
        for( Aresta aresta : listArestas )
        {
            if( aresta.getVerticeDestino().getNome().toLowerCase().equals(v1.getNome().toLowerCase()) && aresta.getVerticeOrigem().getNome().toLowerCase().equals(v2.getNome().toLowerCase()) || 
                    aresta.getVerticeDestino().getNome().toLowerCase().equals(v2.getNome().toLowerCase()) && aresta.getVerticeOrigem().getNome().toLowerCase().equals(v1.getNome().toLowerCase()) )
            {
                this.listArestas.remove(aresta);
                break;
            }
        }
        
        this.numArestas--;
        
    }

    /**
     * retorna a adjacencia do vertice v
     * @param v vertice
     * @return lista de adjacencia
     */
    public List<Aresta> adj(Vertice v)
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
    public Vertice get(String name){
        
        List<Vertice> vertices = this.adj;
        
        //forEach percorre cada item do ArrayList para procurar o vertice
        for(Vertice vertice : vertices )
            if( vertice.getNome().toLowerCase().equals(name.toLowerCase()) )
                return vertice;
        
        return null;
    }
    
    public void carregarArquivo( String arquivoNome ) throws IOException
    {
        File arqgrafo = new File( arquivoNome + ".txt");
        FileReader rd = new FileReader(arqgrafo);
        
        try {
            BufferedReader buff = new BufferedReader(rd);
            String linha = "";

            while( true )
            {
                linha = buff.readLine();
                if(!linha.equals("vertices"))
                {
                    String[] textoSeparado = linha.split (Pattern.quote (" "));
                    
                    String categoria = "";
                    
                    if( Integer.parseInt(textoSeparado[0]) == 0 )
                        categoria = "estacionamento";
                    else if( Integer.parseInt(textoSeparado[0]) == 1 )
                        categoria = "coleta";
                    else if( Integer.parseInt(textoSeparado[0]) == 2 )
                        categoria = "banco";
                    
                    String nome = textoSeparado[1];
                    String x = textoSeparado [2];
                    String y = textoSeparado[3];
                    System.out.println(Arrays.toString(textoSeparado));
                    //Vertice vertice = new Vertice(categoria,nome,x,y);
                    this.adcionaVertice(nome, categoria);
                }
                else
                    break;
            }
            
            while( true )
            {
                linha = buff.readLine();
                if(!linha.equals("arestas"))
                {
                    String[] textoSeparado = linha.split (Pattern.quote (" "));
                    String origem = textoSeparado[0];
                    String destino = textoSeparado[1];
                    String peso = textoSeparado[2];
                    System.out.println(Arrays.toString(textoSeparado));
                    //Aresta aresta = new Aresta(origem,destino,peso);
                    this.adicionaAresta( this.get(origem), this.get(destino), Integer.parseInt(peso));
                }   
                else{
                    break;
                }
            }
        } 
        catch(FileNotFoundException ex) {
            java.lang.System.out.println("erro no arquivo");
        } 
        finally {
            rd.close();
        }
    }
    
    public MenorCaminho dijkstra()
    {
        if( this.getEstacionamento() != null )
        {
            MenorCaminho m = new MenorCaminho(this.getAdj(), this.getListArestas(), this.getEstacionamento());
            return m;
        }
        
        return null;
    }

    public List<Aresta> getListArestas() {
        return listArestas;
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
    
    public Vertice getEstacionamento() {
        return estacionamento;
    }

    public void setEstacionamento(Vertice estacionamento) {
        this.estacionamento = estacionamento;
    }
}  
   
