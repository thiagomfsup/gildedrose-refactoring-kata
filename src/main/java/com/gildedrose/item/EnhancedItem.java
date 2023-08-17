package com.gildedrose.item;

import com.gildedrose.Item;

public class EnhancedItem {
    private static final int MAX_QUALITY_VALUE = 50;
    private Item item;

    private ItemCategory category;

    public EnhancedItem(Item item, ItemCategory category) {
        this.item = item;
        this.category = category;
    }

    public EnhancedItem(String name, int sellIn, int quality, ItemCategory category) {
        this(new Item(name, sellIn, quality), category);
    }

    public void updateQuality() {
        final var itemUpdateStrategy = category.getUpdateStrategy();

        if (itemUpdateStrategy.allowChangingSellDate())
            decreaseSellIn();

        tryUpdateQuality(itemUpdateStrategy.calculateQualityDelta(this));
    }

    private void tryUpdateQuality(int delta) {
        if (delta == 0)
            return; // nothing to change

        final int tempQuality = item.quality + delta;

        if (tempQuality < 0) {
            item.quality = 0;
        } else if(tempQuality > MAX_QUALITY_VALUE) {
            item.quality = MAX_QUALITY_VALUE;
        } else {
            item.quality = tempQuality;
        }
    }

    private void decreaseSellIn() {
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

    public ItemCategory getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return item.toString();
    }
}
