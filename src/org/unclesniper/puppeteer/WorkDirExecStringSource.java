package org.unclesniper.puppeteer;

import java.util.Map;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("workDirExecString")
public class WorkDirExecStringSource extends AbstractExecStringSource {

	private String prefix;

	private StringTransform transform;

	private String suffix;

	public WorkDirExecStringSource() {}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public StringTransform getTransform() {
		return transform;
	}

	public void setTransform(StringTransform transform) {
		this.transform = transform;
	}

	public void setTransform(WordQuoter transform) {
		this.transform = transform == null ? null : new QuotingStringTransform(transform);
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	@Override
	protected void buildStringImpl(ExecSlave.ExecInfo info, StringBuilder sink)  {
		if(info.workdir == null || info.workdir.length() == 0)
			return;
		if(prefix != null)
			sink.append(prefix);
		sink.append(transform == null ? info.workdir : transform.transformString(info.workdir));
		if(suffix != null)
			sink.append(suffix);
	}

}
