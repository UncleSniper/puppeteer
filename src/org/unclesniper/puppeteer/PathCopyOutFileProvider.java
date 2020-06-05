package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;

public class PathCopyOutFileProvider extends AbstractCopyOutFileProvider {

	private final List<MachineStepStringSource> pathPieces = new LinkedList<MachineStepStringSource>();

	public PathCopyOutFileProvider() {}

	public void addPathPiece(MachineStepStringSource piece) {
		if(piece != null)
			pathPieces.add(piece);
	}

	public void addPathPiece(String piece) {
		if(piece != null)
			pathPieces.add(new StringMachineStepStringSource(piece));
	}

	@Override
	protected void copyFromImpl(CopySlave slave, MachineStep.MachineStepInfo info, String source)
			throws PuppetException {
		String path = MachineStepStringSource.accumulate(pathPieces, info);
		slave.copyFrom(info.getMachine(), source, path);
	}

}
