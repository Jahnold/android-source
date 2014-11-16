package com.bloc.recursion;

import java.lang.Integer;
import java.util.*;
import java.util.List;

public class RecursionUtils extends Object {

	/*
	 * findMaxRecursively
	 * Takes a list of numbers and finds the largest among them
	 * using recursive calls.
	 *
	 * @param numbers a list of numbers, can be odd or even numbered
	 * @return the largest number in the list
	 *
	 * Hint: your base case may be a comparison of 2 numbers
	 */
	public static final int findMaxRecursively(List<Integer> numbers) {

		if (numbers.size() == 1) {

			// only one number in the list - it is the max
			return numbers.get(0);

		}
		else {

			// compare the first number on the list to the rest of the list
			// if the rest of the list is bigger than 1 it'll get chunked up recursively
			return Math.max(numbers.get(0), findMaxRecursively(numbers.subList(1,numbers.size())));

		}

	}
}