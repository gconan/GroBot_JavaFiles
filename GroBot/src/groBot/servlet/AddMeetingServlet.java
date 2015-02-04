package groBot.servlet;

import groBot.dao.UserDAO;
import groBot.entity.Meeting;

import java.io.IOException;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * Add meeting to the database
 * @author conangammel
 *
 */
public class AddMeetingServlet extends SecureServlet {
	
	private static final long serialVersionUID = 1L;
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	private ImagesService imageSerice = ImagesServiceFactory.getImagesService();
	private final File UPLOAD_DIRECTORY = new File("C:/uploads");

	/**
	 * @author conangammel
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		super.doPost(req, resp);
		if(email == null) return;
		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
		List<BlobKey> blobKeys = blobs.get("myFile");
		String key = imageSerice.getServingUrl(ServingUrlOptions.Builder.withBlobKey(blobKeys.get(0)));		
		Meeting meet = new Meeting((String)req.getParameter("name"), 
				(String)req.getParameter("location"), 
				(String)req.getParameter("description"),
				email, 
				(String)req.getParameter("day"), 
				(String)req.getParameter("month"), 
				(String)req.getParameter("hour"), 
				(String)req.getParameter("minute"), 
				(String)req.getParameter("year"), 
				(String)req.getParameter("amorpm"),
				(String)req.getParameter("hourend"),
				(String)req.getParameter("minuteend"),
				(String)req.getParameter("amorpmend"),
				key);

		if(((String)req.getParameter("name")).equals("") || ((String)req.getParameter("day")).equals("") 
			|| ((String)req.getParameter("location")).equals("") 
			|| ((String)req.getParameter("day")).equals("")
			|| ((String)req.getParameter("month")).equals("")
			|| ((String)req.getParameter("hour")).equals("")
			|| ((String)req.getParameter("minute")).equals("")
			|| ((String)req.getParameter("year")).equals("")
			|| ((String)req.getParameter("amorpm")).equals("")
			|| ((String)req.getParameter("description")).equals("")){
			String encodedURL = resp.encodeRedirectURL("/error.html");
	        resp.sendRedirect(encodedURL);
	        return;
		}
		
		System.out.println(req.getParameter("description"));
		UserDAO.INSTANCE.addMeetingToDB(meet);
		
		String encodedURL = resp.encodeRedirectURL("/activities.jsp");
        resp.sendRedirect(encodedURL);
	}
}
