package com.itransition.mikrise2.demo.services.impl;

import com.itransition.mikrise2.demo.Dto.UserCommentDto;
import com.itransition.mikrise2.demo.Dto.UserHeaderDto;
import com.itransition.mikrise2.demo.entities.User;
import com.itransition.mikrise2.demo.services.UserTransferService;
import org.springframework.stereotype.Service;

@Service
public class UserTransferServiceImpl implements UserTransferService {
    @Override
    public UserHeaderDto getUserHeaderDto(User user) {
        return new UserHeaderDto(user.getId(), user.getUserRole());
    }

    @Override
    public UserCommentDto getUserCommentDto(User user) {
        return new UserCommentDto(user.getId(), user.getUsername(), user.getPhotoUrl());
    }

}
