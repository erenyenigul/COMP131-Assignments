/*|Student Name: Eren Yenigul
 *|Student Number: 72290
 *|COMP 130/131 Homework #3				
 *|Fine A Car! - A Crossroads Simulation	
 */

import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GOval;
import acm.graphics.GRect;
import java.awt.Color;
import java.awt.Font;

//Ignore SuppressWarnings tag, it is irrelevant to the course.
@SuppressWarnings("serial")
public class Comp130_HW3_Fall19 extends GraphicsProgram {

	// Do not change these variables.
	/**
	 * Light objects.
	 */
	GOval vLight, hLight;
	// Additional instance variables
	// Your code starts here.

	RandomGenerator rgen = new RandomGenerator();

	private int blue_fine = 0;
	private int red_fine = 0;
	private boolean blue_fined = false;
	private boolean red_fined = false;
	GLabel red_text, blue_text;

	// Your code ends here.

	public void run() {

		// Your code ends here.
		// Initialize the labels.
		red_text = new GLabel("", 0, 0);
		blue_text = new GLabel("", 0, 0);
		red_text.setColor(Color.RED);
		blue_text.setColor(Color.BLUE);
		add(blue_text, 0, blue_text.getHeight());
		add(red_text, 0, 0);

		// Initiate cars and their speeds.
		//Images used are labeled for non-commercial reuse with modification.
		GImage blue_car = new GImage("blue_car.png");
		GImage red_car = new GImage("red_car.png");
		
		blue_car.setSize(CAR_WIDTH, CAR_LENGHT);
		red_car.setSize(CAR_LENGHT,CAR_WIDTH);
		//blue_car.setFilled(true);
		//red_car.setFilled(true);
		add(blue_car, SCREEN_WIDTH / 2 - ROAD_THICKNESS / 2 + CAR_WIDTH / 4, 0);
		add(red_car, SCREEN_WIDTH - CAR_LENGHT, SCREEN_HEIGHT / 2 - ROAD_THICKNESS / 2 + CAR_WIDTH / 4);

		int blue_car_speed = rgen.nextInt(MIN_SPEED, MAX_SPEED);
		int red_car_speed = rgen.nextInt(MIN_SPEED, MAX_SPEED);

		// Initiate light times.

		int lighth_time = rgen.nextInt(MIN_LIGHT_TIME, MAX_LIGHT_TIME);
		int lightv_time = rgen.nextInt(MIN_LIGHT_TIME, MAX_LIGHT_TIME);
		int timeh_counter = 0;
		int timev_counter = 0;

		// Main animation loop.
		while (true) {

			//Pauses each iteration to animate.
			pause(PAUSE_TIME);
			
			//Movements of cars with randomly generated speeds.
			blue_car.move(0, blue_car_speed);
			red_car.move(-red_car_speed, 0);
			
			//When two cars crash, the code exits the loop.
			if (blue_car.getY() + CAR_LENGHT >= red_car.getY() && red_car.getX() <= blue_car.getX() + CAR_WIDTH
					&& red_car.getX() + CAR_LENGHT >= blue_car.getX() + CAR_WIDTH
					&& blue_car.getY() <= red_car.getY() + CAR_WIDTH) {
				break;
			}
			
			//When cars leave the screen, their location is set to their initial position with a random speed.
			if (blue_car.getY() >= SCREEN_HEIGHT) {
				blue_car.move(0, -SCREEN_HEIGHT - CAR_LENGHT);
				blue_car_speed = rgen.nextInt(MIN_SPEED, MAX_SPEED);
				blue_fined = false;
			}
			if (red_car.getX() + CAR_LENGHT <= 0) {
				red_car.move(SCREEN_WIDTH + CAR_LENGHT, 0);
				red_car_speed = rgen.nextInt(MIN_SPEED, MAX_SPEED);
				red_fined = false;
			}

			// fineCheck checks if the car is in intersection, if so fines the car according
			// to defined rules.
			
			fineCheck(blue_car, blue_car_speed);
			fineCheck(red_car, red_car_speed);

			// check if enough time passed for lights to change their current state.
			if (timeh_counter >= lighth_time) {
				changeState(hLight);
				lighth_time = rgen.nextInt(MIN_LIGHT_TIME, MAX_LIGHT_TIME);
				timeh_counter = 0;
			}
			if (timev_counter >= lightv_time) {
				changeState(vLight);
				lightv_time = rgen.nextInt(MIN_LIGHT_TIME, MAX_LIGHT_TIME);
				timev_counter = 0;
			}

			// Counts how much time passed since the last state change of the lights.
			timeh_counter += PAUSE_TIME;
			timev_counter += PAUSE_TIME;

		}
		//This gif is taken from Giphy.com
		GImage exp_effect = new GImage("exp_effect.gif");
		exp_effect.setSize(50,50);
		add(exp_effect,blue_car.getX()/2+red_car.getX()/2,blue_car.getY()/2+blue_car.getHeight()/4+red_car.getY()/2);
		// Post collision final display.
		red_text.setLabel("Total Fine : " + red_fine+" $");
		red_text.setLocation(SCREEN_WIDTH - red_text.getWidth(), red_text.getHeight());
		blue_text.setLabel("Total Fine : " + blue_fine+" $");
		pause(500);
		remove(exp_effect);
		// Your code ends here.

	}

