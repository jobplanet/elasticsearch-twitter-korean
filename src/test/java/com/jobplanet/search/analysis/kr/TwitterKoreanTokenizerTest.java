package com.jobplanet.search.analysis.kr;

import com.jobplanet.search.analysis.kr.util.AnalyzerTestUtil;
import com.jobplanet.search.analysis.kr.util.TestToken;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hosang on 2015. 11. 5..
 */
public class TwitterKoreanTokenizerTest extends AnalyzerTestUtil {

    private Set<TestToken> tokenizedToken = new HashSet<TestToken>();
    private StringReader content = new StringReader("나는 아이유가 좋아요.");
    private TwitterKoreanTokenizer tokenizer = new TwitterKoreanTokenizer(content);

    @Before
    public void setUp() throws IOException {
        tokenizedToken.add(getToken("나", 0, 1));
        tokenizedToken.add(getToken("는", 1, 2));
        tokenizedToken.add(getToken("아이유", 3, 6));
        tokenizedToken.add(getToken("가", 6, 7));
        tokenizedToken.add(getToken("좋다", 8, 11));
        tokenizedToken.add(getToken(".", 11, 12));

        tokenizer.reset();
    }

    @Test
    public void testIncrementToken() throws IOException {
        CharTermAttribute charTermAtt = tokenizer.getAttribute(CharTermAttribute.class);
        OffsetAttribute offSetAtt = tokenizer.getAttribute(OffsetAttribute.class);

        int expected_token_count = 6;
        int observed_token_count = 0;
        while(tokenizer.incrementToken()) {
            observed_token_count++;
            TestToken t = getToken(charTermAtt.toString(), offSetAtt.startOffset(), offSetAtt.endOffset());

            System.out.println("termAtt.term() : " + charTermAtt.toString());
            System.out.println("startOffset : " + offSetAtt.startOffset());
            System.out.println("endOffset : " + offSetAtt.endOffset());

            Assert.assertTrue(tokenizedToken.contains(t));
        }

        Assert.assertEquals(expected_token_count, observed_token_count);
    }


}
