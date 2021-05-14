package crw.online.team.service.onlineteam.impl;

import crw.online.team.entity.onlineteam.Project;
import crw.online.team.mapper.onlineteam.ProjectMapper;
import crw.online.team.service.onlineteam.IProjectService;
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
 * @since 2020-12-14
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements IProjectService {

    @Override
    public  IPage<Project> findListByPage(Integer page, Integer pageCount){
        IPage<Project> wherePage = new Page<>(page, pageCount);
        Project where = new Project();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Project project){
        return baseMapper.insert(project);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Project project){
        return baseMapper.updateById(project);
    }

    @Override
    public Project findById(Long id){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  baseMapper.selectById(id);
    }
}
