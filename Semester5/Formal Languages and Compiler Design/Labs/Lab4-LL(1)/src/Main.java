import parser.controller.GrammarController;
import parser.controller.ParserController;
import parser.controller.Program;
import parser.ui.UI;

public class Main {

    public static void main(String[] args) {
        GrammarController grammarController = new GrammarController();
        ParserController parserController = new ParserController(grammarController);

        Program program = new Program(grammarController, parserController);

        UI ui = new UI(program);
        ui.start();

    }
}