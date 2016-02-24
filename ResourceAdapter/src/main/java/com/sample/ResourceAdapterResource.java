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

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class ResourceAdapterResource {

	/* Path for method: "<server address>/mfp/api/adapters/ResourceAdapter" */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/balance")
	@OAuthSecurity(scope="accessRestricted") //This method is protected. Each application can define what "accessRestricted" means.
	public String getBalance(){
		return "19938.80";
	}


}
