package com.gildedrose.item.strategy;

import com.gildedrose.item.EnhancedItem;

public interface ItemUpdateStrategy {

    int calculateQualityDelta(EnhancedItem item);

    default boolean allowChangingSellDate() {
        return true;
    }
}
