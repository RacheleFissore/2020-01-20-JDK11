package it.polito.tdp.artsmia;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.artsmia.model.Adiacenza;
import it.polito.tdp.artsmia.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ArtsmiaController {
	
	private Model model ;
	private boolean entrato = false;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private Button btnArtistiConnessi;

    @FXML
    private Button btnCalcolaPercorso;

    @FXML
    private ComboBox<String> boxRuolo;

    @FXML
    private TextField txtArtista;

    @FXML
    private TextArea txtResult;

    @FXML
    void doArtistiConnessi(ActionEvent event) {
    	if(entrato) {
    		txtResult.clear();
        	txtResult.appendText("Calcola artisti connessi");
        	for(Adiacenza adiacenza : model.artistiConnessi()) {
        		txtResult.appendText(adiacenza + "\n");
        	}
    	}
    	else {
    		txtResult.appendText("Creare prima il grafo");
    	}
    }

    @FXML
    void doCalcolaPercorso(ActionEvent event) {
    	if(entrato && txtArtista.getText() != null) {
    		txtResult.clear();
    		txtResult.appendText("Calcola percorso\n");
    		txtResult.appendText(model.calcolaPercorso(Integer.parseInt(txtArtista.getText())));
    	}
    	else {
    		txtResult.appendText("Creare prima il grafo");
    	}
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Crea grafo\n");
    	entrato = true;
    	
    	if(boxRuolo.getItems() != null) {
    		model.creaGrafo(boxRuolo.getValue());
    		txtResult.appendText("# VERTICI: " + model.getNVertici());
    		txtResult.appendText("\n# ARCHI: " + model.getNArchi());
    	}
    	else {
    		txtResult.appendText("Selezionare un ruolo");
    	}
    }

    public void setModel(Model model) {
    	this.model = model;
    	
    	boxRuolo.getItems().addAll(model.getRuoli());
    }

    
    @FXML
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert btnArtistiConnessi != null : "fx:id=\"btnArtistiConnessi\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert btnCalcolaPercorso != null : "fx:id=\"btnCalcolaPercorso\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert boxRuolo != null : "fx:id=\"boxRuolo\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtArtista != null : "fx:id=\"txtArtista\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Artsmia.fxml'.";
        
    }
}
