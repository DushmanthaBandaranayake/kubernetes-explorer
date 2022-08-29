package org.dush.intellij.plugin.k8.ns;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1NamespaceList;

/**
 * @author dushmantha
 * @since 1.0.0
 */
public interface KubeNamespace
{
    V1NamespaceList listNamespaces() throws ApiException;
}
