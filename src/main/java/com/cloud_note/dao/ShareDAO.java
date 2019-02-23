package com.cloud_note.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cloud_note.entity.Share;

@Repository("shareDAO")
public interface ShareDAO{
	//插入分享的内容
	public void save(Share share);
	
	//搜索分享的内容
	public  List<Share> findLikeTitle(Map params);
} 
