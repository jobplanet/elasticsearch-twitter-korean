package org.elasticsearch.plugin.analysis.kr;


import org.elasticsearch.index.analysis.AnalysisModule;
import org.elasticsearch.index.analysis.TwitterKoreanAnalysisBinderProcessor;
import org.elasticsearch.plugins.AbstractPlugin;

/**
 * @author Hosang Jeon, 2011.11.5 jhsbeat@gmail.com
 */
public class TwitterKoreanAnalyzerPlugin extends AbstractPlugin {

    @Override
    public String name() {
        return "twitter-korean-analyzer";
    }

    @Override
    public String description() {
        return "Twitter Korean Analyzer for ElasticSearch";
    }

    /**
     * This is the method that will register our analyzer with Elasticsearch.
     * @param module
     */
    public void onModule(AnalysisModule module) {
        module.addProcessor(new TwitterKoreanAnalysisBinderProcessor());
    }
}
