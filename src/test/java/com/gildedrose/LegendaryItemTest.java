package com.gildedrose;

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
        final Item legendaryPositiveSellInItem = new Item("Sulfuras, Hand of Ragnaros", POSITIVE_SELLIN, LEGENDARY_QUALITY);
        final Item legendaryOverdueItem = new Item("Sulfuras, Hand of Ragnaros", OVERDUE_SELLIN, LEGENDARY_QUALITY);
        GildedRose gildedRose = new GildedRose(new Item[]{legendaryPositiveSellInItem, legendaryOverdueItem});

        // when
        updateQualityForDays(gildedRose, TEN_DAYS);

        assertThat(legendaryPositiveSellInItem.sellIn, is(POSITIVE_SELLIN));
        assertThat(legendaryOverdueItem.sellIn, is(OVERDUE_SELLIN));
    }

    @Test
    public void legendaryQualityNeverChange() {
        // given
        final Item legendaryPositiveSellInItem = new Item("Sulfuras, Hand of Ragnaros", POSITIVE_SELLIN, LEGENDARY_QUALITY);
        final Item legendaryOverdueItem = new Item("Sulfuras, Hand of Ragnaros", OVERDUE_SELLIN, LEGENDARY_QUALITY);
        GildedRose gildedRose = new GildedRose(new Item[]{legendaryPositiveSellInItem, legendaryOverdueItem});

        // when
        updateQualityForDays(gildedRose, TEN_DAYS);

        assertThat(legendaryPositiveSellInItem.quality, is(LEGENDARY_QUALITY));
        assertThat(legendaryOverdueItem.quality, is(LEGENDARY_QUALITY));
    }

    private void updateQualityForDays(final GildedRose gildedRose, int days) {
        for (int i = 1; i <= days; i++)
            gildedRose.updateQuality();
    }
}

