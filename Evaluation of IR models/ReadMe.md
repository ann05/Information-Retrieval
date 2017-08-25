The goal of this project is to implement various project models, to evaluate the system and to improve the search result based on the students understanding and its implementation.

1. I have implemented the default settings of the VSM, BM25 and DFR model.

2. The classes used for each model in the schema.xml file are as follows:

3. Vector Space Model: org.apache.lucene.search.similarities.ClassicSimilarityFactory

4. BM25 Model: org.apache.lucene.search.similarities.BM25SimilarityFactory

5. DFR Model: org.apache.lucene.search.similarities.DFRSimilarityFactory

6. Recall is a measure of the ability of a system to present all relevant items.

7. recall = number of relevant items retrieved / number of relevant items in collection

8. Precision is a measure of the ability of a system to present only relevant items.

9. precision = number of relevant items retrieved / total number of items retrieved

10. The quality of the IR model was initially evaluated using these factors, but I have used MAP (Mean Average Precision), GM_AP (Geometeric Mean Precision) and BPREF for this model.

11. Vector Space Model: Documents and queries are represented as vectors in a common vector space. ClassicSimilarity is Lucene’s original scoring implementation, based upon the Vector Space Model. It can be implemented in Solr using the following declaration in managed-schema.xml in the core:

BM25 Model:

12. Okapi BM25 (BM stands for Best Match) is based on the probabilistic retrieval framework developed by Stephen E. Robertson, Karen Sparck Jones and others.
13. It ranks documents based on the query terms appearing in each document and is independent of their relative proximity.
14. It can be implemented in Solr using the following declaration in managed-schema.xml in the core: 1.5 0.75
15. K1 can be any value in the range [1.2,2.0] and b = 0.75 usually.

Divergence from Randomness Model:

16. Term weights are computed by measuring the divergence between a term distribution produced by a random process and the actual term distribution.
17. There are three components in DFR: BasicModel AfterEffect Normalization
18. The default values to be taken for these components are already mentioned in the scope of this project, as “BasicModelG”, “Bernoulli” first normalization and “H2” second normalization.
19. It can be implemented in Solr using the following declaration in managed-schema.xml in the core: 6.75 H2 B G
Optimized Scores:

VSM:

20. The MAP value for the given set of queries using the indexed tweets is found to be optimized when synonyms, stopwords and stemmers are removed from the data while being indexed.
21. MAP for this implementation is 0.6858.
BM25:

22. Synonyms, stopwords and stemming tokenizers are included in the optimized implementation of this model too so that query expansion will help improve the performance of the model.
23. The MAP score is 0.6995 for the set of parameters, k1 - 0.6, b - 0.2.
DFR:

24. Synonyms, stopwords and stemming tokenizers are included in the optimized implementation of this model too so that query expansion will help improve the performance of the model.
25. The MAP score when c is 8, normalization is H2, afterEffect is B and basicModel is G is 0.7043.
