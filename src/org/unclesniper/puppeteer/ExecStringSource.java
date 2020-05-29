package org.unclesniper.puppeteer;

import java.util.Map;

public interface ExecStringSource extends Traceable {

	void buildString(Machine machine, Argv argv, String workdir, Map<String, String> environ, int flags,
			StringBuilder sink) throws PuppetException;

}
