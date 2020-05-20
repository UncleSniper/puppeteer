package org.unclesniper.puppeteer;

import java.util.Map;
import java.util.function.Consumer;

public class WorkDirExecWordEmitter implements ExecWordEmitter {

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
	public void buildArgv(Machine machine, Argv argv, String workdir, Map<String, String> environ, int flags,
			Consumer<String> sink) {
		if(workdir == null || workdir.length() == 0)
			return;
		if(prefix == null && suffix == null) {
			sink.accept(transform == null ? workdir : transform.transformString(workdir));
			return;
		}
		StringBuilder builder = new StringBuilder();
		if(prefix != null)
			builder.append(prefix);
		builder.append(transform == null ? workdir : transform.transformString(workdir));
		if(suffix != null)
			builder.append(suffix);
		sink.accept(builder.toString());
	}

}
