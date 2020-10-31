package com.businessad.addapi;

import com.businessad.addapi.entities.Advert;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IBaseController<T>  {
    public List<T> getAll();
    public ResponseEntity<T> get(String id);
    public T create(@Validated @RequestBody T entity);
    public ResponseEntity<String> delete(String id);
    public ResponseEntity<T> Update(@PathVariable String id, @Validated @RequestBody T entity);
}
