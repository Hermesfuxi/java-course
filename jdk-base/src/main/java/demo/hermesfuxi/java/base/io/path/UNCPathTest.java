package demo.hermesfuxi.java.base.io.path;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class UNCPathTest {
    public static void main(String[] args) throws Exception {
        UNCPathTest test = new UNCPathTest();

        test.testURL("file://server/dir/file.txt");  // Windows UNC Path

        test.testURL("file:///Z:/dir/file.txt");     // Windows drive letter path

        test.testURL("file:///dir/file.txt");        // Unix (absolute) path
    }

    private void testURL(String urlString) throws MalformedURLException, URISyntaxException {
        URL url = new URL(urlString);
        System.out.println("URL is: " + url.toString());

        URI uri = url.toURI();
        System.out.println("URI is: " + uri.toString());

        if(uri.getAuthority() != null && uri.getAuthority().length() > 0) {
            // Hack for UNC Path
            uri = (new URL("file://" + urlString.substring("file:".length()))).toURI();
        }

        File file = new File(uri);
        System.out.println("File is: " + file.toString());

        String parent = file.getParent();
        System.out.println("Parent is: " + parent);

        System.out.println("____________________________________________________________");
    }
}
