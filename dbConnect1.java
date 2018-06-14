package test;

import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.*;
public class dbConnect1 {
    //Connection conn=null;
    public static Connection ConnectDB(){

        //String url = "jdbc:mysql://localhost:3306/db";
    	String url = "jdbc:sqlserver://flyingducks.database.windows.net:1433;" + "databaseName=flyingDucksDB;";
        String username = "useradmin";
        String password = "August@123";
        //String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

        System.out.println("Connecting database...");

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.print("db connected...");
            return connection;
      //connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }        
    }



    public static void insert(String p, String fn, long fs, String s, int bid, int bpid, String ipANN, String blid, long bs, int lr, String ipADN, String dNP, String dNId, String st){
       
       String path = p;
       String fileName= fn;
       long fileSize = fs;
       String Status = s;
       int blockIdx = bid;
       int blockPoolId = bpid;
       String ipAdressNameNode = ipANN;
       String blockId = blid;
       long blockSize = bs;
       int liveRepl = lr;
       String ipAdressDataNode = ipADN;
       String dataNodePort = dNP;
       String dataNodeId = dNId;
       String storageType = st;
       int c = 1;
       
        //Class.forName(driver).newInstance();

        Connection conn=null;
        //Statement stmt = null;
        conn = ConnectDB();
        
        try{
        	
        	//stmt = conn.createStatement();
        	
            String query = "insert into hadoopDB values ('"+path+"','"+fileName+"','"+fileSize+"','"+Status+"','"+blockIdx+"','"+blockPoolId+"','"+ipAdressNameNode+"','"+blockId+"','"+blockSize+"','"+liveRepl+"','"+ipAdressDataNode+"','"+dataNodePort+"','"+dataNodeId+"','"+storageType+"')";
            //String query = "select * from test1;";
            System.out.print(query);

      // create the mysql insert preparedstatement 
      PreparedStatement preparedStmt = conn.prepareStatement(query);


      // execute the preparedstatement
      preparedStmt.execute();
      //ResultSet rs = stmt.executeQuery(query);
      //System.out.println("result set is :" +rs);
      //int id1 = rs.getInt("id");
      //System.out.println("id is :" +id1);
      
      //JOptionPane.showMessageDialog(null, "Data added");
      System.out.println("Data Added :" +c);
      c = c + 1;
      conn.close();

        }catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, e);
        }
      
    }
     //public static void main(String args[]) {
        //ConnectDB();
         //insert("/user/Barry/.staging/job_1459002408633_0011/", "job.jar",5300,"OK",0,724569433,"172.16.200.253","107387",5300,2,"172.16.200.2","50010","DS-2ef672b0-768c-48b9-97ce-019ddfd48a77","DISK]");
    //}
}