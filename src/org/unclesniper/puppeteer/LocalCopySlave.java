package org.unclesniper.puppeteer;

import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class LocalCopySlave implements CopySlave {

	public static final LocalCopySlave instance = new LocalCopySlave();

	public LocalCopySlave() {}

	public void copyTo(Machine machine, File source, String destination) throws PuppetException {
		try(FileInputStream fis = new FileInputStream(source)) {
			copyTo(machine, fis, destination);
		}
		catch(IOException ioe) {
			throw new LocalCopyFailedException(destination, true, ioe);
		}
	}

	public void copyTo(Machine machine, InputStream source, String destination) throws PuppetException {
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

	public void copyFrom(Machine machine, String source, File destination) throws PuppetException {
		try(FileOutputStream fos = new FileOutputStream(destination)) {
			copyFrom(machine, source, fos);
		}
		catch(IOException ioe) {
			throw new LocalCopyFailedException(source, false, ioe);
		}
	}

	public void copyFrom(Machine machine, String source, OutputStream destination) throws PuppetException {
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
