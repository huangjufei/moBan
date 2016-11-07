package cn.jiaxiaoAdmin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.jiaxiaoAdmin.model.Classis;


@Repository
public interface ClassisDAO {
	
	public void insertClassis(Classis classis) throws Exception;

	public List<Classis> getClassisIsList() throws Exception;

	public Classis getClassisById(String classId) throws Exception;	
	
}
