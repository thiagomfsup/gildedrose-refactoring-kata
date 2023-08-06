package com.gildedrose;

import com.gildedrose.item.EnhancedItem;
import com.gildedrose.item.ItemCategory;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ConjuredItemTest {

    private static final int TEN_DAYS = 10;
    private static final int FIVE_DAYS = 5;
    private static final int TWICE_AS_FAST = 2;

    @Test
    public void qualityDegradeTwiceAsFastThanNormalItem() {
        final int initialQuality = 10;

        // given
        var conjuredFooItem = new EnhancedItem("foo", TEN_DAYS, initialQuality, ItemCategory.CONJURED);
        var normalFooItem = new EnhancedItem("foo", TEN_DAYS, initialQuality, ItemCategory.CONJURED);
        GildedRose gildedRose = new GildedRose(normalFooItem, conjuredFooItem);

        // when
        updateQualityForDays(gildedRose, FIVE_DAYS);

        assertThat(conjuredFooItem.getQuality(), is(normalFooItem.getQuality() * TWICE_AS_FAST));
    }

    @Test
    public void qualityDegradeTwiceAsFastThanNormalItemWhenOverdue() {
        final int initialQuality = 10;

        // given
        var conjuredFooItem = new EnhancedItem("foo", 0, initialQuality, ItemCategory.CONJURED);
        var normalFooItem = new EnhancedItem("foo", 0, initialQuality, ItemCategory.CONJURED);
        GildedRose gildedRose = new GildedRose(normalFooItem, conjuredFooItem);

        // when
        updateQualityForDays(gildedRose, FIVE_DAYS);

        assertThat(conjuredFooItem.getQuality(), is(normalFooItem.getQuality() * TWICE_AS_FAST * TWICE_AS_FAST));
    }

    private void updateQualityForDays(final GildedRose gildedRose, int days) {
        for (int i = 1; i <= days; i++)
            gildedRose.updateQuality();
    }
}
