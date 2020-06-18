package org.unclesniper.puppeteer.args;

import java.util.Map;

public class RepetitionSyntax extends Syntax {

	private Syntax subject;

	private boolean noneOK;

	public RepetitionSyntax() {}

	public RepetitionSyntax(Syntax subject, boolean noneOK) {
		this.subject = subject;
		this.noneOK = noneOK;
	}

	protected RepetitionSyntax(RepetitionSyntax repetition) {
		super(repetition);
		subject = repetition.subject == null ? null : repetition.subject.duplicate();
		noneOK = repetition.noneOK;
	}

	public Syntax getSubject() {
		return subject;
	}

	public void setSubject(Syntax subject) {
		this.subject = subject;
	}

	public boolean isNoneOK() {
		return noneOK;
	}

	public void setNoneOK(boolean noneOK) {
		this.noneOK = noneOK;
	}

	@Override
	protected void computeFirstSetImpl(SetComputationInfo info) throws NullSyntaxException {
		if(subject == null)
			throw new NullSyntaxException("subject");
		computeFirstSetForChild(info, subject, noneOK, true);
	}

	@Override
	protected void computeFollowSetImpl(SetComputationInfo info) throws NullSyntaxException {
		Map<String, Syntax> theirLiterals = subject.followSet.getLiterals();
		for(Map.Entry<String, Syntax> entry : subject.firstSet.getLiterals().entrySet()) {
			String terminal = entry.getKey();
			if(!theirLiterals.containsKey(terminal)) {
				theirLiterals.put(terminal, entry.getValue());
				info.modified = true;
			}
		}
		Syntax sany = subject.firstSet.getAny();
		if(sany != null && subject.followSet.getAny() == null) {
			subject.followSet.setAny(sany);
			info.modified = true;
		}
		computeFollowSetForChild(info, subject, true);
	}

	@Override
	protected void computePathsImpl() throws LL1ityException {
		Syntax send = subject.firstSet.getEnd();
		if(send != null)
			throw new LL1ityException(this, describe()
					+ " is a repetition of a syntax element that can derive the empty string: "
					+ Syntax.makeIncursion(subject, send));
		Map<String, Syntax> myLiterals = followSet.getLiterals();
		for(Map.Entry<String, Syntax> entry : subject.firstSet.getLiterals().entrySet()) {
			String terminal = entry.getKey();
			Syntax followIncurred = myLiterals.get(terminal);
			if(followIncurred != null)
					throw new LL1ityException(this, describe()
							+ " is a repetition of a syntax element deriving a string beginning with '"
							+ terminal + "' (" + Syntax.makePath(entry.getValue(), subject)
							+ "), which is in the FOLLOW set of the enclosing RepetitionSyntax, incurred from "
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
						+ " is a repetition of a syntax element deriving a string beginning with <any> ("
						+ Syntax.makePath(sany, subject)
						+ ") and " + terminal + " is in the FOLLOW set of the enclosing RepetitionSyntax, "
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
						+ " is a repetition of a syntax element deriving a string beginning with "
						+ terminal + " (" + Syntax.makePath(input.getValue(), subject)
						+ ") and <any> is in the FOLLOW set of the enclosing RepetitionSyntax, "
						+ "incurred from " + fany.describe());
			}
		}
		subject.computePaths();
	}

	@Override
	protected Syntax duplicate() {
		return new RepetitionSyntax(this);
	}

}
