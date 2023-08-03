package com.gildedrose;

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
            new Item("+5 Dexterity Vest", 10, 20),
            new Item("Aged Brie", 2, 0),
            new Item("Aged Brie", 2, 49),
            new Item("Elixir of the Mongoose", 5, 7),
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            // this conjured item does not work properly yet
            new Item("Conjured Mana Cake", 3, 6));
    }

    @Test
    public void qualityShouldNeverBeNegative() {
        // when
        updateQualityForDays(gildedRose, TEN_DAYS);

        // then
        for (Item item : gildedRose.items) {
            assertThat(item.quality, greaterThanOrEqualTo(0));
        }
    }

    @Test
    public void qualityShouldNeverBeGreaterThanFifty() {
        // given NON Legendary items
        gildedRose.items = Arrays.stream(gildedRose.items)
            .filter(item -> !"Sulfuras, Hand of Ragnaros".equals(item.name))
            .toArray(Item[]::new);

        // when
        updateQualityForDays(gildedRose, TEN_DAYS);

        // then
        for (Item item : gildedRose.items) {
            assertThat(item.quality, lessThanOrEqualTo(50));
        }
    }

    private void updateQualityForDays(final GildedRose gildedRose, int days) {
        for (int i = 1; i <= days; i++)
            gildedRose.updateQuality();
    }

}
