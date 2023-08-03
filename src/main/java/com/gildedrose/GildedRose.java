package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (isLegendaryItem(item)) // LEGENDARY ITEM :: never has to be sold or decreases in Quality
                continue;

            if (isAgedItem(item) || isBackstagePassItem(item)) {
                // AGED or BACKSTAGE ITEM
                tryIncreaseItemQuality(item);

                if (isBackstagePassItem(item)) {
                    if (item.sellIn < 11) {
                        tryIncreaseItemQuality(item);
                    }

                    if (item.sellIn < 6) {
                        tryIncreaseItemQuality(item);
                    }
                }
            } else {
                tryDecreaseItemQuality(item);
            }

            item.sellIn = item.sellIn - 1;

            if (item.sellIn < 0) {
                if (isAgedItem(item)) {
                    tryIncreaseItemQuality(item);
                } else {
                    if (!isBackstagePassItem(item)) {
                        tryDecreaseItemQuality(item);
                    } else {
                        item.quality = 0;
                    }
                }
            }
        }
    }

    private boolean isBackstagePassItem(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isAgedItem(Item item) {
        return item.name.equals("Aged Brie");
    }
    private boolean isLegendaryItem(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    // TODO should be an item method
    private void tryDecreaseItemQuality(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    // TODO should be an item method
    private void tryIncreaseItemQuality(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }
}
