package com.jobplanet.search.analysis.kr;

import com.twitter.penguin.korean.TwitterKoreanProcessorJava;
import com.twitter.penguin.korean.tokenizer.KoreanTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import scala.collection.Seq;

import java.io.IOException;

/**
 * Created by hosang on 2015. 11. 5..
 */
public final class TwitterKoreanTokenizer extends TwitterKoreanTokenizerBase {

    public TwitterKoreanTokenizer() {
        termAtt = addAttribute(CharTermAttribute.class);
        offsetAtt = addAttribute(OffsetAttribute.class);
        typeAtt = addAttribute(TypeAttribute.class);
        positionAtt = addAttribute(PositionIncrementAttribute.class);
    }

    @Override
    public final boolean incrementToken() throws IOException {

        clearAttributes();

        if (isFirst) {
            final char[] buffer = new char[IO_BUFFER_SIZE];
            final StringBuilder out = new StringBuilder();
            for (;;) {
                int dataLen = input.read(buffer, 0, buffer.length);
                if (dataLen < 0){
                    break;
                }
                out.append(buffer, 0, dataLen);
            }

            Seq<KoreanTokenizer.KoreanToken> tokens = TwitterKoreanProcessorJava.tokenize(TwitterKoreanProcessorJava.normalize(out));
            Seq<KoreanTokenizer.KoreanToken> stemmed = TwitterKoreanProcessorJava.stem(tokens);
            tokenList = TwitterKoreanProcessorJava.tokensToJavaKoreanTokenList(stemmed);
            isFirst = false;
        }

        if(tokenList.size() <= currentIndex){
            return false;   // No more tokens
        }
        setAttributes(tokenList.get(currentIndex));
        positionAtt.setPositionIncrement(1);
        currentIndex++;

        return true;
    }

}
