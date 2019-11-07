package parseStack;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class PostsHandler extends DefaultHandler {

	boolean bPosts = false;
	FileWriter arquivo;


	@Override
	public void startElement(
			String uri, String localName, String qName, Attributes attributes)
					throws SAXException {
		
		
		if (arquivo == null) {
			
			try {
				arquivo = new FileWriter(new File("Arquivo.txt"));
				arquivo.write("id\ttags\tcreationDate\tscore\tviewCount\n");
			} catch (IOException e) {
				System.out.println("erro ao criar arquivo");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		if (qName.equalsIgnoreCase("posts")) {
			bPosts = true;
		} else if (bPosts && qName.equalsIgnoreCase("row")) {
			String tags = attributes.getValue("Tags");
			
			
			
			if (tags != null && (tags.indexOf("apache-spark") > -1 )) {
				String id = attributes.getValue("Id");
				String creationDate  = attributes.getValue("CreationDate");
				String score =  attributes.getValue("Score");
				String viewCount  = attributes.getValue("ViewCount");
				System.out.println("id["+ id +"]\ttags["+ tags +"]\tcreation["+ creationDate +"]\tscore["+ score +"]\tcount["+ viewCount +"]");
				
				try {
					
					arquivo.write(id +"\t"+ tags +"\t"+ creationDate +"\t"+ score +"\t"+ viewCount +"\n");
					arquivo.flush();
				} catch (IOException e) {
					System.out.println("erro ao escrever no arquivo");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			//	count++;
			}
			
		} else if (!bPosts) {
			bPosts = false;
		}

		
	//	if (count== 5) {
	//		System.exit(0);
	//	}

	}

	@Override
	public void endElement(String uri, 
			String localName, String qName) throws SAXException {

		
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {

	}
	
	@Override
	public void endDocument() {
		if (arquivo != null) {
			try {
				arquivo.close();
			} catch (IOException e) {
				System.out.println("erro ao fechar arquivo");
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		} 
	}
}