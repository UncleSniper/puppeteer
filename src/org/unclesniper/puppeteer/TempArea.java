package org.unclesniper.puppeteer;

import java.io.File;

public interface TempArea {

	File newTempFile() throws PuppetException;

}
