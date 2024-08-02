
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        UI ui = new UI(game);
        ui.show();
        game.start();
    }
}