package com.itransition.mikrise2.demo.services.impl;

import com.itransition.mikrise2.demo.entities.Bonus;
import com.itransition.mikrise2.demo.entities.Company;
import com.itransition.mikrise2.demo.model.BonusCreatingModel;
import com.itransition.mikrise2.demo.services.BonusTransferService;
import org.springframework.stereotype.Service;

@Service
public class BonusTransferServiceImpl implements BonusTransferService {
    @Override
    public Bonus getBonus(BonusCreatingModel bonusCreatingModel, Company company) {
        var bonus = new Bonus();
        bonus.setCompany(company);
        bonus.setInfo(bonusCreatingModel.getInfo());
        bonus.setName(bonusCreatingModel.getName());
        bonus.setPrice(bonusCreatingModel.getPrice());
        return bonus;
    }
}
