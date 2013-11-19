This is a possible solution to the paint shop dilemma.

# Getting Started

[Download Groovy](http://groovy.codehaus.org/Download?nc) if you do not have it.


    $ groovy Paint -[v]f scenario.txt

    This will process the provided file (-f) and output the paint batches or "No solution exists" . For some verbose output use the -v option.

## Test files

There are a number of test samples called *.txt in the directory.

## Overview of how I approached the problem

The batches that are to be made up can be described using a Map. The colour been the key and either 'G' or 'M' is the value.
I created one object called Customer with a map of likes and a method to indicate if the customer was 'fussy'. The customer is
'fussy' if they only like one batch. I decided to use groovy as I did not want to write loads of Java code. I thought about skipping all the
non-matte customers , but that was a bad idea :) . In the end, I settled for an algorithm that is designed to make as much sense as possible
to the reader. I don't do too much checking of the format of the input file.


## Summary of algorithm

The algorithm

* For each line in the file (except first) create a Customer with a map of likes with the 'M' values at the end of the list. e.g. [1:'G',4:'M']
* Sort these customers into fussy first
* Process the customers and create batches as we go. We have No solution exists when not every customer is happy.


For example

Given the following input file....

5
2 M
5 G
1 G
5 G 1 G 4 M
3 G
5 G
3 G 5 G 1 G
3 G
2 M
5 G 1 G
2 M
5 G
4 M
5 G 4 M

Sample Output (with verbose (-v) option)

- The shopkeeper has to create 5 batches of either matte or gloss
- There are 14 customers
- Each customer has a list of batches they like.
- Now we can sort each of these so that the 'fussy' (only one like) ones are processed first and the
- reasonable ones are processed later.
In addition, we will keep the matte selections till last..
Processing --> Customer{likes=[2:M]}
Processing --> Customer{likes=[5:G]}
Processing --> Customer{likes=[1:G]}
Processing --> Customer{likes=[3:G]}
Processing --> Customer{likes=[5:G]}
Processing --> Customer{likes=[3:G]}
Processing --> Customer{likes=[2:M]}
Processing --> Customer{likes=[2:M]}
Processing --> Customer{likes=[5:G]}
Processing --> Customer{likes=[4:M]}
Processing --> Customer{likes=[5:G, 1:G]}
Processing --> Customer{likes=[5:G, 4:M]}
Processing --> Customer{likes=[5:G, 1:G, 4:M]}
Processing --> Customer{likes=[3:G, 5:G, 1:G]}
[G, M, G, M, G]

So, shopkeeper does 5 batches color 1(Gloss), 2(Matt), 3(Gloss), 4(Matt) and 5(Gloss)  and everyone should be happy!




