import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class DomParser {


    private static String FILE_INPUT_PATH = "./src/book.xml";
    private static BufferedReader reader;

    public static void main(String[] args){
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        try
        {
            reader = new BufferedReader(new InputStreamReader(System.in));
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(FILE_INPUT_PATH));
            PrintMenu();
            int choice = Integer.parseInt(reader.readLine().trim());
            switch (choice)
            {
                case 0 : BookController.PrintAllBooks(document);
                break;
                case 1 : BookController.GetBookById(document, reader);
                break;
                case 2: BookController.AddBook(document,reader);
                break;
                case 3 : BookController.DeleteBookById(document,reader);
                break;
                case 4 : BookController.UpdateBook(document,reader);
            }

            if(PrintingBooksAgainRequired())
            {
                BookController.PrintAllBooks(document);
            }

            if(SavingBooksRequired())
            {
                BookController.SaveChanges(document);
            }

        }

        catch (ParserConfigurationException | SAXException | TransformerException| IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void PrintMenu()
    {
        System.out.println("0 - for listing all the books in the database");
        System.out.println("1 - for quering out one specific book");
        System.out.println("2 - for  adding a new book to the database");
        System.out.println("3 - for deleting a book by id");
        System.out.println("4 - for modifying an existing book in the database by id");
        System.out.println("5 - for saving the changes");

    }

    private static boolean PrintingBooksAgainRequired() throws IOException
    {
        System.out.println("Would you like to print all the books again? [Y/N]");
        String choice = reader.readLine().trim().toUpperCase();

        switch (choice)
        {
            case "Y": return true;
            case "N":return false;
            default:return false;
        }
    }

    private static boolean SavingBooksRequired() throws IOException
    {
        System.out.println("Would you like to save the books? [Y/N]");
        String choice = reader.readLine().trim().toUpperCase();

        switch (choice)
        {
            case "Y": return true;
            case "N":return false;
            default:return false;
        }
    }
}
