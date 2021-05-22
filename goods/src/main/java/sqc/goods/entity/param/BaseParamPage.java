package sqc.goods.entity.param;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import sqc.entity.po.BasePO;

/**
 * @Description: 参数分页
 * @Author: Sqcode
 * @Date: 2021/4/30 8:57
 */
@ApiModel
@Data
public class BaseParamPage<T extends BasePO> {

    @ApiModelProperty("当前页数")
    private long current = 1;

    @ApiModelProperty("当前页面每页显示的数量")
    private long size = 10;

    public Page<T> getPage() {
        return new Page<>(this.current, this.size);
    }
}
