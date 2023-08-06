package com.gildedrose.item.strategy;

import com.gildedrose.item.EnhancedItem;

public class ConjuredItemUpdateStrategy implements ItemUpdateStrategy {

    private static final ConjuredItemUpdateStrategy SINGLETON = new ConjuredItemUpdateStrategy();

    private ConjuredItemUpdateStrategy() {
    }

    public static ConjuredItemUpdateStrategy getInstance() {
        return SINGLETON;
    }

    @Override
    public void updateItem(EnhancedItem item) {
        item.tryDecreaseQuality();
        item.tryDecreaseQuality(); // TODO BAD SMELL

        item.decreaseSellIn();

        if (item.hasSellDatePassed()) {
            item.tryDecreaseQuality();
            item.tryDecreaseQuality();
        }
    }
}
