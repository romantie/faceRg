package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import Conn.Conn;
import entity.Students;

public class StuDao {
	 public List<Students> SelectStus() {//selectDao������ݴ����ݿ����õ�
		  List<Students> list=new ArrayList<Students>();//News��ָNews.java
		  ResultSet rs=null;
		  try {
			  Conn dbc=new Conn();
			  Connection conn=dbc.getConn();
			  String sql_select="select * from student limit 30";
			  PreparedStatement pst=conn.prepareStatement(sql_select);//׼��ִ��SQL���
			  rs = pst.executeQuery();//�ǵ���仰��������ݿ�
			  while(rs.next()) {
				  Students stu=new Students(rs.getString("st_no"),rs.getString("st_name"),
						  rs.getString("st_birthday"),rs.getString("st_Email"),rs.getString("st_phone"),
						  rs.getString("st_classno"),rs.getString("st_roll"));
				  list.add(stu);
			  }
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
	     return list;
	  }
	 public int delete(Students stu) {
		 int rs=0;
		Conn dbc = new Conn();
		Connection conn = dbc.getConn();
	    String delete_sql = "delete from student where st_no=?";
	    PreparedStatement pst;
		try {
			pst = conn.prepareStatement(delete_sql);
			pst.setString(1, stu.getSt_no());
			rs = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return rs;
	 }
	 
	 
public Students dushuju(String st_no) {
	Students stu=new Students();
	ResultSet rs=null;
	Conn dbc=new Conn();
	Connection conn=dbc.getConn();
	  String sql_select="select * from student where st_no=?";
	  
	  try {
	    PreparedStatement pst=conn.prepareStatement(sql_select);//׼��ִ��SQL���
	    pst.setString(1, st_no);
		rs = pst.executeQuery();//�����ݿ������ѯ��������ȷ���Ƿ����������δ֪
		if(rs.next()) {
			stu=new Students(rs.getString("st_no"),rs.getString("st_name"),
					  rs.getString("st_birthday"),rs.getString("st_Email"),rs.getString("st_phone"),
					  rs.getString("st_classno"),rs.getString("st_roll"));
			
		}else {
			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}//�ǵ���仰��������ݿ�
	return stu;
}

public int xiugai(Students stu) {
	int rs=0;
	  try {
		  Conn dbc=new Conn();
		  Connection conn=dbc.getConn();
		  String sql_insert="update student set st_no=?,st_name=?,st_birthday=?,st_Email=?,st_phone=?,st_classno=?,st_roll=?"
		  		+ " where st_no=?";
		  PreparedStatement pst=conn.prepareStatement(sql_insert);
		  pst.setString(1,stu.getSt_no());
		  pst.setString(2,stu.getSt_name());
		  pst.setString(3,stu.getSt_birthday());
		  pst.setString(4,stu.getSt_Email());
		  pst.setString(5,stu.getSt_phone());
		  pst.setString(6,stu.getSt_classno());
		  pst.setString(7,stu.getSt_roll());
		  pst.setString(8,stu.getSt_no());
		  rs = pst.executeUpdate();
	  }catch(Exception e) {
		  e.printStackTrace();
	  }
	  return rs;
}
}
