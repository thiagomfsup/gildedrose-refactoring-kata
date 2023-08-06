package com.gildedrose.item;

import com.gildedrose.item.strategy.AgedItemUpdateStrategy;
import com.gildedrose.item.strategy.BackstagePassItemUpdateStrategy;
import com.gildedrose.item.strategy.ItemUpdateStrategy;
import com.gildedrose.item.strategy.LegendaryItemUpdateStrategy;
import com.gildedrose.item.strategy.NormalItemUpdateStrategy;

public enum ItemCategory {
    NORMAL(NormalItemUpdateStrategy.getInstance()),
    AGED(AgedItemUpdateStrategy.getInstance()),
    BACKSTAGE_PASS(BackstagePassItemUpdateStrategy.getInstance()),
    LEGENDARY(LegendaryItemUpdateStrategy.getInstance());

    private final ItemUpdateStrategy updateStrategy;

    ItemCategory(ItemUpdateStrategy updateStrategy) {
        this.updateStrategy = updateStrategy;
    }

    public ItemUpdateStrategy getUpdateStrategy() {
        return updateStrategy;
    }
}
