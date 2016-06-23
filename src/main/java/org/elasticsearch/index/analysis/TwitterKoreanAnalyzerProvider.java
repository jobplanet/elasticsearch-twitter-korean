package org.elasticsearch.index.analysis;

import com.jobplanet.search.analysis.kr.TwitterKoreanAnalyzer;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.settings.IndexSettingsService;

import java.io.IOException;

/**
 * Created by hosang on 2015. 11. 6..
 */
public class TwitterKoreanAnalyzerProvider extends AbstractIndexAnalyzerProvider<TwitterKoreanAnalyzer> {

    private final TwitterKoreanAnalyzer analyzer;

    @Inject
    public TwitterKoreanAnalyzerProvider(Index index, IndexSettingsService indexSettingsService, Environment env,
                                         @Assisted String name,
                                         @Assisted Settings
            settings) throws IOException {
        super(index, indexSettingsService.getSettings(), name, settings);
        analyzer = new TwitterKoreanAnalyzer();
    }

    @Override
    public TwitterKoreanAnalyzer get() {
        return this.analyzer;
    }
}
