package parser;

public class TxtOrderParser extends DelimiterOrderParser {

    private static final String DELIMITER = "\\|";

    public TxtOrderParser() {
        super(DELIMITER);
    }
}
