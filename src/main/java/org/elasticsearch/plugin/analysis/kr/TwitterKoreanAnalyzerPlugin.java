package org.elasticsearch.plugin.analysis.kr;


import org.elasticsearch.common.inject.Module;
import org.elasticsearch.index.analysis.AnalysisModule;
import org.elasticsearch.index.analysis.TwitterKoreanAnalysisBinderProcessor;
import org.elasticsearch.plugins.AbstractPlugin;

/**
 * Created by hosang on 2015. 11. 5..
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

    @Override
    public void processModule(Module module) {
        if(module instanceof AnalysisModule){
            ((AnalysisModule) module).addProcessor(new TwitterKoreanAnalysisBinderProcessor());
        }
    }
}
