package Config;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class MapTypes {
    private static Map<String, List<String>> territoryNeighbors = new HashMap<>();

    public static void addNeighbors(String territory, List<String> neighbors) {
        territoryNeighbors.put(territory, neighbors);
    }

    public static List<String> getNeighbors(String territory) {
        return territoryNeighbors.getOrDefault(territory, new ArrayList<>());
    }


    public static void initializeMap() {
        addNeighbors("Red territory 1", List.of("Red territory 2", "Red territory 4", "Blue territory 2"));
        addNeighbors("Red territory 2", List.of("Red territory 1", "Red territory 3", "Red territory 4", "Red territory 5", "Red territory 6"));
        addNeighbors("Red territory 3", List.of("Red territory 2", "Red territory 5", "Blue territory 1"));
        addNeighbors("Red territory 4", List.of("Red territory 1", "Red territory 2", "Red territory 6"));
        addNeighbors("Red territory 5", List.of("Red territory 2", "Red territory 3",  "Red territory 6"));
        addNeighbors("Red territory 6", List.of("Red territory 4", "Red territory 2", "Red territory 5", "Yellow territory 1", "Yellow territory 2"));

        // Define neighbors for Blue territories
        addNeighbors("Blue territory 1", List.of("Blue territory 2", "Blue territory 3", "Blue territory 3", "Red territory 3"));
        addNeighbors("Blue territory 2", List.of("Blue territory 1", "Blue territory 2", "Blue territory 4"));
        addNeighbors("Blue territory 3", List.of("Blue territory 1", "Blue territory 2", "Blue territory 4", "Blue territory 5", "Blue territory 6"));
        addNeighbors("Blue territory 4", List.of("Blue territory 1","Blue territory 2", "Blue territory 3", "Blue territory 5", "Blue territory 6"));
        addNeighbors("Blue territory 5", List.of("Blue territory 3", "Blue territory 4", "Blue territory 6", "Green territory 1", "Green territory 2"));
        addNeighbors("Blue territory 6", List.of("Blue territory 3", "Blue territory 4", "Blue territory 5", "Green territory 2"));

        // Define neighbors for Yellow territories
        addNeighbors("Yellow territory 1", List.of("Yellow territory 2", "Yellow territory 3", "Yellow territory 4", "Red territory 6"));
        addNeighbors("Yellow territory 2", List.of("Yellow territory 1", "Yellow territory 3", "Yellow territory 4", "Red territory 6", "Green territory 1"));
        addNeighbors("Yellow territory 3", List.of("Yellow territory 1", "Yellow territory 2", "Yellow territory 4", "Yellow territory 5"));
        addNeighbors("Yellow territory 4", List.of("Yellow territory 1", "Yellow territory 2", "Yellow territory 3", "Yellow territory 5"));
        addNeighbors("Yellow territory 5", List.of("Yellow territory 3", "Yellow territory 4", "Yellow territory 6", "Green territory 4"));
        addNeighbors("Yellow territory 6", List.of("Yellow territory 5"));

        // Define neighbors for Green territories
        addNeighbors("Green territory 1", List.of("Green territory 2", "Green territory 3", "Blue territory 5"));
        addNeighbors("Green territory 2", List.of("Green territory 1", "Green territory 3", "Blue territory 5", "Blue territory 6"));
        addNeighbors("Green territory 3", List.of("Green territory 1", "Green territory 2", "Green territory 4", "Green territory 5"));
        addNeighbors("Green territory 4", List.of("Green territory 3", "Green territory 5", "Green territory 6", "Green territory 6", "Yellow territory 4"));
        addNeighbors("Green territory 5", List.of("Green territory 3", "Green territory 4", "Green territory 6"));
        addNeighbors("Green territory 6", List.of("Green territory 4", "Green territory 5"));
    }
}