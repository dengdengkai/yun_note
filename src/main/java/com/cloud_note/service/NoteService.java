package com.cloud_note.service;

import java.util.List;
import java.util.Map;

import com.cloud_note.entity.Note;
import com.cloud_note.util.NoteResult;


public interface NoteService {
       public NoteResult<List<Map>> loadBookNotes(String bookId);
       public NoteResult<Note> loadnote(String noteId);
       //保存更新笔记
       public NoteResult<Object> updateNote(String noteId,String title,String body);
       //增加笔记
       public NoteResult<Note> addNote(String userId,String bookId,String title);
       public NoteResult<Note> deleteNote(String noteId);
     
       //String... 动态参数，就是String[] 数组
     public void deleteNotes(String...ids);
}
