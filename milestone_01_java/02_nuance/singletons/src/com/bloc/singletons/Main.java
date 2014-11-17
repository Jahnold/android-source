package com.bloc.singletons;

import com.bloc.singletons.talkers.*;
import com.bloc.singletons.listeners.*;

public class Main extends Object {

	public static void main(String [] args) {

		// Instantiate some talkers and some listeners
		Talker parent = new Parent();
		Talker petOwner = new PetOwner();
		Talker singer = new Singer();

		Listener child = new Child();
		Listener pet = new Pet();
		Listener audienceMember = new AudienceMember();

		// Register listeners
		Speakerphone speakerPhone = Speakerphone.get();

		speakerPhone.addListener(child);
		speakerPhone.addListener(pet);
		speakerPhone.addListener(audienceMember);

		// Send messages!
		speakerPhone.shoutMessage(parent);
		speakerPhone.shoutMessage(petOwner, Pet.class);

		speakerPhone.removeAll();

	}
}
