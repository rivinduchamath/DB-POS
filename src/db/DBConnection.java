package db;
import java.sql.*;

public class DBConnection {
    private static Connection connection;
    public static Connection getConnection() throws Exception{
        if (null==connection){
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos?" +
                    "createDatabaseIfNotExist=true&allowMultiQueries=true",
                    "root",
                    "1234");
            PreparedStatement pstm = connection.prepareStatement("SHOW TABLES");
            ResultSet resultSet = pstm.executeQuery();
            if (!resultSet.next()){
                String sql = "CREATE TABLE customer (ID VARCHAR (20) NOT NULL PRIMARY KEY ,Name VARCHAR(30) Null,Address VARCHAR(30) NULL) ENGINE=InnoDB DEFAULT CHARSET=latin1;\n" +
                        "\n" +
                        "\n" +
                        "CREATE TABLE item (code VARCHAR(20) NOT NULL PRIMARY KEY,description VARCHAR(40)   NULL,qtyOnHand   INT(20) NULL, unitePrice  DOUBLE (20, 2) NULL)ENGINE=InnoDB DEFAULT CHARSET=latin1;\n" +
                        "\n" +
                        "\n" +
                        "CREATE TABLE orderx(Orderxid  VARCHAR(19) NOT NULL PRIMARY KEY ,cusid VARCHAR(20) NULL,Orderdate DATE NULL,CONSTRAINT orderx_ibfk_1 FOREIGN KEY(cusid)REFERENCES customer (ID)); \n" +
                        "\n" +
                        " CREATE TABLE orderdetail(oderid VARCHAR(30) DEFAULT '' NOT NULL,ItemCodeF  VARCHAR(30) DEFAULT '' NOT NULL, Orderdate  DATE NULL,unitePrice DOUBLE (20, 2) NULL ,qty INT(10) NULL,PRIMARY KEY (oderid, ItemCodeF)," +
                        "CONSTRAINT orderdetail_ibfk_1 FOREIGN KEY (oderid) REFERENCES orderx (Orderxid),CONSTRAINT orderdetail_ibfk_2 FOREIGN KEY (ItemCodeF) \n" +
                        "REFERENCES item (code));";
                pstm = connection.prepareStatement(sql);
                pstm.execute();
            }
        }
        return connection;
    }
}