import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.*;

public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    public ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
    
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
    
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            if(nextOpenBracket < 0){
                currentIndex++;
                continue;
            }
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            if(nextCloseBracket < 0){
                currentIndex = nextOpenBracket + 1;
                continue;
            }
            int openParen = markdown.indexOf("(", nextCloseBracket);
            if(openParen < 0){
                currentIndex = nextCloseBracket + 1;
                continue;
            }
            int closeParen = markdown.indexOf(")", openParen);
            if(closeParen < 0){
                currentIndex = openParen + 1;
                continue;
            }
            int closeParenUsed = closeParen;
            if(containsLine(markdown.substring(closeParen, markdown.length()))){ //this doesn't actually run
                // New variable for finding the index of new lines
                StringBuffer stringBuffer = new StringBuffer(markdown.substring(closeParen + 1, markdown.length()));
                currentIndex = stringBuffer.indexOf("\n") + 1;
                System.out.println("New line at " + stringBuffer.indexOf("\n"));
                toReturn.add(markdown.substring(openParen + 1, closeParen));
                currentIndex = closeParen+1;
            }
            else{
                System.out.println("Markdown length" + markdown.length());
                while(closeParen < markdown.length()-1 && closeParen != -1){
                    closeParenUsed = closeParen;
                    closeParen = markdown.indexOf(")", closeParen+1);
                    System.out.println(closeParen);
                }
                if (closeParen == closeParenUsed){
                    toReturn.add(markdown.substring(openParen + 1, closeParen));
                }
                else{
                    toReturn.add(markdown.substring(openParen + 1, closeParenUsed+1));
                }
                currentIndex=markdown.length();
            }
        }
        System.out.println(toReturn.toString());
        return toReturn;
    }

    boolean containsLine(String str){
    String newline = System.getProperty("line.separator");
    return str.contains(newline);
    }
static boolean containsNewLine(String str) {
    Pattern regex = Pattern.compile("^(.*)$", Pattern.MULTILINE);
        return regex.split(str).length > 0;
}
    @Test
    public void testGetLinks() throws IOException{
        Path fileName = Path.of("test-file.md");
	    String contents = Files.readString(fileName);
        ArrayList<String> linkOne = getLinks(contents);
        assertEquals("[https://something.com, some-page.html]", linkOne.toString());
    }

    @Test
    public void testGetLinksFe() throws IOException{
        Path fileName = Path.of("testFile3.md");
	    String contents = Files.readString(fileName);
        ArrayList<String> linkOne = getLinks(contents);
        assertEquals("[https::look parentheses()]", linkOne.toString());
    }

    @Test
    public void testGetLinksTwo() throws IOException{
        Path fileName = Path.of("testFile2.md");
	    String contents = Files.readString(fileName);
        ArrayList<String> linkOne = getLinks(contents);
        assertEquals("[]", linkOne.toString());
    }

    @Test
    public void testGetLinksFour() throws IOException{
        Path fileName = Path.of("testFile4.md");
	    String contents = Files.readString(fileName);
        ArrayList<String> linkOne = getLinks(contents);
        assertEquals("[www.youtube.com, www.google.com, www.amazon.com()()()()()()()()()()()()]", linkOne.toString());
    }
}