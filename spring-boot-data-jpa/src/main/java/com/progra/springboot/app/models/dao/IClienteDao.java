package com.progra.springboot.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

// import java.util.List;

// import org.springframework.data.repository.CrudRepository;

import com.progra.springboot.app.models.entity.Cliente;


// PagingAndSortingRepository hereda de CrudRepository
public interface IClienteDao extends PagingAndSortingRepository<Cliente,Long> {
	
//	public List<Cliente> findAll();
//	
//	public void save(Cliente cliente);
//	
//	public Cliente findOne(Long id);
//	
//	public void delete(Long id);
	
	
}
