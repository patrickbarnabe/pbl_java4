/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.forteseguro.controller;

import br.uefs.ecomp.forteseguro.model.Aresta;
import br.uefs.ecomp.forteseguro.model.Grafo;
import br.uefs.ecomp.forteseguro.model.Vertice;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import util.Alerts;

/**
 *
 * @author xstri
 */
public class FXMLDocumentController implements Initializable {
    
    Grafo grafo = new Grafo();
    List<Aresta> listTempAdj = new LinkedList();
    
    @FXML
    private Tab tab_inserir, tab_remover, tab_calcular;
    
    @FXML
    private Button btn_inserirVertice, btn_inserirAdj, btn_removerAresta, btn_removerVertice;
    
    @FXML
    private TextField edt_peso_listAdj, edt_destino_listAdj, edt_inserir_verticeNome, edt_inserir_verticeTipo;
    
    @FXML
    private Label txt_arestas;
    
    @FXML
    private void inserirVertice(ActionEvent event) 
    {
        if( !edt_inserir_verticeNome.getText().isEmpty() && !edt_inserir_verticeTipo.getText().isEmpty() )
            if( this.listTempAdj.isEmpty() )
            {
                grafo.adcionaVertice(edt_inserir_verticeNome.getText().toLowerCase(), edt_inserir_verticeTipo.getText().toLowerCase());
                this.listTempAdj.clear();
                txt_arestas.setText("");
                edt_inserir_verticeNome.clear();
                edt_inserir_verticeTipo.clear();
            }
            else
            {
                grafo.adcionaVertice(edt_inserir_verticeNome.getText().toLowerCase(), edt_inserir_verticeTipo.getText().toLowerCase(), this.listTempAdj);
                this.listTempAdj.clear();
                txt_arestas.setText("");
                edt_inserir_verticeNome.clear();
                edt_inserir_verticeTipo.clear();
            }
        else if( edt_inserir_verticeNome.getText().isEmpty() && edt_inserir_verticeTipo.getText().isEmpty() )
            Alerts.showAlert("Error Campo de Text", null, "Preencha um valor para cada um dos campos de texto Tipo e Nome", Alert.AlertType.ERROR);
        else if( edt_inserir_verticeNome.getText().isEmpty() )
            Alerts.showAlert("Error Campo de Text", null, "Preencha um valor para o campo de texto Nome", Alert.AlertType.ERROR);
        else if( edt_inserir_verticeTipo.getText().isEmpty() )
            Alerts.showAlert("Error Campo de Text", null, "Preencha um valor para o campo de texto Tipo", Alert.AlertType.ERROR);
    
    }
    
    @FXML
    private void inserirAdj(ActionEvent event) 
    {
        if( !edt_destino_listAdj.getText().isEmpty() && !edt_peso_listAdj.getText().isEmpty() )
        {
            if( grafo.get(edt_destino_listAdj.getText()) != null )
            {
                Vertice v = grafo.get(edt_destino_listAdj.getText());
                int peso = Integer.parseInt(edt_peso_listAdj.getText());
                Aresta aresta = new Aresta(peso, v);
                txt_arestas.setText( txt_arestas.getText() + edt_destino_listAdj.getText() + " -> " + edt_peso_listAdj.getText() + "\n" );
                this.listTempAdj.add(aresta);
                edt_peso_listAdj.clear();
                edt_destino_listAdj.clear();
            }
            else
                Alerts.showAlert("Error Vertice Destino", null, "Vertice de destino não existente", Alert.AlertType.ERROR);
        }
        else if( edt_peso_listAdj.getText().isEmpty() && edt_destino_listAdj.getText().isEmpty() )
            Alerts.showAlert("Error Campo de Text", null, "Preencha um valor para cada um dos campos de texto Peso e Destino", Alert.AlertType.ERROR);
        else if( edt_destino_listAdj.getText().isEmpty() )
            Alerts.showAlert("Error Campo de Text", null, "Preencha um valor para o campo de texto Destino", Alert.AlertType.ERROR);
        else if( edt_peso_listAdj.getText().isEmpty() )
            Alerts.showAlert("Error Campo de Text", null, "Preencha um valor inteiro positvo para o campo de texto Peso", Alert.AlertType.ERROR);
    }
    
    @FXML
    private void removerAresta(ActionEvent event) 
    {
        
    }
    
    @FXML
    private void inserirArquivo(ActionEvent event) 
    {
        tab_inserir.setDisable(false);
        tab_remover.setDisable(false);
        tab_calcular.setDisable(false);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
