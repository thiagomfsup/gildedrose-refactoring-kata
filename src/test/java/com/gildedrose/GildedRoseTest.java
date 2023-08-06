package com.gildedrose;

import com.gildedrose.item.EnhancedItem;
import com.gildedrose.item.ItemCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

class GildedRoseTest {

    private static final int TEN_DAYS = 10;

    private GildedRose gildedRose;

    @BeforeEach
    public void setUp() {
        // given
        gildedRose = new GildedRose(
            new EnhancedItem("+5 Dexterity Vest", 10, 20, ItemCategory.NORMAL),
            new EnhancedItem("Aged Brie", 2, 0, ItemCategory.AGED),
            new EnhancedItem("Aged Brie", 2, 49, ItemCategory.AGED),
            new EnhancedItem("Elixir of the Mongoose", 5, 7, ItemCategory.NORMAL),
            new EnhancedItem("Sulfuras, Hand of Ragnaros", 0, 80, ItemCategory.LEGENDARY),
            new EnhancedItem("Sulfuras, Hand of Ragnaros", -1, 80, ItemCategory.LEGENDARY),
            new EnhancedItem("Backstage passes to a TAFKAL80ETC concert", 15, 20, ItemCategory.BACKSTAGE_PASS),
            new EnhancedItem("Backstage passes to a TAFKAL80ETC concert", 10, 49, ItemCategory.BACKSTAGE_PASS),
            new EnhancedItem("Backstage passes to a TAFKAL80ETC concert", 5, 49, ItemCategory.BACKSTAGE_PASS),
            // this conjured item does not work properly yet
            new EnhancedItem("Conjured Mana Cake", 3, 6, ItemCategory.NORMAL));
    }

    @Test
    public void qualityShouldNeverBeNegative() {
        // when
        updateQualityForDays(gildedRose, TEN_DAYS);

        // then
        for (EnhancedItem item : gildedRose.items) {
            assertThat(item.getQuality(), greaterThanOrEqualTo(0));
        }
    }

    @Test
    public void qualityShouldNeverBeGreaterThanFifty() {
        // given NON Legendary items
        gildedRose.items = Arrays.stream(gildedRose.items)
            .filter(item -> item.getCategory() != ItemCategory.LEGENDARY)
            .toArray(EnhancedItem[]::new);

        // when
        updateQualityForDays(gildedRose, TEN_DAYS);

        // then
        for (EnhancedItem item : gildedRose.items) {
            assertThat(item.getQuality(), lessThanOrEqualTo(50));
        }
    }

    private void updateQualityForDays(final GildedRose gildedRose, int days) {
        for (int i = 1; i <= days; i++)
            gildedRose.updateQuality();
    }

}
