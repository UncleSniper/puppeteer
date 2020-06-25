package org.unclesniper.puppeteer;

import java.util.function.BiConsumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("networkStepPairMachineStepPair")
public class NetworkStepPairEmitterMachineStepPairEmitter extends AbstractMachineStepPairEmitter {

	private NetworkStepPairEmitter emitter;

	public NetworkStepPairEmitterMachineStepPairEmitter() {}

	public NetworkStepPairEmitterMachineStepPairEmitter(NetworkStepPairEmitter emitter) {
		this.emitter = emitter;
	}

	public NetworkStepPairEmitter getEmitter() {
		return emitter;
	}

	public void setEmitter(NetworkStepPairEmitter emitter) {
		this.emitter = emitter;
	}

	@Override
	protected void buildMapImpl(MachineStep.MachineStepInfo info, BiConsumer<String, String> sink)
			throws PuppetException {
		if(emitter != null)
			emitter.buildMap(info, sink);
	}

}
