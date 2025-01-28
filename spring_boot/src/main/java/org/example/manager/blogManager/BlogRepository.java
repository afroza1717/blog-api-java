package org.example.manager.blogManager;

import org.example.model.db.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("blogRepository")
public interface BlogRepository extends JpaRepository<Blog, Long> {

}
