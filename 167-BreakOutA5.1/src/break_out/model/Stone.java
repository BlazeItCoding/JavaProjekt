package break_out.model;


import java.awt.Color;

/**
 * This class contains the information about the stones characteristics
 * 
 * @author Maren Mainusch and Martin Stuwe
 * 
 */
public class Stone {
	
	/**
	 * The stones position on the playground
	 */
	private Position pos;
	
	/**
	 * The stones type
	 */
	private int type;
	
	/**
	 * The stones value
	 */
	private int value;
		
	/**
	 * The stones color
	 */
	private Color color;
	
	
	/**
	 * The constructor of the stones
	 * The stones position, value, type and color are initialized here.
	 * 
	 * @param type the stones type
	 * @param pos the stones position
	*/
	public Stone(int type,Position pos) {
		 
		this.type = type;
		this.pos= pos;
		
		switch(type){ 
		
			case 1:
		 		value =  type * 100;
		 		color = new Color(0, 0, 255);
		 		break;
		 	case 2:
		 		value =  type * 200; 
		 		color = new Color(0,100,0);
		 		break;
		 	case 3:	
		 		value =  type * 300;
		 		color = new Color(238,173,14);
		 		break;
		 }	
	}
	
	/**
	 * The getter for the stones position
	 * @return pos The stones position
	 */
	public Position getPos() {
		return pos;
	}
	
	/**
	 * The setter for the stones position
	 * @param pos The stones position
	 */
	public void setPos(Position pos) {
		this.pos = pos;
	}
	
	/**
	 * The getter for the stones value
	 * @return value The stones value
	 */
	public int getValue() {
		return this.value;
	}
	
	/**
	 * The setter for the stones value
	 * @param value The stones value
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	/**
	 * The getter for the stones type
	 * @return type The stones type
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * The setter for the stones type
	 * @param type The stones type
	 */
	public void setType(int type) {
		this.type = type;
	}
	
	/**
	 * The getter for the stones color
	 * @return color The stones color
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * The setter for the stones color
	 * @param color The paddles stones color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
}