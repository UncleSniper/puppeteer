package org.unclesniper.puppeteer;

import java.util.function.Consumer;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("stepWordMachineStepWord")
public class StepWordEmitterMachineStepWordEmitter extends AbstractMachineStepWordEmitter {

	private StepWordEmitter emitter;

	public StepWordEmitterMachineStepWordEmitter() {}

	public StepWordEmitterMachineStepWordEmitter(StepWordEmitter emitter) {
		this.emitter = emitter;
	}

	public StepWordEmitter getEmitter() {
		return emitter;
	}

	public void setEmitter(StepWordEmitter emitter) {
		this.emitter = emitter;
	}

	@Override
	protected void buildArgvImpl(MachineStep.MachineStepInfo info, Consumer<String> sink) throws PuppetException {
		if(emitter != null)
			emitter.buildArgv(info, sink);
	}

}
