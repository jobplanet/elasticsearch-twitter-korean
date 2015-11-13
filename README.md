Twitter Korean Analyzer for Lucene and ElasticSearch plugin
===========================================================

**THIS IS NOT A STABLE VERSION YET.**

## Code Status

[![Build Status](https://travis-ci.org/jobplanet/elasticsearch-twitter-korean.svg?branch=master)](https://travis-ci.org/jobplanet/elasticsearch-twitter-korean)


This *Twitter Korean Analyzer* includes Lucene integration of [https://github.com/twitter/twitter-korean-text](twitter-korean-text) and its Elasticsearch plugin.
The plugin includes following modules: 

- Analyzers
  - `twitter_korean_analyzer`
- Tokenizers
  - `twitter_korean_tokenizer`
  - `twitter_korean_phrase_tokenizer`
- Token Filters
  - `twitter_korean_stop_filter`

To package as a jar with dependencies, run maven command with following goals.

```
mvn clean package assembly:single
# The output zip file will be generated in target/releases
```


## Twitter Korean Text Module

[https://github.com/twitter/twitter-korean-text](https://github.com/twitter/twitter-korean-text)

## License

Copyright 2015 JOBPLANET.

Licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0