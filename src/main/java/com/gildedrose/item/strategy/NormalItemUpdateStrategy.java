package com.gildedrose.item.strategy;

import com.gildedrose.item.EnhancedItem;

public final class NormalItemUpdateStrategy implements ItemUpdateStrategy {

    private static final NormalItemUpdateStrategy SINGLETON = new NormalItemUpdateStrategy();

    private static final int DEFAULT_DELTA = -1;

    private NormalItemUpdateStrategy() {
    }

    public static ItemUpdateStrategy getInstance() {
        return SINGLETON;
    }

    @Override
    public int calculateQualityDelta(EnhancedItem item) {
        if (item.hasSellDatePassed())
            return DEFAULT_DELTA * 2;

        return DEFAULT_DELTA;
    }
}
