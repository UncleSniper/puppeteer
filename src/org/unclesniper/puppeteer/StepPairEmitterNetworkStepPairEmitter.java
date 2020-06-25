package org.unclesniper.puppeteer;

import java.util.function.BiConsumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("stepPairNetworkStepPair")
public class StepPairEmitterNetworkStepPairEmitter extends AbstractNetworkStepPairEmitter {

	private StepPairEmitter emitter;

	public StepPairEmitterNetworkStepPairEmitter() {}

	public StepPairEmitterNetworkStepPairEmitter(StepPairEmitter emitter) {
		this.emitter = emitter;
	}

	public StepPairEmitter getEmitter() {
		return emitter;
	}

	public void setEmitter(StepPairEmitter emitter) {
		this.emitter = emitter;
	}

	@Override
	protected void buildMapImpl(NetworkStep.NetworkStepInfo info, BiConsumer<String, String> sink)
			throws PuppetException {
		if(emitter != null)
			emitter.buildMap(info, sink);
	}

}
