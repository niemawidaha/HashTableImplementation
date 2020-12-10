package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        // Test 1: Add values to a hash table:
        testInsertion();

        // Test 2: Delete values from the hash table given a key:
        testDeletion();

        // Test 3: Find values in the hash table given a key
        testSearchFind();
    } // ends main()

    private static void testInsertion() {

        // create members:
        int aKey;
        Node aNode;
        int size = 5, n = 5, keysPerCell = 100;

        // make table:
        HashTable theHashTable = new HashTable(size);

        // insert values into the table:
        // capping it at 5
        aKey = 10;
        aNode = new Node(aKey,"Elon Musk");
        theHashTable.insert(aNode);

        aKey = 11;
        aNode = new Node(aKey,"Kimberly Bryant");
        theHashTable.insert(aNode);

        aKey = 12;
        aNode = new Node(aKey,"Gary Vaynerchuck");
        theHashTable.insert(aNode);

        aKey = 20;
        aNode = new Node(aKey,"Nikola Tesla");
        theHashTable.insert(aNode);

        aKey = 21;
        aNode = new Node(aKey,"Jessica Matthews");
        theHashTable.insert(aNode);

        aKey = 33;
        aNode = new Node(aKey,"Vitalik Buterin");
        theHashTable.insert(aNode);

        aKey = 43;
        aNode = new Node(aKey,"Satoshi");
        theHashTable.insert(aNode);

        System.out.println("-----------------------------------------------------------");
        System.out.println("TEST 1: The insertion function:");
        System.out.println("-----------------------------------------------------------");
        theHashTable.displayTable();
    } // ends testInsertion()

    private static void testDeletion() {

        // create members:
        int aKey;
        Node aNode;
        int size = 5, n = 5, keysPerCell = 100;

        // make table:
        HashTable theHashTable = new HashTable(size);

        // insert values into the table:
        // capping it at 5
        aKey = 10;
        aNode = new Node(aKey,"Elon Musk");
        theHashTable.insert(aNode);

        aKey = 11;
        aNode = new Node(aKey,"Kimberly Bryant");
        theHashTable.insert(aNode);

        aKey = 12;
        aNode = new Node(aKey,"Gary Vaynerchuck");
        theHashTable.insert(aNode);

        aKey = 20;
        aNode = new Node(aKey,"Nikola Tesla");
        theHashTable.insert(aNode);

        aKey = 21;
        aNode = new Node(aKey,"Jessica Matthews");
        theHashTable.insert(aNode);

        aKey = 33;
        aNode = new Node(aKey,"Neil DeGrasse Tyson");
        theHashTable.insert(aNode);

        System.out.println("-----------------------------------------------------------");
        System.out.println("TEST 2: the deletion function - BEFORE ");
        System.out.println("-----------------------------------------------------------");
        // display the table:
        theHashTable.displayTable();
        System.out.println("-----------------------------------------------------------");
        System.out.println("TEST 2: Deleting key: 12 - AFTER ");
        System.out.println("-----------------------------------------------------------");

        theHashTable.delete(12);
        theHashTable.displayTable();

    } // ends testDeletion()

    private static void testSearchFind() {

        // create members:
        int aKey;
        Node aNode;
        int size = 5, n = 5, keysPerCell = 100;

        // make table:
        HashTable theHashTable = new HashTable(size);

        // insert values into the table:
        // capping it at 5
        aKey = 10;
        aNode = new Node(aKey,"Elon Musk");
        theHashTable.insert(aNode);

        aKey = 11;
        aNode = new Node(aKey,"Kimberly Bryant");
        theHashTable.insert(aNode);

        aKey = 12;
        aNode = new Node(aKey,"Gary Vaynerchuck");
        theHashTable.insert(aNode);

        aKey = 20;
        aNode = new Node(aKey,"Nikola Tesla");
        theHashTable.insert(aNode);

        aKey = 21;
        aNode = new Node(aKey,"Jessica Matthews");
        theHashTable.insert(aNode);

        aKey = 33;
        aNode = new Node(aKey,"Neil DeGrasse Tyson");
        theHashTable.insert(aNode);

        // display the table:

        System.out.println("-----------------------------------------------------------");
        System.out.println("TEST 3: The search + find function - With value that exists");
        System.out.println("-----------------------------------------------------------");

        theHashTable.displayTable();
        System.out.println("Find Neil DeGrasse Tyson");

        Node checkIfExists = theHashTable.find(33);
        if(checkIfExists != null){
            System.out.println("Found the node at hash index: " + checkIfExists.getKey());
        } else {
            System.out.println("Couldnt find the node within the hash table.");
        }


        System.out.println("-----------------------------------------------------------");
        System.out.println("TEST 3: The search + find function - With value that doesn't exist");
        System.out.println("-----------------------------------------------------------");

        theHashTable.displayTable();
        System.out.println("Find Satoshi");

        checkIfExists = theHashTable.find(24);
        if(checkIfExists != null){
            System.out.println("Found the node at hash index: " + checkIfExists.getKey());
        } else {
            System.out.println("Couldn't find the node within the hash table.");
        }
        System.out.println("-----------------------------------------------------------");
    } // ends testSearchFind()
} // ends Main class

