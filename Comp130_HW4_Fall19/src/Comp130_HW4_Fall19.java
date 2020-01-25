
//Name:Eren Yenigul

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import acm.graphics.GImage;
import acm.graphics.GPoint;
import acm.graphics.GPolygon;
import acm.graphics.GRect;
import acm.graphics.GRectangle;
import acm.program.GraphicsProgram;

public class Comp130_HW4_Fall19 extends GraphicsProgram {


	/**
	 * Initializer. Prints the welcome messages. Do <b>not</b> modify.
	 */
	public void init() {
		setTitle("MakeAMovie");
		println("Welcome to Make a Movie!");
		println("Start by either creating a new project or opening an existing one.");
	}
	
	/**
	 * Entry method for your implementation.
	 */
	public void run() {
		//your code starts here 
		//commands
//		• setback: Sets the background for the canvas
//		• setgrammar: Sets the grammar for the project.
//		• addscene: Adds a scene to the current project.
//		• removescene: Removes a scene from the current project. • listscenes: Lists all the scenes in the project.
//		• play: Play the scenes in order
//		• exit: Exits the program

		GRect as2 = new GRect(10,10);
		as2.setSize(100,100);
		add(as2,10,10);
		GPolygon asd = new GPolygon();
		asd.addVertex(0, 100);
		asd.addPolarEdge(100*Math.sqrt(3), -60);
		asd.addPolarEdge(100*Math.sqrt(3), 180);
		add(asd, 10,10);
		asd.setFilled(true);
		asd.setFillColor(Color.blue);
		//Program runs until exit command. 
		while(!exit) {
			boolean sts = false;
			String command = readLine(CLI_INPUT_STR);
			sts = execute(command);
			if(!sts) println("Unrecognized command: "+command);
		}
		
		println("Exit successful.");
		// your code ends here
	}
	
	//ADDITIONAL HELPER METHODS//
	//your code starts here 
	private boolean execute(String command) {
		//call method with sting comparison
		switch(command) {
		
		case "setback" : 
			setback(); return true;
		case "setgrammar" :
			setgrammar(); return true;
		case "addscene"  : 
			addscene();
			return true;
		case "removescene" : 
			removescene();
			return true;
		case "listscenes":
			listscenes();
			return true;
		case "play" : 
			play();
			return true;
		case "exit"  :  exit = true ; return true;
		default : return false;
		}
		
	}
	private void play() {
		for(int i=0;i<scenes.size();i++) {
		 
		 ArrayList<String> scene = new ArrayList<String>();
		 //get the scene with all parameters
		 Collections.addAll(scene, scenes.get(i).split(" "));
		 String image_source ="";
		 int time = 0;
		 int scale = 0;
		 int coefficent= 0;
         double[]  from =  {0,0};
         double[]  to = {0,0};
         
         //remove unnecessary information from the arraylist.  
		 scene.remove("to");
		 scene.remove("from");
		 if(scene.remove("seconds")) coefficent = 1000;
		 else coefficent = 1;
		 scene.remove("miliseconds");
		 scene.remove("for");
		
	     //read the information according to the grammar.
		 for(int j=0;j<scene.size();j++) {
			 switch(grammar_arr[j]) {
			    case "scale":
			    	scale = Integer.parseInt(scene.get(j));
			    	break;
			    case "image":
			    	image_source = LIBRARY_PATH+scene.get(j)+IMAGE_TYPE;
			    	break;
			    case "from" :
			    	switch(scene.get(j)) {
			    	case "left": from[1] = getHeight()/2;  break;
			    	case "right": from[0] = getWidth(); from[1] = getHeight()/2; break;
			    	case "top": from[0] = getWidth()/2; break;
			    	case "bottom": from[0] = getWidth()/2; from[1] = getHeight(); break;
			    	case "center": from[0]= getWidth()/2; from[1] = getHeight()/2; break;
			    	}
			    	break;
			    	
			    case "to" :
			    	switch(scene.get(j)) {
			    	case "left": to[1] = getHeight()/2;  break;
			    	case "right": to[0] = getWidth(); to[1] = getHeight()/2; break;
			    	case "top": to[1] = getWidth()/2; break;
			    	case "bottom": to[0] = getWidth()/2; to[1] = getHeight(); break;
			    	case "center": to[0]= getWidth()/2; to[1] = getHeight()/2; break;
			    	}
			    	break;
			    case "time":
			    	//if unit is seconds, multiply the value with 1000 to convert it to milliseconds
			    	time = coefficent* Integer.parseInt(scene.get(j));
			    	break;
			 }
		 }
		 
		 GImage image = new GImage(image_source);
		 image.scale(scale);
		 add(image);
		 //to set location to the exact position, offset by the half of width and height
		 from[0] -= image.getWidth()/2;
		 to[0] -= image.getWidth()/2;
		 from[0] -= image.getHeight()/2;
		 from[1] -= image.getHeight()/2;
		 
		 image.setLocation(from[0],from[1]);
		 
		 //play the scene
		 for(int k = 0; k<time; k=k) {
		   image.move(-(from[0]-to[0])/time,-(from[1]-to[1])/time);
		   pause(PAUSE_TIME);
		   k +=PAUSE_TIME;
		 }
		 
		 //settings for next iteration
		 removeAll();
		 from = null;
		 to = null;
	     image = null;
		}
	}
	private void listscenes() {
		for(int i=0;i<scenes.size();i++) {
			println( (i+1) + ")"+scenes.get(i));
		}
		
	}
	private void removescene() {
		//removes by index
		int sceneNum = readInt("Specify the scene you want to remove: ");
		if(sceneNum<0 || sceneNum>scenes.size()) println("Unrecognized scene!"); 
		else scenes.remove(sceneNum-1);
	}
	
