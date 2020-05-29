package org.unclesniper.puppeteer;

import org.unclesniper.ogdl.ObjectDefinitionLocationProperty;

public abstract class AbstractTraceable implements Traceable {

	private String injectionObjectDefinitionFile;

	private int injectionObjectDefinitionLine;

	public AbstractTraceable() {}

	public String getInjectionObjectDefinitionFile() {
		return injectionObjectDefinitionFile;
	}

	public void setInjectionObjectDefinitionFile(String injectionObjectDefinitionFile) {
		this.injectionObjectDefinitionFile = injectionObjectDefinitionFile;
	}

	public int getInjectionObjectDefinitionLine() {
		return injectionObjectDefinitionLine;
	}

	public void setInjectionObjectDefinitionLine(int injectionObjectDefinitionLine) {
		this.injectionObjectDefinitionLine = injectionObjectDefinitionLine;
	}

	@ObjectDefinitionLocationProperty
	public void setInjectionObjectDefinitionLocation(String file, int line) {
		injectionObjectDefinitionFile = file;
		injectionObjectDefinitionLine = line;
	}

	@Override
	public String getTraceObjectDefinitionLocation() {
		boolean hasFile = injectionObjectDefinitionFile != null && injectionObjectDefinitionFile.length() > 0;
		boolean hasLine = injectionObjectDefinitionLine > 0;
		if(!hasFile && !hasLine)
			return null;
		String start = (hasFile ? injectionObjectDefinitionFile : "<unknown file>") + ':';
		return hasLine ? start + injectionObjectDefinitionLine : start + ":<unknown line>";
	}

}
