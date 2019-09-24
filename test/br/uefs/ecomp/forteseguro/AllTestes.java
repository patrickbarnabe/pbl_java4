/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.forteseguro;

import br.uefs.ecomp.forteseguro.model.ArestaTeste;
import br.uefs.ecomp.forteseguro.model.DijkstraTeste;
import br.uefs.ecomp.forteseguro.model.GrafoTeste;
import br.uefs.ecomp.forteseguro.model.VerticeTeste;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    ArestaTeste.class,
    DijkstraTeste.class,
    GrafoTeste.class,
    VerticeTeste.class
})
public class AllTestes {
    
}
