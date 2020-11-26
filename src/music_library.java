import java.sql.*;
import java.lang.*;
import java.util.Scanner;
public class music_library {
static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
static final String DB_URL="jdbc:mysql://localhost/music_library";
static final String user="root";
static final String password="deep09112";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con=null;
		Statement smt=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DB_URL,user,password);
			smt = con.createStatement();
			System.out.println("Enter your choice:");
			System.out.println("1.Save a song");
			System.out.println("2.Delete a song");
			System.out.println("3.View Songs");
			
			int ch;
			Scanner s=new Scanner(System.in);
			ch=s.nextInt();
			String sql;
			switch(ch) {
			case 1:{
				String name,artist,album;
				int releaseyr,rating;
				System.out.println("Enter the name of the song");
			name=s.next();
			System.out.println("Release year:");
			releaseyr=s.nextInt();
			System.out.println("Enter the name of the artist");
			artist=s.next();
			System.out.println("Enter the album:");
			album=s.next();
			System.out.println("Enter the rating:");
			rating=s.nextInt();
			sql="insert into music_library.songs values(\""+name+"\","+releaseyr+",\""+artist+"\",\""+album+"\","+rating+");";
			smt.executeUpdate(sql);
			System.out.println("Saved");
			break;
			}
			case 2:{String name;
			System.out.println("Enter the name of the song");
			name=s.next();
			sql="delete from music_library.songs where name=\""+name+"\";";
			smt.executeUpdate(sql);
			System.out.println("Deleted");
				break;
			}
			case 3:{int choice1;
				System.out.println("1.View all songs");
				System.out.println("2.View songs by artist");
				System.out.println("3.View songs by album");
				//System.out.println("4.View songs by rating");
				choice1=s.nextInt();
				switch(choice1)
				{
				case 1:{
				sql="select * from music_library.songs;";
				ResultSet rs=smt.executeQuery(sql);
				while(rs.next()) {
					String songname=rs.getString("name");
					int releaseyear=rs.getInt("releaseyr");
					String artist=rs.getString("artist");
					String album=rs.getString("album");
					int rating=rs.getInt("rating");
					System.out.println(songname+" "+releaseyear+" "+artist+" "+album+" "+rating);
				}
				break;
				}
				case 2:{String artist;
					System.out.println("Enter the name of the artist from the following:");
					sql="select artist from music_library.songs";
					ResultSet rs=smt.executeQuery(sql);
					while (rs.next()) {
						String artistname=rs.getString("artist");
						System.out.println(artistname);
						
					}
					artist=s.next();
					sql="select name from music_library.songs where artist=\""+artist+"\";";
					ResultSet rs1=smt.executeQuery(sql);
					while(rs1.next()) {
						String name=rs1.getString("name");
						System.out.println(name);
					}
					break;
					
				}
				case 3:{
					String album;
					System.out.println("Enter the name of the album from the following:");
					sql="select album from music_library.songs";
					ResultSet rs=smt.executeQuery(sql);
					while (rs.next()) {
						String albumname=rs.getString("album");
						System.out.println(albumname);
						
					}
					album=s.next();
					sql="select name from music_library.songs where album=\""+album+"\";";
					ResultSet rs1=smt.executeQuery(sql);
					while(rs1.next()) {
						String name=rs1.getString("name");
						System.out.println(name);
					}
					
				}
				}
				
			}
			}
			
			
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		

	}

}
