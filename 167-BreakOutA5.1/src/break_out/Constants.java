package break_out;

import java.awt.Color;

/**
 * A class that contains all constant values to configure the game
 * 
 * @author dmlux, modified by I. Schumacher
 * modified by Maren Mainusch and Martin Stuwe
 */
public class Constants {

    /**
     * The screen width in pixels
     */
    public static final Double SCREEN_WIDTH = 800.0;
    
    /**
     * The screen height in pixels
     */
    public static final Double SCREEN_HEIGHT = 625.0;
    
    /**
     * the application name
     */
    public static final String APP_TITLE = "iBreakOut";

    /**
     * Debugging flag for special rendering hints
     */
    public static final boolean DEBUG_MODE = false;

    /**
     * The background color for the game menu
     */
    public static final Color BACKGROUND = new Color(0, 0, 255);

    /**
     * Amount of columns for blocks
     */
    public static final Integer SQUARES_X = 20;

    /**
     * Amount of the rows
     */
    public static final Integer SQUARES_Y = 25;
    
    /**
     * The paddle width in pixels
     */
    public static final Double PADDLE_WIDTH = 70.0;
    
    /**
     * The paddle height in pixels
     */
    public static final Double PADDLE_HEIGHT = 15.0;
    
    /**
     * The distance between paddle and the lower reflection offset.
     */
    public static final Double REFLECTION_OFFSET = 10.0;
    
    /**
     * The ball diameter in pixels
     */
    public static final Double BALL_DIAMETER = 15.0;
       
    /**
     * The paddle speed
     */
    public static final Double DX_MOVEMENT = 4.5;
    
    /**
     * The ball speed
     */
    public static final Double BALL_SPEED = 1.0;
    
}
