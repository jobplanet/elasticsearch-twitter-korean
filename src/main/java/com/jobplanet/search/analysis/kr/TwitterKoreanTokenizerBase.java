package com.jobplanet.search.analysis.kr;

import com.twitter.penguin.korean.KoreanTokenJava;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Created by hosang on 2015. 11. 9..
 */
public abstract class TwitterKoreanTokenizerBase extends Tokenizer {

    protected static final int IO_BUFFER_SIZE = 4096;
    protected int offset = 0, finalOffset = 0;
    protected boolean isFirst = true;
    protected List<KoreanTokenJava> tokenList;
    protected int currentIndex = 0;

    protected CharTermAttribute termAtt;
    protected OffsetAttribute offsetAtt;
    protected TypeAttribute typeAtt;
    protected PositionIncrementAttribute positionAtt;

    public TwitterKoreanTokenizerBase(Reader input) { super(input);}

    @Override
    public final void end() throws IOException {
        // set final offset
        clearAttributes();
        offsetAtt.setOffset(finalOffset, finalOffset);
        if(positionAtt != null) { positionAtt.setPositionIncrement(0); }
    }

    @Override
    public void reset() throws IOException {
        super.reset();
        offset = 0;
        finalOffset = 0;
        isFirst = true; // make sure to reset isFirst!!
        tokenList = null;
        currentIndex = 0;
    }

    /**
     * Set token attributes such as term, start offset, end offset, type.
     * @param token
     */
    protected void setAttributes(KoreanTokenJava token){
        char[] buf = token.getText().toCharArray();
        termAtt.copyBuffer(buf, 0, buf.length);
        offset = token.getOffset();
        offsetAtt.setOffset(correctOffset(offset), finalOffset = (correctOffset(offset) + token.getLength()));
        typeAtt.setType(token.getPos().toString());
    }
}
