package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;

public class PathCopyInFileProvider extends AbstractCopyInFileProvider {

	private final List<MachineStepStringSource> pathPieces = new LinkedList<MachineStepStringSource>();

	public PathCopyInFileProvider() {}

	public void addPathPiece(MachineStepStringSource piece) {
		if(piece != null)
			pathPieces.add(piece);
	}

	public void addPathPiece(String piece) {
		if(piece != null)
			pathPieces.add(new StringMachineStepStringSource(piece));
	}

	@Override
	protected void copyToImpl(CopySlave slave, MachineStep.MachineStepInfo info, String destination)
			throws PuppetException {
		String path = MachineStepStringSource.accumulate(pathPieces, info);
		slave.copyTo(info.getMachine(), path, destination);
	}

}
