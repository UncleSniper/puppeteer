package org.unclesniper.puppeteer;

public interface ExecPredicate extends Traceable, StructPrintable {

	boolean test(ExecSlave.ExecInfo info) throws PuppetException;

}
