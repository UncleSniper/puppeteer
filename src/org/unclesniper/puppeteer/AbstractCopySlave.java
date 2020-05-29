package org.unclesniper.puppeteer;

import java.io.InputStream;
import java.io.OutputStream;

public abstract class AbstractCopySlave extends AbstractTraceable implements CopySlave {

	public AbstractCopySlave() {}

	protected abstract void copyToImpl(Machine machine, String source, String destination) throws PuppetException;

	@Override
	public void copyTo(Machine machine, String source, String destination) throws PuppetException {
		try {
			copyToImpl(machine, source, destination);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	protected abstract void copyToImpl(Machine machine, InputStream source, String destination)
			throws PuppetException;

	@Override
	public void copyTo(Machine machine, InputStream source, String destination) throws PuppetException {
		try {
			copyToImpl(machine, source, destination);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	protected abstract void copyFromImpl(Machine machine, String source, String destination)
			throws PuppetException;

	@Override
	public void copyFrom(Machine machine, String source, String destination) throws PuppetException {
		try {
			copyFromImpl(machine, source, destination);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	protected abstract void copyFromImpl(Machine machine, String source, OutputStream destination)
			throws PuppetException;

	@Override
	public void copyFrom(Machine machine, String source, OutputStream destination) throws PuppetException {
		try {
			copyFromImpl(machine, source, destination);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

}
