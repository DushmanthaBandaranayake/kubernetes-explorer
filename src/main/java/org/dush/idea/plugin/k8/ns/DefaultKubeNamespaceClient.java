package org.dush.idea.plugin.k8.ns;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1NamespaceList;

/**
 * A wrapper class for accessing k8 namespaces.
 * Internally uses io.kubernetes.client.openapi k8 client.
 *
 * @author dushmantha
 * @since 1.0.0
 */
public class DefaultKubeNamespaceClient implements KubeNamespace
{
    private final ApiClient apiClient;

    public DefaultKubeNamespaceClient( ApiClient apiClient )
    {
        this.apiClient = apiClient;
    }

    @Override
    public V1NamespaceList listNamespaces() throws ApiException
    {
        CoreV1Api api = new CoreV1Api( apiClient );
        return api.
                listNamespace( null,
                        null,
                        null,
                        null,
                        null,
                        null, null,
                        null,
                        10,
                        false );
    }
}
