package org.unclesniper.puppeteer;

import java.util.Map;

public class WorkDirExecStringSource implements ExecStringSource {

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

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	@Override
	public void buildString(Machine machine, Argv argv, String workdir, Map<String, String> environ, int flags,
			StringBuilder sink)  {
		if(workdir == null || workdir.length() == 0)
			return;
		if(prefix != null)
			sink.append(prefix);
		sink.append(transform == null ? workdir : transform.transformString(workdir));
		if(suffix != null)
			sink.append(suffix);
	}

}
