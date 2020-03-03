package break_out.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import break_out.Constants;
import break_out.model.Position;
import break_out.model.Stone;
import net.miginfocom.swing.MigLayout;

/**
 * The field represents the board of the game. All components are on the board
 * 
 * @author dmlux, modified by iSchumacher
 * modified by Maren Mainusch and Martin Stuwe
 */
public class Field extends JPanel {

	/**
	 * Automatic generated serial version UID
	 */
	private static final long serialVersionUID = 2434478741721823327L;

	/**
	 * The connected view object
	 */
	private View view;

	/**
	 * The background color
	 */
	private Color background;

	/**
	 * The constructor needs a view
	 * 
	 * @param view The view of this board
	 */
	public Field(View view) {
		super();

		this.view = view;
		this.background = new Color(0, 0, 0);

		setFocusable(true);

		// Load settings
		initialize();
	}

	/**
	 * Initializes the settings for the board
	 */
	private void initialize() {
		// creates a layout
		setLayout(new MigLayout("", "0[grow, fill]0", "0[grow, fill]0"));
	}

	/**
	 * Change the background color
	 * @param color The new color
	 */
	public void changeBackground(Color color) {
		background = color;
		repaint();
	}
	
	/**
	 * This method is called when painting/repainting the playground
	 * @param g the graphics object
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		double w = Constants.SCREEN_WIDTH;
		double h = Constants.SCREEN_HEIGHT;

		// Setting the dimensions of the playground
		setPreferredSize(new Dimension((int) w, (int) h));
		setMaximumSize(new Dimension((int) w, (int) h));
		setMinimumSize(new Dimension((int) w, (int) h));

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// Setting the background color
		g2.setColor(background);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		// Setting the color for the following components
		g2.setColor(new Color(200, 200, 200));
		
		// Calls the method for drawing the ball
		drawBall(g2);	
		
		// Calls the method for drawing the paddle
		drawPaddle(g2);
		
		// Calls the method for drawing the grid
		drawGrid(g2);
		
		// Calls the method for drawing the stones
		drawStones(g2);
		
		// Setting the color for the following components
		g2.setColor(new Color(255, 0, 0));
		
		// Calls the method for drawing the score
		drawScore(g2);
		
		// Calls the method for drawing the lives
		drawLives(g2);

	}

	/**
	 * Draws the ball
	 * @param g2 The graphics object
	 */
	private void drawBall(Graphics2D g2) {
		g2.fillOval((int) view.getGame().getLevel().getBall().getPosition().getX(),
				(int) view.getGame().getLevel().getBall().getPosition().getY(),
				Constants.BALL_DIAMETER.intValue(),
				Constants.BALL_DIAMETER.intValue());
	}
	
	/**
	 * Draws the paddle
	 * @param g2 The graphics object
	 */
	private void drawPaddle(Graphics2D g2) {
		g2.fillRoundRect((int) view.getGame().getLevel().getPaddle().getPosition().getX(),
				(int) view.getGame().getLevel().getPaddle().getPosition().getY(),
				Constants.PADDLE_WIDTH.intValue(),
				Constants.PADDLE_HEIGHT.intValue(),
				5,
				5);
	}
	
	/**
	 * Draws the grid
	 * @param g2 The graphics object
	 */
	private void drawGrid(Graphics2D g2) {
		int xi = Constants.SCREEN_WIDTH.intValue()/Constants.SQUARES_X.intValue();
		for ( int i = 1 ; i < Constants.SQUARES_X; i ++ ) {
			g2.drawLine( xi*i , 0, xi*i,  Constants.SCREEN_HEIGHT.intValue());
		}
		
		int yi = Constants.SCREEN_HEIGHT.intValue()/Constants.SQUARES_Y.intValue();
		for ( int i = 1 ; i < Constants.SQUARES_Y; i ++ ) {
			g2.drawLine( 0 , i*yi , Constants.SCREEN_WIDTH.intValue(), i*yi);
		}
	}
	
	/**
	 * Draws the stones
	 * @param g2 The graphics object
	 */
	private void drawStones(Graphics2D g2) {
		Stone [][]stones = view.getGame().getLevel().getStones();
		for (int i = 0; i < Constants.SQUARES_Y; i++) {
			for (int j = 0;j < Constants.SQUARES_X; j++) {
			
				if (stones[i][j].getType() > 0) {
					g2.setColor(stones[i][j].getColor());
			
					g2.fillRoundRect((int) stones[i][j].getPos().getX()+2,
					(int) stones[i][j].getPos().getY()+2,
					(Constants.SCREEN_WIDTH.intValue()/Constants.SQUARES_X.intValue())-4,
					(Constants.SCREEN_HEIGHT.intValue()/Constants.SQUARES_Y.intValue())-4,
					5,
					5);
				}
			}
		}
	}
	
	/**
	 * Draws the score
	 * @param g2
	 */
	private void drawScore(Graphics2D g2) {
		g2.setFont(new Font("TimesRoman", 5, 20));
		g2.drawString("Score: "+ view.getGame().getLevel().getScore(), 300, 15);
	}
	
	/**
	 * Draws the lives
	 * @param g2
	 */
	private void drawLives(Graphics2D g2) {
		g2.drawString("Lives: "+view.getGame().getLevel().getLifeCnt(), 5, 15);
	}
	
}