package com.itransition.mikrise2.demo.services.impl;

import com.itransition.mikrise2.demo.entities.Company;
import com.itransition.mikrise2.demo.entities.Post;
import com.itransition.mikrise2.demo.model.PostCreatingModel;
import com.itransition.mikrise2.demo.services.PostTransferService;
import org.springframework.stereotype.Service;

@Service
public class PostTransferServiceImpl implements PostTransferService {
    @Override
    public Post getPost(PostCreatingModel postCreatingModel) {
        var post = new Post();
        post.setText(postCreatingModel.getText());
        post.setName(postCreatingModel.getName());
        return post;
    }
}
