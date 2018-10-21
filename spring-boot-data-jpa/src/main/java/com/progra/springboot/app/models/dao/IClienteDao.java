package com.progra.springboot.app.models.dao;

import org.springframework.data.jpa.repository.Query;
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
	
	@Query("select c from Cliente c left join fetch c.facturas f where c.id=?1")
	public Cliente fetchByIDWithFacturas(Long id);
	
	
}
