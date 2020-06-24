package org.unclesniper.puppeteer.args;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;
import org.unclesniper.puppeteer.ScopeLevel;
import org.unclesniper.puppeteer.PuppetException;
import org.unclesniper.puppeteer.util.ShorthandName;

@ShorthandName("alternativeSyntax")
public class AlternativeSyntax extends Syntax {

	private final List<Syntax> choices = new LinkedList<Syntax>();

	private final Map<String, Syntax> pathMap = new HashMap<String, Syntax>();

	private Syntax anyChoice;

	private Syntax endChoice;

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
		Syntax endDirect = null;
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
							+ Syntax.makePath(endDirect, endChoice) + " and " + Syntax.makePath(cend, choice));
				endDirect = cend;
				endChoice = choice;
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
									+ Syntax.makePath(endDirect, endChoice)
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
						+ Syntax.makePath(endDirect, endChoice)
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

	@Override
	protected void parseImpl(ScopeLevel scope, ArgumentSource source) throws PuppetException {
		String token = source.current();
		if(token == null) {
			if(endChoice == null)
				throw new ArgumentSyntaxException(anyChoice == null
						? ArgumentSyntaxException.expect(pathMap.keySet(), false) : "argument",
						null, source.location());
			endChoice.parse(scope, source);
		}
		else if(anyChoice != null)
			anyChoice.parse(scope, source);
		else {
			Syntax choice = pathMap.get(token);
			if(choice == null)
				throw new ArgumentSyntaxException(ArgumentSyntaxException.expect(pathMap.keySet(),
						endChoice != null), token, source.location());
			choice.parse(scope, source);
		}
	}

	@Override
	protected void initializeParseImpl(ScopeLevel scope, SetComputationInfo info) {
		for(Syntax choice : choices)
			choice.initializeParse(scope, info);
	}

}
