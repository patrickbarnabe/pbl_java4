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
import static org.junit.Assert.assertFalse;
/**
 *
 * @author xstri
 */
public class VerticeTeste {
    private Vertice v1;
    
    /**
     * Este método é executado antes de cada teste de unidade (testes a seguir),
     * e serve para inicializar objetos que são utilizados nos testes.
     */
    @Before
    public void setUp() throws Exception{
        v1 = new Vertice("coleta", "casa da moeda");
    }
    
    /**
     * Teste de unidade que verifica se os atributos de um Vertice são
     * atribuídos corretamente.
     */
    @Test
    public void testBasic() {
        assertEquals("coleta", v1.getTipo());
        assertEquals("casa da moeda", v1.getNome());
    }
    
    /**
     * Teste de unidade que verifica se o método
     * que compara dois atendentes foi implementado corretamente.
     */
    @Test
    public void testEquals() {
        Vertice temp = new Vertice("coleta","casa da moeda");
        assertTrue(temp.equals(v1));
        
        temp = new Vertice("banco", "mercado");
        assertFalse(temp.equals(v1));
    }
    
}
