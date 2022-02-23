// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            int openParen = markdown.indexOf("(", nextCloseBracket);
            int closeParen = markdown.indexOf(")", openParen);

            //if missing paren/bracket
            if (nextOpenBracket == -1 || nextCloseBracket == -1 || openParen == -1 || closeParen == -1) {
                break; //don't output
            }
            else if (openParen - 1 != nextCloseBracket) { //if parenthesis is not next to close bracket
                break; //maybe continue instead for the image file
            }

            //tests image if there's an exclamation point before the open bracket, increment the index 
            //and don't output that image link
            try {
                if (markdown.substring(nextOpenBracket - 1, nextOpenBracket).equals("!")) {
                    currentIndex++;
                    continue;
                }
            }
            catch (IndexOutOfBoundsException e) {
                //do nothing
                //test git choice
            }

            //does not output words || group's work, not sure
            if (nextOpenBracket == 0) {
                toReturn.add(markdown.substring(openParen + 1, closeParen)); //add whatever's in (..) to toReturn
                currentIndex = closeParen + 1; //next index
            }
            else if (!markdown.substring(nextOpenBracket - 1, nextCloseBracket).equals("!")) { //if there isn't an exclamation point
            //before [...] brackets, add whatever's in (...) to toReturn and increment index
                toReturn.add(markdown.substring(openParen + 1, closeParen));
                currentIndex = closeParen + 1;
            }

            // toReturn.add(markdown.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1;
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
        // System.out.println("test see");
    }
}