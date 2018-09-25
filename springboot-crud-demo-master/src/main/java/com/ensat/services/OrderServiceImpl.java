package com.ensat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensat.entities.ImageOrder;
import com.ensat.repositories.ImageOrderRepository;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	ImageOrderRepository imageOrderRepository;

	@Override
	public Iterable<ImageOrder> getAllOrder() {
		return imageOrderRepository.findAll();
	}

	@Override
	public Iterable<ImageOrder> getAllOrderByUserId() {
		return null;
	}

	@Override
	public ImageOrder getOrderById(Integer id) {
		return imageOrderRepository.findOne(id);
	}

	@Override
	public ImageOrder saveOrder(ImageOrder order) {
		
		return imageOrderRepository.save(order);
	}

	@Override
	public void deleteImageOrder(Integer id) {
		imageOrderRepository.delete(id);
		
	}
	
	
	
	
}
