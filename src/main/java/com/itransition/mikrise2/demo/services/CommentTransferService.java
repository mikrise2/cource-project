package com.itransition.mikrise2.demo.services;

import com.itransition.mikrise2.demo.entities.Comment;
import com.itransition.mikrise2.demo.entities.Post;
import com.itransition.mikrise2.demo.model.CommentCreatingModel;
import com.itransition.mikrise2.demo.model.PostCreatingModel;

public interface CommentTransferService {

    Comment getComment(CommentCreatingModel commentCreatingModel);

}
