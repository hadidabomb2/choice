package financecasestudies.realtimestock;

public class PortfolioUpdateService implements PriceListener {
    private final PriceCache priceCache;
    private final PortfolioRepository repository;
    private final PortfolioValuationService valuationService;

    public PortfolioUpdateService(
        PriceCache priceCache,
        PortfolioRepository repository,
        PortfolioValuationService valuationService
    ) {
        this.priceCache = java.util.Objects.requireNonNull(priceCache, "priceCache");
        this.repository = java.util.Objects.requireNonNull(repository, "repository");
        this.valuationService = java.util.Objects.requireNonNull(valuationService, "valuationService");
    }

    @Override
    public void onPrice(PriceUpdate update) {
        java.util.Objects.requireNonNull(update, "update");
        priceCache.put(update);
        for (Portfolio portfolio : repository.listAll()) {
            double value = valuationService.calculate(portfolio);
            portfolio.setLastValue(value);
            repository.save(portfolio);
        }
    }
}
