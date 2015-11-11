package com.jobplanet.search.analysis.kr;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.analysis.util.FilteringTokenFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hosang on 2015. 11. 11..
 */
public class TwitterKoreanStopFilter extends FilteringTokenFilter {

    private final CharArraySet stopWords;
    private final CharTermAttribute termAtt;

    List stopWordList = Arrays.asList(new String[]{"a", "an", "and", "are", "as", "at", "be", "but", "by",
                    "for", "if", "in", "into", "is", "it", "no", "not", "of", "on", "or", "such", "that", "the",
                    "their", "then", "there", "these", "they", "this", "to", "was", "will", "with",
                    "/>", "<", "다", "화", "로", "은", "는", "의", "적", "을", "으로", "과", "에", "인",
                    ".", ",", "(", ")", "-", "br",
                    "이", "그", "저", "것", "수", "등", "들", "및", "에서", "그리고", "그래서", "또", "또는"}
    );

    public TwitterKoreanStopFilter(TokenStream in){
        super(in);
        CharArraySet stopSet = new CharArraySet(stopWordList.size(), false);
        stopSet.addAll(stopWordList);
        this.stopWords = CharArraySet.unmodifiableSet(stopSet);
        this.termAtt = (CharTermAttribute)this.addAttribute(CharTermAttribute.class);
    }

    @Override
    protected boolean accept() throws IOException {
        return !this.stopWords.contains(this.termAtt.buffer(), 0, this.termAtt.length());
    }
}
