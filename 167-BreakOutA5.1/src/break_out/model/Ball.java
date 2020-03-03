package break_out.model;

import break_out.Constants;
import break_out.model.Position;
import java.awt.Rectangle;
/**
 * This class contains the information about the balls characteristics and behavior
 * 
 * @author iSchumacher, modified by Maren Mainusch and Martin Stuwe
 * 
 */
public class Ball {

	/**
	 * The balls position on the playground
	 */
	private Position position;
	
	/**
	 * The balls direction
	 */
	private Vector2D direction;
	
	/**
	 * The balls center
	 */
	private Position center;
	
	/**
	 * The hit stones MatrixPosition
	 */
	private MatrixPosition hitStonePosition;
		
	/**
	 * The constructor of a ball
	 * The balls position and direction are initialized here.
	 */
	public Ball() {
		//this.position = new Position(392, 610);
		position = new Position((Constants.SCREEN_WIDTH - Constants.BALL_DIAMETER)/2, Constants.SCREEN_HEIGHT - Constants.BALL_DIAMETER - Constants.PADDLE_HEIGHT);
		direction = new Vector2D(1,-1);
		direction.rescale();
	}
	
	/**
	 * The getter for the balls position
	 * @return position The balls current position
	 */
	public Position getPosition() {
		return position;
	}
	
	/**
	 * The getter for the balls center
	 * @return position The centers current position
	 */	
	public Position getCenter() {
		center = new Position((this.position.getX()+ Constants.BALL_DIAMETER/2), (this.position.getY()+Constants.BALL_DIAMETER/2));
		return center;
	}

	/**
	 * The getter for the balls direction
	 * @return direction The balls current direction
	 */
	public Vector2D getDirection() {
		return direction;
	}
	
	/**
	 * The getter for a hit stones MatrixPosition
	 * @return hitStonePosition The stones MatrixPosition
	 */
	public MatrixPosition getHitsStonePosition() {
		return hitStonePosition;
	}
	
	/**
	 *  Method for updating the balls position
	 */
	public void updatePosition() { 
		position.setX(position.getX()+direction.getDx());
		position.setY(position.getY()+direction.getDy());
	}
	
	/**
	 *  Method for the balls reaction at all 4 borders
	 */
	public void reactOnBorder() {
		
		/*
		 * Right Border
		 */
		if (position.getX() >= (Constants.SCREEN_WIDTH- Constants.BALL_DIAMETER)) {
			position.setX(Constants.SCREEN_WIDTH- Constants.BALL_DIAMETER);
			direction.setDx(direction.getDx()*-1);
		}
		
		/*
		 * Left Border
		 */
		if (position.getX() <= 0 ) {
			position.setX(0);
			direction.setDx(direction.getDx()*-1);
		}
		
		/*
		 *  Upper border
		 */
		if (position.getY() <= 0) {
			position.setY(0);
			direction.setDy(direction.getDy() * -1); 
		}
		
		/*
		 *  Bottom border
		 
		if (position.getY() >=  (Constants.SCREEN_HEIGHT - Constants.BALL_DIAMETER)) {
			position.setY(Constants.SCREEN_HEIGHT - Constants.BALL_DIAMETER);
			direction.setDy(direction.getDy() * -1); 
		}	*/
	}
	
	/**
	 *  Method to check balls collision with the paddles top
	 * @param p The current paddle
	 * @return true for collision	false for no collision
	 */
	public boolean hitsPaddle(Paddle p) {
		
		if (((int) position.getY() + Constants.BALL_DIAMETER.intValue() >= (int) p.getPosition().getY()) 
		&& (position.getX() + Constants.BALL_DIAMETER >= p.getPosition().getX())  
		&& (position.getX()  <= p.getPosition().getX() + Constants.PADDLE_WIDTH)) {
			return true; 
		}
		else {
			return false; 
		}
	}
		
	/**
	 * Method for the balls reflection on the paddle
	 * @param p The current paddle
	 */
	public void reflectOnPaddle(Paddle p) {
		Position offset= new Position(0,0);
		offset.setX(p.getPosition().getX()+Constants.PADDLE_WIDTH/2);
		offset.setY(p.getPosition().getY()+Constants.REFLECTION_OFFSET);
		direction = new Vector2D(offset, this.getCenter());
		direction.rescale();
	}
	 
	/**
	 *  Method to check if the ball hits a stone
	 *	calls reflectOnStone() on every hit
	 *  saves the hit stones position as MatrixPosition in hitStonePosition
	 *  @return true if a stone got hit		false if there is no stone hit
	 *  @param stones The stones Array
	 */
	 public boolean hitsStone(Stone[][] stones) {
		 
		 Rectangle ballRect = new Rectangle((int)position.getX(),(int) position.getY(), Constants.BALL_DIAMETER.intValue(), Constants.BALL_DIAMETER.intValue());
		// Rectangle[][] stoneRect = new Rectangle [Constants.SQUARES_Y][Constants.SQUARES_X];
		boolean hit= false;
		 for (int i = 0; i < Constants.SQUARES_Y; i++) {
				for (int j = 0;j < Constants.SQUARES_X; j++) {
					if (stones[i][j].getType()>0) {
						Rectangle stoneRect = new Rectangle((int) stones[i][j].getPos().getX()+2,(int) stones[i][j].getPos().getY()+2,(Constants.SCREEN_WIDTH.intValue()/Constants.SQUARES_X.intValue())-4,
						(Constants.SCREEN_HEIGHT.intValue()/Constants.SQUARES_Y.intValue())-4); 
						if (ballRect.intersects(stoneRect)) {
							reflectOnStone(ballRect, stoneRect);
							hitStonePosition = new MatrixPosition(i,j);
							return true;
						}
					}
	
			}
		}
		 return hit;
	}
	
	 
	 
	 /**
	  *  Method for the balls reflection on the stones
	  *  @param ballRect Rectangle that edges the ball
	  *  @param stoneRect Rectangle that edges the stones
	  */
	 private void reflectOnStone(Rectangle ballRect, Rectangle stoneRect) {
	 	if (ballRect.intersection(stoneRect).getWidth() > ballRect.intersection(stoneRect).getHeight()) {
	 		direction.setDy(direction.getDy()*-1);
		}
	 	else {
			direction.setDx(direction.getDx()*-1);
		}		 
	 }
	 
	 /**
	  *  Method to check if the ball is lost
	  * @return true ball is lost	false ball is not lost
	  */
	 public boolean ballLost() {
		 if ((position.getY() >=  (Constants.SCREEN_HEIGHT - Constants.BALL_DIAMETER))) {
			 return true;
		 }
		 else {
			 return false;
		 }
	 }
	 
}