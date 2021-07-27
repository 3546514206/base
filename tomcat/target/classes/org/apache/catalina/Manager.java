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

import java.beans.PropertyChangeListener;
import java.io.IOException;

/**
 * A <b>Manager</b> manages the pool of Sessions that are associated with a
 * particular Container.  Different Manager implementations may support
 * value-added features such as the persistent storage of session data,
 * as well as migrating sessions for distributable web applications.
 * <p>
 * In order for a <code>Manager</code> implementation to successfully operate
 * with a <code>Context</code> implementation that implements reloading, it
 * must obey the following constraints:
 * <ul>
 * <li>Must implement <code>Lifecycle</code> so that the Context can indicate
 *     that a restart is required.
 * <li>Must allow a call to <code>stop()</code> to be followed by a call to
 *     <code>start()</code> on the same <code>Manager</code> instance.
 * </ul>
 *
 * @author Craig R. McClanahan
 */
public interface Manager {

    // ------------------------------------------------------------- Properties

    /**
     * Get the Context with which this Manager is associated.
     *
     * @return The associated Context
     */
    Context getContext();


    /**
     * Set the Container with which this Manager is associated.
     *
     * @param context The newly associated Context
     */
    void setContext(Context context);


    /**
     * Is this Manager marked as using distributable sessions?
     *
     * @return {@code true} if this manager is marked as distributable otherwise
     *         {@code false}
     */
    boolean getDistributable();


    /**
     * Configure whether this manager uses distributable sessions. If this flag
     * is set, all user data objects added to sessions associated with this
     * manager must implement Serializable.
     *
     * @param distributable The new distributable flag
     */
    void setDistributable(boolean distributable);


    /**
     * Get the default time in seconds before a session managed by this manager
     * will be considered inactive.
     *
     * @return The default maximum inactive interval in seconds
     */
    int getMaxInactiveInterval();


    /**
     * Set the default maximum inactive interval (in seconds)
     * for Sessions created by this Manager.
     *
     * @param interval The new default value
     */
    void setMaxInactiveInterval(int interval);


    /**
     * @return the session id generator
     */
    SessionIdGenerator getSessionIdGenerator();


    /**
     * Sets the session id generator
     *
     * @param sessionIdGenerator The session id generator
     */
    void setSessionIdGenerator(SessionIdGenerator sessionIdGenerator);


    /**
     * Returns the total number of sessions created by this manager.
     *
     * @return Total number of sessions created by this manager.
     */
    long getSessionCounter();


    /**
     * Sets the total number of sessions created by this manager.
     *
     * @param sessionCounter Total number of sessions created by this manager.
     */
    void setSessionCounter(long sessionCounter);


    /**
     * Gets the maximum number of sessions that have been active at the same
     * time.
     *
     * @return Maximum number of sessions that have been active at the same
     * time
     */
    int getMaxActive();


    /**
     * (Re)sets the maximum number of sessions that have been active at the
     * same time.
     *
     * @param maxActive Maximum number of sessions that have been active at
     * the same time.
     */
    void setMaxActive(int maxActive);


    /**
     * Gets the number of currently active sessions.
     *
     * @return Number of currently active sessions
     */
    int getActiveSessions();


    /**
     * Gets the number of sessions that have expired.
     *
     * @return Number of sessions that have expired
     */
    long getExpiredSessions();


    /**
     * Sets the number of sessions that have expired.
     *
     * @param expiredSessions Number of sessions that have expired
     */
    void setExpiredSessions(long expiredSessions);


    /**
     * Gets the number of sessions that were not created because the maximum
     * number of active sessions was reached.
     *
     * @return Number of rejected sessions
     */
    int getRejectedSessions();


    /**
     * Gets the longest time (in seconds) that an expired session had been
     * alive.
     *
     * @return Longest time (in seconds) that an expired session had been
     * alive.
     */
    int getSessionMaxAliveTime();


    /**
     * Sets the longest time (in seconds) that an expired session had been
     * alive.
     *
     * @param sessionMaxAliveTime Longest time (in seconds) that an expired
     * session had been alive.
     */
    void setSessionMaxAliveTime(int sessionMaxAliveTime);


