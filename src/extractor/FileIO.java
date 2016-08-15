package extractor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO
{

	public static void writeToFile(String game, ArrayList<Movelist> movelists) throws IOException {
		//create file
		String filename = game + ".JSON";
		File file = new File(filename);
		file.delete();
		file.createNewFile();
		
		//contents of JSON file by line
		ArrayList<String> charContents = new ArrayList<String>();
		charContents.add("{");
		charContents.add("\t\"game\": \"" + game + "\",");
		charContents.add("\t\"movelists\": [");
	
		//character-movelist data
		for(int a=0; a<movelists.size(); a++) {
			Movelist movelist = movelists.get(a);
			charContents.add("\t\t{");
			
			Char									character 	= movelist.character;
			String 									author		= movelist.author;
			String 									copyright	= movelist.copyright;
			ArrayList<String> 						titles		= movelist.titles;
			ArrayList<ArrayList<String>> 			headers		= movelist.headers;
			ArrayList<ArrayList<ArrayList<String>>> moves		= movelist.moves;
			ArrayList<ArrayList<String>> 			footnotes	= movelist.footnotes;
			
			String indent = "\t\t\t";
			charContents.add(indent + "\"name\": \"" + character.name + "\",");
			charContents.add(indent + "\"fullname\": \"" + character.fullname + "\",");
			charContents.add(indent + "\"sex\": \"" + character.sex + "\",");
			charContents.add(indent + "\"origin\": \"" + character.origin + "\",");
			charContents.add(indent + "\"fightingStyle\": \"" + character.fightingStyle + "\",");
			charContents.add(indent + "\"author\": \"" + author + "\",");
			charContents.add(indent + "\"copyright\": \"" + copyright + "\",");
			for(int i=0; i<titles.size(); i++) {
				charContents.add(indent + "\"" + titles.get(i) + "\": {");
				charContents.add(indent + "\t\"art\": \"" + titles.get(i) + "\",");
				charContents.add(indent + "\t\"header\": [");
				for(int j=0; j<headers.get(i).size(); j++) {	//header
					String c = indent + "\t\t\"" + headers.get(i).get(j) + "\"";
					if(j != headers.get(i).size()-1)
						c += ",";
					charContents.add(c);
				}
				charContents.add(indent + "\t],");
				charContents.add(indent + "\t\"moves\": [");
				
				for(int j=0; j<moves.get(i).size(); j++) {
					String c = indent + "\t\t[";
					for(int k=0; k<moves.get(i).get(j).size(); k++) {
						c += "\"" + moves.get(i).get(j).get(k) + "\"";
						if(k != moves.get(i).get(j).size()-1)
							c += ",";
					}
					c += "]";
					if(j != moves.get(i).size()-1)
						c += ",";
					charContents.add(c);
				}
				charContents.add(indent + "\t],");
				
				//footnotes
				try {
					charContents.add(indent + "\t\"footnotes\": [");
					for(int j=0; j<footnotes.get(i).size(); j++) {
						String c = indent + "\t\t\"" + footnotes.get(i).get(j) + "\"";
						if(j != footnotes.get(i).size()-1) 
							c += ",";
						charContents.add(c);
					}
					charContents.add(indent + "\t]");
				} catch(NullPointerException e) {
					charContents.add(indent + "\t]");
				}
				
				if(i == titles.size()-1)
					charContents.add(indent + "}");
				else
					charContents.add(indent + "},");
			}
			if(a == movelists.size()-1)
				charContents.add("\t\t}");
			else
				charContents.add("\t\t},");
		}
		charContents.add("\t]");
		charContents.add("}");
		
		//write to JSON file
		FileWriter fw = new FileWriter(file);
		for(String c : charContents)
			fw.write(c + "\n");
		fw.flush();
		fw.close();
	}
	
}
