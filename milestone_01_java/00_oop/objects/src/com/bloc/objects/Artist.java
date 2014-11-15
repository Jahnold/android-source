package com.bloc.objects;

import java.lang.String;

class Artist extends Object {
	// The artist's first name
	String mFirstName;
	// The artist's last name
	String mLastName;

	/*
	 * Only Constructor
	 * @param firstName
	 * @param lastName
	 */
	Artist(String firstName, String lastName) {

		this.mFirstName = firstName;
		this.mLastName = lastName;

	}
}