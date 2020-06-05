package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;

public class DeleteFileMachineStep extends AbstractMachineStep {

	private final List<MachineStepStringSource> pathPieces = new LinkedList<MachineStepStringSource>();

	public DeleteFileMachineStep() {}

	public void addPathPiece(MachineStepStringSource piece) {
		if(piece != null)
			pathPieces.add(piece);
	}

	public void addPathPiece(String piece) {
		if(piece != null)
			pathPieces.add(new StringMachineStepStringSource(piece));
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