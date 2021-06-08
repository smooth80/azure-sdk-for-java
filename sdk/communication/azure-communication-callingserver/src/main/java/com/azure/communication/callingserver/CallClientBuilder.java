// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.communication.callingserver;

import com.azure.communication.callingserver.implementation.AzureCommunicationCallingServerServiceImpl;
import com.azure.communication.callingserver.implementation.AzureCommunicationCallingServerServiceImplBuilder;
import com.azure.communication.common.implementation.CommunicationConnectionString;
import com.azure.communication.common.implementation.HmacAuthenticationPolicy;
import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.credential.TokenCredential;
import com.azure.core.http.HttpClient;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.BearerTokenAuthenticationPolicy;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.http.policy.HttpLoggingPolicy;
import com.azure.core.http.policy.HttpPipelinePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.util.ClientOptions;
import com.azure.core.util.Configuration;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.logging.ClientLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * CallClientBuilder that creates CallAsyncClient and CallClient.
 */
@ServiceClientBuilder(serviceClients = { CallClient.class, CallAsyncClient.class })
public final class CallClientBuilder {
    private static final String SDK_NAME = "name";
    private static final String SDK_VERSION = "version";
    private static final String APP_CONFIG_PROPERTIES = "azure-communication-callingserver.properties";

    private final ClientLogger logger = new ClientLogger(CallClientBuilder.class);
    private String connectionString;
    private String endpoint;
    private AzureKeyCredential azureKeyCredential;
    private TokenCredential tokenCredential;
    private HttpClient httpClient;
    private HttpLogOptions httpLogOptions = new HttpLogOptions();
    private HttpPipeline pipeline;
    private Configuration configuration;
    private final Map<String, String> properties = CoreUtils.getProperties(APP_CONFIG_PROPERTIES);
    private final List<HttpPipelinePolicy> customPolicies = new ArrayList<HttpPipelinePolicy>();
    private ClientOptions clientOptions;
    private RetryPolicy retryPolicy;

    /**
     * Set endpoint of the service
     *
     * @param endpoint url of the service
     * @return CallClientBuilder
     */
    public CallClientBuilder endpoint(String endpoint) {
        this.endpoint = Objects.requireNonNull(endpoint, "'endpoint' cannot be null.");
        return this;
    }

    /**
     * Set endpoint of the service
     *
     * @param pipeline HttpPipeline to use, if a pipeline is not supplied, the
     * credential and httpClient fields must be set
     * @return CallClientBuilder
     */
    public CallClientBuilder pipeline(HttpPipeline pipeline) {
        this.pipeline = Objects.requireNonNull(pipeline, "'pipeline' cannot be null.");
        return this;
    }

    /**
     * Sets the {@link TokenCredential} used to authenticate HTTP requests.
     *
     * @param tokenCredential {@link TokenCredential} used to authenticate HTTP
     * requests.
     * @return The updated {@link CallClientBuilder} object.
     * @throws NullPointerException If {@code tokenCredential} is null.
     */
    public CallClientBuilder credential(TokenCredential tokenCredential) {
        this.tokenCredential = Objects.requireNonNull(tokenCredential, "'tokenCredential' cannot be null.");
        return this;
    }

    /**
     * Sets the {@link AzureKeyCredential} used to authenticate HTTP requests.
     *
     * @param keyCredential The {@link AzureKeyCredential} used to authenticate HTTP
     *                      requests.
     * @return The updated {@link CallClientBuilder} object.
     * @throws NullPointerException If {@code keyCredential} is null.
     */
    public CallClientBuilder credential(AzureKeyCredential keyCredential) {
        this.azureKeyCredential = Objects.requireNonNull(keyCredential, "'keyCredential' cannot be null.");
        return this;
    }

    /**
     * Set connectionString to use
     *
     * @param connectionString connection string to set endpoint and initialize AzureKeyCredential
     * @return CallClientBuilder
     */
    public CallClientBuilder connectionString(String connectionString) {
        Objects.requireNonNull(connectionString, "'connectionString' cannot be null.");
        this.connectionString = connectionString;
        return this;
    }

