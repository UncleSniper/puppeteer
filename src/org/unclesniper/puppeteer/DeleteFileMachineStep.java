package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("deleteFile")
public class DeleteFileMachineStep extends AbstractMachineStep {

	private final List<MachineStepStringSource> pathPieces = new LinkedList<MachineStepStringSource>();

	public DeleteFileMachineStep() {}

	public void addPathPiece(MachineStepStringSource piece) {
		if(piece != null)
			pathPieces.add(piece);
	}

	public void addPathPiece(NetworkStepStringSource piece) {
		if(piece != null)
			pathPieces.add(new NetworkStepStringSourceMachineStepStringSource(piece));
	}

	public void addPathPiece(StepStringSource piece) {
		if(piece != null)
			pathPieces.add(new StepStringSourceMachineStepStringSource(piece));
	}

	public void addPathPiece(String piece) {
		if(piece == null)
			return;
		StringMachineStepStringSource string = new StringMachineStepStringSource(piece);
		string.ingestObjectDefinitionLocation(this);
		pathPieces.add(string);
	}

	@Override
	protected void performImpl(MachineStepInfo info) throws PuppetException {
		Machine machine = info.getMachine();
		String path = MachineStepStringSource.accumulate(pathPieces, info);
		if(path.length() == 0)
			return;
		machine.getFileSlave(true).deleteFile(machine, path);
	}

}
