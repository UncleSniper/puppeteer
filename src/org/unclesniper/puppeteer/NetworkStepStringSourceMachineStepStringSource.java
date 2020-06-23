package org.unclesniper.puppeteer;

public class NetworkStepStringSourceMachineStepStringSource extends AbstractMachineStepStringSource {

	private NetworkStepStringSource source;

	public NetworkStepStringSourceMachineStepStringSource() {}

	public NetworkStepStringSourceMachineStepStringSource(NetworkStepStringSource source) {
		this.source = source;
	}

	public NetworkStepStringSource getSource() {
		return source;
	}

	public void setSource(NetworkStepStringSource source) {
		this.source = source;
	}

	@Override
	protected void buildStringImpl(MachineStep.MachineStepInfo info, StringBuilder sink) throws PuppetException {
		if(source != null)
			source.buildString(info, sink);
	}

}