    /**
     * Sets the retry policy to use (using the RetryPolicy type).
     *
     * @param retryPolicy object to be applied
     * @return CallClientBuilder
     */
    public CallClientBuilder retryPolicy(RetryPolicy retryPolicy) {
        this.retryPolicy = Objects.requireNonNull(retryPolicy, "'retryPolicy' cannot be null.");
        return this;
    }

    /**
     * Sets the configuration object used to retrieve environment configuration
     * values during building of the client.
     *
     * @param configuration Configuration store used to retrieve environment
     * configurations.
     * @return the updated CallClientBuilder object
     */
    public CallClientBuilder configuration(Configuration configuration) {
        this.configuration = Objects.requireNonNull(configuration, "'configuration' cannot be null.");
        return this;
    }

    /**
     * Sets the {@link HttpLogOptions} for service requests.
     *
     * @param logOptions The logging configuration to use when sending and receiving
     * HTTP requests/responses.
     * @return the updated CallClientBuilder object
     */
    public CallClientBuilder httpLogOptions(HttpLogOptions logOptions) {
        this.httpLogOptions = Objects.requireNonNull(logOptions, "'logOptions' cannot be null.");
        return this;
    }

    /**
     * Sets the {@link CallClientBuilder} that is used when making API requests.
     * <p>
     * If a service version is not provided, the service version that will be used
     * will be the latest known service version based on the version of the client
     * library being used. If no service version is specified, updating to a newer
     * version of the client library will have the result of potentially moving to a
     * newer service version.
     * <p>
     * Targeting a specific service version may also mean that the service will
     * return an error for newer APIs.
     *
     * @param version {@link CallClientBuilder} of the service to be used when
     * making requests.
     * @return the updated CallClientBuilder object
     */
    public CallClientBuilder serviceVersion(CallClientBuilder version) {
        return this;
    }

    /**
     * Set httpClient to use
     *
     * @param httpClient httpClient to use, overridden by the pipeline field.
     * @return CallClientBuilder
     */
    public CallClientBuilder httpClient(HttpClient httpClient) {
        this.httpClient = Objects.requireNonNull(httpClient, "'httpClient' cannot be null.");
        return this;
    }

    /**
     * Apply additional HttpPipelinePolicy
     *
     * @param customPolicy HttpPipelinePolicy object to be applied after
     * AzureKeyCredentialPolicy, UserAgentPolicy, RetryPolicy,
     * and CookiePolicy
     * @return CallClientBuilder
     */
    public CallClientBuilder addPolicy(HttpPipelinePolicy customPolicy) {
        this.customPolicies.add(Objects.requireNonNull(customPolicy, "'customPolicy' cannot be null."));
        return this;
    }

    /**
     * Create asynchronous client applying HMACAuthenticationPolicy,
     * UserAgentPolicy, RetryPolicy, and CookiePolicy. Additional HttpPolicies
     * specified by additionalPolicies will be applied after them
     *
     * @return CallAsyncClient instance
     */
    public CallAsyncClient buildAsyncClient() {
        return new CallAsyncClient(createServiceImpl());
    }

    /**
     * Create synchronous client applying HmacAuthenticationPolicy, UserAgentPolicy,
     * RetryPolicy, and CookiePolicy. Additional HttpPolicies specified by
     * additionalPolicies will be applied after them
     *
     * @return CallClient instance
     */
    public CallClient buildClient() {
        return new CallClient(buildAsyncClient());
    }

