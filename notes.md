Notes as I go along.

- I created a normal java adapter. By defaults it creates a JAX-RS adapter.
- In the adapter.xml, I removed `JAXRSApplicationClass`
- I deleted the 2 java classes auto-generated.
- This is tedious. I asked if they plan to have a different archetype in maven just for security. The answer is no, instead users should download a template from the console UI. Story = 98543
- Created a new Java class (com.sample.SecuritySampleWithAttempts) and made it extend SecurityCheckWithAttempts
- IntelliJ prompts me to implement `validateCredentials` and `createChallenge`
- I opened a defect regarding the auto-generation: 98994
- In the `validateCredentials` I implemented a simple check (username==password)
- In the `createChallenge`, I'm not really sure what I want to send the client. The client will receive a "challenge". The fact that it got a challenge should be enough to say I need to send credentials no? Why do I care to send something in the challenge? Can I send nothing?
- In the pom.xml, I added a `securityCheckDefinition` element named `SecuritySampleWithAttempts` with my class. Initially I am trying without any properties.
- I added `@OAuthSecurity(scope="SecuritySampleWithAttempts")` to one of my methods in my java adapter
- I am modifying the ResourceRequest sample just for testing
- In the client, I created a simple WLChallengeHandler, initialized with the securitycheck name
- I get an empty challenge - good!
- Now in my challenge handler I am automatically sending submitChallengeAnswer with the expected credentials.
- Works, I get the resources.
- Note: Now with session independence, restarting the application does not log you out. So I won't get the challenge again. The property `successExpirationSec` seems to affect how long my session lasts.
