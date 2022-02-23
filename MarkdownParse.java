// // File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.util.ArrayList;

// public class MarkdownParse {
//     public static ArrayList<String> getLinks(String markdown) {
//         ArrayList<String> toReturn = new ArrayList<>();
//         // find the next [, then find the ], then find the (, then take up to
//         // the next )
//         int currentIndex = 0;
//         while(currentIndex < markdown.length()) {
//             int nextOpenBracket = markdown.indexOf("[", currentIndex);
//             int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
//             int openParen = markdown.indexOf("(", nextCloseBracket);
//             int closeParen = markdown.indexOf(")", openParen);

//             //if missing paren/bracket
//             if (nextOpenBracket == -1 || nextCloseBracket == -1 || openParen == -1 || closeParen == -1) {
//                 break;
//             }
//             else if (openParen - 1 != nextCloseBracket) {
//                 break;
//             }

//             try {
//                 if (markdown.substring(nextOpenBracket - 1, nextOpenBracket).equals("!")) {
//                     currentIndex ++;
//                     continue;
//                 }
//             }
//             catch (IndexOutOfBoundsException e) {
//                 //do nothing
//                 //test git choice
//             }

//             //does not output words
//             if (nextOpenBracket == 0) {
//                 toReturn.add(markdown.substring(openParen + 1, closeParen));
//                 currentIndex = closeParen + 1;
//             }
//             else if (!markdown.substring(nextOpenBracket - 1, nextCloseBracket).equals("!")) {
//                 toReturn.add(markdown.substring(openParen + 1, closeParen));
//                 currentIndex = closeParen + 1;
//             }

//             // toReturn.add(markdown.substring(openParen + 1, closeParen));
//             currentIndex = closeParen + 1;
//         }
//         return toReturn;
//     }
//     public static void main(String[] args) throws IOException {
// 		Path fileName = Path.of(args[0]);
// 	    String contents = Files.readString(fileName);
//         ArrayList<String> links = getLinks(contents);
//         System.out.println(links);
//         // System.out.println("test see");
//     }
// }

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
            if(nextOpenBracket == -1) {
                break;  
            }
            if(nextOpenBracket > 0 && markdown.charAt(nextOpenBracket - 1) == '!') {
                break;
            }
            int nextCloseBracket = markdown.indexOf("](", nextOpenBracket);
            if(nextCloseBracket == -1) {
                break;
            }
            int closeParen = markdown.indexOf(")", nextCloseBracket);
            toReturn.add(markdown.substring(nextCloseBracket + 2, closeParen));
            currentIndex = closeParen + 1;
            // System.out.format("%d\t%d\t%d\t%d\n", currentIndex, nextOpenBracket, nextCloseBracket, closeParen);
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}