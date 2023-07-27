package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class NormalItemTest {

    @Test
    public void qualityDegradeTwiceAsFastWhenOverdue() {
        // given
        var overdueFooItem = new Item("foo", 0, 5);
        var overdueBarItem = new Item("bar", -1, 5);
        GildedRose gildedRose = new GildedRose(new Item[] { overdueFooItem, overdueBarItem });

        // when
        gildedRose.updateQuality();

        assertThat(overdueFooItem.quality, is(3));
        assertThat(overdueBarItem.quality, is(3));
    }
}
