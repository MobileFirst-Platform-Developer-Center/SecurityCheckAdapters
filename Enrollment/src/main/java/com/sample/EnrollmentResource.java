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
import com.ibm.mfp.server.security.external.resource.AdapterSecurityContext;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;

import javax.ws.rs.core.Response;



@Path("/")
public class EnrollmentResource {

	@Context
	AdapterSecurityContext adapterSecurityContext;

	@POST
	@OAuthSecurity(scope = "setPinCode")
	@Path("/setPinCode/{pinCode}")
	public Response setPinCode(@PathParam("pinCode") String pinCode){
		ClientData clientData = adapterSecurityContext.getClientRegistrationData();
		clientData.getProtectedAttributes().put("pinCode", pinCode);
		adapterSecurityContext.storeClientRegistrationData(clientData);
		return Response.ok().build();
	}

	@DELETE
	@OAuthSecurity(scope = "unenroll")
	@Path("/unenroll")
	public Response unenroll(){
		ClientData clientData = adapterSecurityContext.getClientRegistrationData();
		if (clientData.getProtectedAttributes().get("pinCode") != null){
			clientData.getProtectedAttributes().delete("pinCode");
			adapterSecurityContext.storeClientRegistrationData(clientData);
		}
		return Response.ok().build();
	}
}
