package crw.online.team.service.user_info;

import crw.online.team.entity.user_info.UserInfo;
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
public interface IUserInfoService extends IService<UserInfo> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<UserInfo>
     */
    IPage<UserInfo> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param userInfo 
     * @return int
     */
    int add(UserInfo userInfo);

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
     * @param userInfo 
     * @return int
     */
    int updateData(UserInfo userInfo);

    /**
     * id查询数据
     *
     * @param id id
     * @return UserInfo
     */
    UserInfo findById(Long id);
}
