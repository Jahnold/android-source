package com.bloc.singletons;

import java.lang.Class;
import java.util.HashSet;
import java.util.Iterator;

/*
 * This is a singleton class which maintains communication
 * between classes and Listeners
 */
public class Speakerphone extends Object {

	private static Speakerphone sSpeakerphone;

	/*
	 * get
	 * @return the singleton instance of Speakerphone
	 */
	public static Speakerphone get() {

		// if the speakerphone has not bee instantiated yet do so now
		if (sSpeakerphone == null) {
			sSpeakerphone = new Speakerphone();
		}

		return sSpeakerphone;
	}

	// a set of all the listeners
	private HashSet<Listener> mListeners;

	// private constructor
	private	Speakerphone() {

		// instantiate the list of listeners;
		mListeners = new HashSet<Listener>();
	}

	/*
	 * addListener
	 * Add a listener to Speakerphone's list
	 * @param listener an instance of the Listener interface
	 */
	public void addListener(Listener listener) {
		mListeners.add(listener);
	}

	/*
	 * removeListener
	 * Remove a Listener from the Speakerphone's list
	 * @param listener the Listener to remove
	 */
	public void removeListener(Listener listener) {
		mListeners.remove(listener);
	}

	/*
	 * shoutMessage
	 * Sends the message to all of the Listeners tracked by Speakerphone
	 * @param talker a Talker whose message will be sent
	 */
	public void shoutMessage(Talker talker) {
		Iterator<Listener> allListeners = mListeners.iterator();
		while (allListeners.hasNext()) {
			allListeners.next().onMessageReceived(talker.getMessage());
		}
	}

	/*
	 * shoutMessage
	 * Sends the message to all of the Listeners which are instances of
	 * the class parameter
	 * @param talker a Talker whose message will be sent
	 * @param cls a Class object representing the type which the Listener
	 *			  should extend from in order to receive the message
	 *
	 * HINT: see Class.isAssignableFrom()
	 *		 http://docs.oracle.com/javase/7/docs/api/java/lang/Class.html#isAssignableFrom(java.lang.Class)
	 */
	public void shoutMessage(Talker talker, Class cls) {
		Iterator<Listener> allListeners = mListeners.iterator();
		while (allListeners.hasNext()) {

			// get a reference to the current listener
			Listener currentListener = allListeners.next();

			// if it's of the class we're looking for send the message
			if (cls.isAssignableFrom(currentListener.getClass())) {
				currentListener.onMessageReceived(talker.getMessage());
			}

		}
	}

	/*
	 * removeAll
	 * Removes all Listeners from Speakerphone
	 */
	public void removeAll() {
		mListeners.clear();
	}

}