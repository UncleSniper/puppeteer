package org.unclesniper.puppeteer;

import java.util.List;
import java.util.LinkedList;
import org.unclesniper.puppeteer.args.Syntax;
import org.unclesniper.puppeteer.args.SequenceSyntax;
import org.unclesniper.puppeteer.args.ArgumentSource;
import org.unclesniper.puppeteer.args.ArgumentSyntaxException;

public class Plan extends AbstractTraceable {

	private final List<Step> steps = new LinkedList<Step>();

	private Syntax arguments;

	private boolean compiled;

	public Plan() {}

	public Iterable<Step> getSteps() {
		return steps;
	}

	public void addStep(Step step) {
		if(step != null)
			steps.add(step);
	}

	public Syntax getArguments() {
		return arguments;
	}

	public void setArguments(Syntax arguments) {
		this.arguments = arguments;
	}

	public void perform(PuppeteerUI ui, World world, ScopeLevel scope, ArgumentSource args) throws PuppetException {
		if(scope == null)
			scope = new ScopeLevel(null);
		perform(new Step.StepInfo(ui, world, scope), args);
	}

	public void perform(Step.StepInfo info, ArgumentSource args) throws PuppetException {
		try {
			compile();
			ScopeLevel scope = info.getScope();
			arguments.initializeParse(scope);
			arguments.parse(scope, args);
			String token = args.current();
			if(token != null)
				throw new ArgumentSyntaxException(ArgumentSyntaxException.expect(null, true),
						token, args.location());
			for(Step step : steps)
				step.perform(info);
		}
		catch(PuppetException pe) {
			pe.addPuppetFrame(this);
			throw pe;
		}
	}

	public void compile() throws PuppetException {
		if(compiled)
			return;
		if(arguments == null) {
			arguments = new SequenceSyntax();
			arguments.ingestObjectDefinitionLocation(this);
		}
		arguments = arguments.compile();
		compiled = true;
	}

}
