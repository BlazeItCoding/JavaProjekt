package break_out.model;

import break_out.Constants;
import break_out.model.Position;

/**
 * This class represent a two dimensional vector.
 * 
 * @author I. Schumacher, modified by Maren Mainusch and Martin Stuwe
 */
public class Vector2D {

	/**
	 * The x part of the vector
	 */
	private double dx;

	/**
	 * The y part of the vector
	 */
	private double dy;
	
	/**
	 * The vectors length
	 */
	private double length;
	
	/**
	 * This constructor creates a new vector with the given x and y parts.
	 * 
	 * @param dx The x part of the vector
	 * @param dy The y part of the vector
	 */
	public Vector2D(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	/**
	 * This constructor creates a new vector based on the difference of 2 positions.
	 * 
	 * @param pos1 The first position
	 * @param pos2 The second position
	 */
	public Vector2D(Position pos1, Position pos2) {
		dx = pos2.getX() - pos1.getX();
		dy = pos2.getY() - pos1.getY();
		
	}
	
	/**
	 * Getter for the x part
	 * 
	 * @return dx The x part of the vector
	 */
	public double getDx() {
		return dx;
	}

	/**
	 * Setter for the x part
	 * 
	 * @param dx The new x part of the vector
	 */
	public void setDx(double dx) {
		this.dx = dx;
	}

	/**
	 * Getter for the y part
	 * 
	 * @return dy The y part of the vector
	 */
	public double getDy() {
		return dy;
	}

	/**
	 * Setter for the y part
	 * 
	 * @param dy The new y part of the vector
	 */
	public void setDy(double dy) {
		this.dy = dy;
	}
	
	/**
	 * Method to rescale the length of Vector2D to 1
	 */
	public void rescale() {
		length = Math.abs(Math.sqrt(dx*dx+dy*dy));
		dx= (dx/length)*Constants.BALL_SPEED;
		dy = (dy/length)*Constants.BALL_SPEED;		
	}

}
