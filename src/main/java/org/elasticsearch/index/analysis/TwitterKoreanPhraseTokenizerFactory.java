package org.elasticsearch.index.analysis;

import com.jobplanet.search.analysis.kr.TwitterKoreanPhraseTokenizer;
import org.apache.lucene.analysis.Tokenizer;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.settings.IndexSettings;

import java.io.Reader;

/**
 * Created by hosang on 2015. 11. 9..
 */
public class TwitterKoreanPhraseTokenizerFactory extends AbstractTokenizerFactory {

    @Inject
    public TwitterKoreanPhraseTokenizerFactory(Index index, @IndexSettings Settings indexSettings, @Assisted String name, @Assisted Settings settings) {
        super(index, indexSettings, name, settings);
    }

    @Override
    public Tokenizer create(Reader reader) { return new TwitterKoreanPhraseTokenizer(reader); }

}
