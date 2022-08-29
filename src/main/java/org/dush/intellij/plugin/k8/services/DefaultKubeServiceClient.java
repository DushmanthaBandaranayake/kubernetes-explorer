package org.dush.intellij.plugin.k8.services;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1ServiceList;

/**
 * A wrapper class for accessing k8 Services.
 * Internally uses io.kubernetes.client.openapi k8 client.
 *
 * @author dushmantha
 * @since 1.0.0
 */
public class DefaultKubeServiceClient implements KubeService
{
    private final ApiClient apiClient;

    public DefaultKubeServiceClient( ApiClient apiClient )
    {
        this.apiClient = apiClient;
    }

    @Override
    public V1ServiceList listServices( String namespace ) throws ApiException
    {
        CoreV1Api api = new CoreV1Api( apiClient );
        return api.listNamespacedService( namespace,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                10,
                false );
    }
}
