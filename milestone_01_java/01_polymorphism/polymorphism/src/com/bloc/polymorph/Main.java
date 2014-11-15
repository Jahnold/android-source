package com.bloc.polymorph;

import com.bloc.polymorph.pets.*;

public class Main extends Object {

	public static void main(String [] args) {


		Pet dog = new Dog();
		Pet cat = new Cat();
		Pet bird = new Bird();
		Pet snake = new Snake();
		Pet tarantula = new Tarantula();

		// add all our pets to an array
		Pet[] pets = new Pet[] {dog,cat,bird,snake,tarantula};

		// loop through feeh, wash & exercise
		for (int i=0; i<5; i++) {

			pets[i].feed();
			pets[i].wash();
			pets[i].exercise();

		}

		assert dog.isFed() && dog.isWashed() && dog.isExercised() : "Your dog needs a little more attention";
		assert cat.isFed() && cat.isWashed() && cat.isExercised() : "Your cat needs a little more attention";
		assert bird.isFed() && bird.isWashed() && bird.isExercised() : "Your bird needs a little more attention";
		assert snake.isFed() && snake.isWashed() && snake.isExercised() : "Your snake needs a little more attention";
		assert tarantula.isFed() && tarantula.isWashed() && tarantula.isExercised() : "Your tarantula needs a little more attention";
		
		System.out.println("/************************/");
		System.out.println("/*                      */");
		System.out.println("/*                      */");
		System.out.println("/*   If you see this,   */");
		System.out.println("/*   congratulations!   */");
		System.out.println("/*                      */");
		System.out.println("/*                      */");
		System.out.println("/************************/\n");
	}
}
