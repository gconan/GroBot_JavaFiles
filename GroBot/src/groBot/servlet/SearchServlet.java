package groBot.servlet;

import groBot.entity.Item;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.googlecode.objectify.cmd.Query;

import static groBot.services.OfyService.ofy;

/**
 * Call this servlet when the user clicks "Search"
 * This populates an arrayList with all items from the DB that contains
 * the keywords matching the user's search string
 * @author conangammel, ken and namaz re-did the search algorithm
 */
public class SearchServlet extends SecureServlet{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * populates arrayList and redirects to JSP
	 * @author conangammel
	 * @throws IOException
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		super.doPost(req, resp);
		if(email==null)return;
		
		//load all items from DB and create a result arrayList
		String search = req.getParameter("search").toLowerCase();
		Query<Item> allItems = ofy().load().type(Item.class);
		ArrayList<Item> searchResult = new ArrayList<Item>();
		
		//populate arrayList with items matching "search" keywords with an Item's keywords
		for(Item item: allItems){
			for(String keyword: item.getKeywords()){
				if(search.contains(keyword)){
					searchResult.add(item);
					break;
				}
				if(containsCloseNumbers(search, String.valueOf(item.getPrice()))){
					searchResult.add(item);
					break;
				}else{
					//keep going
				}
			}
		}
		//"sending" arrayList to jsp
		HttpSession ses = req.getSession(true);
		ses.setAttribute("searchResults", searchResult);
		String encodedURL = resp.encodeRedirectURL("searchResults.jsp");	//perhpas remove if we combine html and jsp
        resp.sendRedirect(encodedURL);
	}

	/**
	 * Checks for similarity of numbers.Trims cents because they are irrelevant in searching.
	 * If the search number is the same length (same factor of ten)
	 * then it checks that the first numbers are equal. Therefore 40 & 45 would return true
	 * Meanwhile 40 & 400 would return false
	 * 
	 * @author conangammel
	 * @param search - multi-word string [1,max]words
	 * @param keyword - keyword given by Item
	 * @return Boolean - true if numbers are within most significant digit
	 */
	private boolean containsCloseNumbers(String search, String keyword) {
		
		String[] words = search.split(" ");
		String searchNumber;
		for(String s: words){
			if(s.matches("[0-9]")){
				searchNumber=s;
				/*if(searchNumber.contains(".")){
					searchNumber=searchNumber.substring(0,searchNumber.indexOf("."));
				}
				
				if(keyword.length()==searchNumber.length()){
					if(keyword.charAt(0) == searchNumber.charAt(0)){
						if(keyword.length()<4){
							return true;
						}else if(keyword.charAt(1) == searchNumber.charAt(1)){
							return true;
						}else{
							return false;
						}
					}
				}
				break;*/
				
				int searchint = (int)Double.parseDouble(searchNumber);
				int keywordint = (int)Double.parseDouble(keyword);
				int searchmin = min(searchint);
				int searchmax = max(searchint);
				
				if(searchmin <= keywordint && keywordint <= searchmax){
					return true;
				}
				else{
					return false;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * @author Ken
	 * @param int num
	 * @return int
	 */
	private int min(int num){
//		int length = 0, number = num, firstDigit = 0;
//		for (; number > 0; number /=10) {
//			firstDigit = number;
//			length += 1;
//		}
//		System.out.println(length);
//		System.out.println(firstDigit);
//		int fullDigit = firstDigit * (int)Math.pow(10, length-1) + (int)Math.pow(10, length-1) - 1; 
		int length = String.valueOf(num).length();
		int divisor;
		String result;
		if(length==1){
			return 0;
		}
		else{
			divisor = (int)Math.pow(10, length-1);
			result = String.valueOf(num/divisor); //most significant digit
			for(int i = 1; i<length; i++){
				result = result + "0";
			}
			return Integer.parseInt(result);
		}
	}
	
	/**
	 * @author Ken
	 * @param int num
	 * @return int
	 */
	private int max(int num){
		int length = String.valueOf(num).length();
		int divisor;
		String result;
		if(length==1){
			return 9;
		}
		else{
			divisor = (int)Math.pow(10, length-1);
			result = String.valueOf(num/divisor); //most significant digit
			for(int i = 1; i<length; i++){
				result = result + "9";
			}
			return Integer.parseInt(result);
		}
	}
}
