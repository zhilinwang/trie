package ds.trie.linktrie;

import org.junit.Test;

public class TrieNodeTest {

	@Test
	public void test() {
		TrieNode<Character,Integer> node=new TrieNode<Character,Integer>(Character.valueOf('人'));
		System.out.println(node.getKey());
	}

}
