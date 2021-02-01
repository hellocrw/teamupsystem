package crw.team.order.service.demo01;

import crw.team.order.entity.demo01.Demo01;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caorongwu
 * @since 2021-02-01
 */
public interface IDemo01Service extends IService<Demo01> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Demo01>
     */
    IPage<Demo01> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param demo01 
     * @return int
     */
    int add(Demo01 demo01);

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
     * @param demo01 
     * @return int
     */
    int updateData(Demo01 demo01);

    /**
     * id查询数据
     *
     * @param id id
     * @return Demo01
     */
    Demo01 findById(Long id);
}
