package ds.trie.linktrie;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

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

	@Test
	public void test2() {
		Trie<Character, String> trie = new Trie<Character, String>();
		List<Character> elements = new ArrayList<Character>();
		String[] lines = { "大数据时代", "大数据挖掘","大数据与云计算", "正则指引","正则指引手册", "正则表达式", "大数据算法" };
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

}
