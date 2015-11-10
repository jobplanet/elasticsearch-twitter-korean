package com.jobplanet.search.analysis.kr;

import com.twitter.penguin.korean.TwitterKoreanProcessorJava;
import com.twitter.penguin.korean.tokenizer.KoreanTokenizer;
import scala.collection.Seq;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by hosang on 2015. 11. 5..
 */
public final class TwitterKoreanTokenizer extends TwitterKoreanTokenizerBase {

    public TwitterKoreanTokenizer(Reader input) { super(input);}

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
            Seq<KoreanTokenizer.KoreanToken> tokens = TwitterKoreanProcessorJava.tokenize(out);
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
