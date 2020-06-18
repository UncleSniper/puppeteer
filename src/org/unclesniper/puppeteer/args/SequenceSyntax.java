package org.unclesniper.puppeteer.args;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;
import org.unclesniper.puppeteer.ScopeLevel;
import org.unclesniper.puppeteer.PuppetException;

public class SequenceSyntax extends Syntax {

	private final List<Syntax> children = new LinkedList<Syntax>();

	public SequenceSyntax() {}

	protected SequenceSyntax(SequenceSyntax sequence) {
		super(sequence);
		for(Syntax child : sequence.children)
			children.add(child.duplicate());
	}

	public Iterable<Syntax> getChildren() {
		return children;
	}

	public void addChild(Syntax child) {
		if(child != null)
			children.add(child);
	}

	@Override
	protected void computeFirstSetImpl(SetComputationInfo info) throws NullSyntaxException {
		boolean seenDefinite = false;
		Map<String, Syntax> myLiterals = firstSet.getLiterals();
		for(Syntax child : children) {
			child.computeFirstSet(info);
			if(!seenDefinite) {
				if(child.firstSet.getEnd() == null)
					seenDefinite = true;
				for(Map.Entry<String, Syntax> entry : child.firstSet.getLiterals().entrySet()) {
					String terminal = entry.getKey();
					if(!myLiterals.containsKey(terminal)) {
						myLiterals.put(terminal, entry.getValue());
						info.modified = true;
					}
				}
				Syntax cany = child.firstSet.getAny();
				if(cany != null && firstSet.getAny() == null) {
					firstSet.setAny(cany);
					info.modified = true;
				}
			}
		}
		if(!seenDefinite && firstSet.getEnd() == null) {
			firstSet.setEnd(this);
			info.modified = true;
		}
	}

	@Override
	protected void computeFollowSetImpl(SetComputationInfo info) throws NullSyntaxException {
		int index = 0, j, size = children.size();
		Map<String, Syntax> mfollow = followSet.getLiterals();
		Syntax many = followSet.getAny();
		Syntax mend = followSet.getEnd();
		for(Syntax child : children) {
			Map<String, Syntax> firstBeta = new HashMap<String, Syntax>();
			Syntax anyBeta = null;
			for(j = index + 1; j < size; ++j) {
				Syntax beta = children.get(j);
				for(Map.Entry<String, Syntax> entry : beta.firstSet.getLiterals().entrySet()) {
					String terminal = entry.getKey();
					if(!firstBeta.containsKey(terminal))
						firstBeta.put(terminal, entry.getValue());
				}
				Syntax any = beta.firstSet.getAny();
				if(any != null && anyBeta == null)
					anyBeta = any;
				if(beta.firstSet.getEnd() == null)
					break;
			}
			Map<String, Syntax> cfollow = child.followSet.getLiterals();
			for(Map.Entry<String, Syntax> entry : firstBeta.entrySet()) {
				String terminal = entry.getKey();
				if(!cfollow.containsKey(terminal)) {
					cfollow.put(terminal, entry.getValue());
					info.modified = true;
				}
			}
			if(anyBeta != null && child.followSet.getAny() == null) {
				child.followSet.setAny(anyBeta);
				info.modified = true;
			}
			if(j == size) {
				for(Map.Entry<String, Syntax> entry : mfollow.entrySet()) {
					String terminal = entry.getKey();
					if(!cfollow.containsKey(terminal)) {
						cfollow.put(terminal, entry.getValue());
						info.modified = true;
					}
				}
				if(mend != null && child.followSet.getEnd() == null) {
					child.followSet.setEnd(mend);
					info.modified = true;
				}
				if(many != null && child.followSet.getAny() == null) {
					child.followSet.setAny(many);
					info.modified = true;
				}
			}
			child.computeFollowSet(info);
			++index;
		}
	}

	@Override
	protected void computePathsImpl() throws LL1ityException {
		for(Syntax child : children)
			child.computePaths();
	}

	@Override
	protected Syntax duplicate() {
		return new SequenceSyntax(this);
	}

	@Override
	protected void parseImpl(ScopeLevel scope, ArgumentSource source) throws PuppetException {
		for(Syntax child : children)
			child.parse(scope, source);
	}

}
