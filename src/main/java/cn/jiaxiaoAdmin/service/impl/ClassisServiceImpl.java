package cn.jiaxiaoAdmin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jiaxiaoAdmin.dao.ClassisDAO;
import cn.jiaxiaoAdmin.model.Classis;
import cn.jiaxiaoAdmin.service.ClassisService;


@Transactional
@Service
public class ClassisServiceImpl implements ClassisService {
	@Resource
	private ClassisDAO classisDAO;
	
	public void insertClassis(Classis classis) throws Exception {
		classisDAO.insertClassis(classis);
	}

	@Override
	public List<Classis> getClassisIsList() throws Exception{
		
		return classisDAO.getClassisIsList();
	}

	@Override
	public Classis getClassisById(String classId) throws Exception{
		
		return classisDAO. getClassisById(classId);
	}
	
}
