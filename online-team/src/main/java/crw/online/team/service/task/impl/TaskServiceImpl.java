package crw.online.team.service.task.impl;

import crw.online.team.entity.task.Task;
import crw.online.team.mapper.task.TaskMapper;
import crw.online.team.service.task.ITaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caorongwu
 * @since 2021-01-09
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {

    @Override
    public  IPage<Task> findListByPage(Integer page, Integer pageCount){
        IPage<Task> wherePage = new Page<>(page, pageCount);
        Task where = new Task();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Task task){
        return baseMapper.insert(task);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Task task){
        return baseMapper.updateById(task);
    }

    @Override
    public Task findById(Long id){
        return  baseMapper.selectById(id);
    }
}
