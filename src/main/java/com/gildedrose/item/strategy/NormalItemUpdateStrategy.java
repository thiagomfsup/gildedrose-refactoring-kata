package com.gildedrose.item.strategy;

import com.gildedrose.item.EnhancedItem;

public class NormalItemUpdateStrategy implements ItemUpdateStrategy {

    private static final NormalItemUpdateStrategy SINGLETON = new NormalItemUpdateStrategy();

    private NormalItemUpdateStrategy() {
    }

    public static ItemUpdateStrategy getInstance() {
        return SINGLETON;
    }

    @Override
    public void updateItem(EnhancedItem item) {
        item.tryDecreaseQuality();

        item.decreaseSellIn();

        if (item.hasSellDatePassed()) {
            item.tryDecreaseQuality();
        }
    }
}
