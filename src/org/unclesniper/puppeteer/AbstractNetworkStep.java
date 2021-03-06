package org.unclesniper.puppeteer;

public abstract class AbstractNetworkStep extends AbstractGeneralStep implements NetworkStep {

	private Network currentNetwork;

	public AbstractNetworkStep() {}

	protected abstract void performImpl(NetworkStepInfo info) throws PuppetException;

	@Override
	public void perform(NetworkStepInfo info) throws PuppetException {
		Network oldNetwork = currentNetwork;
		try {
			currentNetwork = info.getNetwork();
			info.getUI().executingStep(this);
			performImpl(info);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
		finally {
			currentNetwork = oldNetwork;
		}
	}

	@Override
	public String getStepQualifier() {
		if(currentNetwork == null)
			return null;
		String name = currentNetwork.getName();
		return name == null ? null : "network '" + name + '\'';
	}

}
