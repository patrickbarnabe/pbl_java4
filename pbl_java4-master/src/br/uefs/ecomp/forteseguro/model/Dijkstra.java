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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dijkstra {
    private final List<Aresta> listadeArestas;
    private List<List<Vertice>> listaCaminho;
    private Set<Vertice> verticesVisitados;
    private Set<Vertice> verticesNaoVisitados;
    private Map<Vertice, ArrayList<Vertice>> anteriores;
    private Map<Vertice, Integer> peso;

    /**
     * construtor do dijkstra
     * @param g grafo
     */
    public Dijkstra(Grafo g) {
        this.listadeArestas = new ArrayList<>(g.getNumArestas());
    }

    /**
     * 
     * @param inicial (a primicia dos vertices)
     */
    public void executar(Vertice inicial) {
        this.listaCaminho = new ArrayList<>();
        this.verticesVisitados = new HashSet<>();
        this.verticesNaoVisitados = new HashSet<>();
        this.peso = new HashMap<>();
        this.anteriores = new HashMap<>();
        this.peso.put(inicial, 0);
        this.verticesNaoVisitados.add(inicial);

        while (this.verticesNaoVisitados.size() > 0) {
            Vertice verticeAuxiliar = this.getVerticeDaMenorDistancia(this.verticesNaoVisitados);
            this.verticesVisitados.add(verticeAuxiliar);
            this.verticesNaoVisitados.remove(verticeAuxiliar);
            menorDistancia(verticeAuxiliar);
        }
    }

    /**
     * 
     * @param vertice vertice 
     */
    private void menorDistancia(Vertice vertice) {
        List<Vertice> verticesAdjacentes = this.getVizinhos(vertice);
        ArrayList<Vertice> listAuxiliar;
        for (Vertice auxVertice : verticesAdjacentes) {
            if (this.getMenorDistancia(auxVertice) > this.getMenorDistancia(vertice) + this.getDistancia(vertice, auxVertice)) {
                this.peso.put(auxVertice, this.getMenorDistancia(vertice) + this.getDistancia(vertice, auxVertice));
                listAuxiliar = new ArrayList<>();
                listAuxiliar.add(vertice);
                this.anteriores.put(auxVertice, listAuxiliar);
                this.verticesNaoVisitados.add(auxVertice);
            } else if (this.getMenorDistancia(auxVertice) == this.getMenorDistancia(vertice) + this.getDistancia(vertice, auxVertice)) {
                this.anteriores.get(auxVertice).add(vertice);
            }
        }

    }

    /**
     * 
     * @param verticeOrigem vertice de onde está partindo
     * @param verticeDestino vertice que deseja chegar
     * @return peso da aresta
     */
    private int getDistancia(Vertice verticeOrigem, Vertice verticeDestino) {
        for (Aresta arestaAuxiliar : this.listadeArestas) {
            if (arestaAuxiliar.getVerticeOrigem().equals(verticeOrigem) && arestaAuxiliar.getVerticeDestino().equals(verticeDestino)) {
                return arestaAuxiliar.getPeso();
            }
        }
        return 0;
    }

    /**
     * 
     * @param vertice
     * @return lista de vertices adjacentes
     */
    private List<Vertice> getVizinhos(Vertice vertice) {
        List<Vertice> listaAdjacentes = new ArrayList<>();

        //this.listadeArestas.stream().filter((arestaAuxiliar) -> (arestaAuxiliar.getVerticeOrigem().equals(vertice)
        //        && !isVisitado(arestaAuxiliar.getVerticeDestino()))).forEachOrdered((arestaAuxiliar) -> {
        //    listaAdjacentes.add(arestaAuxiliar.getVerticeDestino());});

        for(Aresta auxAresta : this.listadeArestas) {
            if (auxAresta.getVerticeOrigem().equals(vertice) && !isVisitado(auxAresta.getVerticeDestino()))
                listaAdjacentes.add(auxAresta.getVerticeDestino());
        }
         
        return listaAdjacentes;
    }

    /**
     * 
     * @param vertices (conjunto de vertices)
     * @return menor Vertice
     */
    private Vertice getVerticeDaMenorDistancia(Set<Vertice> vertices) {
        Vertice menor = null;
        for (Vertice auxVertice : vertices) {
            if (menor == null) {
                menor = auxVertice;
            } else if (this.getMenorDistancia(auxVertice) < this.getMenorDistancia(menor)) {
                menor = auxVertice;
            }
        }
        return menor;
    }

    /**
     * verifica se o vertice foi visitado
     * @param vertice (um ponto qualquer)
     * @return true se o vertice foi visitado, false caso contrario
     */
    private boolean isVisitado(Vertice vertice) {
        return verticesVisitados.contains(vertice);
    }

    /**
     * retorna a menor distancia
     * @param vertice (um ponto qualquer)
     * @return um numero inteiro que representa a menor distancia 
     */
    private int getMenorDistancia(Vertice vertice) {
        Integer distancia = peso.get(vertice);
        if (distancia == null) {
            return Integer.MAX_VALUE;
        } else {
            return distancia;
        }
    }

    /**
     * 
     * @param listVertices lista de vertices
     * @param vertice vertice
     * @return lista de vertices do caminho percorrido
     */
    public List<List<Vertice>> getCaminho(ArrayList<Vertice> listVertices, Vertice vertice) {
        if (listVertices == null) {
            listVertices = new ArrayList<>();
        }
        
        ArrayList<Vertice> caminho = new ArrayList<>(listVertices);
        Vertice auxVertice = vertice;
        ArrayList<Vertice> auxListVertice;

        if (this.anteriores.get(auxVertice) == null) {
            return null;
        }

        caminho.add(auxVertice);
        while (this.anteriores.get(auxVertice) != null) {

            auxListVertice = this.anteriores.get(auxVertice);

            if (auxListVertice.size() > 1) {
                for (int i = 1; i < auxListVertice.size(); i++) {
                    auxVertice = auxListVertice.get(i);
                    this.getCaminho(caminho, auxVertice);
                }
            }
            auxVertice = auxListVertice.get(0);
            caminho.add(auxVertice);
        }

        Collections.reverse(caminho);
        this.listaCaminho.add(caminho);
        return this.listaCaminho;
    }
    
}
