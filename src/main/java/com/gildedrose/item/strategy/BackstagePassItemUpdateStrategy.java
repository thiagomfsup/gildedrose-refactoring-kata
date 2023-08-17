package com.gildedrose.item.strategy;

import com.gildedrose.item.EnhancedItem;

public final class BackstagePassItemUpdateStrategy implements ItemUpdateStrategy {
    private static final BackstagePassItemUpdateStrategy SINGLETON = new BackstagePassItemUpdateStrategy();

    private BackstagePassItemUpdateStrategy() {}

    public static BackstagePassItemUpdateStrategy getInstance() {
        return SINGLETON;
    }

    @Override
    public int calculateQualityDelta(EnhancedItem item) {
        if (item.hasSellDatePassed())
            return (-1) * item.getQuality(); // drop quality to zero

        int delta = 1;

        if (item.getSellIn() < 11) {
            delta++;
        }

        if (item.getSellIn() < 6) {
            delta++;
        }

        return delta;
    }
}
