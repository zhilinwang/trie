package ds.Aho.Corasick.AC;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds.trie.linktrie.Trie;
import ds.trie.linktrie.TrieNode;

public class ACPatternsMatcher<K, V> {
	private Trie<K, V> trie = null;
	private Queue<TrieNode<K,V>> queue;
	private HashSet<V> output;

	private static Logger LOG = LoggerFactory
			.getLogger(ACPatternsMatcher.class);

	public ACPatternsMatcher(Trie<K,V> tire) {
		super();
		queue=new LinkedList<TrieNode<K,V>>();
		this.trie = tire;
		output=new HashSet<V>();
		this.ACFail();
	}

	public void ACFail() {
		if (trie == null)
		{
			LOG.info("trie tree can not be null!");
			return;
		}
		TrieNode<K,V> root=trie.getRoot();
		if(root.getChildren().size()>0)
		{
			for(K key: root.getChildren().keySet()){
				TrieNode<K,V> node=root.getChildren().get(key);
				queue.add(node);
				node.setFail(root);
			}
		}
		
		while(queue.size()>0){
			/**
			 * fetch the first element from queue.
			 * And remove it from queue.
			 */
			TrieNode<K,V> now=queue.poll();
			TrieNode<K,V> fail=now.getFail();
			
			if(now.getChildren() ==null || now.getChildren().size()==0) {
				now.setFail(root);
				continue;		
				}				
						
			/**
			 * add element to the tail of queue.
			 */
			for(K key: now.getChildren().keySet()){
				queue.add(now.getChildren().get(key));
			}
			/**
			 * if current fail pointer is null,
			 * set all children's fail to root.
			 */
			if(fail == null){
				for(K key: now.getChildren().keySet()){
					now.getChildren().get(key).setFail(root);
				}
				continue;
			}
			
			/**
			 * Or, create it's fail state.
			 */
			HashMap<K,TrieNode<K,V>> nowSet=now.getChildren();
			HashMap<K,TrieNode<K,V>> failSet=fail.getChildren();			
						
			for(K key: nowSet.keySet()){
				TrieNode<K,V> node=failSet.get(key);
				if(node!=null){
					/**
					 * exists a node in depth(nowSet)-1,
					 * which can be transfered from depth(nowSet).
					 */
					TrieNode<K,V> srcLink=nowSet.get(key);
					srcLink.setFail(node);
				}
			}
			
		}
		
		
	}
	
	public void Query(List<K> pattern)
	{
		TrieNode<K,V> current= trie.getRoot();
		int size=pattern.size();
		int index=0;
		while(index<size){
			K k=pattern.get(index);
			TrieNode<K,V> node=current.getChildren().get(k);
			/**
			 * if pattern matched, move to next state.
			 */
			if(node !=null){
				if(node.getValue()!=null){
					output.add(node.getValue());
				}
				current=node;
				index++;
			}
			if(node==null){
				if(current.getFail()!=null){
					current=current.getFail();
					/**
					 * maybe the heading state is a word.
					 */
					if(current.getValue()!=null){
						output.add(current.getValue());
					}
				}
				else
				{
					/**
					 * if pattern not matched, and not transfer state found. then move to next state.
					 */
					current=trie.getRoot();
					index++;
				}
			}
			
		}
		
	}

	public HashSet<V> getOutput() {
		return output;
	}
	
	
		
	

}
