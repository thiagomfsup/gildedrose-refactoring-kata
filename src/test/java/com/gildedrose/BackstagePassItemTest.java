package com.gildedrose;

import com.gildedrose.item.EnhancedItem;
import com.gildedrose.item.ItemCategory;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BackstagePassItemTest {
    @Test
    public void increaseQualityWhenSellInGreaterThanTenDaysApproach() {
        final int initialQuality = 6;
        final int expectedFinalQuality = 7;
        final int sellInGreaterThanTen = 15;

        // given
        var backstatePassItem = new EnhancedItem("Backstage passes to a TAFKAL80ETC concert", sellInGreaterThanTen,
            initialQuality, ItemCategory.BACKSTAGE_PASS);
        GildedRose gildedRose = new GildedRose(backstatePassItem);

        // when
        gildedRose.updateQuality();

        // then
        assertThat(backstatePassItem.getQuality(), is(expectedFinalQuality));
        assertThat(backstatePassItem.getSellIn(), is(sellInGreaterThanTen - 1));
    }

    @Test
    public void increaseQualityByTwoWhenFinalSellInBetween6And10Days() {
        final int initialQuality = 6;
        final int expectedFinalQuality = 8;

        // given
        final EnhancedItem[] backstagePassItems = IntStream.rangeClosed(7, 11)
            .mapToObj(initialSellIn -> new EnhancedItem("Backstage passes to a TAFKAL80ETC concert", initialSellIn,
                initialQuality, ItemCategory.BACKSTAGE_PASS))
            .toArray(EnhancedItem[]::new);
        GildedRose gildedRose = new GildedRose(backstagePassItems);

        // when
        gildedRose.updateQuality();

        // then
        for(EnhancedItem item : gildedRose.items) {
            assertThat(item.getQuality(), is(expectedFinalQuality));
        }
    }

    @Test
    public void increaseQualityByTwoWhenSellInBetween6And10Days_KeepingMaxQualityLimit() {
        final int initialQuality = 48;
        final int expectedFinalQuality = 50;

        // given
        final EnhancedItem[] backstagePassItems = IntStream.rangeClosed(6, 10)
            .mapToObj(sellIn -> new EnhancedItem("Backstage passes to a TAFKAL80ETC concert", sellIn,
                initialQuality, ItemCategory.BACKSTAGE_PASS))
            .toArray(EnhancedItem[]::new);
        GildedRose gildedRose = new GildedRose(backstagePassItems);

        // when
        gildedRose.updateQuality();

        // then
        for(EnhancedItem item : gildedRose.items) {
            assertThat(item.getQuality(), is(expectedFinalQuality));
        }
    }

    @Test
    public void increaseQualityByThreeWhenFinalSellInBetween1And5Days() {
        final int initialQuality = 6;
        final int expectedFinalQuality = 9;

        // given
        final EnhancedItem[] backstagePassItems = IntStream.rangeClosed(1, 6)
            .mapToObj(initialSellIn -> new EnhancedItem("Backstage passes to a TAFKAL80ETC concert", initialSellIn,
                initialQuality, ItemCategory.BACKSTAGE_PASS))
            .toArray(EnhancedItem[]::new);
        GildedRose gildedRose = new GildedRose(backstagePassItems);

        // when
        gildedRose.updateQuality();

        // then
        for(EnhancedItem item : gildedRose.items) {
            assertThat(item.getQuality(), is(expectedFinalQuality));
        }
    }

    @Test
    public void increaseQualityByThreeWhenSellInBetween1And5Days_KeepingMaxQualityLimit() {
        final int initialQuality = 48;
        final int expectedFinalQuality = 50;

        // given
        final EnhancedItem[] backstagePassItems = IntStream.rangeClosed(1, 5)
            .mapToObj(sellIn -> new EnhancedItem("Backstage passes to a TAFKAL80ETC concert", sellIn,
                initialQuality, ItemCategory.BACKSTAGE_PASS))
            .toArray(EnhancedItem[]::new);
        GildedRose gildedRose = new GildedRose(backstagePassItems);

        // when
        gildedRose.updateQuality();

        // then
        for(EnhancedItem item : gildedRose.items) {
            assertThat(item.getQuality(), is(expectedFinalQuality));
        }
    }

    @Test
    public void qualityDropToZeroAfterConcert() {
        final int initialQuality = 25;
        final int expectedFinalQuality = 0;
        final int sellInDate = 0;

        // given
        EnhancedItem backstagePassItem = new EnhancedItem("Backstage passes to a TAFKAL80ETC concert", sellInDate,
            initialQuality, ItemCategory.BACKSTAGE_PASS);
        GildedRose gildedRose = new GildedRose(backstagePassItem);

        // when
        gildedRose.updateQuality();

        // then
        assertThat(backstagePassItem.getQuality(), is(expectedFinalQuality));
    }
}
