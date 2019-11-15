/** Student Name: Eren Yenigul
 * This is a console program designed to conduct an audition for an architecture
 * competition. Four contestants are competing against each other given their 
 * education, experience and their conciousness about environment. 
 * 
**/

import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;

public class KUArchitect extends ConsoleProgram {

	public void run() {
		// your code starts here
	    
		//Read contestant specs
		getContestantData();
		
		//Compare their points
		comparator();
		
		//Make contestants combat
		combat(1,2,firstPoint,secondPoint);
		combat(2,3,secondPoint,thirdPoint);
		combat(1,3,firstPoint,thirdPoint);
		
		//Final results
		listFinalWins();
		
		// your code ends here
	
	}
		////////////GIVEN HELPER METHODS /////////////////////
	// You need to implement the given helper methods ///
	// You ARE NOT ALLOWED to change the signature of the given methods.
	
	/**
	 * This methods asks the number of year of education and number of year of experience. 
	 * It will keep on asking user input until a valid input is read.
	 * @return - returns the knowledge point
	 */
	

	private double askKnowledge() {
		// your code starts here
		int yearsEducation = readInt("Years of education of KuArchitect#"+currentKUArchitectID+": ");
		while ((yearsEducation<0)||(yearsEducation<4)||(yearsEducation>6) ) {
			yearsEducation = readInt("Please enter a valid value for years of education of KuArchitect#"+currentKUArchitectID+": ");
		}
		int yearsExperience = readInt("Years of experience of KuArchitect#"+currentKUArchitectID+": ");
		while (!((yearsExperience>=2) && (yearsExperience<=10))) {
			yearsExperience = readInt("Please enter a valid value for years of experience of  KuArchitect#"+currentKUArchitectID+": ");
		}
		
		
		return calculateKnowledge(yearsEducation, yearsExperience);
		// your code ends here
		
		
	}

	/**
	 * This method calculates the knowledge point given the number of year of education 
	 * and the number of year of experience
	 * @param yearsEducation - the number of year of education
	 * @param yearsExperience - the number of year of experience
	 * @return - knowledge point
	 */
	
	private double calculateKnowledge(int yearsEducation, int yearsExperience) {
		// your code starts here
		return Math.pow((yearsEducation + yearsExperience)/5.0,yearsExperience-2) + fibbonaci(yearsEducation);
		// your code ends here
	}

	

	/**
	 * This methods asks the number of projects and number of different projects. 
	 * It will keep on asking user input until a valid input is read.
	 * @return - returns the experience point
	 */
	private int askExperience() {
		// your code starts here
		int projectNum = readInt("Number of projects completed by KuArchitect#"+currentKUArchitectID+": ");
		while ((projectNum<4)||(projectNum>15)) {
			projectNum = readInt("Please enter a valid value for number of projects completed by KuArchitect#"+currentKUArchitectID+": ");
		}
		int projectVar = readInt("Number of different projects KuArchitect#"+currentKUArchitectID+": ");
		while ((projectVar<2)||(projectVar>5)) {
			projectVar = readInt("Please enter a valid value for the number of different projects KuArchitect#"+currentKUArchitectID+": ");
		}
		
		return calculateExperience(projectNum, projectVar);
		// your code ends here

	}
	/**
	 * This method calculates the experience point given the number of projects
	 * and the different projects worked on.
	 * @param numProjects - number of projects
	 * @param numDifProjects - number of different projects
	 * @return - experience point
	 */
	
	
	/**
	 * This methods asks the number of awards received.
	 * It will keep on asking user input until a valid input is read.
	 * @return - number of awards
	 */
	private int askAwards() {
		
		// your code starts here
		int awardNum = readInt("Number of awards of KuArchitect#"+currentKUArchitectID+": ");
		while((awardNum<1)||(awardNum>5)) {
			awardNum = readInt("Please enter a valid number for the number of awards of KuArchitect#"+currentKUArchitectID+": ");
		}
		return awardNum;
		// your code ends here
	}

