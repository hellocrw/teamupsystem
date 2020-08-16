package crw.online.team.service.project;

import crw.online.team.entity.project.Project;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caorongwu
 * @since 2020-08-17
 */
public interface IProjectService extends IService<Project> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Project>
     */
    IPage<Project> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param project 
     * @return int
     */
    int add(Project project);

    /**
     * 删除
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改
     *
     * @param project 
     * @return int
     */
    int updateData(Project project);

    /**
     * id查询数据
     *
     * @param id id
     * @return Project
     */
    Project findById(Long id);
}
