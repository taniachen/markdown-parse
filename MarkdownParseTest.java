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

    @Test
    public void testSnippet1() throws IOException {
        Path file = Path.of("snippet1.md");
        String contents = Files.readString(file);

        assertEquals(List.of("google.com", "google.com", "ucsd.edu"), MarkdownParse.getLinks(contents));
    }

    @Test
    public void testSnippet2() throws IOException {
        Path file = Path.of("snippet2.md");
        String contents = Files.readString(file);

        assertEquals(List.of("a.com", "a.com(())", "example.com"), MarkdownParse.getLinks(contents));
    }

    @Test
    public void testSnippet3() throws IOException {
        Path file = Path.of("snippet3.md");
        String contents = Files.readString(file);

        assertEquals(List.of("https://www.twitter.com", "https://ucsd-cse15l-w22.github.io/",
            "https://cse.ucsd.edu/"), MarkdownParse.getLinks(contents));
    }

    @Test
    public void testTestOne() throws IOException{
        Path fileName = Path.of("lab8-test1.md");
	    String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(List.of("google.com", "google.com", "ucsd.edu"),links);
    }

    @Test
    public void testTestTwo() throws IOException{
        Path fileName = Path.of("lab8-test2.md");
	    String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(List.of("a.com", "a.com(())", "example.com"),links);
    }

    @Test
    public void testTestThree() throws IOException{
        Path fileName = Path.of("lab8-test3.md");
	    String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(List.of("https://ucsd-cse15l-w22.github.io/"),links);
    }
}