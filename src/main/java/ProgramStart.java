import java.util.List;
import java.util.Scanner;

import controller.GameHelper;
import model.Game;

/**
 * @author Spencer Tramontina - srtramontina
 * CIS175 - Spring 2024
 * Feb 1, 2024
 */
public class ProgramStart {
	
	static Scanner in = new Scanner(System.in);
	static GameHelper gh = new GameHelper();

	private static void addAnItem() {
		// TODO Auto-generated method stub
		System.out.print("Enter the game's Name: ");
		String name = in.nextLine();
		System.out.print("Enter it's Genre: ");
		String genre = in.nextLine();
		System.out.print("Enter the Platform: ");
		String platform = in.nextLine();
		Game toAdd = new Game(name, genre, platform);
		gh.insertItem(toAdd);
	}
	private static void deleteAnItem() {
		// TODO Auto-generated method stub
		System.out.print("Enter the game's Name: ");
		String name = in.nextLine();
		System.out.print("Enter it's Genre: ");
		String genre = in.nextLine();
		System.out.print("Enter the Platform: ");
		String platform = in.nextLine();
		Game toDelete = new Game(name, genre, platform);
		gh.deleteItem(toDelete);
		}
	private static void editAnItem() {
		// TODO Auto-generated method stub
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by Name");
		System.out.println("2 : Search by Genre");
		System.out.println("3 : Search by Platform");
		int searchBy = in.nextInt();
		in.nextLine();
		List<Game> foundItems;
		if (searchBy == 1) {
			System.out.print("Enter the game's name: ");
			String gameName = in.nextLine();
			foundItems = gh.searchForGameByName(gameName);
		} 
		else if (searchBy == 2){
			System.out.print("Enter the genre: ");
			String gameGenre = in.nextLine();
			foundItems = gh.searchForGameByGenre(gameGenre);
			}
		else {
			System.out.print("Enter the platform: ");
			String gamePlatform = in.nextLine();
			foundItems = gh.searchForGameByPlatform(gamePlatform);
		}
		if (!foundItems.isEmpty()) {
			System.out.println("Found Results.");
			for (Game l : foundItems) {
				System.out.println(l.getId() + " : " + l.toString());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();
			
			Game toEdit = gh.searchForItemById(idToEdit);
			System.out.println("Retrieved: " + toEdit.getName() + ", Genre: " + toEdit.getGenre() + ", Platform: " + toEdit.getPlatform());
			System.out.println("1 : Update Name");
			System.out.println("2 : Update Genre");
			System.out.println("3 : Update Platform");
			int update = in.nextInt();
			in.nextLine();
			
			if (update == 1) {
				System.out.print("New Name: ");
				String newName = in.nextLine();
				toEdit.setName(newName);
			} 
			else if (update == 2) {
				System.out.print("New Genre: ");
				String newGenre = in.nextLine();
				toEdit.setGenre(newGenre);
			}
			else if (update == 3) {
				System.out.print("New Platform: ");
				String newPlatform = in.nextLine();
				toEdit.setPlatform(newPlatform);
			}
			gh.updateItem(toEdit);
		} 
		else {
			System.out.println("---- No results found");
		}
		
	}
	public static void main(String[] args) {
		runMenu();
	}
	
	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("--- Welcome to your Gaming Shelf! What would you like to do? ---");
		
		while (goAgain) {
			System.out.println("* Select an item:");
			System.out.println("* 1 -- Add A game");
			System.out.println("* 2 -- Edit A Game");
			System.out.println("* 3 -- Delete A Game");
			System.out.println("* 4 -- View Game Shelf");
			System.out.println("* 5 -- Exit program");
			System.out.print("* Your selection: ");
			int selection = in.nextInt();
			in.nextLine();
			
			if (selection == 1) {
				addAnItem();
			} 
			else if (selection == 2) {
				editAnItem();
			} 
			else if (selection == 3) {
				deleteAnItem();
			}
			else if (selection == 4) {
				viewTheList();
			} else {
				gh.cleanUp();
				System.out.println(" See you Again! ");
				goAgain = false;
			}
		}
	}
	private static void viewTheList() {
		List<Game> allItems = gh.showAllItems();
		
		for(Game singleItem : allItems) {
			System.out.println(singleItem.returnItemDetails());
		}
		}

}
