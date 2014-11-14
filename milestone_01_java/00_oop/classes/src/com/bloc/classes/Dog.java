package com.bloc.classes;

import java.lang.String;

class Dog {
    // The length of hair which
    final float HAIR_CUT_LENGTH = 0.15f;
    // Minimum weight that any Dog can be
    final float MIN_WEIGHT = 1.25f;
	// Amount of weight to gain after eating
	final float WEIGHT_GAIN = 0.25f;
	// Amount of weight to lose after playing
	final float WEIGHT_LOSS = 0.2f;

	float mHairLength; 			// Hair length
	String mGender;				// Gender, either "male" or "female"
	String mSize;     			// Size, either "tiny", "small", "average", or "large"
	int mAge;					// Its age
	float mWeight;				// Its weight in pounds
	String mColor;				// The color of its coat

	private int playCount = 0;
	private int feedCount = 0;

	/*
	 * getHairLength
	 * @return this Dog's hair length
	 */
	float getHairLength() {
		return mHairLength;
	}

	/*
	 * setHairLength
	 * Sets the length of the Dog's hair
	 * @param hairLength the new length of the hair, a float
	 * @return nothing
	 */
	void setHairLength(float hairLength) {
		mHairLength = hairLength;
	}

	/*
	 * getGender
	 * @return this Dog's gender
	 */
	String getGender() {
		return mGender;
	}

	/*
	 * setGender
	 * Sets this Dog's gender
	 * @param gender the new gender of the Dog, a String
	 * @return nothing
	 */
	void setGender(String gender) {
		mGender = gender;
	}

	/*
	 * getSize
	 * @return the size of the dog
	 */
	String getSize() {
		return mSize;
	}

	/*
	 * setSize
	 * Sets the size of the Dog
	 * @param size the new size of the Dog, a String
	 * @return nothing
	 */
	void setSize(String size) {
		mSize = size;
	}

	/*
	 * getAge
	 * @return this Dog's age
	 */
	public int getAge() {
		return mAge;
	}

	/*
	 * setAge
	 * Sets the age of the Dog
	 * @param age the new age of the Dog, an int
	 * @return nothing
	 */
	public void setAge(int age) {
		this.mAge = age;
	}
	/*
	 * getWeight
	 * @return this Dog's weight
	 */
	public float getWeight() {
		return mWeight;
	}

	/*
	 * setWeight
	 * Sets the weight of the Dog
	 * @param weight the new weight of the Dog, a float
	 * @return nothing
	 */
	public void setWeight(float weight) {
		this.mWeight = weight;
	}
	/*
	 * getColor
	 * @return this Dog's color
	 */
	public String getColor() {
		return mColor;
	}
	/*
	 * setColor
	 * Sets the color of the Dog
	 * @param color the new color of the Dog's coat, a String
	 * @return nothing
	 */
	public void setColor(String color) {
		this.mColor = color;
	}

	/*
	 * feed
	 * Side-effect: 1. The Dog gains weight, specifically WEIGHT_GAIN
	 *              2. Every 3 meals, the Dog grows to a larger size, if * possible
	 *              i.e. "tiny" (3 meals later ->) "small" (3 meals later ->)
	 *                   "average" (3 meals later ->) "large"
	 * @return nothing
	 */
	public void feed() {

		// increase the weight
		mWeight += WEIGHT_GAIN;

		// incriment the feed count
		feedCount++;

		if (feedCount == 3) {
			switch (mSize) {
				case "tiny":
					mSize = "small";
					break;
				case "small":
					mSize = "average";
					break;
				case "average":
					mSize = "large";
					break;
			}
			// reset feed count
			feedCount = 0;
		}
	}

	/*
	 * play
	 * Side-effect: 1. The Dog loses weight, specifically WEIGHT_LOSS
	 *				2. Every 6 play invocations, the Dog shrinks to a smaller *                 size, if possible
	 *				i.e. "large" (6 plays later->) "average" (6 plays later->) *                   "small" -> "tiny"
     *              3. The Dog cannot shrink to a weight smaller than *                 MIN_WEIGHT
	 * @return nothing
	 */
	public void play() {

		// decrease the weight
		mWeight -= WEIGHT_LOSS;

		// ensure it's not below MIN_WEIGHT
		mWeight = (mWeight < MIN_WEIGHT) ? MIN_WEIGHT : mWeight;

		// incriment the feed count
		playCount++;

		if (playCount == 6) {
			switch (mSize) {
				case "large":
					mSize = "average";
					break;
				case "average":
					mSize = "small";
					break;
				case "small":
					mSize = "tiny";
					break;
			}
			// reset play count
			playCount = 0;
		}
	}

	/*
	 * cutHair
	 * Side-effect: the Dog's hair length is reduced by HAIR_CUT_LENGTH
     * The Dog's hair cannot be shorter than 0f
	 * @return nothing
	 */
	public void cutHair() {

		// reduce the hair length
		mHairLength -= HAIR_CUT_LENGTH;

		// ensure it's not less than 0f
		mHairLength = (mHairLength < 0f) ? 0f : mHairLength;

	}

}