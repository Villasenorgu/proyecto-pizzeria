package com.pizzaiolo.domains.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pizzaiolo.domains.contracts.repositories.OrderRepository;
import com.pizzaiolo.domains.contracts.services.OrderService;
import com.pizzaiolo.domains.entities.Order;
import com.pizzaiolo.domains.entities.Order.Status;
import com.pizzaiolo.exceptions.DuplicateKeyException;
import com.pizzaiolo.exceptions.InvalidDataException;
import com.pizzaiolo.exceptions.NotFoundException;

@Service
public class OrderServiceImpl implements OrderService{
	private OrderRepository dao;

	public OrderServiceImpl(OrderRepository dao) {
		this.dao = dao;
	}
	
	@Override
	public List<Order> getAll() {
		return dao.findAll();
	}
	
	@Override
	public Iterable<Order> getAll(Sort sort) {
		return dao.findAll(sort);
	}
	@Override
	public Page<Order> getAll(Pageable pageable) {
		return dao.findAll(pageable);
	}
	
	@Override
	public <T> List<T> getByProjection(Class<T> type) {
		return dao.findByIdOrderIsNotNull(type);
	}

	@Override
	public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
		return dao.findByIdOrderIsNotNull(sort, type);
	}

	@Override
	public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
		return dao.findByIdOrderIsNotNull(pageable, type);
	}

	@Override
	public Order getOne(Integer id) throws NotFoundException {
		var item = dao.findById(id);
		if(item.isEmpty())
			throw new NotFoundException();
		return item.get();
	}
	
	@Override
	public Order add(Order item) throws DuplicateKeyException, InvalidDataException {
		if(item == null)
			throw new IllegalArgumentException();
		if(dao.findById(item.getIdOrder()).isPresent())
			throw new DuplicateKeyException();
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}
	@Override
	public Order change(Order item) throws NotFoundException, InvalidDataException  {
		if(item == null)
			throw new IllegalArgumentException();
		if(dao.findById(item.getIdOrder()).isEmpty())
			throw new NotFoundException();
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}
	@Override
	public void delete(Order item) {
		if(item == null)
			throw new IllegalArgumentException();
		deleteById(item.getIdOrder());
		
	}
	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}

//	@Override
//	public List<Order> findByStatus(Status status) {
//		return dao.findByStatus(status);
//	}
}
