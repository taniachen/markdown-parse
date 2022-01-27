import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.*;

public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void testGetLinks() throws IOException {
        Path file = Path.of("test-file.md");
        String contents = Files.readString(file);

        assertEquals(List.of("https://something.com","some-page.html"), MarkdownParse.getLinks(contents));
    }

    @Test
    public void testGetLinks2() throws IOException {
        Path file = Path.of("other-file.md");
        String contents = Files.readString(file);

        assertEquals(List.of("https://github.com", "https://mail.google.com"), MarkdownParse.getLinks(contents));
    }
}
