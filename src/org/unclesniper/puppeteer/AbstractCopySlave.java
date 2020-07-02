package org.unclesniper.puppeteer;

import java.io.InputStream;
import java.io.OutputStream;

public abstract class AbstractCopySlave extends AbstractTraceable implements CopySlave {

	public AbstractCopySlave() {}

	protected abstract void copyToImpl(MachineStep.MachineStepInfo stepInfo, Machine machine, String source,
			String destination) throws PuppetException;

	@Override
	public void copyTo(MachineStep.MachineStepInfo stepInfo, Machine machine, String source, String destination)
			throws PuppetException {
		try {
			copyToImpl(stepInfo, machine, source, destination);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	protected abstract void copyToImpl(MachineStep.MachineStepInfo stepInfo, Machine machine, InputStream source,
			String destination) throws PuppetException;

	@Override
	public void copyTo(MachineStep.MachineStepInfo stepInfo, Machine machine, InputStream source,
			String destination) throws PuppetException {
		try {
			copyToImpl(stepInfo, machine, source, destination);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	protected abstract void copyFromImpl(MachineStep.MachineStepInfo stepInfo, Machine machine, String source,
			String destination) throws PuppetException;

	@Override
	public void copyFrom(MachineStep.MachineStepInfo stepInfo, Machine machine, String source,
			String destination) throws PuppetException {
		try {
			copyFromImpl(stepInfo, machine, source, destination);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	protected abstract void copyFromImpl(MachineStep.MachineStepInfo stepInfo, Machine machine, String source,
			OutputStream destination) throws PuppetException;

	@Override
	public void copyFrom(MachineStep.MachineStepInfo stepInfo, Machine machine, String source,
			OutputStream destination) throws PuppetException {
		try {
			copyFromImpl(stepInfo, machine, source, destination);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
