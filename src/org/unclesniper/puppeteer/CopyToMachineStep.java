package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("copyTo")
public class CopyToMachineStep extends AbstractMachineStep {

	private CopyInFileProvider source;

	private final List<MachineStepStringSource> destinationPieces = new LinkedList<MachineStepStringSource>();

	public CopyToMachineStep() {}

	public CopyInFileProvider getSource() {
		return source;
	}

	public void setSource(CopyInFileProvider source) {
		this.source = source;
	}

	public void addDestinationPiece(MachineStepStringSource piece) {
		if(piece != null)
			destinationPieces.add(piece);
	}

	public void addDestinationPiece(NetworkStepStringSource piece) {
		if(piece != null)
			destinationPieces.add(new NetworkStepStringSourceMachineStepStringSource(piece));
	}

	public void addDestinationPiece(StepStringSource piece) {
		if(piece != null)
			destinationPieces.add(new StepStringSourceMachineStepStringSource(piece));
	}

	public void addDestinationPiece(String piece) {
		if(piece == null)
			return;
		StringMachineStepStringSource string = new StringMachineStepStringSource(piece);
		string.ingestObjectDefinitionLocation(this);
		destinationPieces.add(string);
	}

	@Override
	protected void performImpl(MachineStepInfo info) throws PuppetException {
		if(source == null)
			throw new CopyInFileProviderMissingException(this);
		String destination = MachineStepStringSource.accumulate(destinationPieces, info);
		source.copyTo(info.getMachine().getCopySlave(true), info, destination);
	}

}
