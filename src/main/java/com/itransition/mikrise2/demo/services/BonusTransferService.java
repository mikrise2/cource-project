package com.itransition.mikrise2.demo.services;

import com.itransition.mikrise2.demo.entities.Bonus;
import com.itransition.mikrise2.demo.entities.Company;
import com.itransition.mikrise2.demo.model.BonusCreatingModel;

public interface BonusTransferService {

    Bonus getBonus(BonusCreatingModel bonusCreatingModel, Company company);

}
