 shelf = makeEmptySmartshelf();
        testObjectEqual("toString", "[]", shelf.toString());
        shelf = makeEmptySmartshelf();
        testObjectEqual("greatestItem", null, shelf.greatestItem());
        shelf = makeEmptySmartshelf();
        testIntEqual("numberOfItems", 0, shelf.numberOfItems());
        shelf = makeEmptySmartshelf();
        testIntEqual("makeNewSmartshelfWith", 0, shelf.makeNewSmartshelfWith(120).numberOfItems());
        shelf = makeEmptySmartshelf();
        testIntEqual("totalWeightInGrammes", 0, shelf.totalWeightInGrammes());
        shelf = makeEmptySmartshelf();
        testDoubleEqual("averageWeightInGrammes", -1.0, shelf.averageWeightInGrammes());
