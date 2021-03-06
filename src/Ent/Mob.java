package src.Ent;

import java.util.Scanner;

/**
 * @author Jack Kurfman
 * @version 3/2/2016.
 */
public class Mob extends Entity{

    public int levelCap;
    public Scanner lineScan = new Scanner(this.lineEntry);

    public Mob(String ID, int level, int x, int y, Direction dir) {
        super(ID, x, y, dir, true);
        this.level = level;
        int dummy = lineScan.nextInt();
        name = lineScan.next();
        this.levelCap = lineScan.nextInt();
        int healthStart = lineScan.nextInt();
        int healthMid = lineScan.nextInt();
        int healthCap = lineScan.nextInt();
        this.health = statFunction(healthStart, healthMid, healthCap);

        calcStats();
    }

    private int statFunction(int start, int mid, int cap) {
        double a;
        double b;
        b = 2 * (double) (mid - start) / (cap - start);
        b = Math.pow(b, 1.0 / (-0.5 * levelCap));
        b = b - 1;
        a = Math.pow(1.0 + b, levelCap);
        a = (cap - start) / (levelCap * a);
        return (int) (a * level * Math.pow(1.0 + b, level)) + start;
    }

    //public void damage(int dmg) {health -= dmg;}
}
