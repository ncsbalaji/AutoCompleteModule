package com.autosuggest.datastructure;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * declared as single-ton class as per necessity of the requirement
 */
public class Trie implements Serializable
{

	private static final long serialVersionUID = 1L;

	private static Trie trie;
	public TrieNode root;
	private static Logger logger = Logger.getLogger("Trie");

	private Trie()
	{
		if( trie != null )
		{
	        throw new InstantiationError( "Creating of this object is not allowed" );
	    }
		// TODO Auto-generated constructor stub
		logger.log(Level.WARNING, "Trie instance created");
		root = new TrieNode();
	}

	public static synchronized Trie getInstance()
	{
		if (trie == null)
		{
			synchronized (Trie.class)
			{
				if (trie == null)
				{
					trie = new Trie();
				}
			}
		}

		return trie;
	}

	public void addWord(String word)
	{
		TrieNode current = root;
		for (Character ch : word.toCharArray())
		{
			if (!current.getChildren().containsKey(ch))
			{
				current.getChildren().put(ch, new TrieNode());
			}

			current = current.getChildren().get(ch);

		}

		current.setEndOfWord(true);
	}

	/*
	 * return node matching with the prefix else return null
	 */
	public TrieNode getNodeWithPrefix(TrieNode node, String prefix)
	{
		if (prefix.isEmpty() || prefix.equals(""))
		{
			return node;
		}
		// if prefix first char is not present then there is no word
		if (!node.isChildPresent(prefix.charAt(0)))
		{
			return null;
		} else
		{
			// else go deep and get the node recursively
			return getNodeWithPrefix(node.getAnyMatchingChild(prefix.charAt(0)), prefix.substring(1));
		}
	}

	/*
	 * populates all the words that are children of the passed node
	 */
	public void getAllChildrenOfMatchingNode(TrieNode node, List<String> autoMatches, String prefix)
	{
		if (node == null)
			return;

		if (node.isEndOfWord())
		{
			autoMatches.add(prefix.toLowerCase());
		}

		// get the children of matching node which are end of words recursively
		for (Character ch : node.getChildren().keySet())
		{
			getAllChildrenOfMatchingNode(node.getAnyMatchingChild(ch), autoMatches, prefix + ch);
		}
	}

	public TrieNode getNodeWithPrefix(String prefix)
	{
		return getNodeWithPrefix(root, prefix);
	}

	public boolean search(String word)
	{
		TrieNode current = root;
		for (Character ch : word.toCharArray())
		{
			TrieNode node = current.getAnyMatchingChild(ch);
			// if node does not exist for given char then return false
			if (node == null)
			{
				return false;
			}
			current = node;
		}
		// return true of current's endOfWord is true else return false.
		return current.isEndOfWord();
	}

	protected Object readResolve()
	{
		return getInstance();
	}

	@Override
	public Object clone() throws CloneNotSupportedException
	{

		return new CloneNotSupportedException();
	}
}
