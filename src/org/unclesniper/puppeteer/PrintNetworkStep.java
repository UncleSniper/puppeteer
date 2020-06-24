package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("networkPrint")
public class PrintNetworkStep extends AbstractNetworkStep {

	private MessageLevel level;

	private final List<NetworkStepStringSource> pieces = new LinkedList<NetworkStepStringSource>();

	public PrintNetworkStep() {}

	public MessageLevel getLevel() {
		return level;
	}

	public void setLevel(MessageLevel level) {
		this.level = level;
	}

	public void addPiece(NetworkStepStringSource piece) {
		if(piece != null)
			pieces.add(piece);
	}

	public void addPiece(StepStringSource piece) {
		if(piece != null)
			pieces.add(new StepStringSourceNetworkStepStringSource(piece));
	}

	public void addPiece(String piece) {
		if(piece != null)
			pieces.add(new StringNetworkStepStringSource(piece));
	}

	@Override
	protected void performImpl(NetworkStepInfo info) throws PuppetException {
		PrintStep.doPrint(this, info, level, NetworkStepStringSource.accumulate(pieces, info));
	}

}
