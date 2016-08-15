package extractor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;

public class HtmlExtractor
{
	final static String[] games = { "tekken6", "tekken5dr", "tekken5", "tekken4", "tekkentag", "tekken3" };
	final static String[] characters = { "akuma", "alex", "alisa", "ancientogre", "angel", "anna", "armorking",
			"asuka", "azazel", "baek", "bob", "bruce", "bryan", "christie", "claudio", "combot", "marduk",
			"crow", "devil", "devilkazuya", "devilkazumi", "Dr._Bosconovitch", "eddy", "feng", "forest", "ganryu",
			"gigas", "gon", "gunjack", "heihachi", "hwoarang", "jack1", "jack2", "jack3", "jack4", "jack5", "jack6",
			"jack7", "jin", "jinpachi", "josie", "julia", "jun", "katarina", "kazumi", "kazuya", "king", "kuma",
			"kunimitsu", "lars", "lee", "lei", "leo", "lili", "ling", "luckychloe", "marshall", "masterraven",
			"michelle", "miguel", "miharu", "mokujin", "NANCY-MI847J", "nina", "panda", "paul", "prototype", 
			"raven", "roger", "rogerjr", "shaheen", "sebastian", "dragunov", "slim_bob", "steve", "tiger", "trueogre",
			"violet", "wang", "yoshimitsu", "zafina"
	};
	
	public static void main (String[] args) throws IOException {

		if(args.length == 1) { 
			if(args[0] == "all") {
				System.out.println("Extracting All Movelists...");
				for(String game : games) {
					extract(game);
				}
			}
			else if(Arrays.asList(games).contains(args[0])){
				System.out.println("Extracting " + args[0] + "Movelists...");
				extract(args[0]);
			}
			else if(args[0].equalsIgnoreCase("help")) {
				System.out.println("Enter a Tekken game or all to get all movelists.");
				System.out.println("Recognized games: ");
				System.out.println(Arrays.toString(games));
			}
			else {
				System.out.println("Game not recognized");
			}
		}
		
		System.out.println("Extracting tekken6 Movelists...");
		extract("tekken6");
		
	}
	
	private static void extract(String game) throws IOException {
		ArrayList<Movelist> movelists = new ArrayList<Movelist>();
		for(String character : characters) {
			Movelist movelist = extractMovelist(game, character);
			if(movelist != null)
				movelists.add(movelist);
			JSONify(game, movelists);
		}
		movelists.clear();
	}
	
	public static Movelist extractMovelist(String game, String character) throws IOException {
		URL url 			= new URL ("http://www.tekkenzaibatsu.com/" + game + "/movelist.php?id=" + character);

		//check if page exists
		try {
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection huc = (HttpURLConnection) url.openConnection();	//open connection to URL
			huc.setRequestMethod("HEAD");
			int responseCode = huc.getResponseCode();	//404, 403, 402, 200, etc..
			if(responseCode != 200) return null;		//quit if webpage doesn't exist
			huc.disconnect();
		} catch (SocketException e) { 					//denied connection
			return null;
		}
		
		URLConnection con			= url.openConnection();
		InputStream is 				= con.getInputStream();	//get inputstream of url
		InputStreamReader isr		= new InputStreamReader(is);
		BufferedReader br 			= new BufferedReader(isr);//read inputstream
		ArrayList<String> contents 	= fillFromBuffer(br);	//put bufferedreader contents into an arraylist
		
		//move list data
		String author 									= null;
		String copyright 								= null;
		ArrayList<String> titles 						= new ArrayList<String>();
		ArrayList<ArrayList<String>> headers 			= new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<ArrayList<String>>> moves 	= new ArrayList<ArrayList<ArrayList<String>>>();
		ArrayList<ArrayList<String>> footnotes			= new ArrayList<ArrayList<String>>();
		
		for(int i=0; i<contents.size(); i++) {
			String line = contents.get(i);	//line in html text
			
			//obtain absolutes
			switch(i) {
			case 11:
				author = line.substring(28, line.length()).replace("/", "").replace(">", "").replace("\"", "").trim();
				break;
			case 12:
				copyright = line.substring(31, line.length()).replace("/", "").replace(">", "").replace("\"", "").trim();
				break;
			default: break;
			}
			
			//dive into the subheaders (Arts like Throws, Basics, Strings, Unblockables, etc.)
			if(line.contains("<tr class=\"subheader\">")) {

				//header of Art (attributes of a move)
				ArrayList<String> header = new ArrayList<String>();
				int j=1;
				while(true) {
					if(contents.get(i+j).trim().equals("</tr>")) break;
					header.add(trimHTML(contents.get(i+j)).trim());
					j++;
				}
				
				//individual moves
				ArrayList<ArrayList<String>> movs = new ArrayList<ArrayList<String>>();
				while(true) {
					j++;
					ArrayList<String> move = new ArrayList<String>();
					if(contents.get(i+j).trim().equals("</table>")) break;
					while(true) {
						if(contents.get(i+j+1).trim().equals("</tr>")) { j++; break; }
						move.add(trimRight(trimHTML(contents.get(i+j+1)).substring(16, trimHTML(contents.get(i+j+1)).length())));
						j++;
					}
					movs.add(move);
				}
				
				if(movs.size() != 0 && movs.get(0).size() == 1) //remove stance related helpers
					movs.remove(0);
				
				//footnotes
				j += 6;
				ArrayList<String> footnote = null;
				if(trimHTML(contents.get(i+j)).trim().equals("Foot Notes")) {
					footnote = new ArrayList<String>();
					while(true) {
						if(contents.get(i+j+1).trim().equals("</fieldset>")) break;
						footnote.add(trimHTML(contents.get(i+j+1)).trim());
						j++;
					}
				}
				
				//add title, header, moves, and footnotes to their respective lists
				titles.add(trimHTML(contents.get(i-4)).trim());
				headers.add(header);
				moves.add(movs);
				footnotes.add(footnote);
			}
		}
		
		is.close();
		isr.close();
		br.close();
		return new Movelist(game, character, author, copyright, titles, headers, moves, footnotes);
	}
	
	public static void JSONify(String game, ArrayList<Movelist> movelists) throws IOException {
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
			
			String									character 	= movelist.character;
			String 									author		= movelist.author;
			String 									copyright	= movelist.copyright;
			ArrayList<String> 						titles		= movelist.titles;
			ArrayList<ArrayList<String>> 			headers		= movelist.headers;
			ArrayList<ArrayList<ArrayList<String>>> moves		= movelist.moves;
			ArrayList<ArrayList<String>> 			footnotes	= movelist.footnotes;
			
			String indent = "\t\t\t";
			charContents.add(indent + "\"name\": \"" + character + "\",");
			charContents.add(indent + "\"author\": \"" + author + "\",");
			charContents.add(indent + "\"copyright\": \"" + copyright + "\",");
			for(int i=0; i<titles.size(); i++) {
				charContents.add(indent + "\"" + titles.get(i) + "\": {");
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
	
	private static ArrayList<String> fillFromBuffer(BufferedReader br) throws IOException {
		String l = null;
		ArrayList<String> contents = new ArrayList<String>();
		while((l = br.readLine()) != null) {
			contents.add(l);
		}
		return contents;
	}
	private static String trimHTML(String str) {
		return str.replaceAll("<[^>]*>", "").replace("&nbsp;", " ").replace("&lt;", "<").replace("&quot;", "\"");
	}
	private static String trimRight(String str) {
		return str.replaceAll("\\s+$","");
	}
	
}
