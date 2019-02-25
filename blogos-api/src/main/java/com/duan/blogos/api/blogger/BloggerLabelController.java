package com.duan.blogos.api.blogger;

import com.alibaba.dubbo.config.annotation.Reference;
import com.duan.blogos.annonation.TokenNotRequired;
import com.duan.blogos.api.BaseController;
import com.duan.blogos.service.blogger.BloggerLabelService;
import com.duan.blogos.service.common.dto.blog.BlogLabelDTO;
import com.duan.blogos.service.common.restful.PageResult;
import com.duan.blogos.service.common.restful.ResultModel;
import org.springframework.web.bind.annotation.*;

/**
 * Created on 2018/1/12.
 * 博文标签API，标签的使用不限定博主，即只要标签存在，任何博主都可以使用
 * <p>
 * 1 查看所有标签
 * 2 查看指定标签
 * 3 修改标签
 * 4 删除标签
 * 5 增加标签
 * 6 获取指定博主创建的标签
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/blogger/{bloggerId}/label")
public class BloggerLabelController extends BaseController {

    @Reference
    private BloggerLabelService bloggerLabelService;

    /**
     * 获取指定博主创建的标签
     */
    @GetMapping
    @TokenNotRequired
    public ResultModel<PageResult<BlogLabelDTO>> list(@PathVariable Long bloggerId,
                                                      @RequestParam(required = false) Integer pageNum,
                                                      @RequestParam(required = false) Integer pageSize) {
        handleAccountCheck(bloggerId);

        ResultModel<PageResult<BlogLabelDTO>> result = bloggerLabelService.listLabelByBlogger(bloggerId, pageNum, pageSize);
        if (result == null)
            return handlerEmptyResult();

        return result;
    }

    /**
     * 新增标签
     */
    @PostMapping
    public ResultModel add(@PathVariable Long bloggerId,
                           @RequestParam("title") String title) {

        Long id = bloggerLabelService.insertLabel(bloggerId, title);
        if (id == null)
            return handlerOperateFail();

        return ResultModel.success(id);
    }

    /**
     * 修改标签
     */
    @PutMapping("/{labelId}")
    public ResultModel update(@PathVariable Long bloggerId, @PathVariable Long labelId,
                              @RequestParam("title") String newTitle) {

        boolean result = bloggerLabelService.updateLabel(labelId, bloggerId, newTitle);
        if (!result)
            return handlerOperateFail();

        return ResultModel.success();
    }

    /**
     * 删除标签
     */
    @DeleteMapping("/{labelId}")
    public ResultModel delete(@PathVariable Long bloggerId, @PathVariable Long labelId) {
        handleAccountCheck(bloggerId);
        boolean result = bloggerLabelService.deleteLabel(bloggerId, labelId);
        if (!result)
            return handlerOperateFail();

        return ResultModel.success();
    }

}
