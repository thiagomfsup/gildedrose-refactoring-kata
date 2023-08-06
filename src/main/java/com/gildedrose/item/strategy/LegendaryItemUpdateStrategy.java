package com.gildedrose.item.strategy;

import com.gildedrose.item.EnhancedItem;

public class LegendaryItemUpdateStrategy implements ItemUpdateStrategy {
    private static final LegendaryItemUpdateStrategy SINGLETON = new LegendaryItemUpdateStrategy();

    private LegendaryItemUpdateStrategy() {
    }

    public static ItemUpdateStrategy getInstance() {
        return SINGLETON;
    }

    @Override
    public void updateItem(EnhancedItem item) {
        // NO-OP
    }
}