	/**
	 * This method decides which architect gets the recycled materials.
	 * @return - whether the architect received a recycled material or not.
	 */
	private boolean hasRecycledMaterial() {
		// your code starts here
	
		return rgen.nextBoolean();
		// your code ends here
	}

	
	
	/**
	 * This method calculates the total points of a KUArchitect
	 * @param knowledge - the knowledge point of a KUArchietct
	 * @param experience - the experience point of a KUArchietct
	 * @param awards - the number of awards of a KUArchietct
	 * @param hasRecycledMaterial - whether KUArchiects received recyled material or not.
	 * @return - the total point of a KUArchitect
	 */
	private int pointCalculator(double knowledge, int experience,  int awards, boolean hasRecycledMaterial) {

		double totalPoint ;
		// your code starts here
		if(hasRecycledMaterial) totalPoint = Math.sqrt(experience*awards)+1.3*knowledge;
		else totalPoint = Math.sqrt(experience*awards)+0.9*knowledge;
		
		return rounded(totalPoint);
		// your code ends here
	}
	
	
	/**
	 * This method assigns the total point calculatef for a KUArchitects and assigns to the correct KUArchitect
	 * @param p - the total point of any given KUArchitect
	 * 
	 */
	
	private int calculateExperience(int numProjects, int numDifProjects) {
		// your code starts here
		return numProjects*factorial(numDifProjects);		
		// your code ends here
	}
	private void assignPoint(int p) {

		// your code starts here
		switch(currentKUArchitectID) {
		case 1:
			totalPointOfKUArchitect1 = p;
			break;
		case 2:
			totalPointOfKUArchitect2 = p;
			break;
		case 3:
			totalPointOfKUArchitect3 = p;
			break;
		case 4:
			totalPointOfKUArchitect4 = p;
			break;
		}
		// your code ends here
	}

	/**
	 * This method compares the total point of all contestants and prints
	 * the first, secong and third order of the KUArchitect according to their total points.
	 */
	private void comparator() {
		// your code starts here
		int currentPoint = 0;
		//get points of contestant with given ID
		for(int i=1;i<5;i++) {
			switch(i) {
			case 1:
				currentPoint = totalPointOfKUArchitect1;
				break;
			case 2:
				currentPoint = totalPointOfKUArchitect2;
				break;
			case 3:
				currentPoint = totalPointOfKUArchitect3;
				break;
			case 4:
				currentPoint = totalPointOfKUArchitect4;
				break;
			}
			 // compare current contestant's point with each three points which are in first 3 list.
			if(currentPoint>=firstPoint) {
				thirdPoint = secondPoint;
				thirdID = secondID;
				secondPoint = firstPoint;
				secondID = firstID;
				firstID = i;
				firstPoint = currentPoint;
			}
			else if(currentPoint>=secondPoint){
					thirdPoint = secondPoint;
					thirdID = secondID;
					secondPoint = currentPoint;
					secondID = i;
			}
			else if(currentPoint>=thirdPoint) {
				thirdPoint = currentPoint;
				thirdID = i;
					
				}
			}
		println();
		println("KUArchitect#"+firstID+ " becomes #1 with "+ firstPoint+" points.");
		println("KUArchitect#"+secondID+ " becomes #2 with " + secondPoint+" points.");
		println("KUArchitect#"+thirdID+ " becomes #3 with "+ thirdPoint+" points.");
		}
		
		
		
		// your code ends here
	

