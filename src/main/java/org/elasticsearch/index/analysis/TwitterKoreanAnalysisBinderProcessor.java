package org.elasticsearch.index.analysis;

/**
 * @author Hosang Jeon, 2011.11.5 jhsbeat@gmail.com
 */
public class TwitterKoreanAnalysisBinderProcessor extends AnalysisModule.AnalysisBinderProcessor {

    @Override
    public void processAnalyzers(AnalyzersBindings analyzersBindings) {
        analyzersBindings.processAnalyzer("twitter_korean_analyzer", TwitterKoreanAnalyzerProvider.class);
    }

    @Override
    public void processTokenizers(TokenizersBindings tokenizersBindings) {
        tokenizersBindings.processTokenizer("twitter_korean_tokenizer", TwitterKoreanTokenizerFactory.class);
        tokenizersBindings.processTokenizer("twitter_korean_phrase_tokenizer", TwitterKoreanPhraseTokenizerFactory.class);
    }


}
