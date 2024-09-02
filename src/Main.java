import Control.Game;
import Model.Map;
import View.UI;

public class Main {
    public static void main(String[] args) {
        Map map = new Map();
        Game game = new Game(map);
        UI ui = new UI(game, map);
        game.setUi(ui);
        game.newGame();
    }
}