////////////////////////////////////////////////////////////////////////
class HashTable {
    //write the missing parts of the methods for the hash table below
    //You can (and should!) use the methods for the linked list
    //the NodeList class is below so that you can read through it

    private final NodeList[] hashArray; //array of lists
    private final int arraySize;

    // -----------------------------------------------------------------
    // constructor:
    public HashTable(int size) {
        //set arraySize
        arraySize = size;

        //create hashArray
        hashArray = new NodeList[arraySize];

        //initialize the lists for each index in the array
        for(int j = 0; j < arraySize; j++){

            hashArray[j] = new NodeList();
        }
    } // end constructor

    // -----------------------------------------------------------------
    // insert(node):
    public void insert(Node newNode) {
        //insert a new node
        //use chaining to resolve collisions

        int key = newNode.getKey();
        int hashValue = hashFunc(key); // hash the key

        hashArray[hashValue].insert(newNode); // insert at hashval
    } // end insert()

    // -----------------------------------------------------------------
    // delete(node):
    public void delete(int key) {

        // delete a node:
        int hashVal = hashFunc(key); // hash the key
        hashArray[hashVal].delete(key); // delete the node
    } // end delete()

    // -----------------------------------------------------------------
    // find(node):
    public Node find(int key) {

        // find the node with the given key if it exists
        int hashValue = hashFunc(key); // hash the key

        Node findNode = hashArray[hashValue].find(key);

        return findNode;
    }

    // -----------------------------------------------------------------
    public int hashFunc(int key) {
        //a very simple hash function
        return key % arraySize;
    }

    // -----------------------------------------------------------------
    public void displayTable() {
        for (int j=0; j< arraySize; j++) {
            //for each cell, we'll print the cell number then the list
            System.out.print(j + ": ");
            hashArray[j].displayList();
        }
    }
}

////////////////////////////////////////////////////////////////////////
class Node {

    //node for an id and name, used in a doubly-linked list
    private final int key;
    private final String name;
    public Node next;
    public Node prev;

    // -----------------------------------------------------------------
    public Node(int idNum, String nodeName) {
        key = idNum;
        name = nodeName;
        next = null;
        prev = null;
    }

    // -----------------------------------------------------------------
    public int getKey() {
        return key;
    }

    // -----------------------------------------------------------------
    public String getName() {
        return name;
    }

    // -----------------------------------------------------------------
    public void displayNode() {
        System.out.println("key: " + key + " val: " + name + " ");
    }
}

////////////////////////////////////////////////////////////////////////
class NodeList {
    //a doubly-linked list
    private Node first;

    public void NodeList() {
        first = null;
    }

    // -----------------------------------------------------------------
    // insert(): inserts an item into a hash table
    public void insert(Node newNode) {

        int key = newNode.getKey();

        // start at the first value:
        Node previous = null;
        Node current = first;

        // iterate until the end of the list
        while(current != null && key > current.getKey()){

            previous = current;
            current = current.next;

            // go to the next item
        }

        if(previous == null){
            first = newNode;
        } else {
            previous.next = newNode;
        }

        newNode.next = current;
    } // ends insert()

    // -----------------------------------------------------------------
    // delete(): removes an item from a hash table
    public void delete(int key) {

        // delete link:
        // assuming this is a non empty list:
        // start at the first val
        Node previous = null;
        Node current = first;

        // iterate until the end of the list to find the node
        while(current != null && key != current.getKey()){

            // key == current
            previous = current;
            current = current.next; // jump to the next node
        }

        // disconnect the link
        if(previous == null){
            first = first.next;
        } else
            previous.next = current.next;
    } // ends delete()

    // -----------------------------------------------------------------
    // find() locates an item in a hash table
    public Node find(int key) {

        // start at the first value
        Node current = first;

        // until the end of the list
        while (current != null && current.getKey() <= key) {

            if(current.getKey() == key){
                return current;
            }
            current = current.next;
        }
        return null;
    } // ends find()

    // -----------------------------------------------------------------
    public void displayList() {
        System.out.println("List from first to last: ");
        Node current = first;
        while (current != null) {
            current.displayNode();
            current = current.next;
        }
        System.out.println();
    }
}

