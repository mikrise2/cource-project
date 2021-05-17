package com.itransition.mikrise2.demo.services;

import com.itransition.mikrise2.demo.Dto.UserCommentDto;
import com.itransition.mikrise2.demo.Dto.UserHeaderDto;
import com.itransition.mikrise2.demo.entities.User;

public interface UserTransferService {
    UserHeaderDto getUserHeaderDto(User user);

    UserCommentDto getUserCommentDto(User user);
}
