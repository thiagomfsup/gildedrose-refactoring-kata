package com.gildedrose;

import com.gildedrose.item.EnhancedItem;
import com.gildedrose.item.strategy.AgedItemUpdateStrategy;
import com.gildedrose.item.strategy.BackstagePassItemUpdateStrategy;
import com.gildedrose.item.strategy.LegendaryItemUpdateStrategy;
import com.gildedrose.item.strategy.NormalItemUpdateStrategy;
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
            new EnhancedItem("+5 Dexterity Vest", 10, 20, NormalItemUpdateStrategy.getInstance()),
            new EnhancedItem("Aged Brie", 2, 0, AgedItemUpdateStrategy.getInstance()),
            new EnhancedItem("Aged Brie", 2, 49, AgedItemUpdateStrategy.getInstance()),
            new EnhancedItem("Elixir of the Mongoose", 5, 7, NormalItemUpdateStrategy.getInstance()),
            new EnhancedItem("Sulfuras, Hand of Ragnaros", 0, 80, LegendaryItemUpdateStrategy.getInstance()),
            new EnhancedItem("Sulfuras, Hand of Ragnaros", -1, 80, LegendaryItemUpdateStrategy.getInstance()),
            new EnhancedItem("Backstage passes to a TAFKAL80ETC concert", 15, 20, BackstagePassItemUpdateStrategy.getInstance()),
            new EnhancedItem("Backstage passes to a TAFKAL80ETC concert", 10, 49, BackstagePassItemUpdateStrategy.getInstance()),
            new EnhancedItem("Backstage passes to a TAFKAL80ETC concert", 5, 49, BackstagePassItemUpdateStrategy.getInstance()),
            // this conjured item does not work properly yet
            new EnhancedItem("Conjured Mana Cake", 3, 6, NormalItemUpdateStrategy.getInstance()));
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
            .filter(item -> !"Sulfuras, Hand of Ragnaros".equals(item.getName())) // TODO How to do it another way?
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
