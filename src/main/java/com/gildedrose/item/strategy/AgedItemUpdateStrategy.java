package com.gildedrose.item.strategy;

import com.gildedrose.item.EnhancedItem;

public final class AgedItemUpdateStrategy implements ItemUpdateStrategy {

    private final static AgedItemUpdateStrategy SINGLETON = new AgedItemUpdateStrategy();

    private AgedItemUpdateStrategy() {
    }

    @Override
    public int calculateQualityDelta(EnhancedItem item) {
        return item.hasSellDatePassed() ? 2 : 1;
    }

    public static AgedItemUpdateStrategy getInstance() {
        return SINGLETON;
    }
}
