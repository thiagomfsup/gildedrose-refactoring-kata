package com.gildedrose;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BackstagePassItemTest {
    @Test
    public void increaseQualityWhenSellInGreaterThanTenApproaches() {
        final int initialQuality = 6;
        final int expectedFinalQuality = 7;
        final int sellInGreaterThanTen = 15;

        // given
        var backstatePassItem = new Item("Backstage passes to a TAFKAL80ETC concert", sellInGreaterThanTen, initialQuality);
        GildedRose gildedRose = new GildedRose(new Item[]{backstatePassItem});

        // when
        gildedRose.updateQuality();

        // then
        assertThat(backstatePassItem.quality, is(expectedFinalQuality));
        assertThat(backstatePassItem.sellIn, is(sellInGreaterThanTen - 1));
    }

    @Test
    public void increaseQualityByTwoWhenSellInBetween6And10Days() {
        final int initialQuality = 6;
        final int expectedFinalQuality = 8;

        // given
        final Item[] backstagePassItems = IntStream.rangeClosed(6, 10)
            .mapToObj(sellIn -> new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, initialQuality))
            .toArray(Item[]::new);
        GildedRose gildedRose = new GildedRose(backstagePassItems);

        // when
        gildedRose.updateQuality();

        // then
        for(Item item : gildedRose.items) {
            assertThat(item.quality, is(expectedFinalQuality));
        }
    }

    @Test
    public void increaseQualityByThreeWhenSellInBetween1And5Days() {
        final int initialQuality = 6;
        final int expectedFinalQuality = 9;

        // given
        final Item[] backstagePassItems = IntStream.rangeClosed(1, 5)
            .mapToObj(sellIn -> new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, initialQuality))
            .toArray(Item[]::new);
        GildedRose gildedRose = new GildedRose(backstagePassItems);

        // when
        gildedRose.updateQuality();

        // then
        for(Item item : gildedRose.items) {
            assertThat(item.quality, is(expectedFinalQuality));
        }
    }

    @Test
    public void qualityDropToZeroAfterConcert() {
        final int initialQuality = 25;
        final int expectedFinalQuality = 0;
        final int sellInDate = 0;

        // given
        Item backstagePassItem = new Item("Backstage passes to a TAFKAL80ETC concert", sellInDate, initialQuality);
        GildedRose gildedRose = new GildedRose(new Item[]{backstagePassItem});

        // when
        gildedRose.updateQuality();

        // then
        assertThat(backstagePassItem.quality, is(expectedFinalQuality));
    }
}
