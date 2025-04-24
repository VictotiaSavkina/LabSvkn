
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.File;
import java.text.DecimalFormat;

public class LibraryProcessing {
    public static void main(String[] args) {
        try {
            // 1. Загрузка XML-файла
            File xmlFile = new File("library.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // 2. Получение списка всех книг
            NodeList bookList = doc.getElementsByTagName("book");
            System.out.println("Список книг в библиотеке:");

            // 3. Вывод информации о книгах и расчет средней цены
            double totalPrice = 0;
            for (int i = 0; i < bookList.getLength(); i++) {
                Element book = (Element) bookList.item(i);
                String id = book.getAttribute("id");
                String title = book.getElementsByTagName("title").item(0).getTextContent();
                String author = book.getElementsByTagName("author").item(0).getTextContent();
                String year = book.getElementsByTagName("year").item(0).getTextContent();
                String genre = book.getElementsByTagName("genre").item(0).getTextContent();
                String price = book.getElementsByTagName("price").item(0).getTextContent();

                totalPrice += Double.parseDouble(price);

                System.out.println("\nID: " + id);
                System.out.println("Название: " + title);
                System.out.println("Автор: " + author);
                System.out.println("Год: " + year);
                System.out.println("Жанр: " + genre);
                System.out.println("Цена: $" + price);
            }

            // 4. Вычисление средней цены
            DecimalFormat df = new DecimalFormat("#.##");
            double averagePrice = totalPrice / bookList.getLength();
            System.out.println("\nСредняя цена книг: $" + df.format(averagePrice));

            // 5. Фильтрация книг по жанру (Роман)
            System.out.println("\nКниги в жанре 'Роман':");
            XPath xPath = XPathFactory.newInstance().newXPath();
            String expression = "/library/book[genre='Роман']";
            NodeList novels = (NodeList) xPath.compile(expression)
                    .evaluate(doc, javax.xml.xpath.XPathConstants.NODESET);

            for (int i = 0; i < novels.getLength(); i++) {
                Element novel = (Element) novels.item(i);
                System.out.println(novel.getElementsByTagName("title").item(0).getTextContent());
            }

            // 6. Фильтрация книг по году (после 1900)
            System.out.println("\nКниги, изданные после 1900 года:");
            expression = "/library/book[year > 1900]";
            NodeList modernBooks = (NodeList) xPath.compile(expression)
                    .evaluate(doc, javax.xml.xpath.XPathConstants.NODESET);

            for (int i = 0; i < modernBooks.getLength(); i++) {
                Element book = (Element) modernBooks.item(i);
                System.out.println(book.getElementsByTagName("title").item(0).getTextContent()
                        + " (" + book.getElementsByTagName("year").item(0).getTextContent() + ")");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}