package com.bloc.objects;

class Ensemble extends Object {
	// Name
	String mName;

	// All of the artists in the group
	Artist[] mArtists;

	/*
	 * First Constructor
	 * This constructor takes in a variable length of Artist objects
	 * @param artists variable length artists
	 */
	Ensemble(Artist[] artists) {

		this.mArtists = artists;

		// set the name to be the name of the first artist
		this.mName = artists[0].mFirstName + " " + artists[0].mLastName;
	}

	/*
	 * Second Constructor
	 * This constructor takes a name and a variable length of Artist objects
	 * Side-effect: if the 'name' parameter is null, uses the first and           <-- should this be with the first constructor???
	 * 				last name of the first Artist
	 * Hint:		You can add Strings together with a '+'
	 * @param name the name of the group
	 * @param artists variable length artists
	 */
	Ensemble(String name, Artist[] artists) {

		if (name == null) {
			this.mName = artists[0].mFirstName + " " + artists[0].mLastName;
			System.out.println(this.mName);
		}
		else {
			this.mName = name;
		}

		this.mArtists = artists;

	}
}