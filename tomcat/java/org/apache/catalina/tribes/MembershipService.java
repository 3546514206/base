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

package org.apache.catalina.tribes;


/**
 * MembershipService Interface<br>
 * The <code>MembershipService</code> interface is the membership component
 * at the bottom layer, the IO layer (for layers see the javadoc for the {@link Channel} interface).<br>
 */
public interface MembershipService {

    int MBR_RX = Channel.MBR_RX_SEQ;
    int MBR_TX = Channel.MBR_TX_SEQ;

    /**
     * Returns the properties for the configuration used.
     */
    java.util.Properties getProperties();

    /**
     * Sets the properties for the membership service. This must be called before
     * the <code>start()</code> method is called.
     * The properties are implementation specific.
     * @param properties - to be used to configure the membership service.
     */
    void setProperties(java.util.Properties properties);

    /**
     * Starts the membership service. If a membership listeners is added
     * the listener will start to receive membership events.
     * Performs a start level 1 and 2
     * @throws java.lang.Exception if the service fails to start.
     */
    void start() throws java.lang.Exception;

    /**
     * Starts the membership service. If a membership listeners is added
     * the listener will start to receive membership events.
     * @param level - level MBR_RX starts listening for members, level MBR_TX
     * starts broad casting the server
     * @throws java.lang.Exception if the service fails to start.
     * @throws java.lang.IllegalArgumentException if the level is incorrect.
     */
    void start(int level) throws java.lang.Exception;


    /**
     * Starts the membership service. If a membership listeners is added
     * the listener will start to receive membership events.
     * @param level - level MBR_RX stops listening for members, level MBR_TX
     * stops broad casting the server
     * @throws java.lang.IllegalArgumentException if the level is incorrect.
     */

    void stop(int level);

    /**
     * @return true if the the group contains members
     */
    boolean hasMembers();


    /**
     *
     * @param mbr Member
     * @return Member
     */
    Member getMember(Member mbr);
    /**
     * Returns a list of all the members in the cluster.
     */

    Member[] getMembers();

    /**
     * Returns the member object that defines this member
     */
    Member getLocalMember(boolean incAliveTime);

    /**
     * Return all members by name
     */
    String[] getMembersByName() ;

    /**
     * Return the member by name
     */
    Member findMemberByName(String name) ;

    /**
     * Sets the local member properties for broadcasting
     */
    void setLocalMemberProperties(String listenHost, int listenPort, int securePort, int udpPort);

    /**
     * Sets the membership listener, only one listener can be added.
     * If you call this method twice, the last listener will be used.
     * @param listener The listener
     */
    void setMembershipListener(MembershipListener listener);

    /**
     * removes the membership listener.
     */
    void removeMembershipListener();

    /**
     * Set a payload to be broadcasted with each membership
     * broadcast.
     * @param payload byte[]
     */
    void setPayload(byte[] payload);

    void setDomain(byte[] domain);

    /**
     * Broadcasts a message to all members
     * @param message
     * @throws ChannelException
     */
    void broadcast(ChannelMessage message) throws ChannelException;

}
