package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("pathCopyInFile")
public class PathCopyInFileProvider extends AbstractCopyInFileProvider {

	private final List<MachineStepStringSource> pathPieces = new LinkedList<MachineStepStringSource>();

	public PathCopyInFileProvider() {}

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
	protected void copyToImpl(CopySlave slave, MachineStep.MachineStepInfo info, String destination)
			throws PuppetException {
		String path = MachineStepStringSource.accumulate(pathPieces, info);
		slave.copyTo(info.getMachine(), path, destination);
	}

}
