package org.unclesniper.puppeteer;

import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("networkStepWordMachineStepWord")
public class NetworkStepWordEmitterMachineStepWordEmitter extends AbstractMachineStepWordEmitter {

	private NetworkStepWordEmitter emitter;

	public NetworkStepWordEmitterMachineStepWordEmitter() {}

	public NetworkStepWordEmitterMachineStepWordEmitter(NetworkStepWordEmitter emitter) {
		this.emitter = emitter;
	}

	public NetworkStepWordEmitter getEmitter() {
		return emitter;
	}

	public void setEmitter(NetworkStepWordEmitter emitter) {
		this.emitter = emitter;
	}

	@Override
	protected void buildArgvImpl(MachineStep.MachineStepInfo info, Consumer<String> sink) throws PuppetException {
		if(emitter != null)
			emitter.buildArgv(info, sink);
	}

}
