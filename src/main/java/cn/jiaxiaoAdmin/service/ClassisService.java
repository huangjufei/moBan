package cn.jiaxiaoAdmin.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.jiaxiaoAdmin.model.Classis;


@Repository
public interface ClassisService {
	//插入班级信心
	public void insertClassis(Classis classis) throws Exception;
	//得到班级的List集合 
	public List<Classis> getClassisIsList() throws Exception;
	
	//根据班级id 得到对应的班级信息
	public Classis getClassisById(String classId) throws Exception;	
	
}
