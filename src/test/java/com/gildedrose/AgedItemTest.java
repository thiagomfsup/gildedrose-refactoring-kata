package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AgedItemTest {
    @Test
    public void increaseQualityWhenGettingOlder() {
        final int initialQuality = 6;
        final int expectedFinalQuality = 7;

        // given
        var agedBrieItem = new Item("Aged Brie", 5, initialQuality);
        GildedRose gildedRose = new GildedRose(new Item[]{agedBrieItem});

        // when
        gildedRose.updateQuality();

        assertThat(agedBrieItem.quality, is(expectedFinalQuality));
    }

    @Test
    public void increaseQualityTwiceAsFastWhenOverdue() {
        final int initialQuality = 6;
        final int expectedFinalQuality = 8;

        // given
        var overdueAgedBrieItem = new Item("Aged Brie", -1, initialQuality);
        GildedRose gildedRose = new GildedRose(new Item[]{overdueAgedBrieItem});

        // when
        gildedRose.updateQuality();

        // then
        assertThat(overdueAgedBrieItem.quality, is(expectedFinalQuality));
    }
}

