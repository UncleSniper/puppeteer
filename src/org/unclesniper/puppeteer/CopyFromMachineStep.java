package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;

public class CopyFromMachineStep extends AbstractMachineStep {

	private final List<MachineStepStringSource> sourcePieces = new LinkedList<MachineStepStringSource>();

	private CopyOutFileProvider destination;

	public CopyFromMachineStep() {}

	public void addSourcePiece(MachineStepStringSource piece) {
		if(piece != null)
			sourcePieces.add(piece);
	}

	public void addSourcePiece(NetworkStepStringSource piece) {
		if(piece != null)
			sourcePieces.add(new NetworkStepStringSourceMachineStepStringSource(piece));
	}

	public void addSourcePiece(StepStringSource piece) {
		if(piece != null)
			sourcePieces.add(new StepStringSourceMachineStepStringSource(piece));
	}

	public void addSourcePiece(String piece) {
		if(piece != null)
			sourcePieces.add(new StringMachineStepStringSource(piece));
	}

	public CopyOutFileProvider getDestination() {
		return destination;
	}

	public void setDestination(CopyOutFileProvider destination) {
		this.destination = destination;
	}

	@Override
	protected void performImpl(MachineStepInfo info) throws PuppetException {
		if(destination == null)
			throw new CopyOutFileProviderMissingException(this);
		String source = MachineStepStringSource.accumulate(sourcePieces, info);
		destination.copyFrom(info.getMachine().getCopySlave(true), info, source);
	}

}
