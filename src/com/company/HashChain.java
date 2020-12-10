package com.company;
import java.io.*;

// This class demonstrates hash table with seperate chaining

public class HashChain {
    public static void main(String[] args) throws IOException {

        // test insert(data)
        // testInsertion();


        // test delete(data)
        // testDeletion();

        // test find(data)
        testSearchFind();


    } // ends main

    private static void testInsertion() {

        int aKey;
        Link aDataItem;
        int size, n, keysPerCell = 100;

        // declare sizes:
        size = 5;
        n = 5;

        // make table:
        HashTable theHashTable = new HashTable(size);

        // insert 10 values into the table:
        aKey = 10;
        aDataItem = new Link(aKey);
        theHashTable.insert(aDataItem);

        aKey = 20;
        aDataItem = new Link(aKey);
        theHashTable.insert(aDataItem);

        aKey = 30;
        aDataItem = new Link(aKey);
        theHashTable.insert(aDataItem);

        aKey = 31;
        aDataItem = new Link(aKey);
        theHashTable.insert(aDataItem);

        // display the table:
        theHashTable.displayTable();
    } // ends testInsertion()

    private static void testDeletion() {

        int aKey;
        Link aDataItem;
        int size, n, keysPerCell = 100;

        // declare sizes:
        size = 5;
        n = 5;

        // make table:
        HashTable theHashTable = new HashTable(size);

        // insert 10 values into the table:
        aKey = 10;
        aDataItem = new Link(aKey);
        theHashTable.insert(aDataItem);

        aKey = 20;
        aDataItem = new Link(aKey);
        theHashTable.insert(aDataItem);

        aKey = 30;
        aDataItem = new Link(aKey);
        theHashTable.insert(aDataItem);

        aKey = 31;
        aDataItem = new Link(aKey);
        theHashTable.insert(aDataItem);

        // display the table:
        theHashTable.displayTable();

        System.out.println("Deleting: 31");
        theHashTable.delete(31);
        theHashTable.displayTable();
    }

    private static void testSearchFind() {

        int aKey;
        Link aDataItem;
        int size, n, keysPerCell = 100;

        // declare sizes:
        size = 5;
        n = 5;

        // make table:
        HashTable theHashTable = new HashTable(size);

        // insert 10 values into the table:
        aKey = 10;
        aDataItem = new Link(aKey);
        theHashTable.insert(aDataItem);

        aKey = 20;
        aDataItem = new Link(aKey);
        theHashTable.insert(aDataItem);

        aKey = 30;
        aDataItem = new Link(aKey);
        theHashTable.insert(aDataItem);

        aKey = 31;
        aDataItem = new Link(aKey);
        theHashTable.insert(aDataItem);

        // display the table:
        theHashTable.displayTable();
        System.out.println("Find: 20 ");

        Link checkIfExists = theHashTable.find(25);
        if(checkIfExists != null){
            System.out.print("Found the link at index:  " + checkIfExists);
        } else {
            System.out.println("Couldn't find this link in the index");
        }
    } // ends TestSearchFind
} // ends HashChain class
////////////////////////////////////////////////////////////////////////
class Link
{
    // private members:
    private int iData;    // data item
    public Link next;       // next link in list

    // -----------------------------------------------------------------
    // constructor:
    public Link(int iT){
        iData = iT;
    }

    // -----------------------------------------------------------------
    // accessor methods:
    public int getKey(){
        return  iData;
    }

    // -----------------------------------------------------------------
    // displayLink():
    public void displayLink(){
        System.out.println(iData + " ");
    }
} // ends Link class
////////////////////////////////////////////////////////////////////////

class SortedList{

    private Link first; // reference to the first list in the item

    // -----------------------------------------------------------------
    public void SortedList(){
        first = null;
    }

    // -----------------------------------------------------------------
    public void insert(Link theLink){

        // insert link in order:
        int key = theLink.getKey();

        // start at first:
        Link previous =  null;
        Link current = first;

        // until end of list
        while(current != null && key > current.getKey()){
            previous = current;
            current = current.next;

            // go to the next item
        }

        // if beginning of list:
        // first -> new link
        // and if its not at the beginning
        // prev -> new link
        // new lik -> current
        if (previous == null){
            first = theLink;
        } else {
            previous.next = theLink;
        }

        theLink.next = current;
    } // ends insert

    // -----------------------------------------------------------------
    // delete()
    public void delete(int key){

        // delete link
        // assuming this is a non empty list
        // start at first
        Link previous = null;
        Link current = first;

        // until the end of the list
        while(current != null && key != current.getKey()){

            // key == current:
            previous = current;
            current = current.next; // go to the next link
        }

        // disconnect link
        // if its the beginning of the list
        // delete the first link
        // otherwise if its not at the beginning
        // delete the current link:
        if(previous == null){
            first = first.next;
        } else
            previous.next = current.next;
    } // end delete()

    // -----------------------------------------------------------------
    // find()
    public Link find(int key){

        // find the link:
        // start at first:
        Link current = first;

        // until end of the list:
        while(current != null && current.getKey() <= key){
            if(current.getKey() == key){
                return current;
            }
            current = current.next;
        }
        return null;
    } // end find()

    // -----------------------------------------------------------------
    // displayList()
    public void displayList(){
        System.out.println("List (first -> last: )");

        Link current = first; // start at the beginning of the list

        while(current != null){
            current.displayLink(); // print data
            current = current.next; // move to next link
        }
        System.out.println("");
    }
} // end Sorted List class

////////////////////////////////////////////////////////////////////////
class HashTable{

    // array of lists:
    private SortedList[] hashArray; // array of lists
    private int arraySize;

    // -----------------------------------------------------------------
    // constructor:
    public HashTable(int size){
        arraySize = size;

        // create array:
        hashArray = new SortedList[arraySize];

        // fill array:
        for(int j = 0; j < arraySize; j++){

            // with lists:
            hashArray[j] = new SortedList();
        }
    } // ends HashTable constructor

    // -----------------------------------------------------------------
    // displayTable():
    public void displayTable(){

        // for each cell:
        for(int j = 0; j < arraySize; j++){

            // display cell number:
            System.out.println(j + ". ");
            hashArray[j].displayList(); // display list
        }
    } // ends displayTable()

    // -----------------------------------------------------------------
    // hashFunc:
    public int hashFunc(int key){
        return key % arraySize;
    } // end hashFunc()

    // -----------------------------------------------------------------
    // insert():
    public void insert(Link theLink){

        int key = theLink.getKey();
        int hashVal = hashFunc(key); // hash the key

        hashArray[hashVal].insert(theLink); // insert at hashVal
    } // end insert()

    // -----------------------------------------------------------------
    // delete():
    public void delete(int key){

        // delete a link:
        int hashVal = hashFunc(key);    // hash the key
        hashArray[hashVal].delete(key); // delete link
    } // end delete()

    // -----------------------------------------------------------------
    // find():
    public Link find(int key){

        // find link:
        int hashVal = hashFunc(key); // hash the key

        Link theLink = hashArray[hashVal].find(key); // get link
        return theLink; // return link
    } // ends inf()
} // ends HashTable
////////////////////////////////////////////////////////////////////////
