package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("machinePrint")
public class PrintMachineStep extends AbstractMachineStep {

	private MessageLevel level;

	private final List<MachineStepStringSource> pieces = new LinkedList<MachineStepStringSource>();

	public PrintMachineStep() {}

	public MessageLevel getLevel() {
		return level;
	}

	public void setLevel(MessageLevel level) {
		this.level = level;
	}

	public void addPiece(MachineStepStringSource piece) {
		if(piece != null)
			pieces.add(piece);
	}

	public void addPiece(NetworkStepStringSource piece) {
		if(piece != null)
			pieces.add(new NetworkStepStringSourceMachineStepStringSource(piece));
	}

	public void addPiece(StepStringSource piece) {
		if(piece != null)
			pieces.add(new StepStringSourceMachineStepStringSource(piece));
	}

	public void addPiece(String piece) {
		if(piece != null)
			pieces.add(new StringMachineStepStringSource(piece));
	}

	@Override
	protected void performImpl(MachineStepInfo info) throws PuppetException {
		PrintStep.doPrint(this, info, level, MachineStepStringSource.accumulate(pieces, info));
	}

}
