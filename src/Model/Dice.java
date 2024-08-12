package Model;

public class Dice {
    public static int rollDice () {
        return (int)Math.floor(Math.random() * 6) + 1;
    };
}
