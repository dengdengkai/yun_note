package com.cloud_note.service;

import java.util.List;

import com.cloud_note.entity.Note;
import com.cloud_note.entity.Share;
import com.cloud_note.util.NoteResult;

public interface ShareService {
   public NoteResult<Note> share(String noteId);
   
   public NoteResult<List<Share>> findNote(String keyword,int page);
}
