package org.unclesniper.puppeteer;

public abstract class AbstractMachineIntPropertyReference extends AbstractMachinePropertyReference {

	private Integer fallback;

	public AbstractMachineIntPropertyReference() {}

	public Integer getFallback() {
		return fallback;
	}

	public void setFallback(Integer fallback) {
		this.fallback = fallback;
	}

	protected Integer getPropertyValue(Machine machine) throws MissingMachineIntPropertyException {
		String property = getProperty();
		if(property == null)
			return null;
		Integer value = machine.getIntProperty(property, getFallback());
		if(value == null && isRequired())
			throw new MissingMachineIntPropertyException(machine, property);
		return value;
	}

	protected String getTransformedPropertyValue(Machine machine) throws MissingMachineIntPropertyException {
		Integer value = getPropertyValue(machine);
		if(value == null)
			return null;
		String sval = value.toString();
		StringTransform transform = getTransform();
		return transform == null ? sval : transform.transformString(sval);
	}

}
