package ds.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import ds.Aho.Corasick.AC.ACPatternsMatcher;
import ds.trie.linktrie.Trie;

@SuppressWarnings("deprecation")
public class TrieFactoryTest {

	@SuppressWarnings({ "unchecked"})
	@Test
	public void test() {
		try {
			Trie<Character,String> trie=TrieFactory.constructTrieFromFile("src/test/resources/keyword.txt", "\t");
			//trie.print();
			String word="abap";
			List<Character> elements = new ArrayList<Character>();
			for (char c : word.toCharArray()) {
				elements.add(c);
			}
			trie.add(elements, word);
			String s= trie.search(elements);			
			Assert.assertEquals(s, word);
			String line="The ga3 mutant of Arabidopsis is a gibberellin-responsive dwarf. We present data showing that the ga3-1 mutant is deficient in ent-kaurene oxidase activity, the first cytochrome P450-mediated step in the gibberellin biosynthetic pathway. By using a combination of conventional map-based cloning and random sequencing we identified a putative cytochrome P450 gene mapping to the same location as GA3. Relative to the progenitor line, two ga3 mutant alleles contained single base changes generating in-frame stop codons in the predicted amino acid sequence of the P450. A genomic clone spanning the P450 locus complemented the ga3-2 mutant. The deduced GA3 protein defines an additional class of cytochrome P450 enzymes. The GA3 gene was expressed in all tissues examined, RNA abundance being highest in inflorescence tissue";

			List<Character> pattern = new ArrayList<Character>();
			for (char c : line.toCharArray()) {
				pattern.add(c);
			}
			ACPatternsMatcher<Character, String> matcher = new ACPatternsMatcher<Character, String>(
					trie);
			matcher.Query(pattern);
			for (String value : matcher.getOutput()) {
				System.out.println(value);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
