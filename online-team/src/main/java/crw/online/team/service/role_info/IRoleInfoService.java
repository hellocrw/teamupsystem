package crw.online.team.service.role_info;

import crw.online.team.entity.role_info.RoleInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author caorongwu
 * @since 2021-01-09
 */
public interface IRoleInfoService extends IService<RoleInfo> {

    /**
     * 查询角色表分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<RoleInfo>
     */
    IPage<RoleInfo> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加角色表
     *
     * @param roleInfo 角色表
     * @return int
     */
    int add(RoleInfo roleInfo);

    /**
     * 删除角色表
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改角色表
     *
     * @param roleInfo 角色表
     * @return int
     */
    int updateData(RoleInfo roleInfo);

    /**
     * id查询数据
     *
     * @param id id
     * @return RoleInfo
     */
    RoleInfo findById(Long id);
}
