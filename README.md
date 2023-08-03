# Gilded Rose Refactoring Kata

This Kata was originally created by Terry Hughes (http://twitter.com/TerryHughes). It is already on GitHub [here](https://github.com/NotMyself/GildedRose). See also [Bobby Johnson's description of the kata](https://iamnotmyself.com/refactor-this-the-gilded-rose-kata/).

This Java code has been taken from Emily Bache's [GildedRose-Refactoring-Kata](https://github.com/emilybache/GildedRose-Refactoring-Kata/tree/main) repo.

Gilded Rose Requirements Specification [here](GildedRoseRequirements.txt).

## Solving Process
### 1st Iteration
**Goals**:: Write Unit Tests and get 100% coverage on the `GildedRose.updateQuality()` method.

Unit tests has been written to give confidence during refactoring. While unit tests was being created, some observation has been made:
1. The `GildedRose.updateQuality()` method not only updates `quality` field on an item, but also `sellIn` field, thus it violates the single responsibility principle (SRP).
2. Item could somehow be classified into "Normal", "Aged", "Legendary", and "Backstage Pass" categories (eventually "Conjured" category shall be supported).

### 2nd Iteration
**Goals**:: refactoring the `GildedRose.updateQuality()` method.

Since it is 100% covered by unit tests, it is time to "refactor" it!
* Even though code coverage was 100%, there were corner cases that wasn't tested, which make me doubt about test coverage.

#### First refactoring round:
* use *enhanced for* statement instead;
* give duplicated code a concept name. E.g.,
```java
  // Instead of :
  if (items[i].quality < 50) {
    items[i].quality = items[i].quality + 1;
  }
  // Use :
  tryIncreaseItemQuality(Item item)
```
Ideal would be `item.tryIncreaseQuality()`, but rules said that `Item` class cannot be changed.
