package com.jobplanet.search.analysis.kr;

import com.twitter.penguin.korean.TwitterKoreanProcessorJava;
import com.twitter.penguin.korean.phrase_extractor.KoreanPhraseExtractor;
import com.twitter.penguin.korean.tokenizer.KoreanTokenizer;
import scala.collection.Seq;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Created by hosang on 2015. 11. 9..
 */
public final class TwitterKoreanPhraseTokenizer extends TwitterKoreanTokenizerBase {

    private List<KoreanPhraseExtractor.KoreanPhrase> tokenList;

    public TwitterKoreanPhraseTokenizer(Reader input) { super(input);}

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
            tokenList = TwitterKoreanProcessorJava.extractPhrases(stemmed, true, false);
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

    /**
     * Set token attributes such as term, start offset, end offset, type.
     * @param token
     */
    private void setAttributes(KoreanPhraseExtractor.KoreanPhrase token){

        char[] buf = token.text().toCharArray();
        termAtt.copyBuffer(buf, 0, buf.length);
        offset = token.offset();
        offsetAtt.setOffset(correctOffset(offset), finalOffset = (correctOffset(offset) + token.length()));
        typeAtt.setType(token.pos().toString());

    }
}
