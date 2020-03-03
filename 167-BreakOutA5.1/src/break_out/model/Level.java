package break_out.model;

import break_out.Constants;
import break_out.controller.JSONReader;


/**
 * This class contains information about the running game
 * 
 * @author dmlux
 * @author I. Schumacher, modified by Maren Mainusch and Martin Stuwe
 */
public class Level extends Thread {

    /**
     * The game to which the level belongs 
     */
    private Game game;
	 
    /**
   	 * The number of the level
   	 */
    private int levelnr;
       
    /**
	 * The score of the level
	 */
    private int score;
    
    /**
     * The ball of the level
     */
    private Ball ball;
    
    /**
     * Flag that shows if the ball was started
     */
    private boolean ballWasStarted = false;
   
    /**
     * The paddle of the level
     */
    private Paddle paddle;
    
    /**
     * Flag that shows if the level is finished.
     */
    private boolean finished = false;
    
    /**
     * The stones array
     */
    private Stone[][] stones = new Stone[Constants.SQUARES_Y][Constants.SQUARES_X];
    
    /**
     * The amount of lives that are remaining.
     */
    private int lifeCnt;
    
    /**
     * The constructor creates a new level object and needs the current game object, 
     * the number of the level to be created and the current score
     * @param game The game object
     * @param levelnr The number of the new level object
     * @param score The score
     */
    public Level(Game game, int levelnr, int score) {
    	this.game = game;
    	this.levelnr = levelnr;
    	this.score = score;
        ball = new Ball();
        paddle = new Paddle();
        
        
        loadLevelData(levelnr);
    }

    /**
     * The getter for the ball object
     * @return ball The ball of the level
     */
    public Ball getBall() {
    	return ball;
    }
    
    /**
     * The getter for the paddle object
     * @return paddle The paddle of the level
     */
    public Paddle getPaddle() {
    	return paddle;
    }
    
    /**
     * The getter for the stones array
     * @return stones array
     */
    public Stone[][] getStones() {
        return stones;
    }
    
    /**
     * The getter for the score
     * @return score The score
     */
    public int getScore() {
    	return score;
    }
    
    /**
     * The getter for the lives
     * @return lifeCnt The lives that are left
     */
    public int getLifeCnt() {
    	return lifeCnt;
    }  
    
    /**
     * Sets ballWasStarted to true, the ball is moving
     */
    public void startBall() {
        ballWasStarted = true;
    }

    /**
     * Sets ballWasStarted to false, the ball is stopped
     */
    public void stopBall() {
        ballWasStarted = false;
    }
    
    /**
     * Returns if the ball is moving or stopped
     * @return ballWasStarted True: the ball is moving; false: the ball is stopped
     */
    public boolean ballWasStarted() {
        return ballWasStarted;
    }
    /**
     * Sets finished to true, the level ends
     */
    public void setFinished() {
    	finished = true;//
    }

    /**
     * The method of the level thread
     */
    public void run() {	
    
    	// endless loop 
    	while (!finished) {
    		    		
            // if ballWasStarted is true, the ball is moving and reacting
	        if (ballWasStarted) {
	     
	        	// Call here the balls method for reacting on the borders of the playground
	           ball.reactOnBorder();
	            		            	
	            // Call here the balls method for updating his position on the playground
	        	ball.updatePosition();
	        	
	        	// Balls reflection on the paddle
	        	if (ball.hitsPaddle(getPaddle())) {
	        		ball.reflectOnPaddle(getPaddle());
	        	}
	        	
	        	// updates the stones and score if a stone has been hit
	            if (ball.hitsStone(stones)) {
	            	updateStonesAndScore();
	            }
	            
	            // Decreases the lives that are left if the ball gets lost
	            if (ball.ballLost()) {
	            	decreaseLives();
	            }
	            
	            // Ends the level if there are no stones left
	            if (allStonesBroken()) {
	            	finished=true;
	            	game.getController().toStartScreen();
	            }
	            
	        	// The paddles method for updating his position on the playground
	        	paddle.updatePosition(); 
	        	
	        	// Tells the observer to repaint the components on the playground
	            game.notifyObservers();
	       }
	       
	        // The thread pauses for a short time 
	        try {
	            Thread.sleep(4);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
    	}   
    }
    
    /**
    * Loads the information for the level from a json-file located in the folder /res of the project
    * @param levelnr The number X for the LevelX.json file
    */
    private void loadLevelData(int levelnr) {
    	JSONReader reader = new JSONReader("res/Level"+levelnr+".json");
    	int[][] stoneTypes = reader.getStones2DArray();
		lifeCnt = reader.getLifeCounter();
		
		for (int i = 0; i < Constants.SQUARES_Y; i++) {
			for (int j = 0; j < Constants.SQUARES_X; j++) {
				stones[i][j] = new Stone(stoneTypes[i][j], new Position(Constants.SCREEN_WIDTH/Constants.SQUARES_X*j, Constants.SCREEN_HEIGHT/Constants.SQUARES_Y*i));
			}
		}
    }
    
    /**
    * Method to update the stones and the score if a stone has been hit.
    */    
    private void updateStonesAndScore() {
    	score = score + stones[ball.getHitsStonePosition().getLine()][ball.getHitsStonePosition().getColumn()].getValue();
    	stones[ball.getHitsStonePosition().getLine()][ball.getHitsStonePosition().getColumn()]= 
    			new Stone(stones[ball.getHitsStonePosition().getLine()]
    					[ball.getHitsStonePosition().getColumn()].getType()-1,stones[ball.getHitsStonePosition().getLine()]
    							[ball.getHitsStonePosition().getColumn()].getPos());
    }
    
    /**
     * Method to check if there are no stones left
     * @return true: All stones are broken		false: There are stones left
     */
    private boolean allStonesBroken() {
    	int brokenCheck= 0;
    	for (int i = 0; i < Constants.SQUARES_Y; i++) {
			for (int j = 0; j < Constants.SQUARES_X; j++) {
				brokenCheck = brokenCheck + stones[i][j].getType();
			}
    	}
		if (brokenCheck == 0) {
        	 return true;
        }
		else {
			return false;
		}
	}
    
    /**
     * Method to decrease the Lives that are left if the ball hits the bottom border
     * Ends the	level and returns to the start screen if there are no lives left
     */    
    private void decreaseLives() {
    	lifeCnt= lifeCnt -1;
    	if (lifeCnt >= 0) {
    		ball.getPosition().setX((Constants.SCREEN_WIDTH - Constants.BALL_DIAMETER)/2); 
    		ball.getPosition().setY((Constants.SCREEN_HEIGHT - Constants.BALL_DIAMETER - Constants.PADDLE_HEIGHT));
    		stopBall();
    		paddle.getPosition().setX((Constants.SCREEN_WIDTH - Constants.PADDLE_WIDTH)/2);
    		paddle.getPosition().setY(Constants.SCREEN_HEIGHT - Constants.PADDLE_HEIGHT);
    	}
    	else {
    		finished=true;
        	game.getController().toStartScreen();
    	}
    }

}
    
    
    
    

    


	
