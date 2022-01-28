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
   
    @Test
    public void testGetLinks() throws IOException{
        Path fileName = Path.of("test-file.md");
	    String contents = Files.readString(fileName);
        ArrayList<String> linkOne = MarkdownParse.getLinks(contents);
        assertEquals("[https://something.com, some-page.html]", linkOne.toString());
    }
}