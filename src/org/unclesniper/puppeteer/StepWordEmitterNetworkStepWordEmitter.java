package org.unclesniper.puppeteer;

import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("stepWordNetworkStepWord")
public class StepWordEmitterNetworkStepWordEmitter extends AbstractNetworkStepWordEmitter {

	private StepWordEmitter emitter;

	public StepWordEmitterNetworkStepWordEmitter() {}

	public StepWordEmitterNetworkStepWordEmitter(StepWordEmitter emitter) {
		this.emitter = emitter;
	}

	public StepWordEmitter getEmitter() {
		return emitter;
	}

	public void setEmitter(StepWordEmitter emitter) {
		this.emitter = emitter;
	}

	@Override
	protected void buildArgvImpl(NetworkStep.NetworkStepInfo info, Consumer<String> sink) throws PuppetException {
		if(emitter != null)
			emitter.buildArgv(info, sink);
	}

}
