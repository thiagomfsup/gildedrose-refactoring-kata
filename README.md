# Gilded Rose Refactoring Kata

This Kata was originally created by Terry Hughes (http://twitter.com/TerryHughes). It is already on
GitHub [here](https://github.com/NotMyself/GildedRose).
See also [Bobby Johnson's description of the kata](https://iamnotmyself.com/refactor-this-the-gilded-rose-kata/).

This Java code has been taken from Emily Bache's [GildedRose-Refactoring-Kata](https://github.com/emilybache/GildedRose-Refactoring-Kata/tree/main) repo.

Gilded Rose Requirements Specification [here](GildedRoseRequirements.txt).

## Solving Process
### 1st Iteration
**Goals**:: Write Unit Tests and get 100% coverage on the `GildedRose.updateQuality()` method.

Unit tests has been written to give confidence during refactoring. While unit tests was being created, some observation
has been made:
1. The `GildedRose.updateQuality()` method not only updates `quality` field on an item, but also `sellIn` field, thus it
violates the single responsibility principle (SRP).
2. Item could somehow be classified into "Normal", "Aged", "Legendary", and "Backstage Pass" categories (eventually
"Conjured" category shall be supported).

### 2nd Iteration
**Goals**:: refactoring the `GildedRose.updateQuality()` method.

Since it is 100% covered by unit tests, it is time to "refactor" it!
* Even though code coverage was 100%, there were corner cases that wasn't tested, which make me doubt about test coverage.

#### First refactoring round:
* Use *enhanced for* statement instead;
* Give duplicated code a concept name. E.g.,
```java
// Instead of :
if (items[i].quality < 50) {
   items[i].quality = items[i].quality + 1;
}

// Use :
tryIncreaseItemQuality(Item item)
```
Ideal would be `item.tryIncreaseQuality()`, but rules said that `Item` class cannot be changed.
#### Second refactoring round:
* Create method to identify an item category (e.g., `private boolean isAgedItem(Item item) {}`).
Once again, it is not ideal, and it shouldn't be a `GildedRose` class responsibility to perform those verifications.
* Use `varargs` param instead.

### 3rd Iteration
**Goals**:: refactoring the entire solution and introduce a way to classify (categorize) items.

Huge change introduced to the solution in this iteration, but keeping the goblin satisfied. Leeroy wouldn't recognize it
and probably would think that it has been entirely rewritten 😅 Anyway...

To keep constraint on changing `Item` class, the `EnhanceItem` class has been introduced. Basically, it encapsulates an
`Item` object together with a `ItemUpdateStrategy` interface. As it can be derived from this class name, the *Strategy*
pattern has been used, which allow defining the behavior expected when updating item quality.

To avoid unnecessary object instantiation, the *Singleton* pattern has been used on each class that implements the
`ItemUpdateStrategy` interface.

Although applying the *Strategy* pattern seemed to be a good idea, it doesn't solve all the problems. For example, it
is not possible to know to which category an `EnhanceItem` object belongs to. The following test snippet code shows this
limitation:
```java
// given NON Legendary items
gildedRose.items = Arrays.stream(gildedRose.items)
   .filter(item -> !"Sulfuras, Hand of Ragnaros".equals(item.getName()))
   .toArray(EnhancedItem[]::new);
```

To solve it, the `ItemCategory` enum has been introduced. Each category has an `ItemUpdateStrategy` property, which
describes how an item of that category should update quality. Basically it solves two question:
1. Given an item, which category does it belong to?
2. Based on the item category, how to update quality/sellIn?

Introducing the `ItemCategory` enum makes that test easy to understand:
```java
// given NON Legendary items
gildedRose.items = Arrays.stream(gildedRose.items)
   .filter(item -> item.getCategory() != ItemCategory.LEGENDARY)
   .toArray(EnhancedItem[]::new);
```

Since `ItemCategory` has an `ItemUpdateStrategy` object aggregated, introducing a new category will cause (most likely)
a strategy for update to be created as well. Seems that it's not an issue for this problem domain, but it doesn't smell
good...

### 4th Iteration
**Goals**:: introduce support to "Conjured" items.

The "CONJURED" category has been created, so was the `ConjuredItemUpdateStrategy` class. Unit tests for Conjured items
was added as well.
