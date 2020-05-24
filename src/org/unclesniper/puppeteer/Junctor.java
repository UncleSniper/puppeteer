package org.unclesniper.puppeteer;

public enum Junctor {

	ANY,
	ALL;

	public static boolean getShortCircuit(Junctor junctor) {
		switch(junctor == null ? Junctor.ALL : junctor) {
			case ANY:
				return true;
			case ALL:
				return false;
			default:
				throw new Doom("Unrecognized Junctor: " + junctor.name());
		}
	}

}
