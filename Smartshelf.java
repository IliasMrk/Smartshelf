import java.util.ArrayList;
import java.util.Arrays;

/**
 * A Smartshelf holds zero or more Items and can provide information about the
 * Items. One can add Items to a Smartshelf during its lifetime, empty the
 * Smartshelf, create a copy which contains Items only up to a certain weight,
 * and make various queries to the Smartshelf. (Thus, the number of Items that
 * will be stored by a Smartshelf object is not yet known when the new object
 * * is created, and
 *
 * 
 * @author Ilias Markou
 */
public class Smartshelf {

    // variable to store the weight of the current item
    private double weight;
    // variable to keep count of items
    private int itemsCount = 0;
    // variable to store the total weight of the items
    private int totalWeight = 0;
    // variable to store the greatest item
    private Item greatestItem;
    // variable to use as temporary length and partially fill the array
    private final int TEMP_LENGTH = 10;

    /*
     * partially filled items Array
     */
    private Item[] itemsArray = new Item[TEMP_LENGTH];

    /* Constructors */

    /**
     * Constructs a new Smartshelf without any Items.
     */

    public Smartshelf() {

    }

    /**
     * Constructs a new Smartshelf containing the non-null Items in items.
     * The items array may be modified by the caller afterwards without
     * affecting this Smartshelf, and it will not be modified by this
     * constructor.
     *
     * @param items must not be null; non-null elements are added to the
     *              constructed Smartshelf
     */
    public Smartshelf(Item[] items) {
        // call to the constructor without items
        this();
        // adds all items supplied to this constructor using the addAll method to
        // firstly remove or non-null items

        this.addAll(items);

    }

    /* Modifiers */

    /**
     * Adds an Item e to this Smartshelf if e is not null; does not modify this
     * Smartshelf otherwise. Returns true if e is not null, false otherwise.
     *
     * @param e an item to be added to this Smartshelf
     * @return true if e is not null, false otherwise
     */
    public boolean add(Item e) {

        // calls the method to see if the item added is non-null and if so, adds them to
        // the smartshelfs array
        if (itemIsNotNull(e) == true) {
            // items array in this smartshelf to store all the items inserted using the
            // itemsCount as position
            this.itemsArray[itemsCount] = e;
            // stores the weight for each item added
            this.weight = e.getWeightInGrammes();
            // increments the total weight according to the weight inserted from the added
            // item
            this.totalWeight += this.weight;
            // increments the items count for every item added to this array
            this.itemsCount++;

            return true;

        } else {

            return false;
        }
    }

