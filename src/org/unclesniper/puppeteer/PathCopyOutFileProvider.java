package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("pathCopyOutFile")
public class PathCopyOutFileProvider extends AbstractCopyOutFileProvider {

	private final List<MachineStepStringSource> pathPieces = new LinkedList<MachineStepStringSource>();

	public PathCopyOutFileProvider() {}

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
	protected void copyFromImpl(CopySlave slave, MachineStep.MachineStepInfo info, String source)
			throws PuppetException {
		String path = MachineStepStringSource.accumulate(pathPieces, info);
		slave.copyFrom(info.getMachine(), source, path);
	}

}
