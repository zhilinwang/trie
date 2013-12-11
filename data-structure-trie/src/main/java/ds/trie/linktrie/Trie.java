package ds.trie.linktrie;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the tire data structure.
 * 
 * @author Chilam
 * 
 * @param <K>
 *            Node key type.
 * @param <V>
 *            Node word value type.
 */
public class Trie<K, V> {
	TrieNode<K, V> root = null;
	Logger LOG = LoggerFactory.getLogger(this.getClass());

	public Trie() {
		super();
		root = new TrieNode<K, V>(null);
	}

	/**
	 * add words to trie dictionary
	 * 
	 * @param elements
	 *            break word down into character
	 * @param value
	 *            word value
	 */
	public void add(List<K> elements, V value) {
		TrieNode<K, V> parent = this.root;
		TrieNode<K, V> node = null;
		Iterator<K> iter = elements.iterator();
		/**
		 * traverse each element of elements step by step and, construct node in
		 * trie dictionary tree. *
		 */
		while (iter.hasNext()) {
			K key = (K) iter.next();
			node = parent.contain(key);
			if (node == null) {
				TrieNode<K, V> curr = new TrieNode<K, V>(key);
				parent.addChild(key, curr);
				parent = curr;
			} else {
				parent = node;
			}
		}
		/**
		 * Store value into leaf child, which means this trie dictionary
		 * contains this word. error handling. should not happen for ever.
		 */
		if (parent.getValue() != null && !parent.getValue().equals(value)) {
			LOG.info("inconsistent value,new value will overwrite old one!");
		}
		parent.setValue(value);

	}
	/**
	 * Search word in trie tree, if exist return value, else return null
	 * @param elements 
	 * @return null or value
	 */
	public V search(List<K> elements) {
		TrieNode<K,V> parent=root;
		Iterator<K> iter=elements.iterator();
		TrieNode<K,V> node=null;
		K key=null;
		while(iter.hasNext())
		{
			key=(K) iter.next();
			node=parent.getChildren().get(key);
			if(node == null)
			{
				return null;
			}
			parent=node;
			
		}	
		return parent.getValue();
	}
	/**
	 * remove words in trie tree.
	 * @param elements elements array of words.
	 */
	public void remove(List<K> elements)
	{
		if(this.search(elements)==null) return;
		
		TrieNode<K,V> pile=root;
		TrieNode<K,V> parent=root;
		TrieNode<K,V> node=null;
		K key=null;
		K trieKey=null;
		int index=0;
		Iterator<K> iter=elements.iterator();
		while(iter.hasNext())
		{
			index++;
			key=(K) iter.next();
			node=parent.getChildren().get(key);
			if(node.getChildren().size()>1)
			{
				pile=node;
				trieKey=elements.get(index);
			}
			parent=node;
		}
		
		pile.getChildren().get(trieKey).getChildren().clear();
		pile.getChildren().remove(trieKey);
		
	}

	/**
	 * test auxiliary function
	 */
	public void print() {
		System.out.println("root level:");
		if (this.root.getChildren().size() != 0) {
			for (K key : this.root.getChildren().keySet()) {
				TrieNode<K, V> node = this.root.getChildren().get(key);
				node.print();
			}
		}
	}
}
