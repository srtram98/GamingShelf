package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Game;

/**
 * @author Spencer Tramontina - srtramontina
 * CIS175 - Spring 2024
 * Jan 31, 2024
 */
public class GameHelper {
static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("GamingShelf");
	
	public void insertItem(Game g) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(g);
		em.getTransaction().commit();
		em.close();
	}
	
	@SuppressWarnings("unchecked")	
	public List<Game> showAllItems(){
		EntityManager em = emfactory.createEntityManager();
		List<Game> allItems = em.createQuery("SELECT i FROM Game i").getResultList();
		return allItems;
	}
	
	public void deleteItem(Game toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Game> typedQuery = em.createQuery("SELECT g FROM Game g WHERE g.name = :selectedName AND g.platform = :selectedPlatform", Game.class);
	
		typedQuery.setParameter("selectedName", toDelete.getName());
		typedQuery.setParameter("selectedPlatform", toDelete.getPlatform());
		
		typedQuery.setMaxResults(1);
		
		Game result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public Game searchForItemById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Game found = em.find(Game.class, idToEdit);
		em.close();
		return found;
	}

	public void updateItem(Game toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
		
	}

	public List<Game> searchForGameByName(String gameName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Game> typedQuery = em.createQuery("SELECT g FROM Game g WHERE g.name = :selectedName", Game.class);
		typedQuery.setParameter("selectedName", gameName);
		List<Game> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	public List<Game> searchForGameByGenre(String gameGenre) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Game> typedQuery = em.createQuery("SELECT g FROM Game g WHERE g.genre = :selectedGenre", Game.class);
		typedQuery.setParameter("selectedGenre", gameGenre);
		
		List<Game> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public List<Game> searchForGameByPlatform(String gamePlatform) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Game> typedQuery = em.createQuery("SELECT g FROM Game g WHERE g.platform = :selectedPlatform", Game.class);
		typedQuery.setParameter("selectedPlatform", gamePlatform);
		
		List<Game> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public void cleanUp() {
		emfactory.close();
	}
	
}
