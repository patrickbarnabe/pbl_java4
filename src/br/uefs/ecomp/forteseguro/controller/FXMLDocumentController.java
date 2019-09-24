/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.forteseguro.controller;

import br.uefs.ecomp.forteseguro.model.Aresta;
import br.uefs.ecomp.forteseguro.model.Dijkstra;
import br.uefs.ecomp.forteseguro.model.Grafo;
import br.uefs.ecomp.forteseguro.model.Vertice;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
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
    String novoEstacionamento = "";
    
    @FXML
    private Tab tab_inserir, tab_remover, tab_calcular, tab_inicio;
    
    @FXML
    private Button btn_calcularMenorCaminho, btn_inserirAdj, btn_removerVertice, btn_novoEstacionamento;
    
    @FXML
    private TextField edt_peso_listAdj, edt_destino_listAdj, 
            edt_inserir_verticeNome, edt_inserir_verticeTipo, 
            edt_remover_nomeVertice, edt_remover_verticeDestino,
            edt_remover_verticeOrigem, edt_nomeArquivo,
            edt_pontoColeta, edt_pontoBanco;
    
    @FXML
    private Label txt_arestas, txt_caminho, txt_nomeCaminhoLabel;
    
    /**
     * Metodo reponsavel por inserir um vertice no grafo
     * @param event 
     */
    @FXML
    private void inserirVertice(ActionEvent event) 
    {
        if( !edt_inserir_verticeNome.getText().isEmpty() )
            if( this.listTempAdj.isEmpty() && !edt_inserir_verticeTipo.getText().isEmpty() )
            {
                Vertice v = new Vertice(edt_inserir_verticeTipo.getText().toLowerCase(), edt_inserir_verticeNome.getText().toLowerCase() );
                if( grafo.verificaVertice(v) == false )
                {
                    grafo.adcionaVertice( v.getNome(), v.getTipo() );
                    this.listTempAdj.clear();
                    txt_arestas.setText("");

                    if( "estacionamento".equals( edt_inserir_verticeTipo.getText().toLowerCase() ) )
                        btn_calcularMenorCaminho.setDisable(false);
                }
                else
                    Alerts.showAlert("Error", null, "Vertice já existente", Alert.AlertType.ERROR);
                
                btn_inserirAdj.setDisable(false);
                tab_remover.setDisable(false);
                edt_inserir_verticeNome.clear();
                edt_inserir_verticeTipo.clear();
            }
            else
            {
                grafo.adcionaVertice(edt_inserir_verticeNome.getText().toLowerCase(), edt_inserir_verticeTipo.getText().toLowerCase(), this.listTempAdj);
                this.listTempAdj.clear();
                txt_arestas.setText("");
                
                if( "estacionamento".equals( edt_inserir_verticeTipo.getText().toLowerCase() ) )
                    btn_calcularMenorCaminho.setVisible(true);
                
                btn_inserirAdj.setDisable(false);
                tab_remover.setDisable(false);
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
    
    /**
     * Metodo resposavel por criar uma ligação entre dois vertices do grafo
     * @param event 
     */
    @FXML
    private void inserirAdj(ActionEvent event) 
    {
        if( !edt_destino_listAdj.getText().isEmpty() && !edt_peso_listAdj.getText().isEmpty() && !edt_inserir_verticeNome.getText().isEmpty() )
        {
            if( !edt_destino_listAdj.getText().equals(edt_inserir_verticeNome.getText()) && grafo.get(edt_destino_listAdj.getText()) != null && grafo.get(edt_inserir_verticeNome.getText()) != null && Integer.parseInt(edt_peso_listAdj.getText()) > 0 )
            {
                Vertice v = grafo.get(edt_destino_listAdj.getText());
                Vertice u = grafo.get(edt_inserir_verticeNome.getText());
                int peso = Integer.parseInt(edt_peso_listAdj.getText());
                Aresta aresta = new Aresta(peso, u, v);
                txt_arestas.setText( txt_arestas.getText() + edt_destino_listAdj.getText() + " -> " + edt_peso_listAdj.getText() + "\n" );
                this.listTempAdj.add(aresta);
                
                if( grafo.verificaVertice(u) == true )
                    btn_inserirAdj.setDisable(true);
                
                edt_peso_listAdj.clear();
                edt_destino_listAdj.clear();
            }
            else if ( edt_destino_listAdj.getText().equals(edt_inserir_verticeNome.getText())  )
                Alerts.showAlert("Error", null, "Insira vertices de origem e destino diferente", Alert.AlertType.ERROR);
            else if ( Integer.parseInt( edt_peso_listAdj.getText()) <= 0  )
                Alerts.showAlert("Error", null, "Insira um valor inteiro positivo no campo Peso", Alert.AlertType.ERROR);
            else if( grafo.get(edt_inserir_verticeNome.getText()) == null )
                Alerts.showAlert("Error Vertice Origem", null, "Vertice de origem não existente", Alert.AlertType.ERROR);
            else
                Alerts.showAlert("Error Vertice Destino", null, "Vertice de destino não existente", Alert.AlertType.ERROR);
        }
        else if( edt_peso_listAdj.getText().isEmpty() && edt_destino_listAdj.getText().isEmpty() )
            Alerts.showAlert("Error Campo de Text", null, "Preencha um valor para cada um dos campos de texto Peso e Destino", Alert.AlertType.ERROR);
        else if( edt_inserir_verticeNome.getText().isEmpty() )
                Alerts.showAlert("Error Vertice Origem", null, "Insira um vertice de origem", Alert.AlertType.ERROR);
        else if( edt_destino_listAdj.getText().isEmpty() )
            Alerts.showAlert("Error Campo de Text", null, "Preencha um valor para o campo de texto Destino", Alert.AlertType.ERROR);
        else if( edt_peso_listAdj.getText().isEmpty() )
            Alerts.showAlert("Error Campo de Text", null, "Preencha um valor inteiro positvo para o campo de texto Peso", Alert.AlertType.ERROR);
    }
    
    /**
     * Metodo responsavel por remover uma aresta do grafo
     * @param event 
     */
    @FXML
    private void removerAresta(ActionEvent event) 
    {
        if( !edt_remover_verticeOrigem.getText().isEmpty() && !edt_remover_verticeDestino.getText().isEmpty())
        {
            if( grafo.get( edt_remover_verticeOrigem.getText() ) != null && grafo.get( edt_remover_verticeDestino.getText() ) != null  )
            {
                Vertice v1 = grafo.get( edt_remover_verticeOrigem.getText() );
                Vertice v2 = grafo.get( edt_remover_verticeDestino.getText() );
                
                boolean bool = false;
                
                for( Aresta aresta : this.grafo.getListArestas() )
                {
                    if( aresta.getVerticeDestino().getNome().toLowerCase().equals(v1.getNome().toLowerCase()) && aresta.getVerticeOrigem().getNome().toLowerCase().equals(v2.getNome().toLowerCase()) || 
                            aresta.getVerticeDestino().getNome().toLowerCase().equals(v2.getNome().toLowerCase()) && aresta.getVerticeOrigem().getNome().toLowerCase().equals(v1.getNome().toLowerCase()) )
                    {
                        grafo.removerAresta(v1, v2);
                        bool = true;
                        break;
                    }
                }
                
                if( bool == false )
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
    
    /**
     * Metodo responsavel por remover um vertice do grafo
     * @param event 
     */
    @FXML
    private void removerVertice(ActionEvent event) 
    {
        if( !edt_remover_nomeVertice.getText().isEmpty() && grafo.get( edt_remover_nomeVertice.getText() ) != null )
        {
            Vertice vertice = grafo.get( edt_remover_nomeVertice.getText() );
            if( vertice.getTipo().equals("estacionamento") )
            {
                grafo.removerVertice( vertice );
                edt_remover_nomeVertice.clear();
                tab_calcular.setDisable(true);
                tab_inserir.setDisable(true);
                tab_inicio.setDisable(true);
                btn_novoEstacionamento.setVisible(true);
                
                List<String> list = new ArrayList<String>();
                
                for( Vertice nomeV : this.grafo.getAdj() )
                    if( !nomeV.getTipo().equals("estacionamento") )
                        list.add(nomeV.getNome());
                
                btn_novoEstacionamento.setOnAction(e -> {
                    // o primeiro parâmetro é a escola padrão e os outros são os valores da Choice Box
                    ChoiceDialog dialogoRegiao = new ChoiceDialog();
                    
                    dialogoRegiao.getItems().addAll(list);
                    dialogoRegiao.setTitle("Inserir novo Estacionamento");
                    dialogoRegiao.setHeaderText("Vertices cadastrados");
                    dialogoRegiao.setContentText("Vertices:");
                    dialogoRegiao.showAndWait().ifPresent(r -> novoEstacionamento = r.toString() );
                    
                    if( !novoEstacionamento.equals("") )
                    {
                        this.grafo.setEstacionamento( this.grafo.get( novoEstacionamento ) );
                        this.grafo.get( novoEstacionamento ).setTipo("estacionamento");
                        tab_calcular.setDisable(false);
                        tab_inserir.setDisable(false);
                        tab_inicio.setDisable(false);
                        btn_novoEstacionamento.setVisible(false);
                    }
                });
            }
            else{
                grafo.removerVertice( vertice );
                edt_remover_nomeVertice.clear();
            }
        }
        else if( edt_remover_nomeVertice.getText().isEmpty() )
            Alerts.showAlert("Error Campo de Text", null, "Preencha um valor para o campo de texto Nome do Vertice", Alert.AlertType.ERROR);
        else
            Alerts.showAlert("Error Vertice", null, "O ponto não existe", Alert.AlertType.ERROR);
    }
    
    /**
     * Metodo responsavel por inserir o arquivo com os dados do grafo
     * @param event
     * @throws IOException 
     */
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
                if( "estacionamento".equals(this.grafo.getEstacionamento().getTipo().toLowerCase()) )
                    btn_calcularMenorCaminho.setDisable(false);
            }
            else
                Alerts.showAlert("Error", null, "Campo do nome de arquivo vazio", Alert.AlertType.ERROR);
        }
        catch (FileNotFoundException ex) 
        {
            Alerts.showAlert("Erro Arquivo", null, "O arquivo não foi encontrado", Alert.AlertType.ERROR);
        } 
    }
    
    /**
     * Metodo responsavel por calcular o menor caminho do grafo e enviar este caminho
     * para a view
     * @param event
     * @throws IOException 
     */
    @FXML
    private void calcularMenorCaminho(ActionEvent event) throws IOException 
    {
        if( !edt_pontoColeta.getText().isEmpty() && !edt_pontoBanco.getText().isEmpty() )
        {
            if( this.grafo.get(edt_pontoColeta.getText()) != null && this.grafo.get(edt_pontoColeta.getText()).getTipo().equals("coleta") &&
                    this.grafo.get(edt_pontoBanco.getText()) != null && this.grafo.get(edt_pontoBanco.getText()).getTipo().equals("banco")
                    && this.grafo.getEstacionamento() != null )
            {                
                txt_caminho.setText( "" );
                
                try
                {
                    Dijkstra d = new Dijkstra(grafo);

                    d.executar(this.grafo.getEstacionamento());
                    List listColeta = d.getCaminho(this.grafo.getAdj(), this.grafo.get(edt_pontoColeta.getText())).get(0);

                    Dijkstra e = new Dijkstra(grafo);

                    e.executar(this.grafo.get(edt_pontoColeta.getText()));
                    List listBanco = e.getCaminho(this.grafo.getAdj(), this.grafo.get(edt_pontoBanco.getText())).get(0);

                    for( Object coleta : listColeta )
                        if( !((Vertice)coleta).getNome().toLowerCase().equals(edt_pontoColeta.getText().toLowerCase()) )
                            txt_caminho.setText( txt_caminho.getText() + ((Vertice)coleta).getNome() + " -> " );
                        else if( ((Vertice)coleta).getNome().toLowerCase().equals(edt_pontoColeta.getText().toLowerCase()) )
                        {
                            txt_caminho.setText( txt_caminho.getText() + ((Vertice)coleta).getNome() + "\n" );
                            break;
                        }

                    for( Object banco : listBanco )
                        if( !((Vertice)banco).getNome().toLowerCase().equals(edt_pontoBanco.getText().toLowerCase()) )
                            txt_caminho.setText( txt_caminho.getText() + ((Vertice)banco).getNome() + " -> " );
                        else if( ((Vertice)banco).getNome().toLowerCase().equals(edt_pontoBanco.getText().toLowerCase()) )
                        {
                           txt_caminho.setText( txt_caminho.getText() + ((Vertice)banco).getNome() + "\n" );
                           break;
                        }

                    txt_nomeCaminhoLabel.setVisible(true);
                    
                }catch( NullPointerException e ){
                    Alerts.showAlert("Error", null, "Não existe uma ligação entre os dois pontos informados", Alert.AlertType.ERROR);
                    edt_pontoBanco.clear();
                    edt_pontoColeta.clear();
                }
            }
            else if( !this.grafo.get(edt_pontoBanco.getText()).getTipo().equals("banco") )
                Alerts.showAlert("Error", null, "O Ponto informado não é um ponto de Banco", Alert.AlertType.ERROR);
            else if( !this.grafo.get(edt_pontoColeta.getText()).getTipo().equals("coleta") )
                Alerts.showAlert("Error", null, "O Ponto informado não é um ponto de Coleta", Alert.AlertType.ERROR);
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
