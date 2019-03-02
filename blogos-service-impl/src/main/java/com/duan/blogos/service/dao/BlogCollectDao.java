package com.duan.blogos.service.dao;

import com.duan.blogos.service.common.enums.BlogSortRule;
import com.duan.blogos.service.entity.BlogCollect;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 2017/12/27.
 *
 * @author DuanJiaNing
 */
@Repository
public interface BlogCollectDao extends BaseDao<BlogCollect> {

    /**
     * 根据博主和博文删除收藏文章
     *
     * @param bloggerId 博主id
     * @param blogId    博文id
     * @return 执行结果
     */
    int deleteCollectByBloggerId(@Param("bloggerId") Long bloggerId, @Param("blogId") Long blogId);

    /**
     * 获取指定博主指定类别的收藏博文
     *
     * @param bloggerId 博主id
     * @param category  类别id
     * @return 查询结果
     */
    List<BlogCollect> listCollectBlog(@Param("bloggerId") Long bloggerId,
                                      @Param("category") Long category,
                                      @Param("sortRule") BlogSortRule sortRule);

    /**
     * 根据博文id和博主id更新收藏
     *
     * @param bloggerId 博主id（收藏者）
     * @param blogId    博文id
     * @param reason    收藏理由
     * @param category  收藏到类别
     * @return 影响行数
     */
    int updateByUnique(@Param("bloggerId") Long bloggerId,
                       @Param("blogId") Long blogId,
                       @Param("reason") String reason,
                       @Param("category") Integer category);

    /**
     * 根据博文id获得所有收藏记录
     *
     * @param blogId 博文id
     * @return 查询结果
     */
    List<BlogCollect> listAllCollectByBlogId(Long blogId);

    /**
     * 统计指定博主的博文收藏数
     *
     * @param bloggerId 博主id
     * @return 数量
     */
    Integer countByCollectorId(Long bloggerId);

    /**
     * 根据博主id和博文id获取收藏记录
     *
     * @param bloggerId 博主id
     * @param blogId    博文id
     * @return 查询记录
     */
    BlogCollect getCollect(@Param("bloggerId") Long bloggerId, @Param("blogId") Long blogId);

    /**
     * 查询指定博主收藏的所有博文id
     *
     * @param bloggerId 博主id
     * @return 只查询收藏博文 id
     */
    List<BlogCollect> listAllIdByBloggerId(Long bloggerId);
}