package br.com.codenation.mapfood.repository;

import br.com.codenation.mapfood.model.Motorcyclist;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MotocyclistRepository extends PagingAndSortingRepository<Motorcyclist,Long> {

    List<Motorcyclist> findByAvailable(Boolean available);
}
