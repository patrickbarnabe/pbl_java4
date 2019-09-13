/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class ArestaTeste {
    private Aresta a1;
    
    /**
     * Este método é executado antes de cada teste de unidade (testes a seguir),
     * e serve para inicializar objetos que são utilizados nos testes.
     */
    @Before
    public void setUp() throws Exception{
        a1 = new Aresta(5,"salvador","irara");
        a1 = new Aresta("salvador","irara",5);
        
    }
    
    /**
     * Teste de unidade que verifica se os atributos de um aresta são
     * atribuídos corretamente.
     */
    @Test
    public void testBasic() {
        assertEquals("Rosa", a1.getVerticeOrigem());
        assertEquals(1999, a1.getVerticeDestino());
    }
    
    /**
     * Teste de unidade que verifica se o método
     * que compara dois atendentes foi implementado corretamente.
     */
    @Test
    public void testEquals() {
        Aresta temp = new Aresta("Rosa", "8871-1025", 4567);
        assertTrue(temp.equals(a1));
        
        temp = new Aresta("Romário", "5555-1234", 2222);
        assertFalse(temp.equals(a1));
    }
    
}
