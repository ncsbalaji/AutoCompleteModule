package com.autosuggest.fileread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.autosuggest.constants.Constants;
import com.autosuggest.datastructure.Trie;

@WebListener
/*
 * Loads during server start
 */
public class FileLoader implements ServletContextListener 
{

	private static Logger logger = Logger.getLogger("FileLoader");
	public FileLoader() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public void contextInitialized(ServletContextEvent event) 
	{
		new FileLoader().loadTrie();
    }
	
    public void contextDestroyed(ServletContextEvent event) 
    {
        // Do your thing during webapp's shutdown.
    }
    

	public Trie loadTrie()
	{
		Trie trie = Trie.getInstance();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(
				getClass().getClassLoader().getResourceAsStream(Constants.FileName))))
		{
		    String line = br.readLine();

		    while (line != null) 
		    {
		        logger.log(Level.INFO, line);
		        trie.addWord(line);
		        line = br.readLine();
		    }
		}
		catch (Exception e)
		{
			logger.log(Level.SEVERE, "Exception during file loading");
			e.printStackTrace();
		}
		
		return trie;
	}
	
	 /*
     * FOR TESTING PURPOSE
     */
	/*public static void main(String[] args) 
	{
		new FileLoader().loadTrie();
		String prefix = "A";
		TrieNode node = Trie.getInstance().getNodeWithPrefix(prefix);
		System.out.println(node.isEndOfWord());
		List<String> autoMatches = new ArrayList<String>();
		Trie.getInstance().getAllChildrenOfMatchingNode(node, autoMatches, prefix);
		autoMatches.stream().forEach(System.out :: println);
		
		logger.log(Level.INFO, "webservice response size"+ autoMatches.size());
	}*/
}
