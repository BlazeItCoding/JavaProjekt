package break_out.model;

import break_out.Constants;

/**
 * This class contains the information about the paddles characteristics and behavior
 * 
 * @author Maren Mainusch and Martin Stuwe
 * 
 */
public class Paddle {

	/**
	 * The paddles position on the playground
	 */
	private Position position;
	
	/**
	 * The paddles direction
	 */
	private int direction;
	
	/**
	 * The constructor of the paddle
	 * The paddles position is initialized here.
	 */
	public Paddle() {
		position = new Position((Constants.SCREEN_WIDTH - Constants.PADDLE_WIDTH)/2, (Constants.SCREEN_HEIGHT - Constants.PADDLE_HEIGHT));
	}
	
	/**
	 * The getter for the paddles position
	 * @return position The paddles current position
	 */
	public Position getPosition() {
		return position;
	}
	
	/**
	 * The getter for the paddles direction
	 * @return direction The paddles current direction
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * The setter for the paddles direction
	 * @param direction The paddles current direction
	 */
	public void setDirection(int direction) {
		this.direction = direction;
		}
	
	/**
	 *  Method for updating the balls position
	 */
	public void updatePosition() {
		position.setX(position.getX()+direction*Constants.DX_MOVEMENT);
		if (position.getX()> Constants.SCREEN_WIDTH - Constants.PADDLE_WIDTH || position.getX() < 0 ) {
			if (position.getX()> Constants.SCREEN_WIDTH - Constants.PADDLE_WIDTH) {
				position.setX(Constants.SCREEN_WIDTH - Constants.PADDLE_WIDTH);
			}
			else {
				position.setX(0);
			}
		}
	}

}

