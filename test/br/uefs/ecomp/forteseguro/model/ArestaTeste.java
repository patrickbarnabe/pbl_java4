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
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
/**
 *
 * @author xstri
 */
public class ArestaTeste {
    private Aresta a1;
    private Vertice v1;
    private Vertice v2;
    
    /**
     * Este método é executado antes de cada teste de unidade (testes a seguir),
     * e serve para inicializar objetos que são utilizados nos testes.
     */
    @Before
    public void setUp() throws Exception{
        v1 = new Vertice("salvador", "partida");
        v2 = new Vertice("irara", "chegada");
        a1 = new Aresta(5, v1, v2);
        
    }
    
    /**
     * Teste de unidade que verifica se os atributos de um aresta são
     * atribuídos corretamente.
     */
    @Test
    public void testBasic() {
        assertEquals(v1.getNome(), a1.getVerticeOrigem().getNome());
        assertEquals(v2.getNome(), a1.getVerticeDestino().getNome());
    }
    
    /**
     * Teste de unidade que verifica se o método
     * que compara dois atendentes foi implementado corretamente.
     */
    @Test
    public void testEquals() {
        Aresta temp = new Aresta(5, v1, v2);
        assertTrue(temp.equals(a1));
    }
    
}
