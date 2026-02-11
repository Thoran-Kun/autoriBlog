package com.salvatore.autoriBlog.services;

import com.salvatore.autoriBlog.entities.Blog;
import com.salvatore.autoriBlog.exceptions.NotFoundException;
import com.salvatore.autoriBlog.payloads.NewBlogPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BlogsService {

    private List<Blog> blogsDB = new ArrayList<>();

    public List<Blog> findAll() {
      return this.blogsDB;
    }

    public Blog saveBlog(NewBlogPayload payload) {
        Blog newBlog = new Blog(payload.getCategoria(), payload.getTitolo(), payload.getContenuto(), payload.getTempoDiLettura());
        this.blogsDB.add(newBlog);
        log.info("il Blog " + newBlog.getId() + " è stato aggiunto correttamente");
        return newBlog;
    }

    public Blog findById(long blogId) {
        Blog found = null;
        for(Blog blog : this.blogsDB){
            if(blog.getId() == blogId) found = blog;
        }
        if(found == null) throw new NotFoundException(blogId);
        return found;
    }

    public Blog findByIdAndUpdate(long blogId, NewBlogPayload payload) {
        Blog found = null;
        for(Blog blog : this.blogsDB){
            if(blog.getId() == blogId){
                found = blog;
                found.setCategoria(payload.getCategoria());
                found.setTitolo(payload.getTitolo());
                found.setContenuto(payload.getContenuto());
                found.setTempoDiLettura(payload.getTempoDiLettura());
            }
        }
        if(found == null) throw new NotFoundException(blogId);
        return found;
    }

    public void findByIdAndDelete(long blogId) {
        Blog found = null;
        for(Blog blog : this.blogsDB){
            if(blog.getId() == blogId) found = blog;
        }
        if(found == null) throw new NotFoundException(blogId);
        this.blogsDB.remove(found);
    }
}
