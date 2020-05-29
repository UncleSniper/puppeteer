package org.unclesniper.puppeteer;

public abstract class AbstractMachineStringPropertyReference extends AbstractTraceable {

	private String property;

	private String fallback;

	private boolean required;

	private StringTransform transform;

	public AbstractMachineStringPropertyReference() {}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getFallback() {
		return fallback;
	}

	public void setFallback(String fallback) {
		this.fallback = fallback;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public StringTransform getTransform() {
		return transform;
	}

	public void setTransform(StringTransform transform) {
		this.transform = transform;
	}

	protected String getPropertyValue(Machine machine) throws MissingMachineStringPropertyException {
		if(property == null)
			return null;
		String value = machine.getStringProperty(property, fallback);
		if(value == null && required)
			throw new MissingMachineStringPropertyException(machine, property);
		return transform == null ? value : transform.transformString(value);
	}

}
