package org.unclesniper.puppeteer;

import java.util.Map;
import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("envExecWord")
public class EnvExecWordEmitter extends AbstractExecWordEmitter {

	private String prefix;

	private StringTransform keyTransform;

	private String infix;

	private StringTransform valueTransform;

	private String suffix;

	public EnvExecWordEmitter() {}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public StringTransform getKeyTransform() {
		return keyTransform;
	}

	public void setKeyTransform(StringTransform keyTransform) {
		this.keyTransform = keyTransform;
	}

	public void setKeyTransform(WordQuoter keyTransform) {
		this.keyTransform = keyTransform == null ? null : new QuotingStringTransform(keyTransform);
	}

	public String getInfix() {
		return infix;
	}

	public void setInfix(String infix) {
		this.infix = infix;
	}

	public StringTransform getValueTransform() {
		return valueTransform;
	}

	public void setValueTransform(StringTransform valueTransform) {
		this.valueTransform = valueTransform;
	}

	public void setValueTransform(WordQuoter valueTransform) {
		this.valueTransform = valueTransform == null ? null : new QuotingStringTransform(valueTransform);
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	@Override
	protected void buildArgvImpl(Machine machine, Argv argv, String workdir, Map<String, String> environ,
			int flags, Consumer<String> sink) {
		if(environ == null || environ.isEmpty())
			return;
		StringBuilder builder = new StringBuilder();
		for(Map.Entry<String, String> entry : environ.entrySet()) {
			builder.setLength(0);
			if(prefix != null)
				builder.append(prefix);
			builder.append(keyTransform == null ? entry.getKey() : keyTransform.transformString(entry.getKey()));
			if(infix != null)
				builder.append(infix);
			builder.append(valueTransform == null ? entry.getValue()
					: valueTransform.transformString(entry.getValue()));
			if(suffix != null)
				builder.append(suffix);
			sink.accept(builder.toString());
		}
	}

}
