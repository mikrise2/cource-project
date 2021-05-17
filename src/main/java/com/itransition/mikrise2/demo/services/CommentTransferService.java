package com.itransition.mikrise2.demo.services;

import com.itransition.mikrise2.demo.entities.Comment;
import com.itransition.mikrise2.demo.model.CommentCreatingModel;

public interface CommentTransferService {
    Comment getComment(CommentCreatingModel commentCreatingModel);
}
