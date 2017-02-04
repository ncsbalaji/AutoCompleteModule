package com.autosuggest.datastructure;

import java.util.HashMap;
import java.util.Map;

public class TrieNode 
{

	private Map<Character, TrieNode> children;
	private boolean endOfWord;
	
	public TrieNode() {
		children = new HashMap<Character, TrieNode>();
		endOfWord = false;
	}

	public Map<Character, TrieNode> getChildren() {
		return children;
	}

	public void setChildren(Map<Character, TrieNode> children) {
		this.children = children;
	}

	public boolean isEndOfWord() {
		return endOfWord;
	}

	public void setEndOfWord(boolean endOfWord) {
		this.endOfWord = endOfWord;
	}
	
	public boolean isChildPresent(Character ch)
	{
		return children.containsKey(Character.toLowerCase(ch)) || children.containsKey(Character.toUpperCase(ch));
	}
	
	public TrieNode getAnyMatchingChild(Character ch)
	{
		return children.get(Character.toLowerCase(ch)) != null ? children.get(Character.toLowerCase(ch)) : children.get(Character.toUpperCase(ch));
		
	}
	
	
}
