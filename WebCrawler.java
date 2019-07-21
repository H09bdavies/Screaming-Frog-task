import java.net.*;
import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class WebCrawler {
  public static void main(String[] args) {
    URL url;
    InputStream is = null;
    BufferedReader dis;
    String line;

    try {
        url = new URL("https://stackoverflow.com/");
        is = url.openStream();  // throws an IOException
        dis = new BufferedReader(new InputStreamReader(is));

        while ((line = dis.readLine()) != null) {
            System.out.println(line);
        }
    } catch (MalformedURLException mue) {
         mue.printStackTrace();
    } catch (IOException ioe) {
         ioe.printStackTrace();
    } finally {
        try {
            is.close();
        } catch (IOException ioe) {
            // nothing to see here
        }
    }

    Document doc = Jsoup.connect("http://en.wikipedia.org/").get();
    log(doc.title());
    Elements newsHeadlines = doc.select("#mp-itn b a");
    for (Element headline : newsHeadlines) {
      log("%s\n\t%s", 
        headline.attr("title"), headline.absUrl("href"));
    }
  }
}