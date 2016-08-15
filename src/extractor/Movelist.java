package extractor;

import java.util.ArrayList;

public class Movelist
{
	String									game;
	String									character;
	String 									author;
	String 									copyright;
	ArrayList<String> 						titles;
	ArrayList<ArrayList<String>> 			headers;
	ArrayList<ArrayList<ArrayList<String>>> moves;
	ArrayList<ArrayList<String>> 			footnotes;
	
	public Movelist(String game, String character, String author, String copyright, ArrayList<String> titles,
			ArrayList<ArrayList<String>> headers, ArrayList<ArrayList<ArrayList<String>>> moves, 
			ArrayList<ArrayList<String>> footnotes) {
		
		this.game			= game;
		this.character		= character;
		this.author 		= author;
		this.copyright 		= copyright;
		this.titles 		= titles;
		this.headers 		= headers;
		this.moves 			= moves;
		this.footnotes 		= footnotes;
	}
}
