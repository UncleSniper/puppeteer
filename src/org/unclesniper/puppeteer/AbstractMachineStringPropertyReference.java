package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import java.util.function.Consumer;

public abstract class AbstractMachineStringPropertyReference extends AbstractTraceable {

	private String property;

	private String fallback;

	private boolean required;

	private StringTransform transform;

	private final List<String> prefixWords = new LinkedList<String>();

	private final List<String> suffixWords = new LinkedList<String>();

	private boolean useExecHost;

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

	public void addPrefixWord(String word) {
		if(word != null)
			prefixWords.add(word);
	}

	public Iterable<String> getPrefixWords() {
		return prefixWords;
	}

	protected void putPrefixWords(Consumer<String> sink) {
		for(String word : prefixWords)
			sink.accept(word);
	}

	protected void putPrefixWords(StringBuilder sink) {
		for(String word : prefixWords)
			sink.append(word);
	}

	public void addSuffixWord(String word) {
		if(word != null)
			suffixWords.add(word);
	}

	public Iterable<String> getSuffixWords() {
		return suffixWords;
	}

	protected void putSuffixWords(Consumer<String> sink) {
		for(String word : suffixWords)
			sink.accept(word);
	}

	protected void putSuffixWords(StringBuilder sink) {
		for(String word : suffixWords)
			sink.append(word);
	}

	public boolean isUseExecHost() {
		return useExecHost;
	}

	public void setUseExecHost(boolean useExecHost) {
		this.useExecHost = useExecHost;
	}

	protected String getPropertyValue(Machine machine) throws MissingMachineStringPropertyException {
		if(property == null)
			return null;
		String value = machine.getStringProperty(property, fallback);
		if(value == null && required)
			throw new MissingMachineStringPropertyException(machine, property);
		return transform == null ? value : transform.transformString(value);
	}

	protected Machine getCorrectMachine(Machine machine, Machine execHost)
			throws MissingTargetMachineException, MissingExecHostException {
		if(useExecHost) {
			if(machine == null)
				throw new MissingTargetMachineException();
			return machine;
		}
		else {
			if(execHost == null)
				throw new MissingExecHostException();
			return execHost;
		}
	}

}
