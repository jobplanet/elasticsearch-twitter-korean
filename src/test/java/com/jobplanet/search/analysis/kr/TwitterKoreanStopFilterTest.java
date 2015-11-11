package com.jobplanet.search.analysis.kr;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jobplanet.search.analysis.kr.util.AnalyzerTestUtil;
import com.jobplanet.search.analysis.kr.util.TestToken;
import org.apache.lucene.analysis.TokenStream;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

/**
 * Created by hosang on 2015. 11. 11..
 */
public class TwitterKoreanStopFilterTest extends AnalyzerTestUtil {

    private List<TestToken> tokens = null;
    private StringReader reader = new StringReader("이번 사건은 전적으로 the King의 잘못인 것 으로 생각합니다.");

    @Before
    public void setUp() {
        tokens = Lists.newArrayList();
        tokens.add(getToken("이번", 0, 2));
        tokens.add(getToken("사건", 3, 5));
        tokens.add(getToken("전적", 7, 9));
        tokens.add(getToken("King", 16, 20));
        tokens.add(getToken("잘못", 22, 24));
        tokens.add(getToken("생각", 31, 33));
        tokens.add(getToken("하다", 33, 36));
    }

    @Test
    public void stopFilter() throws IOException {

        Map<String, String> stopWordDictionaryMap = Maps.newHashMap();
        stopWordDictionaryMap.put("the", null);
        stopWordDictionaryMap.put(".", null);

        TokenStream stream = new TwitterKoreanStopFilter(new TwitterKoreanTokenizer(reader));
        stream.reset();

        List<TestToken> extractedTokens = collectExtractedNouns(stream);

        stream.close();

        verify(tokens, extractedTokens);
    }
}
