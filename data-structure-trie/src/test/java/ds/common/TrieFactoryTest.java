package ds.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import ds.trie.linktrie.Trie;

public class TrieFactoryTest {

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
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
