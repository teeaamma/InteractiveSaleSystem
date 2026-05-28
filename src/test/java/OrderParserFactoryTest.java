import Parser.NoExtensionOrderParser;
import Parser.OrderParser;
import Parser.OrderParserFactory;
import Parser.TxtOrderParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderParserFactoryTest {

    @Test
    void getParser_txtParser(){
        String fileName = "test.txt";

        OrderParser orderParser = OrderParserFactory.getParser(fileName);

        assertInstanceOf(TxtOrderParser.class, orderParser);
    }

    @Test
    void getParser_noExtensionParser(){
        String fileName = "test";

        OrderParser orderParser = OrderParserFactory.getParser(fileName);

        assertInstanceOf(NoExtensionOrderParser.class, orderParser);
    }

    @Test
    void getParser_unknownExtension(){
        String fileName = "test.csv";

        assertThrows(RuntimeException.class, () -> OrderParserFactory.getParser(fileName));
    }

    @Test
    void getParser_null(){
        String fileName = null;

        assertThrows(RuntimeException.class, () -> OrderParserFactory.getParser(fileName));
    }
}
