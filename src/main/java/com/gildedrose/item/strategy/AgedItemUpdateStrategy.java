package com.gildedrose.item.strategy;

import com.gildedrose.item.EnhancedItem;

public class AgedItemUpdateStrategy implements ItemUpdateStrategy {

    private final static AgedItemUpdateStrategy SINGLETON = new AgedItemUpdateStrategy();

    private AgedItemUpdateStrategy() {
    }

    @Override
    public void updateItem(EnhancedItem item) {
        item.tryIncreaseQuality();

        item.decreaseSellIn();

        if (item.hasSellDatePassed()) {
            item.tryIncreaseQuality();
        }
    }

    public static AgedItemUpdateStrategy getInstance() {
        return SINGLETON;
    }
}
