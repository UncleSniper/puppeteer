package org.unclesniper.puppeteer;

public abstract class AbstractMachineStepStringSource extends AbstractTraceable implements MachineStepStringSource {

	public AbstractMachineStepStringSource() {}

	protected abstract void buildStringImpl(MachineStep.MachineStepInfo info, StringBuilder sink)
			throws PuppetException;

	@Override
	public void buildString(MachineStep.MachineStepInfo info, StringBuilder sink) throws PuppetException {
		try {
			buildStringImpl(info, sink);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
