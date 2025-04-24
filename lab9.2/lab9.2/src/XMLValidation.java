import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

public class XMLValidation {
    public static void main(String[] args) {
        try {
            // 1. Указываем файлы XML и XSD
            File xmlFile = new File("library.xml");
            File xsdFile = new File("library.xsd");

            // 2. Создаем фабрику схем
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            // 3. Компилируем схему
            Schema schema = factory.newSchema(xsdFile);

            // 4. Создаем валидатор
            Validator validator = schema.newValidator();

            // 5. Валидируем XML файл
            validator.validate(new StreamSource(xmlFile));
            System.out.println("XML файл валиден по XSD схеме.");

        } catch (Exception e) {
            System.out.println("Ошибка валидации: " + e.getMessage());
            e.printStackTrace();
        }
    }
}