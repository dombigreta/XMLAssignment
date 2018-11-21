import org.w3c.dom.*;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.stream.Stream;

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

    public static void GetBookById(int Id, NodeList Books)
    {
        if(Books.getLength() > 0 )
        {
            for(int i = 0;  i< Books.getLength(); i++)
            {
                int id = Integer.parseInt(Books.item(i).getAttributes().getNamedItem("Id").getTextContent());
                if(id == Id)
                {
                    System.out.println(Books.item(i).getTextContent());
                    return;
                }
            }
            System.out.println("There is no such book in the database!");
        }
    }
    public static void addBook(Book book, Document document)
    {
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

    public static int deleteBookById(int Id, NodeList Books)
    {
        if(Books.getLength() > 0)
        {
            for(int i = 0; i < Books.getLength(); i++)
            {

                int id = Integer.parseInt(Books.item(i).getAttributes().getNamedItem("Id").getTextContent());
                if(id == Id)
                {
                    Node BookToBeRemoved = Books.item(i);
                    Node ParentBook = BookToBeRemoved.getParentNode();
                    ParentBook.removeChild(BookToBeRemoved);
                    return id;
                }
            }
        }
        return -1;
    }

    public static void updateBook(Document document, Book book)
    {
        //todo
    }
}
