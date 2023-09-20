package data.connector;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pojo.Login;

import data.login.FriendRequest;
import data.login.Login1;



public class Database {

	
	public static Login1 login(String username)
	{  Login1 loginBean=new Login1();
		Connection con = Dbase.getConnection();
		 try {
			PreparedStatement ps=con.prepareStatement("select * from login where username=?");
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();  
            if(rs.next()){  
            	loginBean.setId(rs.getInt("id"));
            	loginBean.setUsername(rs.getString("username"));
            	loginBean.setPass(rs.getString("pass"));
            	loginBean.setFname(rs.getString("fname"));
            	loginBean.setSname(rs.getNString("sname"));
            	loginBean.setMob(rs.getString("mob"));
            	loginBean.setEmail(rs.getString("email"));
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
         
		return loginBean;
		
	}
	
	public static int save(Login login)
	{
		int status=0;
		Connection con = Dbase.getConnection();
		try {
			PreparedStatement ps=con.prepareStatement(  
			        "insert into login(fname,sname,email,mob,username,pass) values (?,?,?,?,?,?)");
			ps.setString(1, login.getFname());
			ps.setString(2, login.getSname());
			ps.setString(3, login.getEmail());
			ps.setString(4, login.getMob());
			ps.setString(5, login.getUsername());
			ps.setString(6, login.getPass());
			
			status=ps.executeUpdate();  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return status;
		
	}
	
	public static int saveRequest(int sender_id,int receiver_id,String status)
	{
		int sts=0;
		Connection con = Dbase.getConnection();
		try {
			PreparedStatement ps=con.prepareStatement(  
			        "insert into requestmapping(sender_id,receiver_id,status) values (?,?,?)");
			ps.setInt(1, sender_id);
			ps.setInt(2, receiver_id);
			ps.setString(3, status);
			
			sts=ps.executeUpdate();  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return sts;
		
	}
	public static Login1 getById(int id){  
		Login1 user=new Login1();  
	          
	        try{  
	            Connection con=Dbase.getConnection();  
	            PreparedStatement ps=con.prepareStatement("select * from login where id=?");  
	            ps.setInt(1,id);  
	            ResultSet rs=ps.executeQuery();  
	            if(rs.next()){  
	            	user.setId(rs.getInt("id"));
	            	user.setFname(rs.getString("fname"));
	            	user.setSname(rs.getString("sname"));
	            	user.setEmail(rs.getString("email"));
	            	user.setMob(rs.getString("mob"));
	                
	            }  
	            con.close();  
	        }catch(Exception ex){ex.printStackTrace();}  
	          
	        return user;  
	    } 
	public static ArrayList<FriendRequest> getRequestsList(int id) throws SQLException
	{
		ArrayList<FriendRequest> list=new ArrayList<FriendRequest>();
		Connection con=Dbase.getConnection();  
        PreparedStatement ps=con.prepareStatement("select * from requestmapping where receiver_id=? or sender_id=?");  
        ps.setInt(1,id);  
        ps.setInt(2, id);
        ResultSet rs=ps.executeQuery();  
        if(rs.next()){ 
        	FriendRequest  friendRequest= new FriendRequest();
        	friendRequest.setSender_id(rs.getInt("sender_id"));
        	friendRequest.setReceiver_id(rs.getInt("receiver_id"));
        	friendRequest.setStatus(rs.getString("status"));
            list.add(friendRequest);
        }
		return list;  
	}
	public static FriendRequest getRequest(int sender_id,int receiver_id) throws SQLException
	{
		FriendRequest  friendRequest= new FriendRequest();
		Connection con=Dbase.getConnection();  
        PreparedStatement ps=con.prepareStatement("select * from requestmapping where sender_id=? and receiver_id=?");  
        ps.setInt(1,sender_id);  
        ps.setInt(2,receiver_id);  
        ResultSet rs=ps.executeQuery();  
        if(rs.next()){ 
        	
        	friendRequest.setSender_id(rs.getInt("sender_id"));
        	friendRequest.setReceiver_id(rs.getInt("receiver_id"));
        	friendRequest.setStatus(rs.getString("status"));
            
        }
		return friendRequest;  
	}
	
	 public static ArrayList<Login1> getUsers()
	 {
		 List<Login1> usersList=new ArrayList<>();
		 Connection con=Dbase.getConnection(); 
		 Login1 user=null;
		 try {
			PreparedStatement ps=con.prepareStatement("select * from login");
			ResultSet rs=ps.executeQuery();  
			while(rs.next()){  
				user=new Login1();
				user.setId(rs.getInt("id"));
				user.setFname(rs.getString("fname"));
				user.setSname(rs.getString("sname"));
				user.setEmail(rs.getString("email"));
				user.setMob(rs.getString("mob"));
				usersList.add(user);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		 return (ArrayList<Login1>) usersList;
	 }
	 
	 public static int updateStatus(int sender_id,int receiver_id,String sts){  
	        int status=0;  
	        try{  
	            Connection con=Dbase.getConnection();  
	            PreparedStatement ps=con.prepareStatement(  
	                         "update requestmapping set status=? where sender_id=? and receiver_id=?");  
	            ps.setString(1, sts);
	            ps.setInt(2, sender_id);
	            ps.setInt(3, receiver_id);
	              
	            status=ps.executeUpdate();  
	              
	            con.close();  
	        }catch(Exception ex){ex.printStackTrace();}  
	          
	        return status;  
	    } 
	 
	 
	 public static ArrayList<Login1> requestList(String username)
	 {
		 List<Login1> usersList=new ArrayList<>();
		 Connection con=Dbase.getConnection(); 
		 Login1 user=null;
		 try {
			PreparedStatement ps=con.prepareStatement("select * from login where username=?");
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();  
			while(rs.next()){  
				user=new Login1();
				user.setId(rs.getInt("id"));
				user.setFname(rs.getString("fname"));
				user.setSname("sname");
				user.setEmail(rs.getString("email"));
				user.setMob(rs.getString("mob"));
				usersList.add(user);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		 return (ArrayList<Login1>) usersList;
	 }
	/* 
	 public static int delete(int eau_id){  
	        int status=0;  
	        try{  
	        	Connection con=JDBCUtility.getConnection();;  
	            PreparedStatement ps=con.prepareStatement("delete from eau_staff where eau_id=?");  
	            ps.setInt(1,eau_id);  
	            status=ps.executeUpdate();  
	              
	            con.close();  
	        }catch(Exception e){e.printStackTrace();}  
	          
	        return status;  
	    }
	 
	 public static int update(EAUStaff staff){  
	        int status=0;  
	        try{  
	            Connection con=JDBCUtility.getConnection();  
	            PreparedStatement ps=con.prepareStatement(  
	                         "update eau_staff set first_name=?,last_name=?,dept=?,joining_year=?,email=?,address=?,city=?,country=? where eau_id=?");  
	            ps.setString(1,staff.getFirst_name());
	            ps.setString(2, staff.getLast_name());
	            ps.setString(3, staff.getDept());
	            ps.setString(4, staff.getJoining_year());
	            ps.setString(5, staff.getEmail());
	            ps.setString(6, staff.getAddress());
	            ps.setString(7, staff.getCity());
	            ps.setString(8, staff.getCountry());
	            ps.setInt(9, staff.getEau_id());
	              
	            status=ps.executeUpdate();  
	              
	            con.close();  
	        }catch(Exception ex){ex.printStackTrace();}  
	          
	        return status;  
	    } 
	 public static EAUStaff getById(int eau_id){  
		 EAUStaff staff=new EAUStaff();  
	          
	        try{  
	            Connection con=JDBCUtility.getConnection();  
	            PreparedStatement ps=con.prepareStatement("select * from eau_staff where eau_id=?");  
	            ps.setInt(1,eau_id);  
	            ResultSet rs=ps.executeQuery();  
	            if(rs.next()){  
	            	staff.setEau_id(rs.getInt(1));
	            	staff.setFirst_name(rs.getString(2));
	            	staff.setLast_name(rs.getString(3));
	            	staff.setDept(rs.getString(4));
	            	staff.setJoining_year(rs.getString(5));
	            	staff.setEmail(rs.getString(6));
	            	staff.setAddress(rs.getString(7));
	            	staff.setCity(rs.getString(8));
	            	staff.setCountry(rs.getString(9));
	                
	            }  
	            con.close();  
	        }catch(Exception ex){ex.printStackTrace();}  
	          
	        return staff;  
	    }  */
}
