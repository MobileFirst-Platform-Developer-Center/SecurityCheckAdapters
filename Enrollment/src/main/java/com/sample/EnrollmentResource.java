/**
 * Copyright 2016 IBM Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sample;

import com.ibm.mfp.adapter.api.OAuthSecurity;
import com.ibm.mfp.server.registration.external.model.ClientData;
import com.ibm.mfp.server.registration.external.model.PersistentAttributes;
import com.ibm.mfp.server.security.external.resource.AdapterSecurityContext;


import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/")
public class EnrollmentResource {

	@Context
	AdapterSecurityContext adapterSecurityContext;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/publicData")
	public String getPublicData(){
		return "some data"; ////// ???
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@OAuthSecurity(scope = "accessRestricted")
	@Path("/balance")
	public String getBalance(){
		return "19938.80";
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@OAuthSecurity(scope = "transactionsPrivilege")
	@Path("/transactions")
	public String getTransactions(){
		return "transactions"; ////// ???
	}

	@GET
	@Path("/isEnrolled")
	public boolean isEnrolled(){
//		Map<String, Object> response = new HashMap<String, Object>();
//		boolean isEnrolled = getAttributes().get("pinCode") != null;
//		response.put("isEnrolled", isEnrolled);
		ClientData clientData = adapterSecurityContext.getClientRegistrationData();
		return clientData.getProtectedAttributes().get("pinCode") != null;
	}

	@POST
	@OAuthSecurity(scope = "setPinCode")
	@Path("/setPinCode/{pinCode}")
	public Response setPinCode(@PathParam("pinCode") String pinCode){
		ClientData clientData = adapterSecurityContext.getClientRegistrationData();
		clientData.getProtectedAttributes().put("pinCode", pinCode);
		adapterSecurityContext.storeClientRegistrationData(clientData);
		return Response.ok().build();
	}





}
