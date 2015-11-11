Twitter Korean Analyzer for Lucene and ElasticSearch plugin
===========================================================

**THIS IS NOT A STABLE VERSION YET.**

This *Twitter Korean Analyzer* includes Lucene integration of [https://github.com/twitter/twitter-korean-text](twitter-korean-text) and its Elasticsearch plugin.
The plugin includes the `twitter_korean_analyzer` analyzer and `twitter_korean_tokenizer`, `twitter_korean_phrase_tokenizer` tokenizer.

To package as a jar with dependencies, run maven command with following goals.

```
mvn clean package assembly:single
# The output zip file will be generated in target/releases
```


Twitter Korean Text Module
==============================

[https://github.com/twitter/twitter-korean-text](https://github.com/twitter/twitter-korean-text)