    /**
     * Gets the average time (in seconds) that expired sessions had been
     * alive. This may be based on sample data.
     *
     * @return Average time (in seconds) that expired sessions had been
     * alive.
     */
    int getSessionAverageAliveTime();


    /**
     * Gets the current rate of session creation (in session per minute). This
     * may be based on sample data.
     *
     * @return  The current rate (in sessions per minute) of session creation
     */
    int getSessionCreateRate();


    /**
     * Gets the current rate of session expiration (in session per minute). This
     * may be based on sample data
     *
     * @return  The current rate (in sessions per minute) of session expiration
     */
    int getSessionExpireRate();


    // --------------------------------------------------------- Public Methods

    /**
     * Add this Session to the set of active Sessions for this Manager.
     *
     * @param session Session to be added
     */
    void add(Session session);


    /**
     * Add a property change listener to this component.
     *
     * @param listener The listener to add
     */
    void addPropertyChangeListener(PropertyChangeListener listener);


    /**
     * Change the session ID of the current session to a new randomly generated
     * session ID.
     *
     * @param session   The session to change the session ID for
     */
    void changeSessionId(Session session);


    /**
     * Change the session ID of the current session to a specified session ID.
     *
     * @param session   The session to change the session ID for
     * @param newId   new session ID
     */
    void changeSessionId(Session session, String newId);


    /**
     * Get a session from the recycled ones or create a new empty one.
     * The PersistentManager manager does not need to create session data
     * because it reads it from the Store.
     *
     * @return An empty Session object
     */
    Session createEmptySession();


    /**
     * Construct and return a new session object, based on the default
     * settings specified by this Manager's properties.  The session
     * id specified will be used as the session id.
     * If a new session cannot be created for any reason, return
     * <code>null</code>.
     *
     * @param sessionId The session id which should be used to create the
     *  new session; if <code>null</code>, the session
     *  id will be assigned by this method, and available via the getId()
     *  method of the returned session.
     * @exception IllegalStateException if a new session cannot be
     *  instantiated for any reason
     *
     * @return An empty Session object with the given ID or a newly created
     *         session ID if none was specified
     */
    Session createSession(String sessionId);


    /**
     * Return the active Session, associated with this Manager, with the
     * specified session id (if any); otherwise return <code>null</code>.
     *
     * @param id The session id for the session to be returned
     *
     * @exception IllegalStateException if a new session cannot be
     *  instantiated for any reason
     * @exception IOException if an input/output error occurs while
     *  processing this request
     *
     * @return the request session or {@code null} if a session with the
     *         requested ID could not be found
     */
    Session findSession(String id) throws IOException;


    /**
     * Return the set of active Sessions associated with this Manager.
     * If this Manager has no active Sessions, a zero-length array is returned.
     *
     * @return All the currently active sessions managed by this manager
     */
    Session[] findSessions();


    /**
     * Load any currently active sessions that were previously unloaded
     * to the appropriate persistence mechanism, if any.  If persistence is not
     * supported, this method returns without doing anything.
     *
     * @exception ClassNotFoundException if a serialized class cannot be
     *  found during the reload
     * @exception IOException if an input/output error occurs
     */
    void load() throws ClassNotFoundException, IOException;


    /**
     * Remove this Session from the active Sessions for this Manager.
     *
     * @param session Session to be removed
     */
    void remove(Session session);


    /**
     * Remove this Session from the active Sessions for this Manager.
     *
     * @param session   Session to be removed
     * @param update    Should the expiration statistics be updated
     */
    void remove(Session session, boolean update);


    /**
     * Remove a property change listener from this component.
     *
     * @param listener The listener to remove
     */
    void removePropertyChangeListener(PropertyChangeListener listener);


    /**
     * Save any currently active sessions in the appropriate persistence
     * mechanism, if any.  If persistence is not supported, this method
     * returns without doing anything.
     *
     * @exception IOException if an input/output error occurs
     */
    void unload() throws IOException;


    /**
     * This method will be invoked by the context/container on a periodic
     * basis and allows the manager to implement
     * a method that executes periodic tasks, such as expiring sessions etc.
     */
    void backgroundProcess();
}
