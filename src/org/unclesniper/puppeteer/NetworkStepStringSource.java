package org.unclesniper.puppeteer;

public interface NetworkStepStringSource extends Traceable {

	void buildString(NetworkStep.NetworkStepInfo info, StringBuilder sink) throws PuppetException;

}
