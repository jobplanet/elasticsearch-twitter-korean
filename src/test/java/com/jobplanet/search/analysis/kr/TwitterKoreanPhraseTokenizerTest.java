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
 * Created by hosang on 2015. 11. 9..
 */
public class TwitterKoreanPhraseTokenizerTest extends AnalyzerTestUtil {

    private Set<TestToken> tokenizedToken = new HashSet<TestToken>();
    private StringReader content = new StringReader("블랙프라이데이: 이날 미국의 수백만 소비자들은 크리스마스 선물을 할인된 가격에 사는 것을 주 목적으로 블랙프라이데이 쇼핑을 한다.");
    private TwitterKoreanPhraseTokenizer tokenizer = new TwitterKoreanPhraseTokenizer(content);

    @Before
    public void setUp() throws IOException {
        tokenizedToken.add(getToken("블랙프라이데이", 0, 7));
        tokenizedToken.add(getToken("이날", 9, 11));
        tokenizedToken.add(getToken("이날 미국", 9, 14));
        tokenizedToken.add(getToken("이날 미국의 수백만", 9, 19));
        tokenizedToken.add(getToken("미국의 수백만", 12, 19));
        tokenizedToken.add(getToken("수백만", 16, 19));
        tokenizedToken.add(getToken("이날 미국의 수백만 소비자들", 9, 24));
        tokenizedToken.add(getToken("미국의 수백만 소비자들", 12, 24));
        tokenizedToken.add(getToken("수백만 소비자들", 16, 24));
        tokenizedToken.add(getToken("크리스마스", 26, 31));
        tokenizedToken.add(getToken("크리스마스 선물", 26, 34));
        tokenizedToken.add(getToken("할인", 36, 38));
        tokenizedToken.add(getToken("가격", 40, 42));
        tokenizedToken.add(getToken("주 목적", 50, 54));
        tokenizedToken.add(getToken("블랙프라이데이 쇼핑", 57, 67));
        tokenizedToken.add(getToken("미국", 12, 14));
        tokenizedToken.add(getToken("소비자들", 20, 24));
        tokenizedToken.add(getToken("선물", 32, 34));
        tokenizedToken.add(getToken("목적", 52, 54));
        tokenizedToken.add(getToken("쇼핑", 65, 67));

        tokenizer.reset();
    }

    @Test
    public void testIncrementToken() throws IOException {
        CharTermAttribute charTermAtt = tokenizer.getAttribute(CharTermAttribute.class);
        OffsetAttribute offSetAtt = tokenizer.getAttribute(OffsetAttribute.class);

        while(tokenizer.incrementToken()) {
            TestToken t = getToken(charTermAtt.toString(), offSetAtt.startOffset(), offSetAtt.endOffset());

            System.out.println("termAtt.term() : " + charTermAtt.toString());
            System.out.println("startOffset : " + offSetAtt.startOffset());
            System.out.println("endOffset : " + offSetAtt.endOffset());

            Assert.assertTrue(tokenizedToken.contains(t));
        }
    }
}
