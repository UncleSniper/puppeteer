package org.unclesniper.puppeteer;

import org.unclesniper.ogdl.ObjectDefinitionLocationProperty;

public abstract class AbstractTraceable implements Traceable {

	private String injectionObjectDefinitionFile;

	private int injectionObjectDefinitionLine;

	public AbstractTraceable() {}

	protected AbstractTraceable(AbstractTraceable traceable) {
		injectionObjectDefinitionFile = traceable.injectionObjectDefinitionFile;
		injectionObjectDefinitionLine = traceable.injectionObjectDefinitionLine;
	}

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

	public void ingestObjectDefinitionLocation(AbstractTraceable other) {
		if(other == null)
			return;
		boolean haveFile = injectionObjectDefinitionFile != null && injectionObjectDefinitionFile.length() > 0;
		boolean haveLine = injectionObjectDefinitionLine > 0;
		boolean hasFile = other.injectionObjectDefinitionFile != null
				&& other.injectionObjectDefinitionFile.length() > 0;
		boolean hasLine = other.injectionObjectDefinitionLine > 0;
		if(!haveFile && hasFile)
			injectionObjectDefinitionFile = other.injectionObjectDefinitionFile;
		if(!haveLine && hasLine)
			injectionObjectDefinitionLine = other.injectionObjectDefinitionLine;
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
		String start = hasFile ? injectionObjectDefinitionFile + ':' : "<unknown file>:";
		return hasLine ? start + injectionObjectDefinitionLine : start + "<unknown line>";
	}

}
