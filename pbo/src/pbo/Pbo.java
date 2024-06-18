/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pbo;
import java.sql.*;  
import java.util.Scanner;

/**
 *
 * @author ikhsan
 */
public class Pbo {

    /**
     * @param args the command line arguments
     */
    
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1/pbo";
    static final String USER = "root";
    static final String PASS = "";
    
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    
       
    public static void main(String[] args) {
        
        Scanner inp = new Scanner(System.in);
        int menu;
        do{
            System.out.println("");
            System.out.println("Menu CRUD Database perpustakaan");
            System.out.println("1. Insert Buku");
            System.out.println("2. Delete Buku");
            System.out.println("3. Update Buku");
            System.out.println("4. Daftar Buku");
            System.out.println("5. Keluar");
            System.out.println("Masukkan menu = ");
            menu = inp.nextInt();
        
        switch(menu)
        {
            case 1:
                insert();
                break;
            case 2:
                delete();
                break;
            case 3:
                update();
                break;  
            case 4:
                show();
                break;
            default:
                System.out.println("Pilihan Salah!");
        }
        }while(menu != 5);
    }
    
    public static void insert()
	{
            Scanner inp = new Scanner(System.in);
            
            System.out.println("Masukkan Judul Buku = ");
            String judul_buku = inp.next();
            System.out.println("Masukkan tahun terbit = ");
            int tahun_terbit = inp.nextInt();
            System.out.println("Masukkan stok = ");
            int stok = inp.nextInt();
            System.out.println("Masukkan id penulis = ");
            int penulis = inp.nextInt();
            
            try {
		Class.forName(JDBC_DRIVER);
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		stmt = conn.createStatement();
			
		String sql = "INSERT INTO buku (judul_buku,tahun_terbit,stok,penulis) VALUES (?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
			
		ps.setString(1, judul_buku);
		ps.setInt(2, tahun_terbit);
		ps.setInt(3, stok);
		ps.setInt(4, penulis);
			
		ps.execute();
                System.out.println("Berhasil Insert!");
			
		stmt.close();
		conn.close();
            }
            catch(Exception e) {
		e.printStackTrace();
            }
	}

	public static void show()
	{
            try {
		Class.forName(JDBC_DRIVER);
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		stmt = conn.createStatement();
			
		rs = stmt.executeQuery("SELECT * FROM buku");
		int i = 1;
		while(rs.next())
                {
                    System.out.println("Data ke-"+i);
                    System.out.println("Judul Buku : "+rs.getString("judul_buku"));
                    System.out.println("Tahun Terbit : "+rs.getString("tahun_terbit"));
                    System.out.println("Stok : "+rs.getString("stok"));
                    System.out.println("ID Penulis : "+rs.getString("penulis"));
                    System.out.println("");
                    i++;
		}
            }
            catch(Exception e)
            {
		e.printStackTrace();
            }
	}
        
        public static void delete()
        {
            Scanner inp = new Scanner(System.in);
            
            int id;
            System.out.println("Masukkan ID buku yang ingin dihapus = ");
            id = inp.nextInt();
            
            
            try {
		Class.forName(JDBC_DRIVER);
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		stmt = conn.createStatement();
                
		String sql = "DELETE FROM buku WHERE id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
                
		ps.setInt(1, id);
		ps.execute();
                System.out.println("Berhasil Delete!");
			
                stmt.close();
               	conn.close();
		}
		catch(Exception e) {
                    e.printStackTrace();
                }
        }
        
        public static void update()
        {
            Scanner inp = new Scanner(System.in);
            
            int id;
            System.out.println("Masukkan ID buku yang ingin diupdate = ");
            id = inp.nextInt();
            System.out.println("Masukkan Judul Buku = ");
            String judul_buku = inp.next();
            System.out.println("Masukkan tahun terbit = ");
            int tahun_terbit = inp.nextInt();
            System.out.println("Masukkan stok = ");
            int stok = inp.nextInt();
            System.out.println("Masukkan id penulis = ");
            int penulis = inp.nextInt();
            
            try {
		Class.forName(JDBC_DRIVER);
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		stmt = conn.createStatement();
                
		String sql = "UPDATE buku SET judul_buku=?, tahun_terbit=?, stok=?, penulis=? WHERE id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
                
                ps.setString(1, judul_buku);
                ps.setInt(2, tahun_terbit);
                ps.setInt(3, stok);
                ps.setInt(4, penulis);
		ps.setInt(5, id);
		ps.execute();
                System.out.println("Berhasil Update Data!");
			
                stmt.close();
               	conn.close();
		}
		catch(Exception e) {
                    e.printStackTrace();
                }
        }
    
}