    /**
     * Adds all non-null Items in items to this Smartshelf.
     *
     * @param items contains the Item objects to be added to
     *              this Smartshelf; must not be null (but may contain null)
     * @return true if at least one element of items is non-null;
     *         false otherwise
     */
    public boolean addAll(Item[] items) {
        // variable to store the count of non-null items
        int nonNull = 0;
        // loops through the items array in the method
        for (Item item : items) {
            // calls add method to check if value is not null and add them to this
            // smartshelf
            if (add(item) == true) {
                // if value is added it increments the count of non-null values
                nonNull++;
            }

        }
        /**
         * if at least one element is non-null returns true,
         * otherwise returns false
         */
        if (nonNull > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Empties this Smartshelf to a Smartshelf that contains 0 Items.
     */
    public void empty() {
        /**
         * empties the items counted so far, empties the array and likewise, the total
         * weight to 0
         */
        this.itemsCount = 0;
        this.itemsArray = new Item[TEMP_LENGTH];

        this.totalWeight = 0;

    }

    /**
     * Removes certain Items from this Smartshelf. Exactly those Items are kept
     * whose weight in grammes is less than or equal to the specified maximum
     * weight in grammes.
     *
     * @param maxItemWeightInGrammes the maximum weight in grammes for the
     *                               Items that are kept
     */
    public void keepOnlyItemsWith(int maxItemWeightInGrammes) {
        /**
         * to keep only items with the specific weight, we first decrease the count to 0
         * and then increment the count for each item that meets the weight requirements
         * by adding it to the smartshelf using the add method,otherwise we deduct the
         * weight of the items that did not meet the required weight from the total
         * weight
         */
        this.itemsCount = 0;

        for (Item e : this.itemsArray) {
            if ((itemIsNotNull(e) == true) && (e.getWeightInGrammes() <= maxItemWeightInGrammes)) {
                add(e);
            } else if ((itemIsNotNull(e) == true) && (e.getWeightInGrammes() > maxItemWeightInGrammes)) {

                this.totalWeight -= e.getWeightInGrammes();
            }
        }
    }

    /* Accessors */

    /**
     * Returns the number of non-null Items in this Smartshelf.
     *
     * @return the number of non-null Items in this Smartshelf
     */
    public int numberOfItems() {
        // returns the number of items counted in this smartshelf

        return this.itemsCount;

    }

    /**
     * Returns the total weight of the Items in this Smartshelf.
     *
     * @return the total weight of the Items in this Smartshelf.
     */
    public int totalWeightInGrammes() {
        // return the total weight of items in this smartshelf

        return this.totalWeight;

    }

    /**
     * Returns the average weight in grammes of the (non-null) Items
     * in this Smartshelf. In case there is no Item in this Smartshelf,
     * -1.0 is returned.
     *
     * For example, if this Smartshelf has the contents
     * new Item("clock", 400)
     * and
     * new Item("textbook", 395),
     * the result is: 397.5
     *
     * @return the average weight of the Items in this Smartshelf,
     *         or -1.0 if there is no such Item.
     */
    public double averageWeightInGrammes() {

        // converting total weight to double
        double itemsWeight = this.totalWeight;
        // checking if smartshelf has more than 0 items and if so it returns the total
        // weight else -1
        if (this.itemsCount > 0) {
            return itemsWeight / numberOfItems();
        } else {
            return -1;
        }
    }

    /**
     * Returns the greatest Item in this Smartshelf according to the
     * natural ordering of Item given by its compareTo method;
     * null if this Smartshelf does not contain any Item objects
     *
     * @return the greatest Item in this Smartshelf according to the
     *         natural ordering of Item given by its compareTo method;
     *         null if this Smartshelf does not contain any Item objects
     */
    public Item greatestItem() {
        // loop through this array in smartshelf
        for (Item e : this.itemsArray) {
            // if the item in the array is not null and the greatest item is null, the value
            // of e is assigned to the greatest item
            if ((itemIsNotNull(e) == true) && (itemIsNotNull(greatestItem) == false)) {
                this.greatestItem = e;
            }
            // if both the item e and the greatest item are non-null we compare them and
            // assigned the largest value to greatest item
            else if ((itemIsNotNull(greatestItem) == true) && (itemIsNotNull(e) == true)) {
                if (e.compareTo(greatestItem) >= 0) {
                    this.greatestItem = e;
                }
            }
        }
        return this.greatestItem;
    }

    /**
     * Returns true if Item e is not null, false otherwise
     * 
     * @param Item
     * @return true if not null, false otherwise
     * @author Ilias Markou
     */
    public boolean itemIsNotNull(Item e) {
        // boolean to use for returning true if item is not null else false otherwise
        boolean result = false;
        // if item is not null returns true
        if (e != null) {
            result = true;
        }
        // else it returns false
        else if (e == null) {
            result = false;
        }
        return result;
    }

    /**
     * Returns a new Smartshelf with exactly those Items of this Smartshelf
     * whose weight is less than or equal to the specified method parameter.
     * Does not modify this Smartshelf.
     *
     * @param maxItemWeightInGrammes the maximum weight in grammes for the
     *                               Items in the new Smartshelf
     * @return a new Smartshelf with exactly those Items of this Smartshelf
     *         whose weight is less than or equal to the specified method parameter
     */
    public Smartshelf makeNewSmartshelfWith(int maxItemWeightInGrammes) {
        // create a new smartshelf with exactly those items in this array
        Smartshelf shelf = this;
        // call method to keep in this smartshelf only the items with the specific
        // weight required
        shelf.keepOnlyItemsWith(maxItemWeightInGrammes);

        return shelf;

    }

    /**
     * Returns a string representation of this Smartshelf. The string
     * representation consists of a list of the Smartshelf's contents,
     * enclosed in square brackets ("[]"). Adjacent Items are
     * separated by the characters ", " (comma and space). Items are
     * converted to strings as by their toString() method. The
     * representation does not mention any null references.
     *
     * So for
     *
     * Item i1 = new Item("Pen", 15);
     * Item i2 = new Item("Letter", 20);
     * Item i3 = null;
     * Item[] items = { i1, i2, i3, i1 };
     * Smartshelf k = new Smartshelf(items);
     *
     * the call k.toString() will return one of the three following Strings:
     *
     * "[(Pen, 15g), (Pen, 15g), (Letter, 20g)]"
     * "[(Pen, 15g), (Letter, 20g), (Pen, 15g)]"
     * "[(Letter, 20g), (Pen, 15g), (Pen, 15g)]"
     *
     * @return a String representation of this Smartshelf
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        // create an arraylist of items to store the items
        ArrayList<Item> namesList = new ArrayList<Item>();
        // loop through this array of items and if there is no empty value add it to the
        // arraylist
        for (Item e : itemsArray) {
            if (e != null) {
                namesList.add(e);

            }
        }
        // return the list to string
        return (namesList).toString();
    }

    /* class methods */

    /**
     * Class method to return a Smartshelf with the highest total weight from an
     * array of Smartshelfs. If we have an array with a Smartshelf of 3000 grammes
     * and a Smartshelf with 4000 grammes, the Smartshelf with 4000 grammes is
     * returned.
     *
     * Entries of the array may be null, and your method should work also in
     * the presence of such entries. So if in the above example we had an
     * additional third array entry null, the result would be exactly the same.
     *
     * If there are several Smartshelfs with the same weight, it is up to the
     * method implementation to choose one of them as the result (i.e., the
     * choice is implementation-specific, and method users should not rely on
     * any particular behaviour).
     *
     * @param Smartshelfs must not be null, but may contain null
     * @return one of the Smartshelfs with the highest total weight among all
     *         Smartshelfs in the parameter array; null if there is no non-null
     *         reference in Smartshelfs
     */
    public static Smartshelf heaviestSmartshelf(Smartshelf[] Smartshelfs) {

        // create a new smartshelf to store the heaviest
        Smartshelf heaviest = new Smartshelf();
        /** loop through smartshelfs and compare them to find the heaviest */
        for (int i = 0; i < Smartshelfs.length; i++) {
            if (Smartshelfs[i].totalWeightInGrammes() > heaviest.totalWeightInGrammes()) {
                heaviest = Smartshelfs[i];
            }
        }

        return heaviest;

    }
}
