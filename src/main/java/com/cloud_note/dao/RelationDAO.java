package com.cloud_note.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cloud_note.entity.Book;
import com.cloud_note.entity.User;

@Repository
public interface RelationDAO {
      //关联多个对象 两条SQL语句
	   public User findUserAndBooks(String userId);
      // 关联多个对象一条语句
       public User findUserAndBooks1(String userId);
        //关联单个对象两条语句
       public List<Book> findBookAndUser();
       
       //关联单个对象一条sql语句
       public List<Book> findBookAndUser1();
}
