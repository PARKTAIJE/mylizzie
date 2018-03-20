package wagner.stephanie.lizzie.gui;

import wagner.stephanie.lizzie.Lizzie;
import wagner.stephanie.lizzie.rules.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * The window used to display the game.
 */
public class LizzieFrame extends JFrame {
    private static final int FPS = 10; // frames per second
    private static final String[] commands = {"left arrow = undo",
            "right arrow = redo",
            "space = toggle pondering",
            "right click = undo",
            "mouse wheel scroll = undo/redo",
            "key 'P' = pass",
            "key 'S' = Show/hide move number",
            "key 'O' = Show settings dialog",
            "key 'C' = Clear board"
    };
    private static BoardRenderer boardRenderer = new BoardRenderer();

    private final BufferStrategy bs;

    public boolean showMoveNumber = false;

    /**
     * Creates a window and refreshes the game state at FPS.
     */
    public LizzieFrame() {
        super("Lizzie - Leela Zero Interface");

        // on 1080p windows screens, this is a good width/height
        setSize(657, 687);
        setLocationRelativeTo(null); // start centered
        setExtendedState(Frame.MAXIMIZED_BOTH); // start maximized

        setVisible(true);

        createBufferStrategy(2);
        bs = getBufferStrategy();

        // set fps
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(
                this::repaint,
                0,
                1000 / FPS,
                TimeUnit.MILLISECONDS);

        Input input = new Input();
        this.addMouseListener(input);
        this.addKeyListener(input);
        this.addMouseWheelListener(input);

        // shut down leelaz, then shut down the program when the window is closed
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Lizzie.leelaz.shutdown();
                System.exit(0);
            }
        });
    }

    // instead of the usual mod pattern (0 1 2 0 1 2...), it bounces back and forth: (0 1 2 1 0 1 2 1...)
    private static long bouncingMod(long a, int mod) {
        a = a % (2 * mod);
        if (a >= mod)
            a = 2 * mod - a;
        return a;
    }

    // Toggle show/hide move number
    public void toggleShowMoveNumver() {
        this.showMoveNumber = !this.showMoveNumber;
    }


    /**
     * Draws the game board and interface
     *
     * @param g0 not used
     */
    public void paint(Graphics g0) {
        if (bs == null)
            return;

        // initialize
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int topInset = this.getInsets().top;

        g.setColor(Color.GREEN.darker().darker());
        g.fillRect(0, 0, getWidth(), getHeight());

        int maxSize = (int) (Math.min(getWidth(), getHeight() - topInset) * 0.99);
        maxSize = Math.max(maxSize, Board.BOARD_SIZE + 5); // don't let maxWidth become too small

        int boardX = (getWidth() - maxSize) / 2;
        int boardY = topInset + (getHeight() - topInset - maxSize) / 2;
        boardRenderer.setLocation(boardX, boardY);
        boardRenderer.setBoardWidth(maxSize);
        boardRenderer.draw(g);

        // draw the commands, right of the board.
        Font font = new Font("Sans Serif", Font.PLAIN, (int) (maxSize * 0.02));
        g.setFont(font);
        int commandsX = (int) (boardX + maxSize * 1.01);
        int commandsY = (int) (getHeight() * 0.2);

        Color boxColor = new Color(200, 255, 200);
        g.setColor(boxColor);
        g.fillRect(commandsX, commandsY, (int) (maxSize * 0.35), (int) (commands.length * font.getSize() * 1.1));
        g.setColor(boxColor.darker());
        g.drawRect(commandsX, commandsY, (int) (maxSize * 0.35), (int) (commands.length * font.getSize() * 1.1));

        g.setColor(Color.BLACK);
        for (int i = 0; i < commands.length; i++) {
            g.drawString(commands[i], commandsX, font.getSize() + (int) (commandsY + i * font.getSize() * 1.1));
        }

        // cleanup
        g.dispose();
        bs.show();
    }

    /**
     * Checks whether or not something was clicked and performs the appropriate action
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public void onClicked(int x, int y) {
        // check for board click
        int[] boardCoordinates = boardRenderer.convertScreenToCoordinates(x, y);

        if (boardCoordinates != null) {
            Lizzie.board.place(boardCoordinates[0], boardCoordinates[1]);
        }
    }
}