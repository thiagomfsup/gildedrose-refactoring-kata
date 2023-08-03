package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (item.name.equals("Sulfuras, Hand of Ragnaros")) // LEGENDARY ITEM :: never has to be sold or decreases in Quality
                continue;

            if (!item.name.equals("Aged Brie") && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                tryDecreaseItemQuality(item);
            } else {
                // AGED or BACKSTAGE ITEM
                tryIncreaseItemQuality(item);

                if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (item.sellIn < 11) {
                        tryIncreaseItemQuality(item);
                    }

                    if (item.sellIn < 6) {
                        tryIncreaseItemQuality(item);
                    }
                }
            }

            item.sellIn = item.sellIn - 1;

            if (item.sellIn < 0) {
                if (!item.name.equals("Aged Brie")) {
                    if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        tryDecreaseItemQuality(item);
                    } else {
                        item.quality = 0;
                    }
                } else {
                    tryIncreaseItemQuality(item);
                }
            }
        }
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
