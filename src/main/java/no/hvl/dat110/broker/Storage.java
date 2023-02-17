package no.hvl.dat110.broker;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import no.hvl.dat110.common.TODO;
import no.hvl.dat110.common.Logger;
import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messagetransport.Connection;

public class Storage {

	// data structure for managing subscriptions
	// maps from a topic to set of subscribed users
	protected ConcurrentHashMap<String, Set<String>> subscriptions;
	
	// data structure for managing currently connected clients
	// maps from user to corresponding client session object
	
	protected ConcurrentHashMap<String, ClientSession> clients;
	protected ConcurrentHashMap<String, ArrayList<Message>> buffer;
	public void addToBuffer(String user, Message msg) {
		buffer.get(user).add(msg);
	}
	public ArrayList<Message> getFromBuffer(String user) {
		return buffer.get(user);
	}
	public void cleanBuffer(String user) {
		buffer.remove(user);
	}
	public Storage() {
		subscriptions = new ConcurrentHashMap<String, Set<String>>();
		clients = new ConcurrentHashMap<String, ClientSession>();
		buffer = new ConcurrentHashMap<String, ArrayList<Message>>();
	}

	public Collection<ClientSession> getSessions() {
		return clients.values();
	}

	public Set<String> getTopics() {

		return subscriptions.keySet();

	}

	// get the session object for a given user
	// session object can be used to send a message to the user
	
	public ClientSession getSession(String user) {

		ClientSession session = clients.get(user);

		return session;
	}

	public Set<String> getSubscribers(String topic) {

		return (subscriptions.get(topic));

	}

	public void addClientSession(String user, Connection connection) {
		ClientSession session = new ClientSession(user, connection);
		clients.put(user, session);
	}

	public void removeClientSession(String user) {
		clients.remove(user);
		if (buffer.get(user) == null) {
			ArrayList<Message> messages = new ArrayList<>();
			buffer.put(user, messages);
		}
	}

	public void createTopic(String topic) {
		subscriptions.put(topic, ConcurrentHashMap.newKeySet());
	}

	public void deleteTopic(String topic) {
		subscriptions.remove(topic);
	}

	public void addSubscriber(String user, String topic) {
		Set subs = subscriptions.get(topic);
		if (subs != null) {
			subs.add(user);
		}
	}

	public void removeSubscriber(String user, String topic) {
		Set subs = subscriptions.get(topic);
		subs.remove(user);
	}
}
