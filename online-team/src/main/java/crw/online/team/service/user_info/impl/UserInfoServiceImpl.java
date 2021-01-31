package crw.online.team.service.user_info.impl;

import crw.online.team.entity.user_info.UserInfo;
import crw.online.team.mapper.user_info.UserInfoMapper;
import crw.online.team.service.user_info.IUserInfoService;
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
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Override
    public  IPage<UserInfo> findListByPage(Integer page, Integer pageCount){
        IPage<UserInfo> wherePage = new Page<>(page, pageCount);
        UserInfo where = new UserInfo();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(UserInfo userInfo){
        return baseMapper.insert(userInfo);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(UserInfo userInfo){
        return baseMapper.updateById(userInfo);
    }

    @Override
    public UserInfo findById(Long id){
        return  baseMapper.selectById(id);
    }
}
