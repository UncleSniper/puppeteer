package org.unclesniper.puppeteer;

import java.util.function.BiConsumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("stepPairMachineStepPair")
public class StepPairEmitterMachineStepPairEmitter extends AbstractMachineStepPairEmitter {

	private StepPairEmitter emitter;

	public StepPairEmitterMachineStepPairEmitter() {}

	public StepPairEmitterMachineStepPairEmitter(StepPairEmitter emitter) {
		this.emitter = emitter;
	}

	public StepPairEmitter getEmitter() {
		return emitter;
	}

	public void setEmitter(StepPairEmitter emitter) {
		this.emitter = emitter;
	}

	@Override
	protected void buildMapImpl(MachineStep.MachineStepInfo info, BiConsumer<String, String> sink)
			throws PuppetException {
		if(emitter != null)
			emitter.buildMap(info, sink);
	}

}
