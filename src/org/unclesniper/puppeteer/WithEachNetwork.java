package org.unclesniper.puppeteer;

public class WithEachNetwork extends AbstractWithAnyNetwork {

	private boolean requiresAtLeastOne;

	public WithEachNetwork() {}

	public boolean isRequiresAtLeastOne() {
		return requiresAtLeastOne;
	}

	public void setRequiresAtLeastOne(boolean requiresAtLeastOne) {
		this.requiresAtLeastOne = requiresAtLeastOne;
	}

	@Override
	public void perform(StepInfo info) throws PuppetException {
		NetworkPredicate predicate = getThePredicate();
		Iterable<NetworkStep> steps = getSteps();
		try {
			boolean hasOne = false;
			NetworkStep.NetworkStepInfo ninfo = new NetworkStep.NetworkStepInfo(info);
			for(Network network : info.getWorld().getNetworks()) {
				ninfo.setNetwork(network);
				if(!predicate.test(ninfo))
					continue;
				hasOne = true;
				for(NetworkStep step : steps)
					step.perform(ninfo);
			}
			if(!hasOne && requiresAtLeastOne)
				throw new NoMatchingNetworkException();
		}
		catch(PuppetException pe) {
			GeneralStep.addFrame(pe, this);
			throw pe;
		}
	}

}
