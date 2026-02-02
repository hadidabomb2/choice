package financecasestudies.realtimestock;

import java.util.Objects;

public class Controller {
	private final PriceFeed priceFeed;
	private final PortfolioUpdateService updateService;

	public Controller(PriceFeed priceFeed, PortfolioUpdateService updateService) {
		this.priceFeed = Objects.requireNonNull(priceFeed, "priceFeed");
		this.updateService = Objects.requireNonNull(updateService, "updateService");
	}

	public void start() {
		priceFeed.subscribe(updateService);
	}

	public void stop() {
		priceFeed.unsubscribe(updateService);
	}

	public void ingest(PriceUpdate update) {
		Objects.requireNonNull(update, "update");
		priceFeed.publish(update);
	}
}