	//Helper Methods
	//Helper Methods start here.
	private void fineCheck(GImage car, int speed) {

		//Checks if the car is in intersection
		if (car.getX() > SCREEN_WIDTH / 2 - ROAD_THICKNESS / 2 && car.getX() < SCREEN_WIDTH / 2 + ROAD_THICKNESS / 2
				&& car.getY() + CAR_LENGHT > SCREEN_HEIGHT / 2 - ROAD_THICKNESS / 2
				&& car.getY() < SCREEN_HEIGHT / 2 + ROAD_THICKNESS / 2) {
			//If car is blue(CHECKS BY THE HEIGHT OF THE CAR. THE CARS HAVE DIFFERENT HEIGHTS) the operation will affect blue car.
			if (car.getHeight() == CAR_LENGHT && !blue_fined) {
				int oldFine = blue_fine;
				if (speed > SPEED_LIMIT && vLight.getFillColor() == Color.RED) {
					blue_fine += SPEED_FINE * (speed - SPEED_LIMIT) + RED_LIGHT_FINE;
					blue_text.setLabel("+" + (blue_fine - oldFine) + "$ Reason: speeding & red light ");
				} else if (vLight.getFillColor() == Color.RED) {
					blue_fine += RED_LIGHT_FINE;
					blue_text.setLabel("+" + (blue_fine - oldFine) + "$ Reason: red light ");
				} else if (speed > SPEED_LIMIT) {
					blue_fine += SPEED_FINE * (speed - SPEED_LIMIT);
					blue_text.setLabel("+" + (blue_fine - oldFine) + "$ Reason: speeding ");
				}
				//boolean that is reset to false every time car starts over from its inital position
				blue_fined = true;
			}
			//If car is red(CHECKS BY HEIGHT OF THE CAR) the operation will affect red car.
			if (car.getHeight() == CAR_WIDTH && !red_fined) {
				int oldFine = red_fine;
				if (speed > SPEED_LIMIT && vLight.getFillColor() == Color.RED) {
					red_fine += SPEED_FINE * (speed - SPEED_LIMIT) + RED_LIGHT_FINE;
					red_text.setLabel("+" + (red_fine - oldFine) + "$ Reason: speeding & red light ");
				} else if (vLight.getFillColor() == Color.RED) {
					red_fine += RED_LIGHT_FINE;
					red_text.setLabel("+" + (red_fine - oldFine) + "$ Reason: red light ");
				} else if (speed > SPEED_LIMIT) {
					red_fine += SPEED_FINE * (speed - SPEED_LIMIT);
					red_text.setLabel("+" + (red_fine - oldFine) + "$ Reason: speeding ");
				}
				//boolean that is reset to false everytime car starts over from its inital position
				red_fined = true;
				
				//Red label's location should be reseted as it may leave out the screen
				red_text.setLocation(SCREEN_WIDTH - red_text.getWidth(), red_text.getHeight());
			}
		}

	}

	//changes state of light according to its current color.
	private void changeState(GOval light) {

		if (light.getFillColor() == Color.RED)
			light.setFillColor(Color.GREEN);
		else
			light.setFillColor(Color.RED);
	}
	
	//Helper Methods end here.
	
	/*
	 * DO NOT change anything below this line!
	 */

	/**
	 * Initialization method. This method is guaranteed to run before run().
	 */
	public void init() {

		// Initialize the screen size.
		this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);

		// Set the background color.
		this.setBackground(new Color(0, 128, 0));

		// Construct the roads.
		constructRoads();

