import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.List;

public class World {

    String name; //Map Name
    int[][] map; //Map's Integer Array for Tiling
    boolean[][] clip; //Map's Collisions
    int rows; //Rows of Map's Array
    int cols; //Columns of Map's Array
    float xStart = 1.5f;
    float yStart = 1.5f;

    Entity[] mobs;

    /**
     * This Constructor only needs the World Name, and will use the method 'getMapData' to load the world
     * @param name Name of the World
     */
    public World(String name) {
        this.name = name;
        getMapData();


        mobs = new Entity[4];
    }

    public void getMapData() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("WorldFiles/" + name + ".json"));
            JSONObject json = (JSONObject) obj;
            rows = Integer.valueOf((String) json.get("Rows"));
            cols = Integer.valueOf((String) json.get("Cols"));
            JSONArray tiles = (JSONArray) json.get("TileMap");
            map = new int[rows][cols];
            int count = 0;
            for (Object i : tiles) {
                for (Object j : (List) i) {
                    map[count / cols][count++ % cols] = Integer.valueOf((String) j);
                }
            }
            JSONArray collision = (JSONArray) json.get("ClipMap");
            clip = new boolean[rows][cols];
            count = 0;
            for (Object i : collision) {
                for (Object j : (List) i) {
                    clip[count / cols][count++ % cols] = (j.equals("1"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Init() {
        for(int n = 0; n < 4; n ++) {
            mobs[n] = new Entity(Integer.toString(n), "1");
            mobs[n].location.setX(4*n + 4);
            mobs[n].location.setY(4);
            mobs[n].loadTexture("Data/Char01.png");
        }
    }
}
