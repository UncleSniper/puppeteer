package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import java.util.regex.Pattern;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("print")
public class PrintStep extends AbstractStep {

	private static final Pattern SPLIT_LINES = Pattern.compile("\\r?\\n");

	private MessageLevel level;

	private final List<StepStringSource> pieces = new LinkedList<StepStringSource>();

	public PrintStep() {}

	public MessageLevel getLevel() {
		return level;
	}

	public void setLevel(MessageLevel level) {
		this.level = level;
	}

	public void addPiece(StepStringSource piece) {
		if(piece != null)
			pieces.add(piece);
	}

	public void addPiece(String piece) {
		if(piece == null)
			return;
		StringStepStringSource string = new StringStepStringSource(piece);
		string.ingestObjectDefinitionLocation(this);
		pieces.add(string);
	}

	@Override
	protected void performImpl(StepInfo info) throws PuppetException {
		PrintStep.doPrint(this, info, level, StepStringSource.accumulate(pieces, info));
	}

	public static void doPrint(Object emitter, StepInfo info, MessageLevel level, String text) {
		if(text == null)
			return;
		String[] message = PrintStep.SPLIT_LINES.split(text);
		PuppeteerUI ui = info.getUI();
		switch(level == null ? MessageLevel.INFO : level) {
			case VERBOSE:
				ui.info(emitter, message, true);
				break;
			case INFO:
				ui.info(emitter, message, false);
				break;
			case WARN:
				ui.warn(emitter, message);
				break;
			default:
				throw new Doom("Unrecognized MessageLevel: " + level.name());
		}
	}

}