	////////////ADDITIONAL HELPER METHODS /////////////////////
	// Feel free to add additional helper methods
	// your code starts here
	private int factorial (int x) {
		int result = 1;
		for(int i = 1; i<=x;i++) result *=i;
		
		return result;
	}
	private int fibbonaci(int x) {
		int first = 1;
		int second = 1;
		for(int i = 0; i<x-2;i++) {
			first = first + second;
			second = first - second;
		}
		return first;
		
	}
	private int rounded(double value) {
		double result;
		if(value%1<0.5) result= value-(value%1);
		else result = (value + 1-value%1);
		int resultx = (int) result;
		return resultx;
	}
	private void listFinalWins() {
		println();
		println("The game is now completed and scores are as below:");
		println("KUArchitect#"+firstID+ " won "+ numberOfWins1 +" games out of "+ NTIMES*2 +" games.");
		println("KUArchitect#"+secondID+ " won "+ numberOfWins2 +" games out of "+ NTIMES*2 +" games.");
		println("KUArchitect#"+thirdID+ " won "+ numberOfWins3 +" games out of "+ NTIMES*2 +" games.");
		
		println();
		println("CONGRATULATIONS KuArchitect#"+findIDofWinner()+"!! YOU ARE THE WINNER of KuArchitect" );
		
	}

	
	private void getContestantData() {
		while(currentKUArchitectID<5) {
			println();
			println("NEW CONTESTANT:");
			// get all the data and assign them to variables.
			double knowledge = askKnowledge(); int experience = askExperience();  int awards = askAwards(); boolean hasRecycledMaterial = hasRecycledMaterial();
			//calculate points from given data
			int point = pointCalculator(knowledge, experience, awards, hasRecycledMaterial);
			assignPoint(point);
			println("KUArchitect#"+currentKUArchitectID+ " reached "+ point+" points.");
			println("______________________________________________");
			currentKUArchitectID++;
		}
		currentKUArchitectID = 1;
	}
	private int findIDofWinner() {
	    //Checks every win number to get the detect the greatest and return the id.
		if((numberOfWins1>numberOfWins2)&&(numberOfWins1>numberOfWins3)) return firstID;
		else if((numberOfWins2>numberOfWins3)&&(numberOfWins2>numberOfWins1)) return secondID;
		else if((numberOfWins3>numberOfWins1)&&(numberOfWins3>numberOfWins2)) return thirdID;
		else return 0;
	}
	private void combat(int position1, int position2, int point1, int point2) {
		int wins1 = 0;
		int wins2 = 0;
		
		//Combat NTIMES with random generator
		for(int i = 0; i<NTIMES;i++) {
		     if(rgen.nextBoolean(point1*1./(point1+point2))) {
		    	 wins1++; 
		     }else {
		    	 wins2++;
		     }
		}
		//Increase win numbers of given contestants.
		assignCombatWins(position1, position2, wins1, wins2);
	}

	private void assignCombatWins(int position1, int position2, int win1, int win2){
		switch(position1) {
		case 1:
			numberOfWins1 += win1;
			break;
		case 2:
			numberOfWins2 += win1;
			break;
		case 3:
			numberOfWins3 += win1;
			break;
		}
		switch(position2) {
		case 1:
			numberOfWins1 += win2;
			break;
		case 2:
			numberOfWins2 += win2;
			break;
		case 3:
			numberOfWins3 += win2;
			break;
		}
	}


	// your code ends here
	
	//////////// GIVEN VARIABLES and CONSTANTS /////////////////////
	int currentKUArchitectID = 1;

	int totalPointOfKUArchitect1;
	int totalPointOfKUArchitect2;
	int totalPointOfKUArchitect3;
	int totalPointOfKUArchitect4;

	int firstID;
	int secondID;
	int thirdID;

	int firstPoint;
	int secondPoint;
	int thirdPoint;
	
	private final int CONTESTANT_NUM = 4;
	private final int NTIMES = 100;
	static RandomGenerator rgen = new RandomGenerator();
	////////////ADDITIONAL VARIABLES and CONSTANTS  /////////////////////
	// Feel free to add additional variables and constants 
	// your code starts here
	int numberOfWins1 = 0;
	int numberOfWins2 = 0;
	int numberOfWins3 = 0;
	// your code ends here

}
