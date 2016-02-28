
public class GameClient implements Runnable {
    public static void main(String[] args){
        GameClient GC = new GameClient();
        GC.run();
    }

    @Override
    public void run() {
        Game game = new Game();
        Thread gameLoop = new Thread(this);
        game.Init();
        Boolean done = false;
        int frames = 0;
        while (!done) {
            done = game.Update();
            game.Render();

            /*
            try {
                gameLoop.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            */
            System.out.printf("\nFrame %d", frames++);

        }
        game.Quit();
    }
}
