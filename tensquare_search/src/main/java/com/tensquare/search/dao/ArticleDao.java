package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author yzy
 * @classname ArticleDao
 * @description TODO
 * @create 2019-07-05 15:51
 */
public interface ArticleDao extends ElasticsearchRepository<Article, String> {

    /**
     * 检索
     */
    public Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
