/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.forteseguro.model;
//import br.uefs.ecomp.forteseguro.model.Vertice;
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
