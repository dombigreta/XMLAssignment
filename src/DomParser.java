import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

public class DomParser {


    private static String FILE_INPUT_PATH = "C:\\Users\\i5\\IdeaProjects\\XMLAssignment\\xml\\book.xml";

    public static void main(String[] args){
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        try
        {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(FILE_INPUT_PATH));
            NodeList Books = document.getElementsByTagNameNS("books","book");
            BookController.PrintAllBooks(document);
            BookController.GetBookById(2345,Books);
            int deletedBookId = BookController.deleteBookById(3456,Books);
            BookController.addBook(new Book(7898,"Nincs neki","Lorem shit",12,100000),document);
            BookController.PrintAllBooks(document);
        }
        catch (ParserConfigurationException | SAXException | TransformerException | IOException e)
        {
            e.printStackTrace();
        }
    }
}
