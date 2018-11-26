import org.w3c.dom.*;

import javax.print.Doc;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

public class BookController {

    public static void PrintAllBooks(Document document) throws TransformerException
    {

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","2");

        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(System.out);

        transformer.transform(source,result);
    }

    public static void GetBookById(Document document, BufferedReader reader) throws IOException
    {

        NodeList booksElement = document.getElementsByTagNameNS("books","book");
        System.out.println("Please enter the id of the book you'd like to print out");

        int Id = Integer.parseInt(reader.readLine().trim());
        Node BookTobePrinted = FindNode(booksElement,Id);
        if(isNull(BookTobePrinted))
        {
            System.out.println("No such book by id");
            return;
        }
        PrintNode(BookTobePrinted);
    }
    public static void AddBook(Document document,BufferedReader reader) throws IOException
    {
        System.out.println("Please enter the id:");
        int id = Integer.parseInt(reader.readLine().trim());

        System.out.println("Please enter the title:");
        String title = reader.readLine();

        System.out.println("Please enter the description:");
        String description = reader.readLine();

        System.out.println("Please enter the amount available:");
        int amountAvailable = Integer.parseInt(reader.readLine().trim());

        System.out.println("Please enter the price");
        int price = Integer.parseInt(reader.readLine().trim());

        Book book = new Book(id,title, description, amountAvailable,price);

        Element booksElement =  document.getDocumentElement();
        Element bookElement = document.createElement("book");
        bookElement.setAttribute("Id",String.valueOf(book.getId()));

        booksElement.appendChild(bookElement);

        Element  titleElement = document.createElement("title");
        bookElement.appendChild(titleElement);
        Text titleText = document.createTextNode(book.getTitle());
        titleElement.appendChild(titleText);

        Element  descriptionElement = document.createElement("description");
        bookElement.appendChild(descriptionElement);
        Text descriptionText = document.createTextNode(book.getDescription());
        descriptionElement.appendChild(descriptionText);

        Element  amountAvailableElement = document.createElement("amount-available");
        bookElement.appendChild(amountAvailableElement);
        Text amountAvailableText = document.createTextNode(String.valueOf(book.getAmountAvailable()));
        amountAvailableElement.appendChild(amountAvailableText);

        Element  priceElement = document.createElement("price");
        bookElement.appendChild(priceElement);
        Text priceText = document.createTextNode(String.valueOf(book.getPrice()));
        priceElement.appendChild(priceText);



    }

    public static void DeleteBookById(Document document, BufferedReader reader) throws  IOException
    {

        NodeList booksElement = document.getElementsByTagNameNS("books","book");
        System.out.println("Please enter the id of the book which you'd like to remove");
        int Id = Integer.parseInt(reader.readLine().trim());
        Node bookToBeRemoved = FindNode(booksElement,Id);
        if(isNull(bookToBeRemoved))
        {
            System.out.println("No such book by id!");
            return;
        }
        Node parentElement =  bookToBeRemoved.getParentNode();
        parentElement.removeChild(bookToBeRemoved);
    }

    public static void UpdateBook(Document document, BufferedReader reader) throws IOException
    {
        System.out.println("Please enter the id:");
        int Id = Integer.parseInt(reader.readLine().trim());
        NodeList booksElement = document.getElementsByTagNameNS("books","book");
        Node BookTobeUpdated = FindNode(booksElement,Id);
        if(isNull(BookTobeUpdated))
        {
            System.out.println("The book you wanted to update was not found!");
            return;
        }
        System.out.println("Please enter the title:");
        String title = reader.readLine();

        System.out.println("Please enter the description:");
        String description = reader.readLine();

        System.out.println("Please enter the amount available:");
        int amountAvailable = Integer.parseInt(reader.readLine().trim());

        System.out.println("Please enter the price");
        int price = Integer.parseInt(reader.readLine().trim());

        BookTobeUpdated.getChildNodes().item(1).setTextContent(title);
        BookTobeUpdated.getChildNodes().item(3).setTextContent(description);
        BookTobeUpdated.getChildNodes().item(5).setTextContent(String.valueOf(amountAvailable));
        BookTobeUpdated.getChildNodes().item(7).setTextContent(String.valueOf(price));


    }

    public static void SaveChanges(Document document) throws  TransformerException
    {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Result result =  new StreamResult(new File("output.xml"));
        Source input =  new DOMSource(document);
        transformer.transform(input,result);
    }

    private static void PrintNode(Node node)
    {
        System.out.println(node.getTextContent());
    }

    private static Node FindNode(NodeList list, int Id)
    {
        if(list.getLength() > 0)
        {
            for(int i = 0; i < list.getLength(); i++)
            {
                int id = Integer.parseInt(list.item(i).getAttributes().getNamedItem("Id").getTextContent());
                if(id == Id)
                {
                    return list.item(i);
                }
            }
        }
        return null;
    }

}
