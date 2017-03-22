package it.polito.tdp.spellchecker.model;
import java.io.*;
import java.util.*;

public class Dictionary {
	
	private List<RichWord>inserite;
	private List<String>dizionario;
	private int contErrori=0;
	
	public Dictionary() {
		
		this.inserite=new LinkedList<RichWord>();
		this.dizionario=new LinkedList<String>();
	}

	public void loadDictionary(String language){
		try {
			BufferedReader br;
			br = new BufferedReader(new FileReader("rsc/"+language+".txt"));
		
			String word;
			while((word = br.readLine()) != null ) {
				dizionario.add(word);
			}
			br.close();
			} catch (IOException e){
			System. out .println("Errore nella lettura del file");
			}
	}
	
	public void addParola(RichWord parola){
		inserite.add(parola);
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		
		List<RichWord> paroleSbagliate=new LinkedList<RichWord>();
		for(String str:inputTextList){
			if(!dizionario.contains(str)){
				RichWord rw=new RichWord(str,false);
				paroleSbagliate.add(rw);
			}
			else{
				RichWord rw=new RichWord(str,true);
			}
		}
		return paroleSbagliate;
	}	
}
