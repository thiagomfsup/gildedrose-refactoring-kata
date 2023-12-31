package com.gildedrose;

import com.gildedrose.item.EnhancedItem;
import com.gildedrose.item.ItemCategory;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class NormalItemTest {

    @Test
    public void qualityDegradeTwiceAsFastWhenOverdue() {
        // given
        var overdueFooItem = new EnhancedItem("foo", 0, 5, ItemCategory.NORMAL);
        var overdueBarItem = new EnhancedItem("bar", -1, 5, ItemCategory.NORMAL);
        GildedRose gildedRose = new GildedRose(overdueFooItem, overdueBarItem);

        // when
        gildedRose.updateQuality();

        assertThat(overdueFooItem.getQuality(), is(3));
        assertThat(overdueBarItem.getQuality(), is(3));
    }
}
