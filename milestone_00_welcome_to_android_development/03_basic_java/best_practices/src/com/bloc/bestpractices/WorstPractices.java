package com.bloc.bestpractices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WorstPractices extends Object {

// EDIT BELOW

	public static void main(String [] args) {
		int magicNumber = WorstPractices.generateMagicNumber(false);
		magicNumber *= 5;
		if (magicNumber > 18) {
			while(magicNumber > 0)
			{
				magicNumber--;
			}
		}
	}

	/** 
	*   Return a Magic Number
	*
	*	A magic number is generated using a seed determined by the value of isSeedHigh
	*	
	*   @param	  isSeedHigh  if set to TRUE then the seed is 34, if FALSE the seed is 21
	*   @return 			  the generated magic number as an int
	*/
	private static int generateMagicNumber(boolean isSeedHigh) {
			
		// choose seed depending on the value of isSeedHigh 
		int seed = isSeedHigh ? 34 : 21;
		
		float modifyingFloat = .5f;
		for (int i = 0; i < seed; i++) { 
			modifyingFloat *= seed;
		} 
		
		return (int) modifyingFloat * seed;
	}

// STOP EDITING
}
