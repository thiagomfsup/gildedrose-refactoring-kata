package com.gildedrose.item.strategy;

import com.gildedrose.item.EnhancedItem;

public class ConjuredItemUpdateStrategy implements ItemUpdateStrategy {

    private static final ConjuredItemUpdateStrategy SINGLETON = new ConjuredItemUpdateStrategy();

    private static final int DEFAULT_DELTA = -2;

    private ConjuredItemUpdateStrategy() {
    }

    public static ConjuredItemUpdateStrategy getInstance() {
        return SINGLETON;
    }

    @Override
    public int calculateQualityDelta(EnhancedItem item) {
        if (item.hasSellDatePassed())
            return DEFAULT_DELTA * 2;

        return DEFAULT_DELTA;
    }
}
