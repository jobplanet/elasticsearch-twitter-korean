package org.elasticsearch.index.analysis;

/**
 * Created by hosang on 2015. 11. 5..
 */
public class TwitterKoreanAnalysisBinderProcessor extends AnalysisModule.AnalysisBinderProcessor {

    @Override
    public void processAnalyzers(AnalyzersBindings analyzersBindings) {
        analyzersBindings.processAnalyzer("twitter_korean_analyzer", TwitterKoreanAnalyzerProvider.class);
    }

    @Override
    public void processTokenizers(TokenizersBindings tokenizersBindings) {
        tokenizersBindings.processTokenizer("twitter_korean_tokenizer", TwitterKoreanTokenizerFactory.class);
    }

}
