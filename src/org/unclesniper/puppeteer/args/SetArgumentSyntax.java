package org.unclesniper.puppeteer.args;

import org.unclesniper.puppeteer.Argument;
import org.unclesniper.puppeteer.ScopeLevel;
import org.unclesniper.puppeteer.PuppetException;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("setArgSyntax")
public class SetArgumentSyntax extends ZeroWidthSyntax {

	private Argument argument;

	private String value;

	public SetArgumentSyntax() {}

	public SetArgumentSyntax(Argument argument, String value) {
		this.argument = argument;
		this.value = value;
	}

	protected SetArgumentSyntax(SetArgumentSyntax setArgument) {
		super(setArgument);
		argument = setArgument.argument;
		value = setArgument.value;
	}

	public Argument getArgument() {
		return argument;
	}

	public void setArgument(Argument argument) {
		this.argument = argument;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	protected Syntax duplicate() {
		return new SetArgumentSyntax(this);
	}

	@Override
	protected void initializeParseImpl(ScopeLevel scope, SetComputationInfo info) {
		if(argument != null)
			argument.initializeValue(scope);
	}

	@Override
	protected void seen(ScopeLevel scope) throws PuppetException {
		if(argument != null && value != null)
			argument.addValue(scope, value);
	}

}
