package com.jobplanet.search.analysis.kr;

import com.google.common.collect.Lists;
import com.jobplanet.search.analysis.kr.util.AnalyzerTestUtil;
import com.jobplanet.search.analysis.kr.util.TestToken;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.util.List;

/**
 * Created by hosang on 2015. 11. 6..
 */
public class TwitterKoreanAnalyzerTest extends AnalyzerTestUtil {

    private List<TestToken> testTokens = null;

    @Before
    public void setup() {
        testTokens = Lists.newArrayList();
    }

    @Test
    public void testCase1() throws Exception {

        StringReader reader = new StringReader("소설은 사건을 미적으로 질서화하여");

        testTokens.add(getToken("소설", 0, 2));
        testTokens.add(getToken("은", 2, 3));
        testTokens.add(getToken("사건", 4, 6));
        testTokens.add(getToken("을", 6, 7));
        testTokens.add(getToken("미적", 8, 10));
        testTokens.add(getToken("으로", 10, 12));
        testTokens.add(getToken("질서", 13, 15));
        testTokens.add(getToken("화", 15, 16));
        testTokens.add(getToken("하다", 16, 18));

        Analyzer analyzer = new TwitterKoreanAnalyzer();
        TokenStream stream = analyzer.tokenStream("dummy", reader);
        stream.reset();

        List<TestToken> extractedTokens = collectExtractedNouns(stream);

        analyzer.close();

        verify(testTokens, extractedTokens);
    }

}
