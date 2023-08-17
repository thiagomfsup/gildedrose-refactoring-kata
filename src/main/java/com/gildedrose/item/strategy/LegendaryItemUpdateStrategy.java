package com.gildedrose.item.strategy;

import com.gildedrose.item.EnhancedItem;

public final class LegendaryItemUpdateStrategy implements ItemUpdateStrategy {
    private static final LegendaryItemUpdateStrategy SINGLETON = new LegendaryItemUpdateStrategy();

    private LegendaryItemUpdateStrategy() {
    }

    public static ItemUpdateStrategy getInstance() {
        return SINGLETON;
    }

    @Override
    public int calculateQualityDelta(EnhancedItem enhancedItem) {
        return 0;
    }

    @Override
    public boolean allowChangingSellDate() {
        return false;
    }
}
