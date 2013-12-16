package ds.trie.linktrie;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import ds.Aho.Corasick.AC.ACPatternsMatcher;

@SuppressWarnings("deprecation")
public class TrieTest {

	@Test
	@Ignore
	public void test() {
		Trie<Character, String> trie = new Trie<Character, String>();
		List<Character> elements = new ArrayList<Character>();
		String value = "大数据时代";
		for (char c : value.toCharArray()) {
			elements.add(Character.valueOf(c));
		}
		trie.add(elements, value);
		elements.clear();
		value = "大数据挖掘";
		for (char c : value.toCharArray()) {
			elements.add(Character.valueOf(c));
		}
		trie.add(elements, value);
		trie.print();
	}

	@Ignore
	@Test
	public void test2() {
		Trie<Character, String> trie = new Trie<Character, String>();
		List<Character> elements = new ArrayList<Character>();
		String[] lines = { "大数据时代", "大数据挖掘", "大数据与云计算", "正则指引", "正则指引手册",
				"正则表达式", "大数据算法" };
		for (String line : lines) {
			for (char c : line.toCharArray()) {
				elements.add(c);
			}
			trie.add(elements, line);
			elements.clear();
		}
		trie.print();
		String line = "大数据时代";
		for (char c : line.toCharArray()) {
			elements.add(c);
		}
		trie.add(elements, line);
		System.out.println(trie.search(elements));
		trie.remove(elements);
		trie.print();

	}

	@Ignore
	@Test
	public void testACPatternMatcher() {
		Trie<Character, String> trie = new Trie<Character, String>();
		List<Character> elements = new ArrayList<Character>();
		String[] lines = {"He", "中国人", "中国国旗", "亮相月球", "亮相地球","亮相" ,"地球","中国","US","say"};
		for (String line : lines) {
			for (char c : line.toCharArray()) {
				elements.add(c);
			}
			trie.add(elements, line);
			elements.clear();
		}

		trie.print();

		ACPatternsMatcher<Character, String> matcher = new ACPatternsMatcher<Character, String>(
				trie);
		String line = "中国国旗首次首次亮相月球中国人自豪地球人发来贺电USsayHe";
		List<Character> pattern = new ArrayList<Character>();
		for (char c : line.toCharArray()) {
			pattern.add(c);
		}
		matcher.Query(pattern);
		Assert.assertEquals(10, matcher.getOutput().size());
		for (String value : matcher.getOutput()) {
			System.out.println(value);
		}

	}
	
	/**
	 * test data of this test is from the Internet.
	 */
	@Test
	public void test4()
	{
		Trie<Character, String> trie = new Trie<Character, String>();
		List<Character> elements = new ArrayList<Character>();
		String[] lines ={ 
				"microsome",
			    "cytochrome",
			    "cytochrome P450 activity", 
			    "gibberellic acid biosynthesis", 
			    "GA3", 
			    "cytochrome P450", 
			    "oxygen binding", 
			    "AT5G25900.1", 
			    "protein", 
			    "RNA", 
			    "gibberellin", 
			    "Arabidopsis", 
			    "ent-kaurene oxidase activity", 
			    "inflorescence", 
			    "tissue"} ;
		for (String line : lines) {
			for (char c : line.toCharArray()) {
				elements.add(c);
			}
			trie.add(elements, line);
			elements.clear();
		}

		//trie.print();
		
		ACPatternsMatcher<Character, String> matcher = new ACPatternsMatcher<Character, String>(
				trie);
		String line="The ga3 mutant of Arabidopsis is a gibberellin-responsive dwarf. We present data showing that the ga3-1 mutant is deficient in ent-kaurene oxidase activity, the first cytochrome P450-mediated step in the gibberellin biosynthetic pathway. By using a combination of conventional map-based cloning and random sequencing we identified a putative cytochrome P450 gene mapping to the same location as GA3. Relative to the progenitor line, two ga3 mutant alleles contained single base changes generating in-frame stop codons in the predicted amino acid sequence of the P450. A genomic clone spanning the P450 locus complemented the ga3-2 mutant. The deduced GA3 protein defines an additional class of cytochrome P450 enzymes. The GA3 gene was expressed in all tissues examined, RNA abundance being highest in inflorescence tissue";

		List<Character> pattern = new ArrayList<Character>();
		for (char c : line.toCharArray()) {
			pattern.add(c);
		}
		matcher.Query(pattern);
		for (String value : matcher.getOutput()) {
			System.out.println(value);
		}
	}

}
