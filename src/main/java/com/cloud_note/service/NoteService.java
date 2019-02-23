package com.cloud_note.service;

import java.util.List;
import java.util.Map;

import com.cloud_note.entity.Note;
import com.cloud_note.util.NoteResult;


public interface NoteService {
       public NoteResult<List<Map>> loadBookNotes(String bookId);
       public NoteResult<Note> loadnote(String noteId);
       //������±ʼ�
       public NoteResult<Object> updateNote(String noteId,String title,String body);
       //���ӱʼ�
       public NoteResult<Note> addNote(String userId,String bookId,String title);
       public NoteResult<Note> deleteNote(String noteId);
     
       //String... ��̬����������String[] ����
     public void deleteNotes(String...ids);
}
