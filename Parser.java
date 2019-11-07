package parseStack;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Parser {

   public static void main(String[] args) {

      try {
         File inputFile = new File("C:\\Users\\Roberta\\eclipse-workspace\\parseStack\\Posts.xml");
         SAXParserFactory factory = SAXParserFactory.newInstance();
         factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, false);
         SAXParser saxParser = factory.newSAXParser();
         PostsHandler posthandler = new PostsHandler();
         saxParser.parse(inputFile, posthandler);     
      } catch (Exception e) {
         e.printStackTrace();
      }
   }   
}