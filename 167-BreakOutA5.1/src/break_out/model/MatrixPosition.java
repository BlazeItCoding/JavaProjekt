package break_out.model;

/**
 * This class contains the information about the matrix position
 * 
 * @author Maren Mainusch and Martin Stuwe
 * 
 */
public class MatrixPosition {
	
	/**
	 * The matrix line
	 */
	private int line;
	
	/**
	 * The matrix column
	 */
	private int column;

	/**
	 * The constructor of the matrix position
	 * The position line and column.
	 */
	public MatrixPosition(int line, int column) {
		this.line = line;
		this.column = column; 
	}
	
	/**
	 * The setter for the matrix positions line
	 * @param line The matrix positions line
	 */	
	public void setLine(int line) {
		this.line = line;
	}
	
	/**
	 * The getter for the matrix position line
	 * @return line The matrix positions line
	 */	
	public int getLine() {
		return line;
	}
	
	/**
	 * The setter for the matrix positions column
	 * @param column The matrix positions column
	 */	
	public void setColumn(int column) {
		this.column = column;
	}
	
	/**
	 * The getter for the matrix position column
	 * @return line The matrix positions column
	 */	
	public int getColumn() {
		return column;
	}
	
}
