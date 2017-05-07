package com.app;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

 

@Path("/UserService")
public class UserService {

	static ConnectionPool conp= new ConnectionPool();
	static DB db=new DB();
	
	
   @GET
   @Path("/users")
   @Produces("application/json")
   public List<UserInfo> getUsers(@QueryParam("search") String search ){ 
	   int startrow = 1; 
	   int endrow = 10;
	   System.out.println(search);
      return db.getData(search  ,startrow ,endrow);
   }	
   @GET
   @Path("/count")
   @Produces(MediaType.TEXT_PLAIN)
   public String count(@QueryParam("search") String search){ 
	   System.out.println(search);
      return db.getDataCount(search);
   }
   @POST
   @Path("/register")
   @Produces(MediaType.TEXT_PLAIN) 
   public Response usersregister(@FormParam("name") String name,
		   @FormParam("email") String email,
		   @FormParam("type") String type,
		   @FormParam("topic") String topic){
	   
	   db.insertData(name, email, type, topic);

	   return Response.status(200)
				.entity("addUser")
				.build();
   }
   @GET
   @Path("/add")
   @Produces(MediaType.TEXT_PLAIN)
   public String add(@QueryParam("path") String path) throws Exception{ 
	   System.out.println(path);
	   if(Utility.isValidURL(path))   
        return db.addPath(path);
	   else return "Invalid Website name.  Example http://google.com";
   } 
}