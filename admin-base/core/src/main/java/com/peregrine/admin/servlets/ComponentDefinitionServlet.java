package com.peregrine.admin.servlets;

/*-
 * #%L
 * admin base - Core
 * %%
 * Copyright (C) 2017 headwire inc.
 * %%
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * #L%
 */

import com.peregrine.commons.servlets.AbstractBaseServlet;
import com.peregrine.commons.servlets.ServletHelper;
import com.peregrine.commons.util.PerConstants;
import org.apache.sling.api.resource.Resource;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.function.Function;

import static com.peregrine.admin.servlets.AdminPaths.RESOURCE_TYPE_COMPONENT_DEFINITION;
import static com.peregrine.commons.Strings.COLON;
import static com.peregrine.commons.Strings.SLASH;
import static com.peregrine.commons.util.PerConstants.*;
import static com.peregrine.commons.util.PerUtil.*;
import static java.util.Objects.nonNull;
import static org.apache.sling.api.servlets.ServletResolverConstants.SLING_SERVLET_METHODS;
import static org.apache.sling.api.servlets.ServletResolverConstants.SLING_SERVLET_RESOURCE_TYPES;
import static org.osgi.framework.Constants.SERVICE_DESCRIPTION;
import static org.osgi.framework.Constants.SERVICE_VENDOR;

/**
 * Provides the Component Definition of a Resource
 *
 * The API Definition can be found in the Swagger Editor configuration:
 *    ui.apps/src/main/content/jcr_root/api/definintions/admin.yaml
 */
@Component(
    service = Servlet.class,
    property = {
        SERVICE_DESCRIPTION + EQUALS + PER_PREFIX + "Component Definition Servlet",
        SERVICE_VENDOR + EQUALS + PER_VENDOR,
        SLING_SERVLET_METHODS + EQUALS + GET,
        SLING_SERVLET_RESOURCE_TYPES + EQUALS + RESOURCE_TYPE_COMPONENT_DEFINITION
    }
)
@SuppressWarnings("serial")
public final class ComponentDefinitionServlet extends AbstractBaseServlet {

    public static final String EXPLORER_DIALOG_JSON = "explorer_dialog.json";
    public static final String DIALOG_JSON = "dialog.json";
    public static final String OG_TAG_DIALOG_JSON = "og_tag_dialog.json";
    private static final String APPS_PREFIX = APPS_ROOT + SLASH;

    @Override
    protected Response handleRequest(final Request request) throws IOException {
        String path = request.getParameter(PATH);
        Resource resource = request.getResourceByPath(path);
        boolean isPage = resource.isResourceType(PerConstants.PAGE_PRIMARY_TYPE);
        if (isPage) {
            resource = resource.getChild(PerConstants.JCR_CONTENT);
        } else if ("/apps/admin/components/assetview".equals(path)) {
            isPage = true;
        }

        final JsonResponse answer = new JsonResponse();
        final Resource typedResource = findTypedResource(resource);
        final Resource component = extractComponent(typedResource);
        path = component.getPath();
        answer.writeAttribute(PATH, path);
        answer.writeAttribute(NAME, ServletHelper.componentPathToName(path));
        writeInheritedChildRaw(component, isPage ? EXPLORER_DIALOG_JSON : DIALOG_JSON, answer, MODEL);
        writeInheritedChildRaw(component, OG_TAG_DIALOG_JSON, answer, OG_TAGS);

        return answer;
    }

    private void writeInheritedChildRaw(
            final Resource component,
            final String relPath,
            final JsonResponse target,
            final String name
    ) throws IOException {
        final Resource resource = getInheritedChild(component, relPath);
        if (nonNull(resource)) {
            target.writeAttributeRaw(name, ServletHelper.asString(resource.adaptTo(InputStream.class)).toString());
        }
    }

    private Resource findTypedResource(final Resource resource) {
        if (resource.getValueMap().containsKey(SLING_RESOURCE_TYPE)) {
            return resource;
        }

        return findTypedResource(resource.getParent());
    }

    private Resource extractComponent(final Resource resource) {
        if (resource.getPath().startsWith(APPS_PREFIX)) {
            return resource;
        }

        return extractComponent(resource, Resource::getResourceType);
    }

    private Resource extractComponent(final Resource resource, final Function<Resource, String> getType) {
        return Optional.of(resource)
                .map(getType)
                .map(s -> s.replaceAll(COLON, SLASH))
                .map(t -> t.startsWith(APPS_PREFIX) ? t : APPS_PREFIX + t)
                .map(resource::getChild)
                .orElse(null);
    }

    private Resource getInheritedChild(final Resource component, final String relPath) {
        final Resource answer = component.getChild(relPath);
        if (nonNull(answer)) {
            return answer;
        }

        final Resource superComponent = extractSuperComponent(component);
        if (nonNull(superComponent)) {
            return getInheritedChild(superComponent, relPath);
        }

        return null;
    }

    private Resource extractSuperComponent(final Resource resource) {
        return extractComponent(resource,  Resource::getResourceSuperType);
    }

}

