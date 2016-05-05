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

import com.ibm.mfp.server.registration.external.model.ClientData;
import com.ibm.mfp.server.registration.external.model.PersistentAttributes;
import com.ibm.mfp.server.security.external.resource.AdapterSecurityContext;

import javax.ws.rs.*;
import javax.ws.rs.core.*;



@Path("/")
public class EnrollmentResource {

	@Context
	AdapterSecurityContext adapterSecurityContext;

	private ClientData clientData = adapterSecurityContext.getClientRegistrationData();
	private PersistentAttributes attributes = clientData.getProtectedAttributes();

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/publicData")
	public String getPublicData(){
		return "some data"; ////// ???
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/balance")
	public String getBalance(){
		return "19938.80";
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/transactions")
	public String getTransactions(){
		return "transactions"; ////// ???
	}

	@GET
	@Path("/isEnrolled")
	public boolean isEnrolled(){
		return attributes.get("pinCode");
	}

	@POST
	@Path("/setPinCode/{pinCode}")
	public void setPinCode(@PathParam("pinCode") String pinCode){
		attributes.put("pinCode", pinCode);
	}




}
