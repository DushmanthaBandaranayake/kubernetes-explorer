package org.dush.idea.plugin.k8.deployments;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.models.V1DeploymentList;

/**
 * A wrapper class for accessing k8 deployments.
 * Internally uses io.kubernetes.client.openapi k8 client.
 *
 * @author dushmantha
 * @since 1.0.0
 */
public class DefaultKubeDeploymentClient implements KubeDeployment
{
    private final ApiClient apiClient;

    public DefaultKubeDeploymentClient( ApiClient apiClient )
    {
        this.apiClient = apiClient;
    }

    @Override
    public V1DeploymentList listPods( String namespace ) throws ApiException
    {
        AppsV1Api api = new AppsV1Api( apiClient );
        return api.listNamespacedDeployment(
                namespace,
                null,
                false,
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
