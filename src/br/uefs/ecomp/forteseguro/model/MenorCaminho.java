/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.forteseguro.model;

import br.uefs.ecomp.forteseguro.model.Aresta;
import br.uefs.ecomp.forteseguro.model.Grafo;
import br.uefs.ecomp.forteseguro.model.Vertice;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Patrick Santos
 */
public class MenorCaminho {
    
    private Grafo grafo; 
    private Vertice inicio;
    private List<Vertice> vertices;
    private List<Aresta> arestas;  
    private List<Vertice> menorPercurso; 
    private List<Integer> distanciasMenorPercurso; 
    private int custoTotal;
    
    public MenorCaminho( List<Vertice> vertice, List<Aresta> aresta, Vertice origem) {
        this.vertices = vertice; //throws ErroCalculoPercursoException
        this.arestas = aresta;
        this.inicio = origem;
        menorCaminho2(); 
    } 
    
    /**
    *
    * MÃ©todo responsÃ¡vel por achar o menor percurso dado o ponto de chegada.
    * @param chegada vertice que representa o final do percuso.
    * @return a lista de vertices a percorrer.
    */
    public List Dijkstra(String chegada){
        return obterPercurso( obterVertice(chegada) );
    } 
    
    /**
    *
    * MÃ©todo responsÃ¡vel por achar o menor caminho dada uma origem.
    */
    public void menorCaminho2() 
    {
        //throws ErroCalculoPercursoException
        List<Integer> menoresDistancias = new ArrayList();
        List<Vertice> verticeAnterior = new ArrayList(); 
        List<Vertice> naoVisitados = new ArrayList();
        List<Integer> nuvem = new ArrayList();
        Vertice atual; 
        int contador = 0; 
        Vertice menorCaminho = null; 
        List<Vertice> vizinhos = new ArrayList(); 
        int distanciaAnterior = 0;
        int distanciaTemporaria = 0;
        for(Vertice vertice:this.vertices){
            
            //seta os valores iniciais da distancia e dos vertices.
            menoresDistancias.add(Integer.MAX_VALUE);
            verticeAnterior.add(null);
            //adiciona os vertices na lista de nÃ£o visitados.
            if(!vertice.getVisitado()){
                naoVisitados.add(vertice);
            }
            //procura o vertice origem;
            if(vertice.getNome().equalsIgnoreCase(inicio.getNome())){
                inicio = vertice;
                //Da origem para ela mesma a distancia Ã© 0;
                menoresDistancias.set(vertices.indexOf(inicio),0);
                //vertice anterior Ã© a propria origem;
                verticeAnterior.set(0,inicio);
                nuvem.add(vertices.indexOf(inicio));
            }
            
        } 

        //O laÃ§o continua atÃ© todos os vertoces serem visitados.
        while(!naoVisitados.isEmpty()){
            //onde o algortimo vai comeÃ§a,se for a primeira vez comeÃ§a pela origem.
            if(contador == 0){
                atual = naoVisitados.get(naoVisitados.indexOf(inicio));
            }
            //conmeÃ§a pelo vertice de menor caminho encontrado no loop anterior.
            else{ 
                atual = naoVisitados.get(naoVisitados.indexOf(menorCaminho));
                //retira o menorCaminho da lista de nÃ£o visitados por que ele jÃ¡ foi visitado.
                naoVisitados.remove(naoVisitados.indexOf(menorCaminho)); 
            }
            
            //visita a origem para o algoritmo saber que esse vertice jÃ¡ foi visitado.
            inicio.visitar();
            naoVisitados.remove(inicio);
            //confere os vertice adjacentes ao vertice atual
            for(int i =0;i < atual.getListaAdjacencias().size();i++){
                
                vizinhos.add(atual.getListaAdjacencias().get(i).getVerticeDestino());
                //Confere se o vizinho foi visitado.
                if(!vizinhos.get(i).getVisitado()){
                    //Confere se a distancia da aresta somado a distancia Ã© menor que o valor contido no vetor de distancias.
                    if(atual.getListaAdjacencias().get(i).getPeso() + distanciaAnterior < menoresDistancias.get(vertices.indexOf(vizinhos.get(i)))){
                       //seta a nova distancia.
                       menoresDistancias.set(vertices.indexOf(vizinhos.get(i)),atual.getListaAdjacencias().get(i).getPeso() + distanciaAnterior); 
                       //seta por onde o algoritmo deve seguir para ter essa menor distancia.
                       verticeAnterior.set(vertices.indexOf(vizinhos.get(i)), atual); 
                    }
                } 
            } 
            
            //Limpa a lista de vizinhos pois os mesmo nÃ£o serÃ£o mais usados.
            vizinhos.clear();

            if(!naoVisitados.isEmpty())
            { 
                //encontra o menor valor da lista de distancias que ainda nÃ£o foi utilizado.
                int k = menorDistancia(menoresDistancias,nuvem);
                distanciaTemporaria = menoresDistancias.get(k);                
                menorCaminho = vertices.get(k);  
                /*adiciona o indice do menor valor em uma lista separada,para diferenciar as distancias utilizadas e nÃ£o
                utilizadas*/
                nuvem.add(k);            
                atual.visitar();
                distanciaAnterior = distanciaTemporaria; 
            } 
            //variavel utilizada para diferenciar o primeiro loop;
            contador++; 
        }
        
        this.menorPercurso = verticeAnterior; 
        this.distanciasMenorPercurso = menoresDistancias;  
        for(Vertice v: this.vertices){
            v.setVisitar(false);
        }
    } 
    
    /**
    *
    * MÃ©todo responsÃ¡vel por achar menor distancia dada uma lista de distancias.
    * @param distancias as distancias que as arestas representam.
    * @param nuvem lista que guarda os indices que jÃ¡ foram utilizados. 
    * @return o indice do menor valor da lista e que ainda nÃ£o foi utilizado.
    */
    private int menorDistancia(List<Integer> distancias,List<Integer> nuvem) {
        
        int menorDistancia = Integer.MAX_VALUE;
        int posicao = -1;
        for(int i = 0;i< distancias.size();i++){
            if(distancias.get(i) < menorDistancia){ 
                if(!nuvem.contains(i)){
                    menorDistancia = distancias.get(i);
                    posicao = i;
                }
            }
        }
        /*
        if(posicao==-1){
            throw new ErroCalculoPercursoException(); throws ErroCalculoPercursoException
        }*/
        return posicao;
    } 
    
    /**
    *
    * MÃ©todo responsÃ¡vel por obter o menor caminho da origem para um dado vertice de chegada.
    * @param chegada vertice que representa o final do percuso.
    * @return lista de vertices a percorrer.
    */
    public List obterPercurso(Vertice chegada){ 
        
        Vertice auxiliar = chegada; 
        List<Vertice> percurso = new ArrayList();
        if(menorPercurso != null){
            percurso.add(0,chegada);
            while(auxiliar != this.inicio){ 
                percurso.add(0,menorPercurso.get(vertices.indexOf(auxiliar))); 
                auxiliar = percurso.get(0);
            }
        }
        return percurso;
    } 
    
    /**
    *
    * MÃ©todo responsÃ¡vel por retonar o vertice que tem o conteÃºdo especificado.
    * @param nome nome do conteudo presente no vertice;
    * @return o vÃ©rtice que corresponde a String especificada.
    */
    public Vertice obterVertice(String nome){
        
        Vertice vertice = null;
        for(Vertice v:this.vertices){
           if(v.getNome().equalsIgnoreCase(nome)){
               vertice = v;
            } 
        } 
        return vertice;
    }
    
}

