package com.gildedrose.item.strategy;

import com.gildedrose.item.EnhancedItem;

public class BackstagePassItemUpdateStrategy implements ItemUpdateStrategy {
    private static final BackstagePassItemUpdateStrategy SINGLETON = new BackstagePassItemUpdateStrategy();

    private BackstagePassItemUpdateStrategy() {}

    public static BackstagePassItemUpdateStrategy getInstance() {
        return SINGLETON;
    }

    @Override
    public void updateItem(EnhancedItem item) {
        item.tryIncreaseQuality();

        if (item.getSellIn() < 11) { // TODO a new EnhancedItem concept?
            item.tryIncreaseQuality();
        }

        if (item.getSellIn() < 6) {
            item.tryIncreaseQuality();
        }

        item.decreaseSellIn();

        if (item.hasSellDatePassed()) {
            item.dropQualityToZero();
        }
    }
}
