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
            if(containsNewLine(markdown.substring(openParen + 1, closeParen))){
                // New variable for finding the index of new lines
                StringBuffer stringBuffer = new StringBuffer(markdown.substring(openParen + 1, closeParen));
                currentIndex = stringBuffer.indexOf("\n") + 1;
                // System.out.println("New line at " + stringBuffer.indexOf("\n"));
            }
            else {
                if (markdown.indexOf(")", closeParen+1) == closeParen+2 ){
                    toReturn.add(markdown.substring(openParen + 1, closeParen+1));
                }
                else{
                    toReturn.add(markdown.substring(openParen + 1, closeParen));
                }
                currentIndex = closeParen + 1;
            }
        }
        return toReturn;
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
}