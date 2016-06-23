package org.elasticsearch.index.analysis;

import com.jobplanet.search.analysis.kr.TwitterKoreanStopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.settings.IndexSettingsService;

/**
 * Created by hosang on 2015. 11. 11..
 */
public class TwitterKoreanStopFilterFactory extends AbstractTokenFilterFactory {

    @Inject
    public TwitterKoreanStopFilterFactory(Index index, IndexSettingsService indexSettingsService, @Assisted String name, @Assisted
            Settings settings) {
        super(index,indexSettingsService.getSettings(),name,settings);
    }

    public TokenStream create(TokenStream tokenstream) {
        return new TwitterKoreanStopFilter(tokenstream);
    }

}