    private AzureCommunicationCallingServerServiceImpl createServiceImpl() {
        boolean isConnectionStringSet = connectionString != null && !connectionString.trim().isEmpty();
        boolean isEndpointSet = endpoint != null && !endpoint.trim().isEmpty();
        boolean isAzureKeyCredentialSet = azureKeyCredential != null;
        boolean isTokenCredentialSet = tokenCredential != null;

        if (isConnectionStringSet && isEndpointSet) {
            throw logger.logExceptionAsError(new IllegalArgumentException(
                "Both 'connectionString' and 'endpoint' are set. Just one may be used."));
        }

        if (isConnectionStringSet && isAzureKeyCredentialSet) {
            throw logger.logExceptionAsError(new IllegalArgumentException(
                "Both 'connectionString' and 'keyCredential' are set. Just one may be used."));
        }

        if (isConnectionStringSet && isTokenCredentialSet) {
            throw logger.logExceptionAsError(new IllegalArgumentException(
                "Both 'connectionString' and 'tokenCredential' are set. Just one may be used."));
        }

        if (isAzureKeyCredentialSet && isTokenCredentialSet) {
            throw logger.logExceptionAsError(new IllegalArgumentException(
                "Both 'tokenCredential' and 'keyCredential' are set. Just one may be used."));
        }

        if (isConnectionStringSet) {
            CommunicationConnectionString connectionStringObject = new CommunicationConnectionString(connectionString);
            String endpoint = connectionStringObject.getEndpoint();
            String accessKey = connectionStringObject.getAccessKey();
            this.endpoint(endpoint).credential(new AzureKeyCredential(accessKey));
        }

        Objects.requireNonNull(endpoint);
        if (this.pipeline == null) {
            Objects.requireNonNull(httpClient, "no endpoint provided");
        }

        HttpPipeline builderPipeline = this.pipeline;
        if (this.pipeline == null) {
            builderPipeline = createHttpPipeline(httpClient);
        }

        AzureCommunicationCallingServerServiceImplBuilder clientBuilder = new AzureCommunicationCallingServerServiceImplBuilder();
        clientBuilder.endpoint(endpoint).pipeline(builderPipeline);

        return clientBuilder.buildClient();
    }

    /**
     * Allows the user to set a variety of client-related options, such as
     * user-agent string, headers, etc.
     *
     * @param clientOptions object to be applied
     * @return CallClientBuilder
     */
    public CallClientBuilder clientOptions(ClientOptions clientOptions) {
        this.clientOptions = clientOptions;
        return this;
    }

    private HttpPipelinePolicy createHttpPipelineAuthPolicy() {
        if (this.tokenCredential != null) {
            return new BearerTokenAuthenticationPolicy(this.tokenCredential,
                    "https://communication.azure.com//.default");
        } else if (this.azureKeyCredential != null) {
            return new HmacAuthenticationPolicy(this.azureKeyCredential);
        } else {
            throw logger.logExceptionAsError(
                    new IllegalArgumentException("Missing credential information while building a client."));
        }
    }

    private HttpPipeline createHttpPipeline(HttpClient httpClient) {
        List<HttpPipelinePolicy> policyList = new ArrayList<>();

        ClientOptions buildClientOptions = (clientOptions == null) ? new ClientOptions() : clientOptions;
        HttpLogOptions buildLogOptions = (httpLogOptions == null) ? new HttpLogOptions() : httpLogOptions;

        String applicationId = null;
        if (!CoreUtils.isNullOrEmpty(buildClientOptions.getApplicationId())) {
            applicationId = buildClientOptions.getApplicationId();
        } else if (!CoreUtils.isNullOrEmpty(buildLogOptions.getApplicationId())) {
            applicationId = buildLogOptions.getApplicationId();
        }

        // Add required policies
        policyList.add(this.createHttpPipelineAuthPolicy());
        String clientName = properties.getOrDefault(SDK_NAME, "UnknownName");
        String clientVersion = properties.getOrDefault(SDK_VERSION, "UnknownVersion");
        policyList.add(new UserAgentPolicy(applicationId, clientName, clientVersion, configuration));
        policyList.add((this.retryPolicy == null) ? new RetryPolicy() : this.retryPolicy);
        policyList.add(new CookiePolicy());

        // Add additional policies
        if (!this.customPolicies.isEmpty()) {
            policyList.addAll(this.customPolicies);
        }

        // Add logging policy
        policyList.add(new HttpLoggingPolicy(this.getHttpLogOptions()));

        return new HttpPipelineBuilder().policies(policyList.toArray(new HttpPipelinePolicy[0])).httpClient(httpClient)
                .build();
    }

    private HttpLogOptions getHttpLogOptions() {
        if (this.httpLogOptions == null) {
            this.httpLogOptions = new HttpLogOptions();
        }

        return this.httpLogOptions;
    }
}
