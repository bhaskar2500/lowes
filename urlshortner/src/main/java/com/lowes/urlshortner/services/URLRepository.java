package com.lowes.urlshortner.services;

import com.lowes.urlshortner.dao.models.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories("com.lowes.urlshortner")
public interface URLRepository extends JpaRepository<URL,Long> {
    URL findURLByLongUrl(String longUrl);

}
