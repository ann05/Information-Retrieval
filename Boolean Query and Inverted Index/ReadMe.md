1. Java program for inverted index and boolean query.

Steps:

Step 1: Interact with Lucene Index and Build Your Own Inverted Index
As the examples in textbook, postings lists should be stored as Linked Lists.
You will need to construct your own index in which postings of each term should be ordered by
increasing document IDs. For example:
term1 doc1 doc2 doc4
term2 doc2 doc4 doc7
In order to use the APIs to talk to the given index and get the information you need, import the Lucene
module to your project. “lucene-core-6.2.0.jar” can be downloaded from here: https://lucene.apache.org/
core/ .
Note:
• You are supposed to ONLY use the methods included in package org.apache.lucene.index to get
information for your index. DO NOT use other classes which can answer the queries directly, such as
IndexSearcher.
• Take a look at the java doc for the class IndexReader (https://lucene.apache.org/core/6_2_1/core/org/
apache/lucene/index/IndexReader.html ).

Step 2: Boolean Query Processing
1. Get postings lists
This method retrieves the postings lists for each of the given query terms. Input of this method
will be a set of terms: term0, term1, … , termN. It should output the postings for each term in the
following format:
GetPostings
term0
Postings list: 1000 2000 3000 … (by increasing document IDs)
GetPostings
term1
Postings list: 1000 3000 6000 … (by increasing document IDs)
…
GetPostings
termN
Postings list: 2000 7000 8000 … (by increasing document IDs)
2. Term-at-a-time AND query
This method is to implement multi-term boolean AND query on the index using term-at-a-time
strategy (TAAT). Input of this function will be a set of terms: term0, term1, …, termN. Output
format of this method should be:
TaatAnd
term0 term1 … termN
Results: 1000 2000 3000 … (by increasing document IDs)
Number of documents in results: x
Number of comparisons: y
If the result of the query is empty, output:
TaatAnd
term0 term1 … termN
Results: empty
Number of documents in results: 0
Number of comparisons: y
3. Term-at-a-time OR query
This function is to implement multi-term boolean OR query on the index using TAAT. Input of
this function will be a set of query terms: term0, term1, … , termN. Output format of this method
should be:
TaatOr
term0 term1 … termN
Results: 1000 2000 3000 … (by increasing document IDs)
Number of documents in results: x
Number of comparisons: y
4. Document-at-a-time AND query
This function is to implement multi-term boolean AND query on the index using document-at-atime
strategy (DAAT). Input of the function will be a set of query terms: term0, term1, …, termN.
Output the following to the output file:
DaatAnd
term0 term1 … termN
Results: 1000 2000 3000 … (by increasing document IDs)
Number of documents in results: x
Number of comparisons: y
If the result of the query is empty, output:
DaatAnd
term0 term1 … termN
Results: empty
Number of documents in results: 0
Number of comparisons: y
5. Document-at-a-time OR query
This function is to implement multi-term boolean OR query on the index using DAAT. Input of
this function will be a set of query terms: term0, term1, …, termN. Output the following to the
output file:
DaatOr
term0 term1 … termN
Results: 1000 2000 3000 … (by increasing document IDs)
Number of documents in results: x
Number of comparisons: y
