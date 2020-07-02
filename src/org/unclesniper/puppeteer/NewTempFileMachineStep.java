package org.unclesniper.puppeteer;

import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("newTempFile")
public class NewTempFileMachineStep extends AbstractMachineStep {

	private MachineStringCapture pathCapture;

	public NewTempFileMachineStep() {}

	public MachineStringCapture getPathCapture() {
		return pathCapture;
	}

	public void setPathCapture(MachineStringCapture pathCapture) {
		this.pathCapture = pathCapture;
	}

	@Override
	protected void performImpl(MachineStepInfo info) throws PuppetException {
		Machine machine = info.getMachine();
		String path = machine.getFileSlave(true).newTempFile(info, machine);
		if(pathCapture == null)
			info.getUI().warn(this, "Creating a temp file without capturing its path makes little sense");
		else
			pathCapture.captureString(info, path);
	}

}
