package extractor;

import java.io.BufferedReader;
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
	public static void main (String[] args) throws IOException {
		ArrayList<Char> characters = GameInfo.createCharacters();	//create characters	
		if(args.length == 1) { 
			if(args[0].equalsIgnoreCase("all")) {
				System.out.println("Extracting All Movelists...");
				for(String game : GameInfo.games) {
					extract(game, characters);
				}
			}
			else if(Arrays.asList(GameInfo.games).contains(args[0].toLowerCase())){
				System.out.println("Extracting " + args[0] + " Movelists...");
				extract(args[0].toLowerCase(), characters);
			}
			else if(args[0].equalsIgnoreCase("help")) {
				System.out.println("Enter a Tekken game or all to get all movelists.");
				System.out.println("Recognized games: ");
				System.out.println(Arrays.toString(GameInfo.games));
			}
			else {
				System.out.println("Game \"" + args[0] + "\" not recognized.");
				System.out.println("Use argument \"help\" for a list of recognized games.");
			}
		}
		else if(args.length == 0){ //no args => run most recent game
			System.out.println("Extracting " + GameInfo.games[0] + " Movelists...");
			extract(GameInfo.games[0], characters);
		}
		else { //multiple args
			for(String arg : args) {
				if(Arrays.asList(GameInfo.games).contains(arg.toLowerCase())) {
					System.out.println("Extracting " + arg + " Movelists...");
					extract(arg.toLowerCase(), characters);
				}
				else {
					System.out.println("Game \"" + arg + "\" not recognized.");
					System.out.println("Use argument \"help\" for a list of recognized games.");
				}
			}
		}
	}
	
	private static void extract(String game, ArrayList<Char> characters) throws IOException {
		ArrayList<Movelist> movelists = new ArrayList<Movelist>();
		for(Char character : characters) {
			Movelist movelist = extractMovelist(game, character);
			if(movelist != null)
				movelists.add(movelist);
			FileIO.writeToFile(game, movelists);
		}
		movelists.clear();
	}
	
	public static Movelist extractMovelist(String game, Char character) throws IOException {
		URL url = new URL ("http://www.tekkenzaibatsu.com/" + game + "/movelist.php?id=" + character.id);

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
						int indent;
						if( game.equals("tekken4") || game.equals("tekken3") || game.equals("tekkentag") )
							indent = 18;
						else
							indent = 16;
						move.add(trimRight(trimHTML(contents.get(i+j+1)).substring(indent, trimHTML(contents.get(i+j+1)).length())));
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
				if( game.equals("tekken4") || game.equals("tekken3") || game.equals("tekkentag") )
					titles.add(trimHTML(contents.get(i-3)).trim());
				else
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
	
	private static ArrayList<String> fillFromBuffer(BufferedReader br) throws IOException {
		String l = null;
		ArrayList<String> contents = new ArrayList<String>();
		while((l = br.readLine()) != null) {
			contents.add(l);
		}
		return contents;
	}
	private static String trimHTML(String str) {
		return str.replaceAll("<[^>]*>", "").replace("&nbsp;", " ").replace("&lt;", "<")
				.replace("&quot;", "").replace("&amp;", "&");
	}
	private static String trimRight(String str) {
		return str.replaceAll("\\s+$","");
	}
	
}
