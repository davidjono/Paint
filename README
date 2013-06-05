This is a possible solution to the paint shop dilemma.

# Getting Started

[Dowload Groovy](http://groovy.codehaus.org/Download?nc) if you do not have it.


usage: groovy Paint -f[h] filename
 -f,--filename <filename>   the file to parse
 -h,--help                  usage information
 -v,--verbose               Verbose output

    $ groovysh Paint  -[v]f scenario.txt

## Test files

There are a number of test samples called *.txt in the directory.

## Overview of how I approached the problem

The batches that are to be made up can be described using a Map. The colour been the key and either 'G' or 'M' is the value.
I created one object called Customer with a map of likes and a method to indicate if the customer was 'fussy'. The customer is
'fussy' if they only like one batch. I decided to use groovy as I did not want to write loads of Java code. I thought about skipping all the
non-matte customers , but that was a bad idea. In the end I settled for an algorithm that is designed to make as much sense as possible
to the reader. I don't do too much checking of the format of the input file.


## Summary of algorithm

The algorithm

* For each line in the file (except first) create a Customer with a map of likes with the 'M' values at the end of the list. e.g. [1:'G',4:'M']
* Sort these customers into fussy first
* Process the customers and create batches as we go. We have No solution exists when not every customer is happy.
