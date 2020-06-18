package org.unclesniper.puppeteer.args;

import java.util.Map;

public class OptionSyntax extends Syntax {

	private Syntax subject;

	public OptionSyntax() {}

	public OptionSyntax(Syntax subject) {
		this.subject = subject;
	}

	protected OptionSyntax(OptionSyntax option, int dummy) {
		super(option);
		subject = option.subject == null ? null : option.subject.duplicate();
	}

	public Syntax getSubject() {
		return subject;
	}

	public void setSubject(Syntax subject) {
		this.subject = subject;
	}

	@Override
	protected void computeFirstSetImpl(SetComputationInfo info) throws NullSyntaxException {
		if(subject == null)
			throw new NullSyntaxException("subject");
		computeFirstSetForChild(info, subject, true, true);
	}

	@Override
	protected void computeFollowSetImpl(SetComputationInfo info) throws NullSyntaxException {
		computeFollowSetForChild(info, subject, true);
	}

	@Override
	protected void computePathsImpl() throws LL1ityException {
		Syntax send = subject.firstSet.getEnd();
		if(send != null)
			throw new LL1ityException(this, describe()
					+ " is an option of a syntax element that can derive the empty string: "
					+ Syntax.makeIncursion(subject, send));
		Map<String, Syntax> myLiterals = followSet.getLiterals();
		for(Map.Entry<String, Syntax> entry : subject.firstSet.getLiterals().entrySet()) {
			String terminal = entry.getKey();
			Syntax followIncurred = myLiterals.get(terminal);
			if(followIncurred != null)
				throw new LL1ityException(this, describe()
						+ " is an option of a syntax element deriving a string beginning with '"
						+ terminal + "' (" + Syntax.makePath(entry.getValue(), subject)
						+ "), which is in the FOLLOW set of the enclosing OptionSyntax, incurred from "
						+ followIncurred.describe());
		}
		Syntax sany = subject.firstSet.getAny();
		if(sany != null) {
			Map.Entry<String, Syntax> input = followSet.getAnyInput();
			if(input != null) {
				String terminal = input.getKey();
				if(terminal == null)
					terminal = "<any>";
				else
					terminal = '\'' + terminal + '\'';
				throw new LL1ityException(this, describe()
						+ " is an option of a syntax element deriving a string beginning with <any> ("
						+ Syntax.makePath(sany, subject)
						+ ") and " + terminal + " is in the FOLLOW set of the enclosing OptionSyntax, "
						+ "incurred from " + input.getValue().describe());
			}
		}
		Syntax fany = followSet.getAny();
		if(fany != null) {
			Map.Entry<String, Syntax> input = subject.firstSet.getAnyInput();
			if(input != null) {
				String terminal = input.getKey();
				if(terminal == null)
					terminal = "<any>";
				else
					terminal = '\'' + terminal + '\'';
				throw new LL1ityException(this, describe()
						+ " is an option of a syntax element deriving a string beginning with "
						+ terminal + " (" + Syntax.makePath(input.getValue(), subject)
						+ ") and <any> is in the FOLLOW set of the enclosing OptionSyntax, "
						+ "incurred from " + fany.describe());
			}
		}
		subject.computePaths();
	}

	@Override
	protected Syntax duplicate() {
		return new OptionSyntax(this, 0);
	}

}
