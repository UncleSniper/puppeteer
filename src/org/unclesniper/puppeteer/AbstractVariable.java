package org.unclesniper.puppeteer;

public abstract class AbstractVariable extends AbstractTraceable implements Variable {

	private String name;

	public AbstractVariable() {}

	public AbstractVariable(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
