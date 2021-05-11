package mcm.personal.microservicios.commons.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;


//public class CommonServiceImpl<E, R extends CrudRepository<E, Long>> implements CommonService<E> {
public class CommonServiceImpl<E, R extends PagingAndSortingRepository<E, Long>> implements CommonService<E> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonServiceImpl.class);

	@Autowired
	protected R repository;
	
	@Override
	public Iterable<E> findAll() {
		return repository.findAll();
	}
	
	@Override
	public Page<E> findAll(Pageable pageable) {
		LOGGER.debug("Num pagina" + pageable.getPageNumber());
		LOGGER.debug("Tamaño pagina: " + pageable.getPageSize());
		return findAll(pageable);
	}

	@Override
	public Optional<E> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public E save(E entity) {
		return repository.save(entity);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	

}
