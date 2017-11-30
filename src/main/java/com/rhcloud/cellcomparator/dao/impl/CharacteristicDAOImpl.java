package com.rhcloud.cellcomparator.dao.impl;

import javax.ejb.Stateless;

import com.rhcloud.cellcomparator.cdi.qualifier.CharacteristicDAO;
import com.rhcloud.cellcomparator.entity.Characteristic;

@Stateless
@CharacteristicDAO
public class CharacteristicDAOImpl extends AbstractDAOImpl<Characteristic> {

}
