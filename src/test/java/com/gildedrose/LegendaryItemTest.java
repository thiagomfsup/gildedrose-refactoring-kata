package com.gildedrose;

import com.gildedrose.item.EnhancedItem;
import com.gildedrose.item.strategy.LegendaryItemUpdateStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LegendaryItemTest {

    private static final int TEN_DAYS = 10;
    private static final int POSITIVE_SELLIN = 5;
    private static final int OVERDUE_SELLIN = -5;
    private static final int LEGENDARY_QUALITY = 80;

    @Test
    public void hasNeverToBeSold() {
        // given
        final EnhancedItem legendaryPositiveSellInItem = new EnhancedItem("Sulfuras, Hand of Ragnaros",
            POSITIVE_SELLIN, LEGENDARY_QUALITY, LegendaryItemUpdateStrategy.getInstance());

        final EnhancedItem legendaryOverdueItem = new EnhancedItem("Sulfuras, Hand of Ragnaros",
            OVERDUE_SELLIN, LEGENDARY_QUALITY, LegendaryItemUpdateStrategy.getInstance());
        GildedRose gildedRose = new GildedRose(legendaryPositiveSellInItem, legendaryOverdueItem);

        // when
        updateQualityForDays(gildedRose, TEN_DAYS);

        assertThat(legendaryPositiveSellInItem.getSellIn(), is(POSITIVE_SELLIN));
        assertThat(legendaryOverdueItem.getSellIn(), is(OVERDUE_SELLIN));
    }

    @Test
    public void legendaryQualityNeverChange() {
        // given
        final EnhancedItem legendaryPositiveSellInItem = new EnhancedItem("Sulfuras, Hand of Ragnaros",
            POSITIVE_SELLIN, LEGENDARY_QUALITY, LegendaryItemUpdateStrategy.getInstance());

        final EnhancedItem legendaryOverdueItem = new EnhancedItem("Sulfuras, Hand of Ragnaros",
            OVERDUE_SELLIN, LEGENDARY_QUALITY, LegendaryItemUpdateStrategy.getInstance());
        GildedRose gildedRose = new GildedRose(legendaryPositiveSellInItem, legendaryOverdueItem);

        // when
        updateQualityForDays(gildedRose, TEN_DAYS);

        assertThat(legendaryPositiveSellInItem.getQuality(), is(LEGENDARY_QUALITY));
        assertThat(legendaryOverdueItem.getQuality(), is(LEGENDARY_QUALITY));
    }

    private void updateQualityForDays(final GildedRose gildedRose, int days) {
        for (int i = 1; i <= days; i++)
            gildedRose.updateQuality();
    }
}

