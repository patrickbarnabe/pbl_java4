/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.forteseguro.controller;

import br.uefs.ecomp.forteseguro.model.Aresta;
import br.uefs.ecomp.forteseguro.model.Grafo;
import br.uefs.ecomp.forteseguro.model.Vertice;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import util.MenorCaminho;

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
    private TextField edt_peso_listAdj, edt_destino_listAdj, 
            edt_inserir_verticeNome, edt_inserir_verticeTipo, 
            edt_remover_nomeVertice, edt_remover_verticeDestino,
            edt_remover_verticeOrigem, edt_nomeArquivo,
            edt_pontoColeta, edt_pontoBanco;
    
    @FXML
    private Label txt_arestas, txt_caminho, txt_nomeCaminhoLabel;
    
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
        if( !edt_destino_listAdj.getText().isEmpty() && !edt_peso_listAdj.getText().isEmpty() && !edt_inserir_verticeNome.getText().isEmpty() )
        {
            if( grafo.get(edt_destino_listAdj.getText()) != null && Integer.parseInt(edt_peso_listAdj.getText()) > 0 )
            {
                Vertice v = grafo.get(edt_destino_listAdj.getText());
                Vertice u = grafo.get(edt_inserir_verticeNome.getText());
                int peso = Integer.parseInt(edt_peso_listAdj.getText());
                Aresta aresta = new Aresta(peso, u, v);
                txt_arestas.setText( txt_arestas.getText() + edt_destino_listAdj.getText() + " -> " + edt_peso_listAdj.getText() + "\n" );
                this.listTempAdj.add(aresta);
                edt_peso_listAdj.clear();
                edt_destino_listAdj.clear();
            }
            else if ( Integer.parseInt(edt_peso_listAdj.getText()) <= 0  )
                Alerts.showAlert("Error", null, "Insira um valor inteiro positivo no campo Peso", Alert.AlertType.ERROR);
            else if( edt_inserir_verticeNome.getText().isEmpty() )
                Alerts.showAlert("Error Vertice Origem", null, "Insira um vertice de origem", Alert.AlertType.ERROR);
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
        if( !edt_remover_verticeOrigem.getText().isEmpty() && !edt_remover_verticeDestino.getText().isEmpty())
        {
            if( grafo.get( edt_remover_verticeOrigem.getText() ) != null && grafo.get( edt_remover_verticeDestino.getText() ) != null  )
            {
                Vertice v1 = grafo.get( edt_remover_verticeOrigem.getText() );
                Vertice v2 = grafo.get( edt_remover_verticeDestino.getText() );
                if( v1.getListaAdjacencias().contains(v2) && v2.getListaAdjacencias().contains(v1) )
                    grafo.removerAresta(v1, v2);
                else
                    Alerts.showAlert("Error Aresta", null, "A ligação não existe", Alert.AlertType.ERROR);
                
                edt_remover_verticeOrigem.clear();
                edt_remover_verticeDestino.clear();
            }
            else if( grafo.get( edt_remover_verticeOrigem.getText() ) == null )
                Alerts.showAlert("Error Vertice", null, "O ponto de Origem não existe", Alert.AlertType.ERROR);
            else if( grafo.get( edt_remover_verticeDestino.getText() ) == null )
                Alerts.showAlert("Error Vertice", null, "O ponto de Destino não existe", Alert.AlertType.ERROR);
        }
        else if( edt_remover_verticeOrigem.getText().isEmpty() )
            Alerts.showAlert("Error Campo de Text", null, "Preencha um valor para o campo de texto Vertice Origem", Alert.AlertType.ERROR);
        else if( edt_remover_verticeDestino.getText().isEmpty() )
            Alerts.showAlert("Error Campo de Text", null, "Preencha um valor para o campo de texto Vertice Destino", Alert.AlertType.ERROR);
    }
    
    @FXML
    private void removerVertice(ActionEvent event) 
    {
        if( !edt_remover_nomeVertice.getText().isEmpty() && grafo.get( edt_remover_nomeVertice.getText() ) != null )
        {
            Vertice v = grafo.get( edt_remover_nomeVertice.getText() );
            grafo.removerVertice( v );
            edt_remover_nomeVertice.clear();
        }
        else if( edt_remover_nomeVertice.getText().isEmpty() )
            Alerts.showAlert("Error Campo de Text", null, "Preencha um valor para o campo de texto Nome do Vertice", Alert.AlertType.ERROR);
        else
            Alerts.showAlert("Error Vertice", null, "O ponto não existe", Alert.AlertType.ERROR);
    }
    
    @FXML
    private void inserirArquivo(ActionEvent event) throws IOException 
    {
        try{
            if( !edt_nomeArquivo.getText().isEmpty() )
            {
                grafo.carregarArquivo(edt_nomeArquivo.getText());
                tab_inserir.setDisable(false);
                tab_remover.setDisable(false);
                tab_calcular.setDisable(false);
            }
            else
                Alerts.showAlert("Error", null, "Campo do nome de arquivo vazio", Alert.AlertType.ERROR);
        }
        catch (FileNotFoundException ex) 
        {
            Alerts.showAlert("Erro Arquivo", null, "O arquivo não foi encontrado", Alert.AlertType.ERROR);
        } 
    }
    
    @FXML
    private void calcularMenorCaminho(ActionEvent event) throws IOException 
    {
        if( !edt_pontoColeta.getText().isEmpty() && !edt_pontoBanco.getText().isEmpty() )
        {
            if( this.grafo.get(edt_pontoColeta.getText()) != null && this.grafo.get(edt_pontoBanco.getText()) != null )
            {
                txt_nomeCaminhoLabel.setVisible(true);
                //MenorCaminho m = new MenorCaminho(this.grafo.getAdj(), this.grafo.getListArestas(), this.grafo.get("A"));
                //txt_caminho.setText( m.Dijkstra("BB").get(0).toString() );
                //txt_caminho.setText( grafo.dijkstra( edt_pontoColeta.getText(), edt_pontoBanco.getText()) );
            }
            else if( this.grafo.get(edt_pontoColeta.getText()) == null )
                Alerts.showAlert("Error", null, "Ponto de coleta não existente", Alert.AlertType.ERROR);
            else if( this.grafo.get(edt_pontoBanco.getText()) == null )
                Alerts.showAlert("Error", null, "Ponto do Banco não existente", Alert.AlertType.ERROR);
        }
        else if( edt_pontoColeta.getText().isEmpty() )
            Alerts.showAlert("Error", null, "Campo do nome de coleta vazio", Alert.AlertType.ERROR);
        else if( edt_pontoBanco.getText().isEmpty() )
            Alerts.showAlert("Error", null, "Campo do nome do banco vazio", Alert.AlertType.ERROR);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
