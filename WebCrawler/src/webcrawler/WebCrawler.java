package webcrawler;

import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.HashMap;
import java.time.LocalDateTime;

public class WebCrawler {
	private static void Crawl(String url, int depth, int startdepth) {
		if(depth == 0) {
			return;
		} else {
			System.out.println("navigated to: " + url);
			Document doc=null;
			try {
				doc = Jsoup.connect(url).get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Current depth: " + (startdepth - depth + 1));
			depth --;
	    	System.out.println("Title:" + doc.title());
		    Elements metaTags = doc.getElementsByTag("meta");
	    	System.out.println("Meta tags:");
		    for (Element tag : metaTags) {
		    	System.out.println("	Name: " + tag.attr("name") + "    Content: " + tag.attr("content"));
		    }
		    HashMap<String, String> visitedURLs = new HashMap<String, String>();
		    visitedURLs.put(url, LocalDateTime.now().toString());
		    Elements anchors = doc.select("a");
		    String newurl = null;
		    for (Element anchor : anchors) {
		    	String testurl = anchor.attr("abs:href").toString();
		    	if(!(visitedURLs.containsKey(testurl)) && testurl.length() <= 50 && testurl.length() != 0) {
		    		newurl = testurl;
		    		visitedURLs.put(testurl, LocalDateTime.now().toString());
				    Crawl(newurl, depth,  startdepth);
		    	}
		    }
		}
	}
    public static void main(String[] args) {
    	String url = args[0];
    	int depth= Integer.parseInt(args[1]);
        Crawl(url, depth, depth);
        System.out.print("Done!");
    }
}