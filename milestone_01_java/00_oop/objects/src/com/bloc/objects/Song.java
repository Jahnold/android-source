package com.bloc.objects;

import java.lang.String;

class Song extends Object {
	// The ensemble which produced it
	Ensemble mEnsemble;
	// Title of the song
	String mTitle;
	// The year it was released
	int mYearReleased;

	/*
	 * Basic Constructor
	 * Side-effects: Assigns some default ensemble, title and
	 *				 and year of your choosing
	 */
	Song() {

		// set the title
		this.mTitle = "The Cave";

		// create two artists
		Artist mumford = new Artist("Marcus", "Mumford");
		Artist marshall = new Artist("Winston", "Marshall");

		// stick them in an ensemble
		this.mEnsemble = new Ensemble("Mumford & Sons", new Artist[] {mumford, marshall});

		// set the year
		this.mYearReleased = 2007;

	}

	/*
	 * Partial Constructor
	 * Side-effects: Sets the year of release to 0
	 * @param ensemble the ensemble responsible
	 * @param title the song title
	 */
	Song(Ensemble ensemble, String title) {

		this.mTitle = title;
		this.mYearReleased = 0;
		this.mEnsemble = ensemble;

	}

	/*
	 * Full Constructor
	 * @param ensemble the ensemble responsible
	 * @param title the song title
	 * @param yearReleased the year the song was released
	 */
	Song(Ensemble ensemble, String title, int yearReleased) {

		this.mTitle = title;
		this.mYearReleased = yearReleased;
		this.mEnsemble = ensemble;

	}
}