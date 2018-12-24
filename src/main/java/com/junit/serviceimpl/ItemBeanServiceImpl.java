package com.junit.serviceimpl;

import org.springframework.stereotype.Service;

import com.junit.bean.ItemBean;
import com.junit.service.ItemBeanService;

@Service
public class ItemBeanServiceImpl implements ItemBeanService{

	@Override
	public ItemBean getItem() {
		 return new ItemBean(1,"omega",10,120);
	}

}
