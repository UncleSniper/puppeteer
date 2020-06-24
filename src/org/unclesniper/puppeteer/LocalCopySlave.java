package org.unclesniper.puppeteer;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("localCopy")
public class LocalCopySlave extends AbstractCopySlave {

	public static final LocalCopySlave instance = new LocalCopySlave();

	public LocalCopySlave() {}

	@Override
	protected void copyToImpl(Machine machine, String source, String destination) throws PuppetException {
		if(LocalUtils.DEBUG_LOCAL) {
			System.err.println("*** DEBUG: copyTo:");
			System.err.println("***            source: '" + source + '\'');
			System.err.println("***            destination: '" + destination + '\'');
		}
		try(FileInputStream fis = new FileInputStream(source)) {
			copyTo(machine, fis, destination);
		}
		catch(IOException ioe) {
			throw new LocalCopyFailedException(destination, true, ioe);
		}
	}

	@Override
	protected void copyToImpl(Machine machine, InputStream source, String destination) throws PuppetException {
		if(LocalUtils.DEBUG_LOCAL) {
			System.err.println("*** DEBUG: copyTo:");
			System.err.println("***            source is stream");
			System.err.println("***            destination: '" + destination + '\'');
		}
		try(FileOutputStream fos = new FileOutputStream(destination)) {
			byte[] buffer = new byte[128];
			for(;;) {
				int count = source.read(buffer);
				if(count <= 0)
					break;
				fos.write(buffer, 0, count);
			}
		}
		catch(IOException ioe) {
			throw new LocalCopyFailedException(destination, true, ioe);
		}
	}

	@Override
	protected void copyFromImpl(Machine machine, String source, String destination) throws PuppetException {
		if(LocalUtils.DEBUG_LOCAL) {
			System.err.println("*** DEBUG: copyFrom:");
			System.err.println("***            source: '" + source + '\'');
			System.err.println("***            destination: '" + destination + '\'');
		}
		try(FileOutputStream fos = new FileOutputStream(destination)) {
			copyFrom(machine, source, fos);
		}
		catch(IOException ioe) {
			throw new LocalCopyFailedException(source, false, ioe);
		}
	}

	@Override
	protected void copyFromImpl(Machine machine, String source, OutputStream destination) throws PuppetException {
		if(LocalUtils.DEBUG_LOCAL) {
			System.err.println("*** DEBUG: copyFrom:");
			System.err.println("***            source: '" + source + '\'');
			System.err.println("***            destination is stream");
		}
		try(FileInputStream fis = new FileInputStream(source)) {
			byte[] buffer = new byte[128];
			for(;;) {
				int count = fis.read(buffer);
				if(count <= 0)
					break;
				destination.write(buffer, 0, count);
			}
		}
		catch(IOException ioe) {
			throw new LocalCopyFailedException(source, false, ioe);
		}
	}

}
