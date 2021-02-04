package crw.team.order.service.demo01.impl;

import crw.team.order.entity.demo01.Demo01;
import crw.team.order.mapper.demo01.Demo01Mapper;
import crw.team.order.service.demo01.IDemo01Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import crw.team.order.service.remoteService.ProjectService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2021-02-01
 */
@Service
public class Demo01ServiceImpl extends ServiceImpl<Demo01Mapper, Demo01> implements IDemo01Service {

    @Autowired
    private ProjectService projectService;

    @Override
    public  IPage<Demo01> findListByPage(Integer page, Integer pageCount){
        IPage<Demo01> wherePage = new Page<>(page, pageCount);
        Demo01 where = new Demo01();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Demo01 demo01){
        return baseMapper.insert(demo01);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Demo01 demo01){
        return baseMapper.updateById(demo01);
    }


    @Override
    @GlobalTransactional(name = "fsp-create-order", rollbackFor = Exception.class)
    public Demo01 findById(Long id){
        Demo01 demo01 = baseMapper.selectById(id);
        demo01.setDemoLog("demo-log修改失败");
        baseMapper.updateById(demo01);

        projectService.findById(84L);
        return  baseMapper.selectById(id);
    }
}
