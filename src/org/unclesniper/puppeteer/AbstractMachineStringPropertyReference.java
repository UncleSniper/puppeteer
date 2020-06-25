package org.unclesniper.puppeteer;

public abstract class AbstractMachineStringPropertyReference extends AbstractMachinePropertyReference {

	private String fallback;

	public AbstractMachineStringPropertyReference() {}

	public String getFallback() {
		return fallback;
	}

	public void setFallback(String fallback) {
		this.fallback = fallback;
	}

	protected String getPropertyValue(Machine machine) throws MissingMachineStringPropertyException {
		String property = getProperty();
		if(property == null)
			return null;
		String value = machine.getStringProperty(property, getFallback());
		if(value == null && isRequired())
			throw new MissingMachineStringPropertyException(machine, property);
		StringTransform transform = getTransform();
		return transform == null || value == null ? value : transform.transformString(value);
	}

}