	private void addscene() {
	
		String description = readLine("Describe the new scene:");
//		for(int i=0; i<description.length; i++) {
//			switch(grammar_arr[i]) {
//			case "time" :
//				if (description[i] <= 0) return false;
//			case "image" :
//				if()
//			
//			}
//		}
		scenes.add(description);	
		
	}
	private void setgrammar() {
		grammar = readLine("Please specify the order of the grammar elements: \n [time, image, scale, from, to]");
		String[] elements = grammar.split(" ");
		for(int i=0; i< elements.length;i++) {
			//if written grammar element is a valid element, add it to grammar array.
			if(getIndex(grammar_list, elements[i]) != -1 && getIndex(grammar_arr, elements[i])==-1)  grammar_arr[i] = elements[i];
			//if not set grammar array to null to reset and print error message.
			else {
				println("Unrecognized grammar or duplication: "+ elements[i]);
				grammar_arr = null;
				break;
			}
		}
	}
	
    private int getIndex(String[] arr, String a) {
    	//get the index of a string in array (search)
    	for(int i=0;i<arr.length;i++) {
    		if(arr[i].equals(a)) return i;
    	}
    	return -1;
    	
    }
	private void setback() {
		String color = readLine("Please specify the background color: (White, Green, Magenta, Blue)");
		switch(color.toLowerCase()) {
		case "white" : setBackground(Color.white); break;
		case "green" : setBackground(Color.green); break;
		case "magenta" : setBackground(Color.magenta); break;
		case "blue" : setBackground(Color.blue); break;
		default : println("Unrecognized color!");
		}
		
	}
	// your code ends here
	
	//ADDITIONAL INSTANCE AND CONSTANT VARIABLES//
	//your code starts here 


	
	private boolean exit = false;
	//order of the grammar elements set by user. 
	private String[] grammar_arr = {"", "", "", "", ""};
	//possible grammar user can use.
	private String[] grammar_list = {"time", "image", "scale", "from", "to"};
	// your code ends here
	
	
	// DO NOT REMOVE THIS SECTION//

	private String grammar = "";
	private List<String> scenes = new ArrayList<String>();
	private static int PAUSE_TIME = 1;
	
	// INSTANCE VARIABLES AND CONSTANTS
	/**
	 * Constant <code>String</code> used to prompt user for commands.
	 */
	public static String CLI_INPUT_STR = "MakeAMovie -> ";

	/**
	 * Path to the folder enclosing the images. Read images from this path.
	 */
	public static String LIBRARY_PATH = "../lib/";

	/**
	 * File extension of image files.
	 */
	public static String IMAGE_TYPE = ".png";
	

}
