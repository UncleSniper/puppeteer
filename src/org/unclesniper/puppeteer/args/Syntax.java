package org.unclesniper.puppeteer.args;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.IdentityHashMap;
import org.unclesniper.puppeteer.Traceable;
import org.unclesniper.puppeteer.AbstractTraceable;

public abstract class Syntax extends AbstractTraceable {

	public static final class SetComputationInfo {

		public boolean modified;

		private final Map<Syntax, Syntax> roots = new IdentityHashMap<Syntax, Syntax>();

		private Map<Syntax, Syntax> cachedRoots;

		public SetComputationInfo() {}

		public boolean addRoot(Syntax root, String property) throws NullSyntaxException {
			if(root == null)
				throw new NullSyntaxException(property);
			if(roots.containsKey(root))
				return false;
			roots.put(root, root);
			cachedRoots = null;
			modified = true;
			return true;
		}

		public Set<Syntax> getRoots() {
			if(cachedRoots == null)
				cachedRoots = new IdentityHashMap<Syntax, Syntax>(roots);
			return Collections.unmodifiableSet(cachedRoots.keySet());
		}

	}

	public static final class FirstFollowSet {

		private final Map<String, Syntax> literals = new HashMap<String, Syntax>();

		private Syntax end;

		private Syntax any;

		public FirstFollowSet() {}

		public Map<String, Syntax> getLiterals() {
			return literals;
		}

		public Syntax getEnd() {
			return end;
		}

		public void setEnd(Syntax end) {
			this.end = end;
		}

		public Syntax getAny() {
			return any;
		}

		public void setAny(Syntax any) {
			this.any = any;
		}

		public Map.Entry<String, Syntax> getAnyLiteral() {
			return literals.isEmpty() ? null : literals.entrySet().iterator().next();
		}

		public Map.Entry<String, Syntax> getAnyInput() {
			if(any != null)
				return new AbstractMap.SimpleEntry<String, Syntax>(null, any);
			return getAnyLiteral();
		}

	}

	protected final FirstFollowSet firstSet = new FirstFollowSet();

	protected final FirstFollowSet followSet = new FirstFollowSet();

	public Syntax() {}

	protected Syntax(Syntax syntax) {
		super(syntax);
	}

	public FirstFollowSet getFirstSet() {
		return firstSet;
	}

	protected final void computeFirstSet(SetComputationInfo info) throws NullSyntaxException {
		try {
			computeFirstSetImpl(info);
		}
		catch(NullSyntaxException nse) {
			nse.addPuppetFrame(this);
			throw nse;
		}
	}

	protected abstract void computeFirstSetImpl(SetComputationInfo info) throws NullSyntaxException;

	protected final void computeFirstSetForChild(SetComputationInfo info, Syntax child, boolean epsilon,
			boolean follow) throws NullSyntaxException {
		if(follow)
			child.computeFirstSet(info);
		Map<String, Syntax> myLiterals = firstSet.getLiterals();
		for(Map.Entry<String, Syntax> entry : child.firstSet.getLiterals().entrySet()) {
			String terminal = entry.getKey();
			if(!myLiterals.containsKey(terminal)) {
				myLiterals.put(terminal, entry.getValue());
				info.modified = true;
			}
		}
		Syntax cend = child.firstSet.getEnd();
		if((epsilon || cend != null) && firstSet.getEnd() == null) {
			firstSet.setEnd(cend == null ? this : cend);
			info.modified = true;
		}
		Syntax cany = child.firstSet.getAny();
		if(cany != null && firstSet.getAny() == null) {
			firstSet.setAny(cany);
			info.modified = true;
		}
	}

	protected final void computeFollowSet(SetComputationInfo info) throws NullSyntaxException {
		try {
			computeFollowSetImpl(info);
		}
		catch(NullSyntaxException nse) {
			nse.addPuppetFrame(this);
			throw nse;
		}
	}

	protected abstract void computeFollowSetImpl(SetComputationInfo info) throws NullSyntaxException;

	protected final void computeFollowSetForChild(SetComputationInfo info, Syntax child, boolean follow)
			throws NullSyntaxException {
		Map<String, Syntax> theirLiterals = child.followSet.getLiterals();
		for(Map.Entry<String, Syntax> entry : followSet.getLiterals().entrySet()) {
			String terminal = entry.getKey();
			if(!theirLiterals.containsKey(terminal)) {
				theirLiterals.put(terminal, entry.getValue());
				info.modified = true;
			}
		}
		Syntax mend = followSet.getEnd();
		if(mend != null && child.followSet.getEnd() == null) {
			child.followSet.setEnd(mend);
			info.modified = true;
		}
		Syntax many = followSet.getAny();
		if(many != null && child.followSet.getAny() == null) {
			child.followSet.setAny(many);
			info.modified = true;
		}
		if(follow)
			child.computeFollowSet(info);
	}

	protected final void computePaths() throws LL1ityException {
		try {
			computePathsImpl();
		}
		catch(LL1ityException le) {
			le.addPuppetFrame(this);
			throw le;
		}
	}

	protected abstract void computePathsImpl() throws LL1ityException;

	protected abstract Syntax duplicate();

	public String getSimpleClassName() {
		String name = getClass().getName();
		int pos = name.lastIndexOf('.');
		return pos < 0 ? name : name.substring(pos + 1);
	}

	public String describe() {
		return getSimpleClassName() + Traceable.makeLocation(this, " defined at ", "", "");
	}

	protected static String makePath(Syntax direct, Syntax via) {
		return via == direct ? direct.describe() : direct.describe() + " (via " + via.describe() + ')';
	}

	protected static String makeIncursion(Syntax offender, Syntax from) {
		return offender == from
				? offender.describe()
				: offender.describe() + " (incurred from " + from.describe() + ')';
	}

}
