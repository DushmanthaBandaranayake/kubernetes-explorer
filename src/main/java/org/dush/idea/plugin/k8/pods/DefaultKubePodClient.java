package org.dush.idea.plugin.k8.pods;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1PodList;

/**
 * A wrapper class for accessing k8 pods.
 * Internally uses io.kubernetes.client.openapi k8 client.
 *
 * @author dushmantha
 * @since 1.0.0
 */
public class DefaultKubePodClient implements KubePods
{
    private final ApiClient apiClient;

    public DefaultKubePodClient( ApiClient apiClient )
    {
        this.apiClient = apiClient;
    }


    @Override
    public V1PodList listPods( String namespace ) throws ApiException
    {
        CoreV1Api api = new CoreV1Api( apiClient );
        V1PodList v1PodList = api.listNamespacedPod( namespace,
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
        return v1PodList;
    }
}
