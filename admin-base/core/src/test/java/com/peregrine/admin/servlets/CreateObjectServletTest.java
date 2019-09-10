package com.peregrine.admin.servlets;

import com.peregrine.admin.resource.AdminResourceHandler.ManagementException;
import com.peregrine.admin.resource.TreeTest;
import org.apache.sling.api.resource.Resource;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;

import static com.peregrine.commons.util.PerConstants.NAME;
import static com.peregrine.commons.util.PerConstants.OBJECT_PRIMARY_TYPE;
import static com.peregrine.commons.util.PerConstants.PATH;
import static com.peregrine.commons.util.PerConstants.TEMPLATE_PATH;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class CreateObjectServletTest
    extends AbstractServletTest
{
    private static final Logger logger = LoggerFactory.getLogger(TreeTest.class.getName());

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Test
    public void testSimpleObjectCreation() throws ManagementException, RepositoryException {
        String resourcePath = "/perapi/admin/createObject.json";
        String parentPath = "/content/test";
        String newResourceName = "newObject";
        String newResourcePath = parentPath + "/" + newResourceName;

        setupCreation(resourcePath, PATH, parentPath, NAME, newResourceName, TEMPLATE_PATH, "peregrine/testTemplate");
        when(mockParentNode.addNode(eq(newResourceName), eq(OBJECT_PRIMARY_TYPE))).thenReturn(mockNewNode);

        // Create and Setup Servlet, call it and check the returned Resource
        CreateObjectServlet servlet = new CreateObjectServlet();
        setupServlet(servlet);
        Resource answer = servlet.doAction(mockRequest);
        assertNotNull("No Resource Created", answer);
        assertEquals("Wrong Folder Create (by path)", newResourcePath, answer.getPath());
    }
}
