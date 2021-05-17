package com.itransition.mikrise2.demo.services;

import com.itransition.mikrise2.demo.entities.Post;
import com.itransition.mikrise2.demo.model.PostCreatingModel;

public interface PostTransferService {

    Post getPost(PostCreatingModel postCreatingModel);

}
