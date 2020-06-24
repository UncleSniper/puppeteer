package org.unclesniper.puppeteer;

import java.util.Map;

public interface ExecStringSource extends Traceable {

	void buildString(ExecSlave.ExecInfo info, StringBuilder sink) throws PuppetException;

}
