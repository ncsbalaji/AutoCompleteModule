package com.autosuggest.exceptionmappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import com.autosuggest.constants.Constants;
import com.sun.jersey.api.NotFoundException;

public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException>
{

	public NotFoundExceptionMapper() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public Response toResponse(NotFoundException e) 
	{
		return Response.status(Status.NOT_FOUND).entity(Constants.EntityNotFound).build();
	}

}
