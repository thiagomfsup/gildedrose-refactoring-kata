package com.gildedrose;

import com.gildedrose.item.EnhancedItem;
import com.gildedrose.item.strategy.AgedItemUpdateStrategy;
import com.gildedrose.item.strategy.BackstagePassItemUpdateStrategy;
import com.gildedrose.item.strategy.LegendaryItemUpdateStrategy;
import com.gildedrose.item.strategy.NormalItemUpdateStrategy;

public class TexttestFixture {
    public static void main(String[] args) {
        System.out.println("OMGHAI!");

        EnhancedItem[] items = new EnhancedItem[] {
            new EnhancedItem("+5 Dexterity Vest", 10, 20, NormalItemUpdateStrategy.getInstance()),
            new EnhancedItem("Aged Brie", 2, 0, AgedItemUpdateStrategy.getInstance()),
            new EnhancedItem("Aged Brie", 2, 49, AgedItemUpdateStrategy.getInstance()),
            new EnhancedItem("Elixir of the Mongoose", 5, 7, NormalItemUpdateStrategy.getInstance()),
            new EnhancedItem("Sulfuras, Hand of Ragnaros", 0, 80, LegendaryItemUpdateStrategy.getInstance()),
            new EnhancedItem("Sulfuras, Hand of Ragnaros", -1, 80, LegendaryItemUpdateStrategy.getInstance()),
            new EnhancedItem("Backstage passes to a TAFKAL80ETC concert", 15, 20, BackstagePassItemUpdateStrategy.getInstance()),
            new EnhancedItem("Backstage passes to a TAFKAL80ETC concert", 10, 49, BackstagePassItemUpdateStrategy.getInstance()),
            new EnhancedItem("Backstage passes to a TAFKAL80ETC concert", 5, 49, BackstagePassItemUpdateStrategy.getInstance()),
            // this conjured item does not work properly yet
            new EnhancedItem("Conjured Mana Cake", 3, 6, NormalItemUpdateStrategy.getInstance()) };

        GildedRose app = new GildedRose(items);

        int days = 2;
        if (args.length > 0) {
            days = Integer.parseInt(args[0]) + 1;
        }

        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, sellIn, quality");
            for (EnhancedItem item : items) {
                System.out.println(item);
            }
            System.out.println();
            app.updateQuality();
        }
    }

}

