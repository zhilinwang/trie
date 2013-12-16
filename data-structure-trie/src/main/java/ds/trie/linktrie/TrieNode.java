package ds.trie.linktrie;

import java.util.HashMap;
/**
 * This trie node data structure
 * @author Chilam
 *
 * @param <K> trie node key type
 * @param <V> trie node value type
 */
public class TrieNode<K,V> {
	private V value;
	private K key;
	private TrieNode<K,V> fail=null;
	
	
	private HashMap<K,TrieNode<K,V>> children=new HashMap<K,TrieNode<K,V>>();
	
	
	
	public TrieNode(K key) {
		super();
		this.key = key;
	}
	public V getValue() {
		return value;
	}	
	
	
	public void setValue(V value) {
		this.value = value;
	}
	
	
	public K getKey() {
		return key;
	}
	
	
	
	public TrieNode<K, V> getFail() {
		return fail;
	}
	
	public void setFail(TrieNode<K, V> fail) {
		this.fail = fail;
	}
	
	public HashMap<K, TrieNode<K, V>> getChildren() {
		return children;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		TrieNode<K,V> other = (TrieNode<K,V>) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}
	/**
	 * if this node's children contain key, it means we can traverse to its child.
	 * @param key
	 * @return its child node, which contain given key.
	 */
	public TrieNode<K,V> contain(K key)
	{
		return this.children.get(key);
	}
	
	/**
	 * if its child doesn't contain given key, it means this a new child node.
	 * @param key key of child node
	 * @param child  child node
	 */
	public void addChild(K key,TrieNode<K,V> child){
		this.children.put(key, child);
	}
	
	/**
	 * test auxiliary function
	 */
	public void print(){
		System.out.print("children level:");
		System.out.println(this.key);
		System.out.print("Fail Node Key:");
		if(this.fail!=null)
		System.out.println(this.fail.key);
		System.out.print("node value:");
		if(this.value!=null) System.out.println(this.value);
		if(this.getChildren().size()!=0)
		{
			for(K key: this.getChildren().keySet())
			{
				TrieNode<K,V> node=this.getChildren().get(key);
				node.print();
			}
		}
	}

	
}
