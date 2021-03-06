package org.unclesniper.puppeteer;

import java.util.Map;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("envExecString")
public class EnvExecStringSource extends AbstractExecStringSource {

	private String mapPrefix;

	private String pairPrefix;

	private StringTransform keyTransform;

	private String pairInfix;

	private StringTransform valueTransform;

	private String pairSuffix;

	private String mapSuffix;

	public EnvExecStringSource() {}

	public String getMapPrefix() {
		return mapPrefix;
	}

	public void setMapPrefix(String mapPrefix) {
		this.mapPrefix = mapPrefix;
	}

	public String getPairPrefix() {
		return pairPrefix;
	}

	public void setPairPrefix(String pairPrefix) {
		this.pairPrefix = pairPrefix;
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

	public String getPairInfix() {
		return pairInfix;
	}

	public void setPairInfix(String pairInfix) {
		this.pairInfix = pairInfix;
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

	public String getPairSuffix() {
		return pairSuffix;
	}

	public void setPairSuffix(String pairSuffix) {
		this.pairSuffix = pairSuffix;
	}

	public String getMapSuffix() {
		return mapSuffix;
	}

	public void setMapSuffix(String mapSuffix) {
		this.mapSuffix = mapSuffix;
	}

	@Override
	protected void buildStringImpl(ExecSlave.ExecInfo info, StringBuilder sink) {
		if(info.environ == null || info.environ.isEmpty())
			return;
		if(mapPrefix != null)
			sink.append(mapPrefix);
		for(Map.Entry<String, String> entry : info.environ.entrySet()) {
			if(pairPrefix != null)
				sink.append(pairPrefix);
			sink.append(keyTransform == null ? entry.getKey() : keyTransform.transformString(entry.getKey()));
			if(pairInfix != null)
				sink.append(pairInfix);
			sink.append(valueTransform == null ? entry.getValue()
					: valueTransform.transformString(entry.getValue()));
			if(pairSuffix != null)
				sink.append(pairSuffix);
		}
		if(mapSuffix != null)
			sink.append(mapSuffix);
	}

}
