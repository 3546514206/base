/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.catalina;

import org.apache.catalina.deploy.NamingResourcesImpl;
import org.apache.tomcat.InstanceManager;
import org.apache.tomcat.JarScanner;
import org.apache.tomcat.util.descriptor.web.*;
import org.apache.tomcat.util.http.CookieProcessor;

import javax.servlet.*;
import javax.servlet.descriptor.JspConfigDescriptor;
import java.net.URL;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * A <b>Context</b> is a Container that represents a servlet context, and
 * therefore an individual web application, in the Catalina servlet engine.
 * It is therefore useful in almost every deployment of Catalina (even if a
 * Connector attached to a web server (such as Apache) uses the web server's
 * facilities to identify the appropriate Wrapper to handle this request.
 * It also provides a convenient mechanism to use Interceptors that see
 * every request processed by this particular web application.
 * <p>
 * The parent Container attached to a Context is generally a Host, but may
 * be some other implementation, or may be omitted if it is not necessary.
 * <p>
 * The child containers attached to a Context are generally implementations
 * of Wrapper (representing individual servlet definitions).
 * <p>
 *
 * @author Craig R. McClanahan
 */
public interface Context extends Container {


    // ----------------------------------------------------- Manifest Constants

    /**
     * Container event for adding a welcome file.
     */
    String ADD_WELCOME_FILE_EVENT = "addWelcomeFile";

    /**
     * Container event for removing a wrapper.
     */
    String REMOVE_WELCOME_FILE_EVENT = "removeWelcomeFile";

    /**
     * Container event for clearing welcome files.
     */
    String  CLEAR_WELCOME_FILES_EVENT = "clearWelcomeFiles";

    /**
     * Container event for changing the ID of a session.
     */
    String CHANGE_SESSION_ID_EVENT = "changeSessionId";


    // ------------------------------------------------------------- Properties

    /**
     * Returns <code>true</code> if requests mapped to servlets without
     * "multipart config" to parse multipart/form-data requests anyway.
     *
     * @return <code>true</code> if requests mapped to servlets without
     *    "multipart config" to parse multipart/form-data requests,
     *    <code>false</code> otherwise.
     */
    boolean getAllowCasualMultipartParsing();


   /**
     * Set to <code>true</code> to allow requests mapped to servlets that
     * do not explicitly declare @MultipartConfig or have
     * &lt;multipart-config&gt; specified in web.xml to parse
     * multipart/form-data requests.
     *
     * @param allowCasualMultipartParsing <code>true</code> to allow such
     *        casual parsing, <code>false</code> otherwise.
     */
   void setAllowCasualMultipartParsing(boolean allowCasualMultipartParsing);


    /**
     * Obtain the registered application event listeners.
     *
     * @return An array containing the application event listener instances for
     *         this web application in the order they were specified in the web
     *         application deployment descriptor
     */
    Object[] getApplicationEventListeners();


    /**
     * Store the set of initialized application event listener objects,
     * in the order they were specified in the web application deployment
     * descriptor, for this application.
     *
     * @param listeners The set of instantiated listener objects.
     */
    void setApplicationEventListeners(Object[] listeners);


    /**
     * Obtain the registered application lifecycle listeners.
     *
     * @return An array containing the application lifecycle listener instances
     *         for this web application in the order they were specified in the
     *         web application deployment descriptor
     */
    Object[] getApplicationLifecycleListeners();


    /**
     * Store the set of initialized application lifecycle listener objects,
     * in the order they were specified in the web application deployment
     * descriptor, for this application.
     *
     * @param listeners The set of instantiated listener objects.
     */
    void setApplicationLifecycleListeners(Object[] listeners);


    /**
     * Obtain the character set name to use with the given Locale. Note that
     * different Contexts may have different mappings of Locale to character
     * set.
     *
     * @param locale The locale for which the mapped character set should be
     *               returned
     *
     * @return The name of the character set to use with the given Locale
     */
    String getCharset(Locale locale);


    /**
     * Return the URL of the XML descriptor for this context.
     *
     * @return The URL of the XML descriptor for this context
     */
    URL getConfigFile();


    /**
     * Set the URL of the XML descriptor for this context.
     *
     * @param configFile The URL of the XML descriptor for this context.
     */
    void setConfigFile(URL configFile);


    /**
     * Return the "correctly configured" flag for this Context.
     *
     * @return <code>true</code> if the Context has been correctly configured,
     *         otherwise <code>false</code>
     */
    boolean getConfigured();


    /**
     * Set the "correctly configured" flag for this Context.  This can be
     * set to false by startup listeners that detect a fatal configuration
     * error to avoid the application from being made available.
     *
     * @param configured The new correctly configured flag
     */
    void setConfigured(boolean configured);


    /**
     * Return the "use cookies for session ids" flag.
     *
     * @return <code>true</code> if it is permitted to use cookies to track
     *         session IDs for this web application, otherwise
     *         <code>false</code>
     */
    boolean getCookies();


    /**
     * Set the "use cookies for session ids" flag.
     *
     * @param cookies The new flag
     */
    void setCookies(boolean cookies);


    /**
     * Gets the name to use for session cookies. Overrides any setting that
     * may be specified by the application.
     *
     * @return  The value of the default session cookie name or null if not
     *          specified
     */
    String getSessionCookieName();


    /**
     * Sets the name to use for session cookies. Overrides any setting that
     * may be specified by the application.
     *
     * @param sessionCookieName   The name to use
     */
    void setSessionCookieName(String sessionCookieName);


    /**
     * Gets the value of the use HttpOnly cookies for session cookies flag.
     *
     * @return <code>true</code> if the HttpOnly flag should be set on session
     *         cookies
     */
    boolean getUseHttpOnly();


    /**
     * Sets the use HttpOnly cookies for session cookies flag.
     *
     * @param useHttpOnly   Set to <code>true</code> to use HttpOnly cookies
     *                          for session cookies
     */
    void setUseHttpOnly(boolean useHttpOnly);


    /**
     * Gets the domain to use for session cookies. Overrides any setting that
     * may be specified by the application.
     *
     * @return  The value of the default session cookie domain or null if not
     *          specified
     */
    String getSessionCookieDomain();


    /**
     * Sets the domain to use for session cookies. Overrides any setting that
     * may be specified by the application.
     *
     * @param sessionCookieDomain   The domain to use
     */
    void setSessionCookieDomain(String sessionCookieDomain);


    /**
     * Gets the path to use for session cookies. Overrides any setting that
     * may be specified by the application.
     *
     * @return  The value of the default session cookie path or null if not
     *          specified
     */
    String getSessionCookiePath();


    /**
     * Sets the path to use for session cookies. Overrides any setting that
     * may be specified by the application.
     *
     * @param sessionCookiePath   The path to use
     */
    void setSessionCookiePath(String sessionCookiePath);


    /**
     * Is a / added to the end of the session cookie path to ensure browsers,
     * particularly IE, don't send a session cookie for context /foo with
     * requests intended for context /foobar.
     *
     * @return <code>true</code> if the slash is added, otherwise
     *         <code>false</code>
     */
    boolean getSessionCookiePathUsesTrailingSlash();


    /**
     * Configures if a / is added to the end of the session cookie path to
     * ensure browsers, particularly IE, don't send a session cookie for context
     * /foo with requests intended for context /foobar.
     *
     * @param sessionCookiePathUsesTrailingSlash   <code>true</code> if the
     *                                             slash is should be added,
     *                                             otherwise <code>false</code>
     */
    void setSessionCookiePathUsesTrailingSlash(
            boolean sessionCookiePathUsesTrailingSlash);


    /**
     * Return the "allow crossing servlet contexts" flag.
     *
     * @return <code>true</code> if cross-contest requests are allowed from this
     *         web applications, otherwise <code>false</code>
     */
    boolean getCrossContext();

    /**
     * Set the "allow crossing servlet contexts" flag.
     *
     * @param crossContext The new cross contexts flag
     */
    void setCrossContext(boolean crossContext);

    /**
     * Return the alternate Deployment Descriptor name.
     *
     * @return the name
     */
    String getAltDDName();

    /**
     * Set an alternate Deployment Descriptor name.
     *
     * @param altDDName The new name
     */
    void setAltDDName(String altDDName) ;

    /**
     * Return the deny-uncovered-http-methods flag for this web application.
     *
     * @return The current value of the flag
     */
    boolean getDenyUncoveredHttpMethods();


    /**
     * Set the deny-uncovered-http-methods flag for this web application.
     *
     * @param denyUncoveredHttpMethods The new deny-uncovered-http-methods flag
     */
    void setDenyUncoveredHttpMethods(boolean denyUncoveredHttpMethods);


    /**
     * Return the display name of this web application.
     *
     * @return The display name
     */
    String getDisplayName();


    /**
     * Set the display name of this web application.
     *
     * @param displayName The new display name
     */
    void setDisplayName(String displayName);


    /**
     * Return the distributable flag for this web application.
     */
    boolean getDistributable();


    /**
     * Set the distributable flag for this web application.
     *
     * @param distributable The new distributable flag
     */
    void setDistributable(boolean distributable);


    /**
     * Return the document root for this Context.  This can be an absolute
     * pathname, a relative pathname, or a URL.
     */
    String getDocBase();


    /**
     * Set the document root for this Context.  This can be an absolute
     * pathname, a relative pathname, or a URL.
     *
     * @param docBase The new document root
     */
    void setDocBase(String docBase);


    /**
     * Return the URL encoded context path, using UTF-8.
     */
    String getEncodedPath();


    /**
     * Return the boolean on the annotations parsing.
     */
    boolean getIgnoreAnnotations();


    /**
     * Set the boolean on the annotations parsing for this web
     * application.
     *
     * @param ignoreAnnotations The boolean on the annotations parsing
     */
    void setIgnoreAnnotations(boolean ignoreAnnotations);


    /**
     * Return the login configuration descriptor for this web application.
     */
    LoginConfig getLoginConfig();


    /**
     * Set the login configuration descriptor for this web application.
     *
     * @param config The new login configuration
     */
    void setLoginConfig(LoginConfig config);


    /**
     * Return the naming resources associated with this web application.
     */
    NamingResourcesImpl getNamingResources();


    /**
     * Set the naming resources for this web application.
     *
     * @param namingResources The new naming resources
     */
    void setNamingResources(NamingResourcesImpl namingResources);


    /**
     * Return the context path for this web application.
     */
    String getPath();


    /**
     * Set the context path for this web application.
     *
     * @param path The new context path
     */
    void setPath(String path);


    /**
     * Return the public identifier of the deployment descriptor DTD that is
     * currently being parsed.
     */
    String getPublicId();


    /**
     * Set the public identifier of the deployment descriptor DTD that is
     * currently being parsed.
     *
     * @param publicId The public identifier
     */
    void setPublicId(String publicId);


    /**
     * Return the reloadable flag for this web application.
     */
    boolean getReloadable();


    /**
     * Set the reloadable flag for this web application.
     *
     * @param reloadable The new reloadable flag
     */
    void setReloadable(boolean reloadable);


    /**
     * Return the override flag for this web application.
     */
    boolean getOverride();


    /**
     * Set the override flag for this web application.
     *
     * @param override The new override flag
     */
    void setOverride(boolean override);


    /**
     * Return the privileged flag for this web application.
     */
    boolean getPrivileged();


    /**
     * Set the privileged flag for this web application.
     *
     * @param privileged The new privileged flag
     */
    void setPrivileged(boolean privileged);


    /**
     * Return the servlet context for which this Context is a facade.
     */
    ServletContext getServletContext();


    /**
     * Return the default session timeout (in minutes) for this
     * web application.
     */
    int getSessionTimeout();


    /**
     * Set the default session timeout (in minutes) for this
     * web application.
     *
     * @param timeout The new default session timeout
     */
    void setSessionTimeout(int timeout);


    /**
     * Returns <code>true</code> if remaining request data will be read
     * (swallowed) even the request violates a data size constraint.
     *
     * @return <code>true</code> if data will be swallowed (default),
     *    <code>false</code> otherwise.
     */
    boolean getSwallowAbortedUploads();


    /**
     * Set to <code>false</code> to disable request data swallowing
     * after an upload was aborted due to size constraints.
     *
     * @param swallowAbortedUploads <code>false</code> to disable
     *        swallowing, <code>true</code> otherwise (default).
     */
    void setSwallowAbortedUploads(boolean swallowAbortedUploads);

    /**
     * Return the value of the swallowOutput flag.
     */
    boolean getSwallowOutput();


    /**
     * Set the value of the swallowOutput flag. If set to true, the system.out
     * and system.err will be redirected to the logger during a servlet
     * execution.
     *
     * @param swallowOutput The new value
     */
    void setSwallowOutput(boolean swallowOutput);


    /**
     * Return the Java class name of the Wrapper implementation used
     * for servlets registered in this Context.
     */
    String getWrapperClass();


    /**
     * Set the Java class name of the Wrapper implementation used
     * for servlets registered in this Context.
     *
     * @param wrapperClass The new wrapper class
     */
    void setWrapperClass(String wrapperClass);


    /**
     * Will the parsing of web.xml and web-fragment.xml files for this Context
     * be performed by a namespace aware parser?
     *
     * @return true if namespace awareness is enabled.
     */
    boolean getXmlNamespaceAware();


    /**
     * Controls whether the parsing of web.xml and web-fragment.xml files for
     * this Context will be performed by a namespace aware parser.
     *
     * @param xmlNamespaceAware true to enable namespace awareness
     */
    void setXmlNamespaceAware(boolean xmlNamespaceAware);


    /**
     * Will the parsing of web.xml and web-fragment.xml files for this Context
     * be performed by a validating parser?
     *
     * @return true if validation is enabled.
     */
    boolean getXmlValidation();


    /**
     * Controls whether the parsing of web.xml and web-fragment.xml files
     * for this Context will be performed by a validating parser.
     *
     * @param xmlValidation true to enable xml validation
     */
    void setXmlValidation(boolean xmlValidation);


    /**
     * Will the parsing of web.xml, web-fragment.xml, *.tld, *.jspx, *.tagx and
     * tagplugin.xml files for this Context block the use of external entities?
     *
     * @return true if access to external entities is blocked
     */
    boolean getXmlBlockExternal();


    /**
     * Controls whether the parsing of web.xml, web-fragment.xml, *.tld, *.jspx,
     * *.tagx and tagplugin.xml files for this Context will block the use of
     * external entities.
     *
     * @param xmlBlockExternal true to block external entities
     */
    void setXmlBlockExternal(boolean xmlBlockExternal);


    /**
     * Will the parsing of *.tld files for this Context be performed by a
     * validating parser?
     *
     * @return true if validation is enabled.
     */
    boolean getTldValidation();


    /**
     * Controls whether the parsing of *.tld files for this Context will be
     * performed by a validating parser.
     *
     * @param tldValidation true to enable xml validation
     */
    void setTldValidation(boolean tldValidation);


    /**
     * Get the Jar Scanner to be used to scan for JAR resources for this
     * context.
     * @return  The Jar Scanner configured for this context.
     */
    JarScanner getJarScanner();

    /**
     * Set the Jar Scanner to be used to scan for JAR resources for this
     * context.
     * @param jarScanner    The Jar Scanner to be used for this context.
     */
    void setJarScanner(JarScanner jarScanner);

    /**
     * Obtain the {@link Authenticator} that is used by this context or
     * <code>null</code> if none is used.
     */
    Authenticator getAuthenticator();

    /**
     * Should the effective web.xml for this context be logged on context start?
     */
    boolean getLogEffectiveWebXml();

    /**
     * Set whether or not the effective web.xml for this context should be
     * logged on context start.
     */
    void setLogEffectiveWebXml(boolean logEffectiveWebXml);

    /**
     * Get the instance manager associated with this context.
     */
    InstanceManager getInstanceManager();

    /**
     * Set the instance manager associated with this context.
     */
    void setInstanceManager(InstanceManager instanceManager);

    /**
     * Obtains the regular expression that specifies which container provided
     * SCIs should be filtered out and not used for this context. Matching uses
     * {@link java.util.regex.Matcher#find()} so the regular expression only has
     * to match a sub-string of the fully qualified class name of the container
     * provided SCI for it to be filtered out.
     *
     * @return The regular expression against which the fully qualified class
     *         name of each container provided SCI will be checked
     */
    String getContainerSciFilter();

    /**
     * Sets the regular expression that specifies which container provided SCIs
     * should be filtered out and not used for this context. Matching uses
     * {@link java.util.regex.Matcher#find()} so the regular expression only has
     * to match a sub-string of the fully qualified class name of the container
     * provided SCI for it to be filtered out.
     *
     * @param containerSciFilter The regular expression against which the fully
     *                           qualified class name of each container provided
     *                           SCI should be checked
     */
    void setContainerSciFilter(String containerSciFilter);


    // --------------------------------------------------------- Public Methods

    /**
     * Add a new Listener class name to the set of Listeners
     * configured for this application.
     *
     * @param listener Java class name of a listener class
     */
    void addApplicationListener(String listener);


    /**
     * Add a new application parameter for this application.
     *
     * @param parameter The new application parameter
     */
    void addApplicationParameter(ApplicationParameter parameter);


    /**
     * Add a security constraint to the set for this web application.
     */
    void addConstraint(SecurityConstraint constraint);


    /**
     * Add an error page for the specified error or Java exception.
     *
     * @param errorPage The error page definition to be added
     */
    void addErrorPage(ErrorPage errorPage);


    /**
     * Add a filter definition to this Context.
     *
     * @param filterDef The filter definition to be added
     */
    void addFilterDef(FilterDef filterDef);


    /**
     * Add a filter mapping to this Context.
     *
     * @param filterMap The filter mapping to be added
     */
    void addFilterMap(FilterMap filterMap);

    /**
     * Add a filter mapping to this Context before the mappings defined in the
     * deployment descriptor but after any other mappings added via this method.
     *
     * @param filterMap The filter mapping to be added
     *
     * @exception IllegalArgumentException if the specified filter name
     *  does not match an existing filter definition, or the filter mapping
     *  is malformed
     */
    void addFilterMapBefore(FilterMap filterMap);

    /**
     * Add the classname of an InstanceListener to be added to each
     * Wrapper appended to this Context.
     *
     * @param listener Java class name of an InstanceListener class
     */
    void addInstanceListener(String listener);


    /**
     * Add a Locale Encoding Mapping (see Sec 5.4 of Servlet spec 2.4)
     *
     * @param locale locale to map an encoding for
     * @param encoding encoding to be used for a give locale
     */
    void addLocaleEncodingMappingParameter(String locale, String encoding);


    /**
     * Add a new MIME mapping, replacing any existing mapping for
     * the specified extension.
     *
     * @param extension Filename extension being mapped
     * @param mimeType Corresponding MIME type
     */
    void addMimeMapping(String extension, String mimeType);


    /**
     * Add a new context initialization parameter, replacing any existing
     * value for the specified name.
     *
     * @param name Name of the new parameter
     * @param value Value of the new  parameter
     */
    void addParameter(String name, String value);


    /**
     * Add a security role reference for this web application.
     *
     * @param role Security role used in the application
     * @param link Actual security role to check for
     */
    void addRoleMapping(String role, String link);


    /**
     * Add a new security role for this web application.
     *
     * @param role New security role
     */
    void addSecurityRole(String role);


    /**
     * Add a new servlet mapping, replacing any existing mapping for
     * the specified pattern.
     *
     * @param pattern URL pattern to be mapped
     * @param name Name of the corresponding servlet to execute
     */
    void addServletMapping(String pattern, String name);


    /**
     * Add a new servlet mapping, replacing any existing mapping for
     * the specified pattern.
     *
     * @param pattern URL pattern to be mapped
     * @param name Name of the corresponding servlet to execute
     * @param jspWildcard true if name identifies the JspServlet
     * and pattern contains a wildcard; false otherwise
     */
    void addServletMapping(String pattern, String name,
                           boolean jspWildcard);


    /**
     * Add a resource which will be watched for reloading by the host auto
     * deployer. Note: this will not be used in embedded mode.
     *
     * @param name Path to the resource, relative to docBase
     */
    void addWatchedResource(String name);


    /**
     * Add a new welcome file to the set recognized by this Context.
     *
     * @param name New welcome file name
     */
    void addWelcomeFile(String name);


    /**
     * Add the classname of a LifecycleListener to be added to each
     * Wrapper appended to this Context.
     *
     * @param listener Java class name of a LifecycleListener class
     */
    void addWrapperLifecycle(String listener);


    /**
     * Add the classname of a ContainerListener to be added to each
     * Wrapper appended to this Context.
     *
     * @param listener Java class name of a ContainerListener class
     */
    void addWrapperListener(String listener);


    /**
     * Factory method to create and return a new Wrapper instance, of
     * the Java implementation class appropriate for this Context
     * implementation.  The constructor of the instantiated Wrapper
     * will have been called, but no properties will have been set.
     */
    Wrapper createWrapper();


    /**
     * Return the set of application listener class names configured
     * for this application.
     */
    String[] findApplicationListeners();


    /**
     * Return the set of application parameters for this application.
     */
    ApplicationParameter[] findApplicationParameters();


    /**
     * Return the set of security constraints for this web application.
     * If there are none, a zero-length array is returned.
     */
    SecurityConstraint[] findConstraints();


    /**
     * Return the error page entry for the specified HTTP error code,
     * if any; otherwise return <code>null</code>.
     *
     * @param errorCode Error code to look up
     */
    ErrorPage findErrorPage(int errorCode);


    /**
     * Return the error page entry for the specified Java exception type,
     * if any; otherwise return <code>null</code>.
     *
     * @param exceptionType Exception type to look up
     */
    ErrorPage findErrorPage(String exceptionType);



    /**
     * Return the set of defined error pages for all specified error codes
     * and exception types.
     */
    ErrorPage[] findErrorPages();


    /**
     * Return the filter definition for the specified filter name, if any;
     * otherwise return <code>null</code>.
     *
     * @param filterName Filter name to look up
     */
    FilterDef findFilterDef(String filterName);


    /**
     * Return the set of defined filters for this Context.
     */
    FilterDef[] findFilterDefs();


    /**
     * Return the set of filter mappings for this Context.
     */
    FilterMap[] findFilterMaps();


    /**
     * Return the set of InstanceListener classes that will be added to
     * newly created Wrappers automatically.
     */
    String[] findInstanceListeners();


    /**
     * Return the MIME type to which the specified extension is mapped,
     * if any; otherwise return <code>null</code>.
     *
     * @param extension Extension to map to a MIME type
     */
    String findMimeMapping(String extension);


    /**
     * Return the extensions for which MIME mappings are defined.  If there
     * are none, a zero-length array is returned.
     */
    String[] findMimeMappings();


    /**
     * Return the value for the specified context initialization
     * parameter name, if any; otherwise return <code>null</code>.
     *
     * @param name Name of the parameter to return
     */
    String findParameter(String name);


    /**
     * Return the names of all defined context initialization parameters
     * for this Context.  If no parameters are defined, a zero-length
     * array is returned.
     */
    String[] findParameters();


    /**
     * For the given security role (as used by an application), return the
     * corresponding role name (as defined by the underlying Realm) if there
     * is one.  Otherwise, return the specified role unchanged.
     *
     * @param role Security role to map
     */
    String findRoleMapping(String role);


    /**
     * Return <code>true</code> if the specified security role is defined
     * for this application; otherwise return <code>false</code>.
     *
     * @param role Security role to verify
     */
    boolean findSecurityRole(String role);


    /**
     * Return the security roles defined for this application.  If none
     * have been defined, a zero-length array is returned.
     */
    String[] findSecurityRoles();


    /**
     * Return the servlet name mapped by the specified pattern (if any);
     * otherwise return <code>null</code>.
     *
     * @param pattern Pattern for which a mapping is requested
     */
    String findServletMapping(String pattern);


    /**
     * Return the patterns of all defined servlet mappings for this
     * Context.  If no mappings are defined, a zero-length array is returned.
     */
    String[] findServletMappings();


    /**
     * Return the context-relative URI of the error page for the specified
     * HTTP status code, if any; otherwise return <code>null</code>.
     *
     * @param status HTTP status code to look up
     */
    String findStatusPage(int status);


    /**
     * Return the set of HTTP status codes for which error pages have
     * been specified.  If none are specified, a zero-length array
     * is returned.
     */
    int[] findStatusPages();


    /**
     * Get the associated ThreadBindingListener.
     */
    ThreadBindingListener getThreadBindingListener();


    /**
     * Get the associated ThreadBindingListener.
     */
    void setThreadBindingListener(ThreadBindingListener threadBindingListener);


    /**
     * Return the set of watched resources for this Context. If none are
     * defined, a zero length array will be returned.
     */
    String[] findWatchedResources();


    /**
     * Return <code>true</code> if the specified welcome file is defined
     * for this Context; otherwise return <code>false</code>.
     *
     * @param name Welcome file to verify
     */
    boolean findWelcomeFile(String name);


    /**
     * Return the set of welcome files defined for this Context.  If none are
     * defined, a zero-length array is returned.
     */
    String[] findWelcomeFiles();


    /**
     * Return the set of LifecycleListener classes that will be added to
     * newly created Wrappers automatically.
     */
    String[] findWrapperLifecycles();


    /**
     * Return the set of ContainerListener classes that will be added to
     * newly created Wrappers automatically.
     */
    String[] findWrapperListeners();


    /**
     * Notify all {@link javax.servlet.ServletRequestListener}s that a request
     * has started.
     * @return <code>true</code> if the listeners fire successfully, else
     *         <code>false</code>
     */
    boolean fireRequestInitEvent(ServletRequest request);

    /**
     * Notify all {@link javax.servlet.ServletRequestListener}s that a request
     * has ended.
     * @return <code>true</code> if the listeners fire successfully, else
     *         <code>false</code>
     */
    boolean fireRequestDestroyEvent(ServletRequest request);

    /**
     * Reload this web application, if reloading is supported.
     *
     * @exception IllegalStateException if the <code>reloadable</code>
     *  property is set to <code>false</code>.
     */
    void reload();


    /**
     * Remove the specified application listener class from the set of
     * listeners for this application.
     *
     * @param listener Java class name of the listener to be removed
     */
    void removeApplicationListener(String listener);


    /**
     * Remove the application parameter with the specified name from
     * the set for this application.
     *
     * @param name Name of the application parameter to remove
     */
    void removeApplicationParameter(String name);


    /**
     * Remove the specified security constraint from this web application.
     *
     * @param constraint Constraint to be removed
     */
    void removeConstraint(SecurityConstraint constraint);


    /**
     * Remove the error page for the specified error code or
     * Java language exception, if it exists; otherwise, no action is taken.
     *
     * @param errorPage The error page definition to be removed
     */
    void removeErrorPage(ErrorPage errorPage);


    /**
     * Remove the specified filter definition from this Context, if it exists;
     * otherwise, no action is taken.
     *
     * @param filterDef Filter definition to be removed
     */
    void removeFilterDef(FilterDef filterDef);


    /**
     * Remove a filter mapping from this Context.
     *
     * @param filterMap The filter mapping to be removed
     */
    void removeFilterMap(FilterMap filterMap);


    /**
     * Remove a class name from the set of InstanceListener classes that
     * will be added to newly created Wrappers.
     *
     * @param listener Class name of an InstanceListener class to be removed
     */
    void removeInstanceListener(String listener);


    /**
     * Remove the MIME mapping for the specified extension, if it exists;
     * otherwise, no action is taken.
     *
     * @param extension Extension to remove the mapping for
     */
    void removeMimeMapping(String extension);


    /**
     * Remove the context initialization parameter with the specified
     * name, if it exists; otherwise, no action is taken.
     *
     * @param name Name of the parameter to remove
     */
    void removeParameter(String name);


    /**
     * Remove any security role reference for the specified name
     *
     * @param role Security role (as used in the application) to remove
     */
    void removeRoleMapping(String role);


    /**
     * Remove any security role with the specified name.
     *
     * @param role Security role to remove
     */
    void removeSecurityRole(String role);


    /**
     * Remove any servlet mapping for the specified pattern, if it exists;
     * otherwise, no action is taken.
     *
     * @param pattern URL pattern of the mapping to remove
     */
    void removeServletMapping(String pattern);


    /**
     * Remove the specified watched resource name from the list associated
     * with this Context.
     *
     * @param name Name of the watched resource to be removed
     */
    void removeWatchedResource(String name);


    /**
     * Remove the specified welcome file name from the list recognized
     * by this Context.
     *
     * @param name Name of the welcome file to be removed
     */
    void removeWelcomeFile(String name);


    /**
     * Remove a class name from the set of LifecycleListener classes that
     * will be added to newly created Wrappers.
     *
     * @param listener Class name of a LifecycleListener class to be removed
     */
    void removeWrapperLifecycle(String listener);


    /**
     * Remove a class name from the set of ContainerListener classes that
     * will be added to newly created Wrappers.
     *
     * @param listener Class name of a ContainerListener class to be removed
     */
    void removeWrapperListener(String listener);


    /**
     * Return the real path for a given virtual path, if possible; otherwise
     * return <code>null</code>.
     *
     * @param path The path to the desired resource
     */
    String getRealPath(String path);


    /**
     * Return the effective major version of the Servlet spec used by this
     * context.
     */
    int getEffectiveMajorVersion();


    /**
     * Set the effective major version of the Servlet spec used by this
     * context.
     */
    void setEffectiveMajorVersion(int major);


    /**
     * Return the effective minor version of the Servlet spec used by this
     * context.
     */
    int getEffectiveMinorVersion();


    /**
     * Set the effective minor version of the Servlet spec used by this
     * context.
     */
    void setEffectiveMinorVersion(int minor);


    /**
     * Obtain the JSP configuration for this context.
     * Will be null if there is no JSP configuration.
     */
    JspConfigDescriptor getJspConfigDescriptor();

    /**
     * Set the JspConfigDescriptor for this context.
     * A null value indicates there is not JSP configuration.
     */
    void setJspConfigDescriptor(JspConfigDescriptor descriptor);

    /**
     * Add a ServletContainerInitializer instance to this web application.
     *
     * @param sci       The instance to add
     * @param classes   The classes in which the initializer expressed an
     *                  interest
     */
    void addServletContainerInitializer(
            ServletContainerInitializer sci, Set<Class<?>> classes);

    /**
     * Is this Context paused whilst it is reloaded?
     */
    boolean getPaused();


    /**
     * Is this context using version 2.2 of the Servlet spec?
     */
    boolean isServlet22();

    /**
     * Notification that servlet security has been dynamically set in a
     * {@link javax.servlet.ServletRegistration.Dynamic}
     * @param registration servlet security was modified for
     * @param servletSecurityElement new security constraints for this servlet
     * @return urls currently mapped to this registration that are already
     *         present in web.xml
     */
    Set<String> addServletSecurity(ServletRegistration.Dynamic registration,
            ServletSecurityElement servletSecurityElement);

    /**
     * Obtains the list of Servlets that expect a resource to be present.
     *
     * @return  A comma separated list of Servlet names as used in web.xml
     */
    String getResourceOnlyServlets();

    /**
     * Sets the (comma separated) list of Servlets that expect a resource to be
     * present. Used to ensure that welcome files associated with Servlets that
     * expect a resource to be present are not mapped when there is no resource.
     */
    void setResourceOnlyServlets(String resourceOnlyServlets);

    /**
     * Checks the named Servlet to see if it expects a resource to be present.
     *
     * @param servletName   Name of the Servlet (as per web.xml) to check
     * @return              <code>true</code> if the Servlet expects a resource,
     *                      otherwise <code>false</code>
     */
    boolean isResourceOnlyServlet(String servletName);

    /**
     * Return the base name to use for WARs, directories or context.xml files
     * for this context.
     */
    String getBaseName();

    /**
     * Set the version of this web application - used to differentiate
     * different versions of the same web application when using parallel
     * deployment. If not specified, defaults to the empty string.
     */
    String getWebappVersion();

    /**
     * Set the version of this web application - used to differentiate
     * different versions of the same web application when using parallel
     * deployment.
     */
    void setWebappVersion(String webappVersion);

    /**
     * Determine whether or not requests listeners will be fired on forwards for
     * this Context.
     */
    boolean getFireRequestListenersOnForwards();

    /**
     * Configure whether or not requests listeners will be fired on forwards for
     * this Context.
     */
    void setFireRequestListenersOnForwards(boolean enable);

    /**
     * Determines if a user presents authentication credentials, will the
     * context will process them when the request is for a non-protected
     * resource.
     */
    boolean getPreemptiveAuthentication();

    /**
     * Configures if a user presents authentication credentials, whether the
     * context will process them when the request is for a non-protected
     * resource.
     */
    void setPreemptiveAuthentication(boolean enable);

    /**
     * Determines if the context is configured to include a response body as
     * part of a redirect response.
     */
    boolean getSendRedirectBody();

    /**
     * Configures if a response body is included when a redirect response is
     * sent to the client.
     */
    void setSendRedirectBody(boolean enable);

    /**
     * Return the Loader with which this Context is associated.
     */
    Loader getLoader();

    /**
     * Set the Loader with which this Context is associated.
     *
     * @param loader The newly associated loader
     */
    void setLoader(Loader loader);

    /**
     * Return the Resources with which this Context is associated.
     */
    WebResourceRoot getResources();

    /**
     * Set the Resources object with which this Context is associated.
     *
     * @param resources The newly associated Resources
     */
    void setResources(WebResourceRoot resources);

    /**
     * Return the Manager with which this Context is associated.  If there is
     * no associated Manager, return <code>null</code>.
     */
    Manager getManager();


    /**
     * Set the Manager with which this Context is associated.
     *
     * @param manager The newly associated Manager
     */
    void setManager(Manager manager);

    /**
     * Gets the flag that indicates if /WEB-INF/classes should be treated like
     * an exploded JAR and JAR resources made available as if they were in a
     * JAR.
     */
    boolean getAddWebinfClassesResources();

    /**
     * Sets the flag that indicates if /WEB-INF/classes should be treated like
     * an exploded JAR and JAR resources made available as if they were in a
     * JAR.
     *
     * @param addWebinfClassesResources The new value for the flag
     */
    void setAddWebinfClassesResources(boolean addWebinfClassesResources);

    /**
     * Add a post construct method definition for the given class, if there is
     * an existing definition for the specified class - IllegalArgumentException
     * will be thrown.
     *
     * @param clazz Fully qualified class name
     * @param method
     *            Post construct method name
     * @throws IllegalArgumentException
     *             if the fully qualified class name or method name are
     *             <code>NULL</code>; if there is already post construct method
     *             definition for the given class
     */
    void addPostConstructMethod(String clazz, String method);

    /**
     * Add a pre destroy method definition for the given class, if there is an
     * existing definition for the specified class - IllegalArgumentException
     * will be thrown.
     *
     * @param clazz Fully qualified class name
     * @param method
     *            Post construct method name
     * @throws IllegalArgumentException
     *             if the fully qualified class name or method name are
     *             <code>NULL</code>; if there is already pre destroy method
     *             definition for the given class
     */
    void addPreDestroyMethod(String clazz, String method);

    /**
     * Removes the post construct method definition for the given class, if it
     * exists; otherwise, no action is taken.
     *
     * @param clazz
     *            Fully qualified class name
     */
    void removePostConstructMethod(String clazz);

    /**
     * Removes the pre destroy method definition for the given class, if it
     * exists; otherwise, no action is taken.
     *
     * @param clazz
     *            Fully qualified class name
     */
    void removePreDestroyMethod(String clazz);

    /**
     * Returns the method name that is specified as post construct method for
     * the given class, if it exists; otherwise <code>NULL</code> will be
     * returned.
     *
     * @param clazz
     *            Fully qualified class name
     *
     * @return the method name that is specified as post construct method for
     *         the given class, if it exists; otherwise <code>NULL</code> will
     *         be returned.
     */
    String findPostConstructMethod(String clazz);

    /**
     * Returns the method name that is specified as pre destroy method for the
     * given class, if it exists; otherwise <code>NULL</code> will be returned.
     *
     * @param clazz
     *            Fully qualified class name
     *
     * @return the method name that is specified as pre destroy method for the
     *         given class, if it exists; otherwise <code>NULL</code> will be
     *         returned.
     */
    String findPreDestroyMethod(String clazz);

    /**
     * Returns a map with keys - fully qualified class names of the classes that
     * have post construct methods and the values are the corresponding method
     * names. If there are no such classes an empty map will be returned.
     *
     * @return a map with keys - fully qualified class names of the classes that
     *         have post construct methods and the values are the corresponding
     *         method names.
     */
    Map<String, String> findPostConstructMethods();

    /**
     * Returns a map with keys - fully qualified class names of the classes that
     * have pre destroy methods and the values are the corresponding method
     * names. If there are no such classes an empty map will be returned.
     *
     * @return a map with keys - fully qualified class names of the classes that
     *         have pre destroy methods and the values are the corresponding
     *         method names.
     */
    Map<String, String> findPreDestroyMethods();

    /**
     * Change the current thread context class loader to the web application
     * class loader. If no web application class loader is defined, or if the
     * current thread is already using the web application class loader then no
     * change will be made. If the class loader is changed and a
     * {@link ThreadBindingListener} is configured then
     * {@link ThreadBindingListener#bind()} will be called after the change has
     * been made.
     *
     * @param usePrivilegedAction
     *          Should a {@link java.security.PrivilegedAction} be used when
     *          obtaining the current thread context class loader and setting
     *          the new one?
     * @param originalClassLoader
     *          The current class loader if known to save this method having to
     *          look it up
     *
     * @return If the class loader has been changed by the method it will return
     *         the thread context class loader in use when the method was
     *         called. If no change was made then this method returns null.
     */
    ClassLoader bind(boolean usePrivilegedAction, ClassLoader originalClassLoader);

    /**
     * Restore the current thread context class loader to the original class
     * loader in used before {@link #bind(boolean, ClassLoader)} was called. If
     * no original class loader is passed to this method then no change will be
     * made. If the class loader is changed and a {@link ThreadBindingListener}
     * is configured then {@link ThreadBindingListener#unbind()} will be called
     * before the change is made.
     *
     * @param usePrivilegedAction
     *          Should a {@link java.security.PrivilegedAction} be used when
     *          setting the current thread context class loader?
     * @param originalClassLoader
     *          The class loader to restore as the thread context class loader
     */
    void unbind(boolean usePrivilegedAction, ClassLoader originalClassLoader);

    /**
     * Obtain the token necessary for operations on the associated JNDI naming
     * context.
     */
    Object getNamingToken();

    /**
     * Obtains the {@link CookieProcessor} that will be used to process cookies
     * for this Context.
     */
    CookieProcessor getCookieProcessor();

    /**
     * Sets the {@link CookieProcessor} that will be used to process cookies
     * for this Context.
     *
     * @param cookieProcessor   The new cookie processor
     */
    void setCookieProcessor(CookieProcessor cookieProcessor);
}
