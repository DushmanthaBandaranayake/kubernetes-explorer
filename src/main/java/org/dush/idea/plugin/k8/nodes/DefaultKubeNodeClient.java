package org.dush.idea.plugin.k8.nodes;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1NodeList;

/**
 * A wrapper class for accessing k8 cluster nodes.
 * Internally uses io.kubernetes.client.openapi k8 client.
 *
 * @author dushmantha
 * @since 1.0.0
 */
public class DefaultKubeNodeClient implements KubeNode
{
    private final ApiClient apiClient;

    public DefaultKubeNodeClient( ApiClient apiClient )
    {
        this.apiClient = apiClient;
    }

    @Override
    public V1NodeList listNodes() throws ApiException
    {
        CoreV1Api api = new CoreV1Api( apiClient );
        return api.
                listNode( null,
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
