package org.unclesniper.puppeteer;

import java.io.InputStream;
import java.io.OutputStream;

public interface CopySlave extends Traceable {

	public final class CopyToInfo {

		public final Machine machine;

		public final Machine execHost;

		public final InFile source;

		public final String destination;

		public CopyToInfo(Machine machine, Machine execHost, InFile source, String destination) {
			this.machine = machine;
			this.execHost = execHost;
			this.source = source;
			this.destination = destination;
		}

	}

	public final class CopyFromInfo {

		public final Machine machine;

		public final Machine execHost;

		public final String source;

		public final OutFile destination;

		public CopyFromInfo(Machine machine, Machine execHost, String source, OutFile destination) {
			this.machine = machine;
			this.execHost = execHost;
			this.source = source;
			this.destination = destination;
		}

	}

	void copyTo(Machine machine, String source, String destination) throws PuppetException;

	void copyTo(Machine machine, InputStream source, String destination) throws PuppetException;

	void copyFrom(Machine machine, String source, String destination) throws PuppetException;

	void copyFrom(Machine machine, String source, OutputStream destination) throws PuppetException;

}
