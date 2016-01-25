/*
 *    Licensed Materials - Property of IBM
 *    5725-I43 (C) Copyright IBM Corp. 2015. All Rights Reserved.
 *    US Government Users Restricted Rights - Use, duplication or
 *    disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
*/

package com.sample;

import com.ibm.mfp.adapter.api.OAuthSecurity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;


@Path("/")
public class ResourceAdapterResource {
	/*
	 * For more info on JAX-RS see https://jax-rs-spec.java.net/nonav/2.0-rev-a/apidocs/index.html
	 */
		
	//Define logger (Standard java.util.Logger)
	static Logger logger = Logger.getLogger(ResourceAdapterResource.class.getName());



	/* Path for method: "<server address>/(project name)/adapters/ResourceAdapter/users" */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/balance")
	@OAuthSecurity(scope="accessRestricted") //This method is protected. Each application can define what "accessRestricted" means.
	public String getBalance(){
		return "19938.80";
	}

		
}
