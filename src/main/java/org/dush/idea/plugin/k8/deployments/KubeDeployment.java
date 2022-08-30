package org.dush.idea.plugin.k8.deployments;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1DeploymentList;

/**
 * Client for accessing k8 deployments.
 * Internally uses io.kubernetes.client.openapi k8 client.
 *
 * @author dushmantha
 * @since 1.0.0
 */
public interface KubeDeployment
{
    /**
     * List k8 pods for a given namespace.
     *
     * @param namespace kubernetes namespace
     * @return {@link io.kubernetes.client.openapi.models.V1DeploymentList }
     * @throws ApiException indicates connection or execution failures
     */
    V1DeploymentList listPods( String namespace ) throws ApiException;
}
