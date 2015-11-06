package com.jobplanet.search.analysis.kr;

import org.apache.lucene.analysis.Analyzer;

import java.io.Reader;

/**
 * Created by hosang on 2015. 11. 6..
 */
public class TwitterKoreanAnalyzer extends Analyzer {

    @Override
    protected TokenStreamComponents createComponents(String s, Reader reader) {
        return new TokenStreamComponents(new TwitterKoreanTokenizer(reader));
    }
}
