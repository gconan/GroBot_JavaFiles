package groBot.servlet;

import groBot.dao.UserDAO;
import groBot.entity.Item;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;

/**
 * Servlet called when adding a new item. Stores the item in the searchAPI Index and the
 * Objectify datastore
 * @author conangammel
 *
 */
public class AddItemServlet extends SecureServlet {
	
	private static final long serialVersionUID = 1L;
	BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	ImagesService imageSerice = ImagesServiceFactory.getImagesService();
	//private final File UPLOAD_DIRECTORY = new File("C:/uploads");
		
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		super.doPost(req, resp);
		if(email == null) return;
			Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
			List<BlobKey> blobKeys = blobs.get("myFile");
			String key;
			//could be useful
//			BlobstoreService bs = BlobstoreServiceFactory.getBlobstoreService();
//			BlobKey blobKey = bs.getUploads(req).get("blob").get(0);
//			final BlobInfo blobInfo = new BlobInfoFactory().loadBlobInfo(blobKey);
//			long size = blobInfo.getSize();
//			if(size > 0){
//			  //process blob
//				key = null;
//			}else{
//			  bs.delete(blobKey);
//			}
			key = imageSerice.getServingUrl(ServingUrlOptions.Builder.withBlobKey(blobKeys.get(0)));
			String name = (String) req.getParameter("name");
			String price = (String) req.getParameter("price");
			String description = (String) req.getParameter("description");
			Item item = new Item (name, price, description, email, key);
			if(name.equals("") || description.equals("")){
				System.out.println("no name");
				String encodedURL = resp.encodeRedirectURL("error.html");
		        resp.sendRedirect(encodedURL);
		        return;
			}
			UserDAO.INSTANCE.addItemToDB(item);

			String encodedURL = resp.encodeRedirectURL("/buy.jsp");
	        resp.sendRedirect(encodedURL);
	}
}
