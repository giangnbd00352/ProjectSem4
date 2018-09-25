package com.ensat.services;

import com.ensat.entities.ImageOrder;

public interface OrderService {
	
	Iterable<ImageOrder> getAllOrder();
	
	Iterable<ImageOrder> getAllOrderByUserId();

	ImageOrder getOrderById(Integer id);

	ImageOrder saveOrder(ImageOrder order);

    void deleteImageOrder(Integer id);

}
