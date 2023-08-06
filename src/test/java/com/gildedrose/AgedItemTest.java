package com.gildedrose;

import com.gildedrose.item.EnhancedItem;
import com.gildedrose.item.strategy.AgedItemUpdateStrategy;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AgedItemTest {
    @Test
    public void increaseQualityWhenGettingOlder() {
        final int initialQuality = 6;
        final int expectedFinalQuality = 7;

        // given
        var agedBrieItem = new EnhancedItem("Aged Brie", 5, initialQuality, AgedItemUpdateStrategy.getInstance());
        GildedRose gildedRose = new GildedRose(agedBrieItem);

        // when
        gildedRose.updateQuality();

        assertThat(agedBrieItem.getQuality(), is(expectedFinalQuality));
    }

    @Test
    public void increaseQualityTwiceAsFastWhenOverdue() {
        final int initialQuality = 6;
        final int expectedFinalQuality = 8;

        // given
        var overdueAgedBrieItem = new EnhancedItem("Aged Brie", -1, initialQuality, AgedItemUpdateStrategy.getInstance());
        GildedRose gildedRose = new GildedRose(overdueAgedBrieItem);

        // when
        gildedRose.updateQuality();

        // then
        assertThat(overdueAgedBrieItem.getQuality(), is(expectedFinalQuality));
    }

    @Test
    public void increaseQualityTwiceAsFastWhenOverdueKeepingMaxQuality() {
        final int initialQuality = 49;
        final int expectedFinalQuality = 50;

        // given
        var overdueAgedBrieItem = new EnhancedItem("Aged Brie", -1, initialQuality, AgedItemUpdateStrategy.getInstance());
        GildedRose gildedRose = new GildedRose(overdueAgedBrieItem);

        // when
        gildedRose.updateQuality();

        // then
        assertThat(overdueAgedBrieItem.getQuality(), is(expectedFinalQuality));
    }
}

