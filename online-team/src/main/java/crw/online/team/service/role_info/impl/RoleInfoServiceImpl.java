package crw.online.team.service.role_info.impl;

import crw.online.team.entity.role_info.RoleInfo;
import crw.online.team.mapper.role_info.RoleInfoMapper;
import crw.online.team.service.role_info.IRoleInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author caorongwu
 * @since 2021-01-09
 */
@Service
public class RoleInfoServiceImpl extends ServiceImpl<RoleInfoMapper, RoleInfo> implements IRoleInfoService {

    @Override
    public  IPage<RoleInfo> findListByPage(Integer page, Integer pageCount){
        IPage<RoleInfo> wherePage = new Page<>(page, pageCount);
        RoleInfo where = new RoleInfo();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(RoleInfo roleInfo){
        return baseMapper.insert(roleInfo);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(RoleInfo roleInfo){
        return baseMapper.updateById(roleInfo);
    }

    @Override
    public RoleInfo findById(Long id){
        return  baseMapper.selectById(id);
    }
}
