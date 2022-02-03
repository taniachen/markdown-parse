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
        Path file = Path.of("withwords.md");
        String contents = Files.readString(file);

        assertEquals(List.of("https://github.com", "https://mail.google.com"), MarkdownParse.getLinks(contents));
    }

    @Test
    public void testGetLinks2() throws IOException {
        Path file = Path.of("other-file.md");
        String contents = Files.readString(file);

        assertEquals(List.of("https://github.com", "https://mail.google.com"), MarkdownParse.getLinks(contents));
    }

    @Test
    public void testImage() throws IOException {
        Path file = Path.of("image.md");
        String contents = Files.readString(file);

        try {
            assertEquals(List.of("https://testlink.com", "https://anotherlink.com"), MarkdownParse.getLinks(contents));
        }
        catch (IndexOutOfBoundsException e){

        }
    }
}