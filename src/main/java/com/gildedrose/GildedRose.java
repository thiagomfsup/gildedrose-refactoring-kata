package com.gildedrose;

import com.gildedrose.item.EnhancedItem;

class GildedRose {
    EnhancedItem[] items;

    public GildedRose(EnhancedItem... items) {
        this.items = items;
    }

    public void updateQuality() {
        for (EnhancedItem item : items) {
            item.updateQuality();
        }
    }
}
