public class Territory {
    private String name;
    private String continent;
    private Player owner;
    private int armyCount;

    public Territory(String name, String continent){
        this.name = name;
        this.continent = continent;
        this.owner = null;
        this.armyCount = 0;
    }
    public String getName(){
        return name;
    }
    public String getContinent(){
        return continent;
    }
    public Player getOwner(){
        return owner;
    }
    public void setOwner(Player owner){
        this.owner = owner;
    }
    public int getArmyCount(){
        return armyCount;
    }
    public void setArmyCount(int armyCount){
        this.armyCount = armyCount;
    }
}
