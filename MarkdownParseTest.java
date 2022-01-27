import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void testGetLinks() throws IOException {
        ArrayList<String> linkList = new ArrayList<String>();
        Path file1 = Path.of("test-file.md");
        String contents = Files.readString(file1);
        linkList.add("https://something.com");
        linkList.add("some-page.html");

        assertEquals(List.of("https://something.com","some-page.html"), MarkdownParse.getLinks(contents));
    }
}