package com.jobplanet.search.analysis.kr.util;

import com.google.common.collect.Lists;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;

/**
 * Created by hosang on 2015. 11. 6..
 */
public class AnalyzerTestUtil {

    protected TestToken getToken(String term, int start, int end) {
        TestToken t = new TestToken();
        t.setTerm(term);
        t.setStartOffset(start);
        t.setEndOffset(end);

        return t;
    }

    protected void verify(List<TestToken> expactedTokens, List<TestToken> extractedTokens) {

        for(TestToken testToken : expactedTokens) {
            Assert.assertTrue("[" + testToken + "] is expacted but not.", extractedTokens.contains(testToken));
        }
    }

    protected List<TestToken> collectExtractedNouns(TokenStream stream) throws IOException {
        CharTermAttribute charTermAtt = stream.addAttribute(CharTermAttribute.class);
        OffsetAttribute offSetAtt = stream.addAttribute(OffsetAttribute.class);
        TypeAttribute typeAttr = stream.addAttribute(TypeAttribute.class);

        List<TestToken> extractedTokens = Lists.newArrayList();

        while(stream.incrementToken()) {
            TestToken t = getToken(charTermAtt.toString(), offSetAtt.startOffset(), offSetAtt.endOffset());

            System.out.println("termAtt.term() : " + charTermAtt.toString());
            System.out.println("startoffSetAtt : " + offSetAtt.startOffset());
            System.out.println("endoffSetAtt : " + offSetAtt.endOffset());
            System.out.println("typeAttr : " + typeAttr.toString());

            extractedTokens.add(t);
        }

        return extractedTokens;
    }
}
