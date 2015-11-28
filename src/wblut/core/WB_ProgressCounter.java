package wblut.core;

public class WB_ProgressCounter {
	protected volatile int count;
	protected volatile String caller;
	protected volatile String text;

	private int limit;
	private int percentageStep;
	private volatile int currentPercentage;
	private volatile int nextUpdate;

	static WB_ProgressTracker tracker = WB_ProgressTracker.instance();

	public WB_ProgressCounter(int limit, int percentageStep) {
		this.count = 0;
		this.limit = limit;
		this.percentageStep = percentageStep;
		if (percentageStep < 0)
			this.percentageStep = 10;
		if (percentageStep > 100)
			this.percentageStep = 10;
		currentPercentage = 0;
		nextUpdate = (int) (limit * 0.01 * (currentPercentage + percentageStep));
		caller = null;
		text = null;

	}

	public void increment() {
		increment(1);
	}

	public void increment(int inc) {
		count += inc;
		if (count >= nextUpdate) {
			while (nextUpdate <= count) {
				currentPercentage += percentageStep;
				nextUpdate = (int) (limit * 0.01 * (currentPercentage + percentageStep));
			}
			tracker.setStatusByString(caller, text, this);
		} else if (count >= limit)
			tracker.setStatusByString(caller, text, this);
	}

	protected int getCount() {

		return count;
	}

	protected int getLimit() {
		return limit;
	}

}