package com.bloc.securitypackages.apples;

import com.bloc.securitypackages.*;

public abstract class Apple extends Fruit {

	public Apple(String name, int calories, Color color, double weight) {
		super(name,calories,color,weight);
	}

	public abstract void bite();

}