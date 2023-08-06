package com.gildedrose.item;

import com.gildedrose.Item;
import com.gildedrose.item.strategy.ItemUpdateStrategy;

public class EnhancedItem {
    private Item item;

    private ItemUpdateStrategy strategy;

    public EnhancedItem(Item item, ItemUpdateStrategy strategy) {
        this.item = item;
        this.strategy = strategy;
    }

    public EnhancedItem(String name, int sellIn, int quality, ItemUpdateStrategy strategy) {
        this(new Item(name, sellIn, quality), strategy);
    }

    public void updateQuality() {
        strategy.updateItem(this);
    }

    // TODO public?
    public void tryDecreaseQuality() {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    // TODO public?
    public void tryIncreaseQuality() {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    // TODO probably can be made another way
    public void dropQualityToZero() {
        item.quality = 0;
    }

    public void decreaseSellIn() {
        item.sellIn = item.sellIn - 1;
    }

    public boolean hasSellDatePassed() {
        return item.sellIn < 0;
    }

    public int getSellIn() {
        return item.sellIn;
    }

    public int getQuality() {
        return item.quality;
    }

    public String getName() {
        return item.name;
    }

    @Override
    public String toString() {
        return item.toString();
    }
}
