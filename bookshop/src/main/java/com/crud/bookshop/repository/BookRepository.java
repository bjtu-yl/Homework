package com.crud.bookshop.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.crud.bookshop.domain.Book;

public interface BookRepository extends PagingAndSortingRepository<Book,Integer>, JpaSpecificationExecutor<Book> {
	
}
