package com.gildedrose;

import com.gildedrose.item.EnhancedItem;
import com.gildedrose.item.ItemCategory;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ConjuredItemTest {

    private static final int TEN_DAYS = 10;
    private static final int FIVE_DAYS = 5;

    @Test
    public void qualityDegradeTwiceAsFastThanNormalItem() {
        final int initialQuality = 15;
        final int expectedNormalItemQualityAfterFiveDays = 10;
        final int expectedConjuredItemQualityAfterFiveDays = 5;

        // given
        var conjuredFooItem = new EnhancedItem("foo", TEN_DAYS, initialQuality, ItemCategory.CONJURED);
        var normalFooItem = new EnhancedItem("foo", TEN_DAYS, initialQuality, ItemCategory.NORMAL);
        GildedRose gildedRose = new GildedRose(normalFooItem, conjuredFooItem);

        // when
        updateQualityForDays(gildedRose, FIVE_DAYS);

        assertThat(normalFooItem.getQuality(), is(expectedNormalItemQualityAfterFiveDays));
        assertThat(conjuredFooItem.getQuality(), is(expectedConjuredItemQualityAfterFiveDays));
    }

    @Test
    public void qualityDegradeTwiceAsFastThanNormalItemWhenOverdue() {
        final int initialQuality = 30;
        final int expectedNormalItemQualityAfterFiveDays = 20;
        final int expectedConjuredItemQualityAfterFiveDays = 10;

        // given
        var conjuredFooItem = new EnhancedItem("foo", 0, initialQuality, ItemCategory.CONJURED);
        var normalFooItem = new EnhancedItem("foo", 0, initialQuality, ItemCategory.NORMAL);
        GildedRose gildedRose = new GildedRose(normalFooItem, conjuredFooItem);

        // when
        updateQualityForDays(gildedRose, FIVE_DAYS);

        assertThat(normalFooItem.getQuality(), is(expectedNormalItemQualityAfterFiveDays));
        assertThat(conjuredFooItem.getQuality(), is(expectedConjuredItemQualityAfterFiveDays));
    }

    @Test
    public void qualityDegradeTwiceAsFastThanNormalItemKeepingQualityNonNegativeWhenOverdue() {
        final int initialQuality = 15;
        final int expectedNormalItemQualityAfterFiveDays = 5;
        final int expectedConjuredItemQualityAfterFiveDays = 0;

        // given
        var conjuredFooItem = new EnhancedItem("foo", 0, initialQuality, ItemCategory.CONJURED);
        var normalFooItem = new EnhancedItem("foo", 0, initialQuality, ItemCategory.NORMAL);
        GildedRose gildedRose = new GildedRose(normalFooItem, conjuredFooItem);

        // when
        updateQualityForDays(gildedRose, FIVE_DAYS);

        assertThat(normalFooItem.getQuality(), is(expectedNormalItemQualityAfterFiveDays));
        assertThat(conjuredFooItem.getQuality(), is(expectedConjuredItemQualityAfterFiveDays));
    }

    private void updateQualityForDays(final GildedRose gildedRose, int days) {
        for (int i = 1; i <= days; i++)
            gildedRose.updateQuality();
    }
}
