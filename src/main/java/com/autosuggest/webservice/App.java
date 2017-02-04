package com.autosuggest.webservice;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.autosuggest.constants.Constants;
import com.autosuggest.datastructure.Trie;
import com.autosuggest.datastructure.TrieNode;
import com.autosuggest.exceptionmappers.NotFoundExceptionMapper;
import com.sun.jersey.api.NotFoundException;


@Path("/suggest/")
public class App 
{
	private static Logger logger = Logger.getLogger("App");
	
	@GET
	@Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponse(@QueryParam("prefix") String prefix,
    							@QueryParam("isEnd") boolean isEnd)
	{
        logger.log(Level.INFO, "[Enter] method getResponse with params " +prefix +Constants.Space+ isEnd);
        
        if(prefix == null || prefix.isEmpty())
        {
        	return Response.status(200).entity("prefix cannot be empty").build();
        }
        
        if(!isEnd)
        {
        	TrieNode node = Trie.getInstance().getNodeWithPrefix(prefix);
    		List<String> autoMatches = new ArrayList<String>();
    		Trie.getInstance().getAllChildrenOfMatchingNode(node, autoMatches, prefix);
    		
    		logger.log(Level.INFO, "webservice response size"+ autoMatches.size());
    		
    		return Response.status(200).entity(autoMatches).build();
        }
        else
		{

			return Trie.getInstance().search(prefix)
					? Response.status(Status.ACCEPTED).entity(Constants.TrueString).build()
					: new NotFoundExceptionMapper().toResponse(new NotFoundException());
		}
        
    }
}
