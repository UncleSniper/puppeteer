package org.unclesniper.puppeteer.args;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;

public class AlternativeSyntax extends Syntax {

	private final List<Syntax> choices = new LinkedList<Syntax>();

	private final Map<String, Syntax> pathMap = new HashMap<String, Syntax>();

	private Syntax anyChoice;

	public AlternativeSyntax() {}

	protected AlternativeSyntax(AlternativeSyntax alternative) {
		super(alternative);
		for(Syntax choice : alternative.choices)
			choices.add(choice.duplicate());
	}

	public Iterable<Syntax> getChoices() {
		return choices;
	}

	public void addChoice(Syntax choice) {
		if(choice != null)
			choices.add(choice);
	}

	@Override
	protected void computeFirstSetImpl(SetComputationInfo info) throws NullSyntaxException {
		if(choices.isEmpty())
			throw new NullSyntaxException("choice");
		for(Syntax choice : choices)
			computeFirstSetForChild(info, choice, false, true);
	}

	@Override
	protected void computeFollowSetImpl(SetComputationInfo info) throws NullSyntaxException {
		for(Syntax choice : choices)
			computeFollowSetForChild(info, choice, true);
	}

	@Override
	protected void computePathsImpl() throws LL1ityException {
		Map<String, Syntax> locations = new HashMap<String, Syntax>();
		Syntax endDirect = null, endVia = null;
		Syntax anyDirect = null;
		Map.Entry<String, Syntax> anyLiteral = null;
		Syntax anyLiteralChoice = null;
		for(Syntax choice : choices) {
			for(Map.Entry<String, Syntax> entry : choice.firstSet.getLiterals().entrySet()) {
				String terminal = entry.getKey();
				Syntax other = pathMap.get(terminal);
				if(other != null)
					throw new LL1ityException(this, describe() + " contains multiple paths for FIRST terminal '"
							+ terminal + "'; I detected " + Syntax.makePath(locations.get(terminal), other)
							+ " and " + Syntax.makePath(entry.getValue(), choice));
				pathMap.put(terminal, choice);
				locations.put(terminal, entry.getValue());
				anyLiteral = entry;
				anyLiteralChoice = choice;
			}
			Syntax cend = choice.firstSet.getEnd();
			if(cend != null) {
				if(endDirect != null)
					throw new LL1ityException(this, describe()
							+ " contains multiple paths for FIRST terminal <epsilon>; I detected "
							+ Syntax.makePath(endDirect, endVia) + " and " + Syntax.makePath(cend, choice));
				endDirect = cend;
				endVia = choice;
			}
			Syntax cany = choice.firstSet.getAny();
			if(cany != null) {
				if(anyDirect != null)
					throw new LL1ityException(this, describe()
							+ " contains multiple paths for FIRST terminal <any>; I detected "
							+ Syntax.makePath(anyDirect, anyChoice) + " and " + Syntax.makePath(cany, choice));
				anyDirect = cany;
				anyChoice = choice;
			}
			choice.computePaths();
		}
		if(anyDirect != null && anyLiteral != null)
			throw new LL1ityException(this, describe() + " contains a path for FIRST terminal <any> ("
					+ Syntax.makePath(anyDirect, anyChoice) + ") and one for '" + anyLiteral.getKey()
					+ "' (" + Syntax.makePath(anyLiteral.getValue(), anyLiteralChoice) + ')');
		if(endDirect != null) {
			Map<String, Syntax> myFollowLiterals = followSet.getLiterals();
			Map.Entry<String, Syntax> anyInput = null;
			Syntax anyInputChoice = null;
			for(Syntax choice : choices) {
				if(choice.firstSet.getEnd() == null) {
					for(Map.Entry<String, Syntax> entry : choice.firstSet.getLiterals().entrySet()) {
						String terminal = entry.getKey();
						Syntax followIncurred = myFollowLiterals.get(terminal);
						if(followIncurred != null)
							throw new LL1ityException(this, describe()
									+ " contains a path deriving the empty string ("
									+ Syntax.makePath(endDirect, endVia)
									+ ") and one deriving a string beginning with '" + terminal + "' ("
									+ Syntax.makePath(entry.getValue(), choice)
									+ "), which is in the FOLLOW set of the enclosing AlternativeSyntax, "
									+ "incurred from " + followIncurred.describe());
					}
					Map.Entry<String, Syntax> input = choice.firstSet.getAnyInput();
					if(input != null) {
						anyInput = input;
						anyInputChoice = choice;
					}
				}
			}
			Syntax followIncurred = followSet.getAny();
			if(followIncurred != null && anyInput != null) {
				String terminal = anyInput.getKey();
				if(terminal == null)
					terminal = "<any>";
				else
					terminal = '\'' + terminal + '\'';
				throw new LL1ityException(this, describe()
						+ " contains a path deriving the empty string ("
						+ Syntax.makePath(endDirect, endVia)
						+ ") and one deriving a string beginning with " + terminal + " ("
						+ Syntax.makePath(anyInput.getValue(), anyInputChoice)
						+ "), and <any> is in the FOLLOW set of the enclosing AlternativeSyntax, "
						+ "incurred from " + followIncurred.describe());
			}
		}
	}

	@Override
	protected Syntax duplicate() {
		return new AlternativeSyntax(this);
	}

}
