package groBot.servlet;

import groBot.dao.UserDAO;
import groBot.entity.User;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
/**
 * 
 * @author conangammel
 *
 */
public class AddProfilePictureServlet extends SecureServlet{
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	private ImagesService imageSerice = ImagesServiceFactory.getImagesService();
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)throws IOException{
		super.doPost(req, resp);
		if(email == null) {
			resp.sendRedirect("index.html");
		}
		
		User user = UserDAO.INSTANCE.getUserByEmail(email);
		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
		List<BlobKey> blobKeys = blobs.get("myFile");
		String key = imageSerice.getServingUrl(ServingUrlOptions.Builder.withBlobKey(blobKeys.get(0)));
		
		user.addImageKey(key);
		UserDAO.INSTANCE.addUser(user);
		
		String encodedURL = resp.encodeRedirectURL("/profile.jsp");
        resp.sendRedirect(encodedURL);
	}
}
