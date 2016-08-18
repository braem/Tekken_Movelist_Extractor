package extractor;

import java.util.ArrayList;

public class GameInfo {

	final static String[] games = { "tekken6", "tekken5dr", "tekken5", "tekken4", "tekkentag", "tekken3" };
	
	public static ArrayList<Char> createCharacters() {
		ArrayList<Char> c = new ArrayList<Char>();
		c.add(new Char("akuma", "Akuma", "Akuma", "Male", "Japan", "Martial arts rooted in Ansatsuken"));
		c.add(new Char("alex", "Alex", "Alex", "Non-Human", "DNA sample taken from an insect", "Commando Wrestling"));
		c.add(new Char("alisa", "Alisa", "Alisa Bosconovitch", "Female", "Russia", "Unique Humanoid-Cyborg Combat"));
		c.add(new Char("ancientogre", "Ancient Ogre", "Ancient Ogre", "Non-Human", "Unknown", ""));
		c.add(new Char("angel", "Angel", "Angel", "Non-Human", "Unknown", "Mishima Style Karate"));
		c.add(new Char("anna", "Anna", "Anna Williams", "Female", "Ireland", "Koppo Assassination Arts based in Aikido"));
		c.add(new Char("armorking", "Armor King", "Armor King", "Male", "Unknown", "Pro Wrestling"));
		c.add(new Char("asuka", "Asuka", "Asuka Kazama", "Female", "Japan", "Kazama Style Traditional Martial Arts"));
		c.add(new Char("azazel", "Azazel", "Azazel", "Non-Human", "Unknown", "Unknown"));
		c.add(new Char("baek", "Baek", "Baek Doo San", "Male", "Korea", "Tae Kwon Do"));
		c.add(new Char("bob", "Bob", "Bob Richards", "Male", "USA", "Free Style Karate"));
		c.add(new Char("bruce", "Bruce", "Bruce Irvin", "Male", "USA", "Muay Thai Kickboxing"));
		c.add(new Char("bryan", "Bryan", "Bryan Fury", "Male", "USA", "Kickboxing"));
		c.add(new Char("christie", "Christie", "Christie Monteiro", "Female", "Brazil", "Capoeira"));
		c.add(new Char("claudio", "Claudio", "Claudio Serafino", "Male", "Italy", "Sirius Styled Purification Sorcery"));
		c.add(new Char("craig", "Marduk", "Craig Marduk", "Male", "Australia", "Vale Tudo"));
		c.add(new Char("dragunov", "Dragunov", "Sergei Dragunov", "Male", "Russia", "Combat Sambo"));
		c.add(new Char("deviljin", "Devil Jin", "Devil Jin", "Non-Human", "Japan", "Advanced Mishima Style Karate, Kazama Style Defense"));
		c.add(new Char("devil", "Devil", "Devil Kazuya", "Non-Human", "Japan", "Advanced Mishima Style Fighting Karate"));
		c.add(new Char("drb", "Dr. Bosconovitch", "Dr. Bosconovitch", "Male", "Russia", "Panic Fighting"));
		c.add(new Char("eddy", "Eddy", "Eddy Gordo", "Male", "Brazil", "Capoeira"));
		c.add(new Char("feng", "Feng", "Feng Wei", "Male", "China", "God Fist Style Chinese Martial Arts"));
		c.add(new Char("forest", "Forest", "Forest Law", "Male", "USA", "Jeet Kune Do"));
		c.add(new Char("ganryu", "Ganryu", "Ganryu", "Male", "Japan", "Sumo Wrestling"));
		c.add(new Char("gigas", "Gigas", "Gigas", "Non-Human", "Unknown", "Destructive Impulse"));
		c.add(new Char("gon", "Gon", "Gon", "Non-Human", "Japan", "Dragon Self Defense"));
		c.add(new Char("gunjack", "Gun Jack", "Gun Jack", "Non-Human", "Unknown", "Power Fighting / Sheer Force"));
		c.add(new Char("heihachi", "Heihachi", "Heihachi Mishima", "Male", "Japan", "Mishima-Style Fighting Karate"));
		c.add(new Char("hwoarang", "Hwoarang", "Hwoarang", "Male", "Korea", "Tae Kwon Do"));
		c.add(new Char("jack2", "Jack-2", "Jack-2", "Non-Human", "Russia", "Power Fighting / Sheer Force"));
		c.add(new Char("jack5", "Jack-5", "Jack-5", "Non-Human", "Russia", "Power Fighting / Sheer Force"));
		c.add(new Char("jack6", "Jack-6", "Jack-6", "Non-Human", "Russia", "Power Fighting / Sheer Force"));
		c.add(new Char("jack7", "Jack-7", "Jack-7", "Non-Human", "Russia", "Power Fighting / Sheer Force"));
		c.add(new Char("jin", "Jin", "Jin Kazama", "Male", "Japan", "Advanced Mishima Karate based on Shito-ryu karate combined with Kazama-style Traditional martial arts based on Koryu bujutsu"));
		c.add(new Char("jinpachi", "Jinpachi", "Jinpachi Mishima", "Male", "Japan", "Advanced Mishima Karate (infused with devil powers)"));
		c.add(new Char("josie", "Josie", "Josie Rizal", "Female", "Philippines", "Eskrima and Kickboxing"));
		c.add(new Char("julia", "Julia", "Julia Chang", "Female", "USA", "Various Chinese Martial Arts"));
		c.add(new Char("jun", "Jun", "Jun Kazama", "Female", "Japan", "Kazama-style Traditional Martial Arts (based on Koryu bujutsu)"));
		c.add(new Char("katarina", "Katarina", "Katarina Alves", "Female", "Brazil", "Savate"));
		c.add(new Char("kazumi", "Kazumi", "Kazumi Mishima", "Female", "Japan", "Hachijou-Style Karate mixed with Mishima-Style Karate"));
		c.add(new Char("kazuya", "Kazuya", "Kazuya Mishima", "Male", "Japan", "Advanced Mishima Style Fighting Karate"));
		c.add(new Char("king", "King", "King", "Male", "Mexico", "Pro Wrestling"));
		c.add(new Char("kuma", "Kuma", "Kuma", "Non-Human", "Japan", "Heihachi Style Advanced Kuma Shin Ken"));
		c.add(new Char("kunimitsu", "Kunimitsu", "Kunimitsu", "Female", "Japan", "Manji Ninjutsu"));
		c.add(new Char("lars", "Lars", "Lars Alexandersson", "Male", "Sweden", "Shorinji Karate and Tekken Force Martial Arts"));
		c.add(new Char("lee", "Lee", "Lee Chaolan", "Male", "Japan", "Jeet Kune Do"));
		c.add(new Char("lei", "Lei", "Lei Wulong", "Male", "China", "5 Form Kung Fu and Zui Quan (Drunken Fist)"));
		c.add(new Char("leo", "Leo", "Leo Kliesen", "Ambiguous", "Germany", "Bajiquan"));
		c.add(new Char("lili", "Lili", "Lili De Rochefort", "Female", "Monaco", "Self-Taught"));
		c.add(new Char("ling", "Xiaoyu", "Ling Xiaoyu", "Female", "China", "Various Chinese Martial Arts"));
		c.add(new Char("luckychloe", "Lucky Chloe", "Lucky Chloe", "Female", "Unknown", "Freestyle Dance"));
		c.add(new Char("marshall", "Marshall", "Marshall Law", "Male", "USA", "Jeet Kune Do"));
		c.add(new Char("masterraven", "Master Raven", "Master Raven", "Female", "Unknown", "Ninjitsu"));
		c.add(new Char("michelle", "Michelle", "Michelle Chang", "Female", "USA", "Chinese Kenpo"));
		c.add(new Char("miguel", "Miguel", "Miguel Caballero Rojo", "Male", "Spain", "None; Brawling"));
		c.add(new Char("miharu", "Miharu", "Miharu Hirano", "Female", "Japan", "Various Chinese Martial Arts"));
		c.add(new Char("nina", "Nina", "Nina Williams", "Female", "Ireland", "Koppo Assassination Arts based in Aikido"));
		c.add(new Char("panda", "Panda", "Panda", "Non-Human", "China", "Heihachi Style Advanced Kuma Shin Ken"));
		c.add(new Char("paul", "Paul", "Paul Phoenix", "Male", "USA", "Judo-based Martial Arts"));
		c.add(new Char("prototype", "Prototype", "Prototype Jack", "Non-Human", "Russia", "Power Fighting / Sheer Force"));
		c.add(new Char("raven", "Raven", "Raven", "Male", "Unknown", "Ninjitsu"));
		c.add(new Char("roger", "Roger", "Roger", "Non-Human", "Unknown", "Commando Wrestling"));
		c.add(new Char("rogerjr", "Roger Jr.", "Roger Jr.", "Non-Human", "Unknown", "Commando Wrestling"));
		c.add(new Char("shaheen", "Shaheen", "Shaheen", "Male", "Saudi Arabia", "Military Fighting Style"));
		c.add(new Char("sebastian", "Sebastian", "Sebastian", "Male", "Monaco", "Self-Taught"));
		c.add(new Char("slim_bob", "Slim Bob", "Slim Bob Richards", "Male", "USA", "Free Style Karate"));
		c.add(new Char("steve", "Steve", "Steve Fox", "Male", "United Kingdom", "Boxing"));
		c.add(new Char("tiger", "Tiger", "Tiger Jackson", "Male", "Unknown", "Capoeira"));
		c.add(new Char("trueogre", "True Ogre", "True Ogre", "Non-Human", "Unknown", "Personal style sampled from many fighters"));
		c.add(new Char("violet", "Violet", "Violet", "Male", "Japan", "Jeet Kune Do"));
		c.add(new Char("wang", "Wang", "Wang Jinrei", "Male", "China", "Xing Yi"));
		c.add(new Char("yoshimitsu", "Yoshimitsu", "Yoshimitsu", "Non-Human", "None (formerly Japan)", "Advanced Manji Ninja Arts"));
		c.add(new Char("zafina", "Zafina", "Zafina", "Female", "Unknown (probably Egypt)", "Ancient Assassination Arts"));
		c.add(new Char("jaycee", "Jaycee", "Jaycee", "Female", "USA", "Various Chinese Martial Arts"));
		c.add(new Char("unknown", "Unknown", "Unknown", "Non-Human", "Unknown", "Kazama Style Self Defense and Demon Power"));
		return c;
	}
}
