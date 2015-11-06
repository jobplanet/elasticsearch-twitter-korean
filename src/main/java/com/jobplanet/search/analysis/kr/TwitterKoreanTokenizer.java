package com.jobplanet.search.analysis.kr;

import com.twitter.penguin.korean.KoreanTokenJava;
import com.twitter.penguin.korean.TwitterKoreanProcessorJava;
import com.twitter.penguin.korean.tokenizer.KoreanTokenizer;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import scala.collection.Seq;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Created by hosang on 2015. 11. 5..
 */
public final class TwitterKoreanTokenizer extends Tokenizer {

    private static final int IO_BUFFER_SIZE = 4096;
    private boolean isFirst = true;
    private List<KoreanTokenJava> tokenList;
    private int currentIndex = 0;

    private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);
    private final OffsetAttribute offsetAtt = addAttribute(OffsetAttribute.class);
    private final TypeAttribute typeAtt = addAttribute(TypeAttribute.class);
    private final PositionIncrementAttribute positionAtt = addAttribute(PositionIncrementAttribute.class);

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

    /**
     * Set token attributes such as term, start offset, end offset, type.
     * @param token
     */
    private void setAttributes(KoreanTokenJava token){

        char[] buf = token.getText().toCharArray();
        termAtt.copyBuffer(buf, 0, buf.length);
        offsetAtt.setOffset(token.getOffset(), token.getOffset() + token.getLength());
        typeAtt.setType(token.getPos().toString());

    }
}
