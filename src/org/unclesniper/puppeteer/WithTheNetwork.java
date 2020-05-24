package org.unclesniper.puppeteer;

public class WithTheNetwork extends AbstractWithAnyNetwork {

	public WithTheNetwork() {}

	@Override
	public void perform(StepInfo info) throws PuppetException {
		NetworkPredicate predicate = getThePredicate();
		Iterable<NetworkStep> steps = getSteps();
		try {
			int networkCount = 0 ;
			Network theNetwork = null;
			NetworkStep.NetworkStepInfo ninfo = new NetworkStep.NetworkStepInfo(info);
			for(Network network : info.getWorld().getNetworks()) {
				ninfo.setNetwork(network);
				if(!predicate.test(ninfo))
					continue;
				theNetwork = network;
				++networkCount;
			}
			if(networkCount != 1)
				throw new AmbiguousNetworkException(networkCount);
			ninfo.setNetwork(theNetwork);
			for(NetworkStep step : steps)
				step.perform(ninfo);
		}
		catch(PuppetException pe) {
			GeneralStep.addFrame(pe, this);
			throw pe;
		}
	}

}
