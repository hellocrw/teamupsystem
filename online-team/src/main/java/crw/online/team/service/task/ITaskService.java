package crw.online.team.service.task;

import crw.online.team.entity.task.Task;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caorongwu
 * @since 2021-01-09
 */
public interface ITaskService extends IService<Task> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Task>
     */
    IPage<Task> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param task 
     * @return int
     */
    int add(Task task);

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
     * @param task 
     * @return int
     */
    int updateData(Task task);

    /**
     * id查询数据
     *
     * @param id id
     * @return Task
     */
    Task findById(Long id);
}
