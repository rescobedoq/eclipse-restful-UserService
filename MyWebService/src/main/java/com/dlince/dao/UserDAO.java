package com.dlince.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.dlince.model.User;

public class UserDAO {
	
	static EntityManagerFactory factory;
	static EntityManager entityManager;
	
	public static List<User> getUsers(){
		begin();
		List<User> list = getUsersQuery();
		end();		
		return list;
	}
	
	public static User getUser(Integer id){
		begin();
		User user = getUserQuery(id);
		end();		
		return user;
	}
	
	public static void deleteUser(Integer id){	
		begin();
		deleteUserQuery(id);
		end();
	}
	
	public static void addUser(User user){	
		begin();
		addUserQuery(user);
		end();
	}
	
	private static List<User> getUsersQuery() {
		String jpql = "Select b From User b";
		Query query = entityManager.createQuery(jpql);
		@SuppressWarnings("unchecked")
		List<User> listUsers = query.getResultList();
		for(User aUser:listUsers) {
			System.out.println(aUser.getEmail());
		}
		return listUsers;
	}
	
	private static User getUserQuery(Integer id) {
		String jpql = "Select b From User b Where b.id = "+String.valueOf(id);
		Query query = entityManager.createQuery(jpql);
		User user = (User) query.getSingleResult();
		System.out.println(user.getEmail());
		return user;
	}
	
	private static void deleteUserQuery(Integer id) {
		String jpql = "Delete From User b Where b.id = "+String.valueOf(id);
		Query query = entityManager.createQuery(jpql);
		query.executeUpdate();
	}
	
	private static void addUserQuery(User user) {
		entityManager.persist(user);
	}
	
	private static void begin() {
		factory = Persistence.createEntityManagerFactory("UserUnit");
		entityManager = factory.createEntityManager();		
		entityManager.getTransaction().begin();
	}
	
	private static void end() {
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}

}
