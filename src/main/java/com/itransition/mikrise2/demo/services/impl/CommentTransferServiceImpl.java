package com.itransition.mikrise2.demo.services.impl;

import com.itransition.mikrise2.demo.entities.Comment;
import com.itransition.mikrise2.demo.entities.LikeDislike;
import com.itransition.mikrise2.demo.model.CommentCreatingModel;
import com.itransition.mikrise2.demo.services.CommentTransferService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CommentTransferServiceImpl implements CommentTransferService {
    @Override
    public Comment getComment(CommentCreatingModel commentCreatingModel) {
        var comment = new Comment();
        comment.setDislike(new LikeDislike(null, 0L, null));
        comment.setLike(new LikeDislike(null, 0L, null));
        comment.setText(commentCreatingModel.getText());
        return comment;
    }
}
