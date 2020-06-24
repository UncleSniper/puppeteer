package org.unclesniper.puppeteer;

import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("workDirExecWord")
public class WorkDirExecWordEmitter extends AbstractExecWordEmitter {

	private String prefix;

	private StringTransform transform;

	private String suffix;

	public WorkDirExecWordEmitter() {}

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
	protected void buildArgvImpl(ExecSlave.ExecInfo info, Consumer<String> sink) {
		if(info.workdir == null || info.workdir.length() == 0)
			return;
		if(prefix == null && suffix == null) {
			sink.accept(transform == null ? info.workdir : transform.transformString(info.workdir));
			return;
		}
		StringBuilder builder = new StringBuilder();
		if(prefix != null)
			builder.append(prefix);
		builder.append(transform == null ? info.workdir : transform.transformString(info.workdir));
		if(suffix != null)
			builder.append(suffix);
		sink.accept(builder.toString());
	}

}
