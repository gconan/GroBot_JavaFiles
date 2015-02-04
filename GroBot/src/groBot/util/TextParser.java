package groBot.util;

import groBot.util.Keyword;

import java.io.IOException;
import java.io.StringReader;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilter;
import org.apache.lucene.analysis.standard.ClassicFilter;
import org.apache.lucene.analysis.standard.ClassicTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

/**
 * 
 * @author namaz
 *
 */
public class TextParser {
	
	public static List<Keyword> parseString(String input) throws IOException {

	    input = input.replaceAll("-+", "-0");
	    input = input.replaceAll("[\\p{Punct}&&[^'-]]+", " ");
	    input = input.replaceAll("(?:'(?:[tdsm]|[vr]e|ll))+\\b", "");
	    
	    StringReader rd = new StringReader(input);

	    TokenStream tokenStream = new ClassicTokenizer(rd);
	    tokenStream = new LowerCaseFilter(tokenStream);
	    tokenStream = new ClassicFilter(tokenStream);
	    tokenStream = new ASCIIFoldingFilter(tokenStream);
	    tokenStream = new StopFilter(tokenStream, EnglishAnalyzer.getDefaultStopSet());

	    List<Keyword> keywords = new LinkedList<Keyword>();
	    CharTermAttribute token = tokenStream.getAttribute(CharTermAttribute.class);
	    tokenStream.reset();
	    
	    while (tokenStream.incrementToken()) {
	        String term = token.toString();
	        String stem = stemmize(term);
	        if (stem != null) {
	            Keyword keyword = find(keywords, new Keyword(stem.replaceAll("-0", "-")));
	            keyword.add(term.replaceAll("-0", "-"));
	        }
	    }

	    Collections.sort(keywords);
    	tokenStream.close();
	    return keywords;
	}
	
	public static String stemmize(String term) throws IOException {

	    TokenStream tokenStream = new ClassicTokenizer(new StringReader(term));
	    tokenStream = new PorterStemFilter(tokenStream);

	    Set<String> stems = new HashSet<String>();
	    CharTermAttribute token = tokenStream.getAttribute(CharTermAttribute.class);
	    tokenStream.reset();

	    while (tokenStream.incrementToken()) {
	        stems.add(token.toString());
	    }

	    if (stems.size() != 1) {
	    	tokenStream.close();
	        return null;
	    }

	    String stem = stems.iterator().next();

	    if (!stem.matches("[\\w-]+")) {
	    	tokenStream.close();
	        return null;
	    }
    	tokenStream.close();
	    return stem;
	}

	public static <T> T find(Collection<T> collection, T example) {
	    for (T element : collection) {
	        if (element.equals(example)) {
	            return element;
	        }
	    }
	    collection.add(example);
	    return example;
	}
}
