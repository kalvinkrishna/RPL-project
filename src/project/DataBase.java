package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class DataBase {

	private String user;
	private String password;
	private java.sql.Statement stmt;
	private Connection con = null;
	private ResultSet rs = null;
	private PreparedStatement pr = null;
	private ArrayList<String> list = new ArrayList<String>();

	public DataBase() {
		Connector();
	}

	private void Connector() {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/jdbctutorial", "root", "");
			stmt = con.createStatement();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> AutoComplete(String ID) {
		try {
			String query = "SELECT deskripsi FROM task WHERE ID='" + ID;
			pr = con.prepareStatement(query);
			rs = pr.executeQuery();
			while (rs.next()) {
				String l = rs.getString("deskripsi");
				list.add(l);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBasenya Salah");
		}
		return list;
	}

	public void Delete(int delete) {
		try {
			String query = "DELETE FROM task WHERE NO= " + delete;
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Data Belum Di delete");
		}

	}

	public ResultSet Display(String ID) {
		try {
			String sql = "UPDATE task SET status='COMPLETE' WHERE ID='"
					+ ID
					+ "' AND (tanggal = CURRENT_DATE() OR tanggals = CURRENT_DATE())";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		try {
			String sql = "SELECT NO,deskripsi as 'Description',lokasi as 'Location',DATE_FORMAT(tanggal,'%d-%m-%Y') AS StartDate,Start,DATE_FORMAT(tanggals,'%d-%m-%Y') AS EndDate,End,jenis as'Type',status as Status FROM task WHERE ID='"
					+ ID + "'";
			pr = con.prepareStatement(sql);
			rs = pr.executeQuery();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return rs;
	}

	public String getID() {
		return user;
	}

	public String getPass() {
		return password;
	}

	public void InsertDataDeadLine(String ID, String activities,
			String Location, String date, String end, String jenis) {
		try {
			String query = "INSERT INTO task(ID,deskripsi,lokasi,tanggals,End,jenis)"
					+ "VALUES('"
					+ ID
					+ "','"
					+ activities
					+ "','"
					+ Location
					+ "','" + date + "','" + end + "','" + jenis + "')";
			stmt.executeUpdate(query);
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, e);
		}
	}

	public void InsertDataFloat(String ID, String activities, String Location,
			String jenis) {
		try {
			String query = "INSERT INTO task (ID,deskripsi,lokasi,jenis) VALUES('"
					+ ID
					+ "','"
					+ activities
					+ "','"
					+ Location
					+ "','"
					+ jenis + "')";
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public void InsertDataTime(String ID, String activities, String Location,
			String date, String dates, String Start, String End, String jenis) {
		try {
			String query = "INSERT INTO task (ID,deskripsi,lokasi,tanggal,tanggals,Start,End,jenis) VALUES('"
					+ ID
					+ "','"
					+ activities
					+ "','"
					+ Location
					+ "','"
					+ date
					+ "','"
					+ dates
					+ "','"
					+ Start
					+ "','"
					+ End
					+ "','"
					+ jenis + "')";

			stmt.executeUpdate(query);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public void Login(String Id, String pass) {
		try {
			String query = "SELECT ID,Password FROM user WHERE ID='" + Id
					+ "' AND Password='" + pass + "'";
			pr = con.prepareStatement(query);
			rs = pr.executeQuery();
			int count = 0;
			while (rs.next()) {
				count += 1;
				if (count == 1) {
					setID(rs.getString("ID"));
					setPass(rs.getString("Password"));
				}
			}
		} catch (SQLException sql) {
			JOptionPane.showMessageDialog(null, sql);
		}

	}

	public void Register(String id, String pass, String nama, double umur2,
			String address) {

		try {
			String query = "INSERT INTO user VALUES('" + id + "','" + pass
					+ "','" + nama + "','" + umur2 + "','" + address + "')";
			stmt.executeUpdate(query);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Please cek your Correct Password");

		}

	}

	public ResultSet Search(String kata, String ID) {

		try {
			String sql = "SELECT NO,deskripsi as Description,lokasi as Location,DATE_FORMAT(tanggal,'%d-%m-%Y') AS 'StartDate',Start,DATE_FORMAT(tanggals,'%d-%m-%Y') AS 'EndDate',End,jenis as Type,status as Status FROM task WHERE ID='"
					+ ID
					+ "'"
					+ " AND (deskripsi LIKE '%"
					+ kata
					+ "%' OR  lokasi LIKE '%" + kata + "%')";
			rs = pr.executeQuery(sql);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return rs;

	}

	private void setID(String id) {
		this.user = id;
	}

	// getter and setter login
	private void setPass(String pass) {
		this.password = pass;
	}

	public ResultSet Sort(String ID, String user, String ORDER) {
		try {
			String query = "SELECT No,deskripsi as Description,lokasi as Location,DATE_FORMAT(tanggal,'%d-%m-%Y') AS StartDATE ,Start,DATE_FORMAT(tanggals,'%d-%m-%Y') AS EndDATE,End,jenis as Type,status as Status FROM task WHERE ID='"
					+ ID + "'ORDER BY " + user + " " + ORDER;
			pr = con.prepareStatement(query);
			rs = pr.executeQuery();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return rs;

	}

	public void Update(String user, String No, String kegiatan, String lokasi,
			String tanggal, String tanggals, String start, String end,
			String kind) {
		if (kind.equals("DeadLine")) {
			try {
				String query = "UPDATE task SET deskripsi='" + kegiatan
						+ "',lokasi='" + lokasi + "',tanggal='" + tanggal
						+ "',jenis='" + kind + "', End='" + end + "'WHERE NO="
						+ No + " AND ID='" + user + "'";
				stmt.executeUpdate(query);
			} catch (SQLException e) {

				JOptionPane.showMessageDialog(null, "Data Anda Belum Lengkap");
			}
		} else if (kind.equals("Time")) {
			try {
				String query = "UPDATE task SET deskripsi='" + kegiatan
						+ "',lokasi='" + lokasi + "',tanggal='" + tanggal
						+ "',jenis='" + kind + "','tanggals='" + tanggals
						+ "','Start='" + start + "','End='" + end
						+ "' WHERE NO=" + No + " AND ID='" + user + "'";
				stmt.executeUpdate(query);
			} catch (SQLException e) {

				JOptionPane.showMessageDialog(null, "Data Anda Belum Lengkap");
			}
		} else if (kind.equals("Float")) {
			try {
				String query = "UPDATE task SET deskripsi='" + kegiatan
						+ "',lokasi='" + lokasi + "',tanggal='" + tanggal
						+ "',jenis='" + kind + "'" + " WHERE NO=" + No
						+ " AND ID='" + user + "'";
				stmt.executeUpdate(query);
			} catch (SQLException e) {

				JOptionPane.showMessageDialog(null, e);
			}
		}
	}

	public ResultSet Logging(String nama) {

		try {
			String query = "SELECT Event,Deskription FROM logid WHERE user='"
					+ nama + "'";
			pr = con.prepareStatement(query);
			rs = pr.executeQuery();

		}

		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return rs;
	}

	public void LogView(String Event, String values, String user) {

		try {
			String query = "INSERT INTO logid Values('" + user + "','" + Event
					+ "','" + values + "')";
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	public void clear(String nama, String Event) {

		if (Event == "Table") {
			try {
				String query = "DELETE FROM task WHERE ID='" + nama + "'";
				stmt.executeUpdate(query);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e);
			}
		} else {

			try {
				String query = "DELETE FROM logid WHERE user='" + nama + "'";
				stmt.executeUpdate(query);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}

	}

}
