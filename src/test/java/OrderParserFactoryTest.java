import parser.OrderParserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderParserFactoryTest {

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