		// Place the lights.
		placeLights();

	}

	/**
	 * This method constructs the lanes and the intersection.
	 */
	public void constructRoads() {

		// Road and crossing objects.
		GRect vRoad, hRoad, crossing;

		// Vertical and horizontal road creation.
		vRoad = createRoad(SCREEN_WIDTH / 2, ROAD_THICKNESS, "v");
		hRoad = createRoad(SCREEN_HEIGHT / 2, ROAD_THICKNESS, "h");
		add(vRoad);
		add(hRoad);

		// Square crossing section.
		crossing = new GRect(SCREEN_WIDTH / 2 - ROAD_THICKNESS / 2, SCREEN_HEIGHT / 2 - ROAD_THICKNESS / 2,
				ROAD_THICKNESS, ROAD_THICKNESS);
		crossing.setColor(new Color(255, 255, 255));
		add(crossing);

		// Lane separator lines.
		for (int i = 0; i < SCREEN_WIDTH / 2 - ROAD_THICKNESS / 2; i += 25) {
			GLine line = new GLine(i, SCREEN_HEIGHT / 2, i + 15, SCREEN_HEIGHT / 2);
			line.setColor(Color.WHITE);
			add(line);
		}
		for (int i = SCREEN_WIDTH; i > SCREEN_WIDTH / 2 + ROAD_THICKNESS / 2; i -= 25) {
			GLine line = new GLine(i, SCREEN_HEIGHT / 2, i - 15, SCREEN_HEIGHT / 2);
			line.setColor(Color.WHITE);
			add(line);
		}
		for (int i = 0; i < SCREEN_HEIGHT / 2 - ROAD_THICKNESS / 2; i += 25) {
			GLine line = new GLine(SCREEN_WIDTH / 2, i, SCREEN_WIDTH / 2, i + 15);
			line.setColor(Color.WHITE);
			add(line);
		}
		for (int i = SCREEN_HEIGHT; i > SCREEN_HEIGHT / 2 + ROAD_THICKNESS / 2; i -= 25) {
			GLine line = new GLine(SCREEN_WIDTH / 2, i, SCREEN_WIDTH / 2, i - 15);
			line.setColor(Color.WHITE);
			add(line);
		}

	}

	/**
	 * This method creates and places the light objects.
	 * 
	 * @see See vLight and hLight for the light objects.
	 */
	public void placeLights() {

		// Create and place the vertical light.
		vLight = new GOval(25, 25);
		vLight.setFilled(true);
		vLight.setFillColor(Color.RED);
		add(vLight, SCREEN_WIDTH / 2 - 75 - 3, SCREEN_HEIGHT / 2 - 100);

		// Create and place the horizontal light.
		hLight = new GOval(25, 25);
		hLight.setFilled(true);
		hLight.setFillColor(Color.RED);
		add(hLight, SCREEN_WIDTH / 2 + 75, SCREEN_HEIGHT / 2 - 75 - 3);

	}

	/**
	 * @param center Center of the road to be built.
	 * @param width  Thickness of the road.
	 * @param dir    Direction of the road. Allowed values: "v", "h"
	 * @throws RuntimeException on invalid orientation.
	 * @return Created road as a GRect.
	 * @see constructRoads()
	 */
	public GRect createRoad(int center, int width, String dir) {

		// Use dir to determine the road orientation.
		if (dir.equals("v")) {

			// Create the road object.
			GRect road = new GRect(center - width / 2, 0, width, SCREEN_HEIGHT);

			// Set border color.
			road.setColor(new Color(0, 0, 0, 0));

			// Set fill color.
			road.setFilled(true);
			road.setFillColor(new Color(50, 50, 50));

			// Return the created road object.
			return road;

		} else if (dir.equals("h")) {

			// Create the road object.
			GRect road = new GRect(0, center - width / 2, SCREEN_WIDTH, width);

			// Set border color.
			road.setColor(new Color(0, 0, 0, 0));

			// Set fill color.
			road.setFilled(true);
			road.setFillColor(new Color(50, 50, 50));

			// Return the created road object.
			return road;

		} else {

			// Ignore throw keyword, it is irrelevant to the course.
			throw new RuntimeException("Invalid argument 'orientation' = '" + dir + "' @ drawRoad.");

		}
	}

	/**
	 * Width of the screen.
	 */
	public static final int SCREEN_WIDTH = 800;

	/**
	 * Height of the screen.
	 */
	public static final int SCREEN_HEIGHT = 600;

	/**
	 * Thickness of the roads constructed on the screen.
	 */
	public static final int ROAD_THICKNESS = 100;

	/**
	 * Width of the cars.
	 */
	public static final int CAR_WIDTH = 30;

	/**
	 * Lenght of the cars.
	 */
	public static final int CAR_LENGHT = 70;

	/**
	 * Color for the blue car.
	 */
	public static final Color CAR_COLOR_BLUE = new Color(0, 0, 192);

	/**
	 * Color for the red car.
	 */
	public static final Color CAR_COLOR_RED = new Color(192, 0, 0);

	/**
	 * Minimum speed allowed for the cars.
	 */
	public static final int MIN_SPEED = 5;

	/**
	 * Maximum speed allowed for the cars.
	 */
	public static final int MAX_SPEED = 30;

	/**
	 * Speed limit for the cars
	 */
	public static final int SPEED_LIMIT = 20;

	/**
	 * Unit fine used to calculate speeding fines.
	 */
	public static final int SPEED_FINE = 10;

	/**
	 * Static fine for passing a red light.
	 */
	public static final int RED_LIGHT_FINE = 100;

	/**
	 * Pause time for the animation.
	 */
	public static final int PAUSE_TIME = 50;

	/**
	 * Minimum time for a traffic light to change.
	 */
	public static final int MIN_LIGHT_TIME = 1000;

	/**
	 * Maximum time for a traffic light to change.
	 */
	public static final int MAX_LIGHT_TIME = 5000;

	/**
	 * Font for use in labels.
	 */
	public static final Font LABEL_FONT = new Font("Courier", Font.PLAIN, 15);

	/**
	 * Margin of fine labels for the Y-axis.
	 */
	public static final int LABEL_Y_MARGIN = 20;

	/**
	 * Margin of fine labels for the X-axis.
	 */
	public static final int LABEL_X_MARGIN = 10;

}
