package ds.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds.trie.linktrie.Trie;

public class TrieFactory {
	public static Logger LOG = LoggerFactory.getLogger(TrieFactory.class);

	@SuppressWarnings("rawtypes")
	public static synchronized Trie constructTrieFromFile(String path,
			String delimiter) throws IOException {
		BufferedReader br = new BufferedReader(FileReaderFacade.readFile(path));
		String line = null;
		Trie<Character, String> trie = new Trie<Character, String>();
		List<Character> elements = new ArrayList<Character>();
		while ((line = br.readLine()) != null) {
			String[] words = line.split(delimiter);
			for (String w : words) {
				if (w.trim().matches("[a-zA-Z]*")) {
					for (char c : w.toCharArray()) {
						elements.add(Character.valueOf(c));
					}
					trie.add(elements, w);
					elements.clear();
				}
			}

		}
		return trie;
	}

}
