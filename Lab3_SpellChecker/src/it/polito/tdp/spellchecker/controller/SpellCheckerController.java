package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.*;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SpellCheckerController {
	
	Dictionary dizionario;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbx;

    @FXML
    private TextArea txtArea1;

    @FXML
    private Button btnSpellCheck;

    @FXML
    private TextArea txtArea2;

    @FXML
    private Label lblErrors;

    @FXML
    private Button btnClearText;

    @FXML
    private Label lblTime;

    @FXML
    void doClearText(ActionEvent event) {
    	txtArea1.clear();
    	txtArea2.clear();
    	lblErrors.setText("");
    	cmbx.setValue(null);
    }
    
    @FXML
    void chooseLanguage(ActionEvent event){
    	dizionario.loadDictionary(cmbx.getValue());
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	long time=System.nanoTime();
    	
    	String input=txtArea1.getText().toLowerCase().replaceAll("[ \\p{Punct}]"," ");
    	String inserite[]=input.split(" ");
    	
    	List<String> parole=new LinkedList<String>();
    	for(int i=0;i<inserite.length;i++){
    		parole.add(inserite[i]);
    	}
    	
    	List<RichWord>output=new LinkedList<RichWord>(dizionario.spellCheckText(parole));
    	long timeTot=System.nanoTime()-time;
    	
    	for(RichWord rw : output)
    		txtArea2.appendText(rw.getParola()+"\n");
		
		lblErrors.setText("The text contains "+output.size()+" errors");
		lblTime.setText("Spell check completed in "+timeTot+" nanoseconds");
    }

    @FXML
    void initialize() {
        assert cmbx != null : "fx:id=\"cmbx\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtArea1 != null : "fx:id=\"txtArea1\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtArea2 != null : "fx:id=\"txtArea2\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert lblErrors != null : "fx:id=\"lblErrors\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        
        cmbx.getItems().addAll("Italian","English");
        dizionario=new Dictionary();
    }
}
